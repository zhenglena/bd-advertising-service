package com.amazon.ata.advertising.service.businesslogic;

import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.EmptyGeneratedAdvertisement;
import com.amazon.ata.advertising.service.model.GeneratedAdvertisement;
import com.amazon.ata.advertising.service.targeting.TargetingEvaluator;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdvertisementSelectionLogicTest {

    private static final String CUSTOMER_ID = "A123B456";
    private static final String MARKETPLACE_ID = "1";

    private static final String CONTENT_ID1 = UUID.randomUUID().toString();
    private static final AdvertisementContent CONTENT1 = AdvertisementContent.builder().withContentId(CONTENT_ID1).build();
    private static final String CONTENT_ID2 = UUID.randomUUID().toString();
    private static final AdvertisementContent CONTENT2 = AdvertisementContent.builder().withContentId(CONTENT_ID2).build();
    private static final String CONTENT_ID3 = UUID.randomUUID().toString();
    private static final AdvertisementContent CONTENT3 = AdvertisementContent.builder().withContentId(CONTENT_ID3).build();
    private static final String CONTENT_ID4 = UUID.randomUUID().toString();
    private static final AdvertisementContent CONTENT4 = AdvertisementContent.builder().withContentId(CONTENT_ID4).build();

    @Mock
    private ReadableDao<String, List<AdvertisementContent>> contentDao;

    @Mock
    private ReadableDao<String, List<TargetingGroup>> targetingGroupDao;

    @Mock
    private Random random;

    @Mock
    private TargetingEvaluator targetingEvaluator;

    private AdvertisementSelectionLogic adSelectionService;


    @BeforeEach
    public void setup() {
        initMocks(this);
        adSelectionService = new AdvertisementSelectionLogic(contentDao, targetingGroupDao);
        adSelectionService.setRandom(random);
    }

    @Test
    public void selectAdvertisement_nullMarketplaceId_EmptyAdReturned() {
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, null);
        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }

    @Test
    public void selectAdvertisement_emptyMarketplaceId_EmptyAdReturned() {
        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, "");
        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }

    @Test
    public void selectAdvertisement_noContentForMarketplace_emptyAdReturned() throws InterruptedException {
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(Collections.emptyList());

        GeneratedAdvertisement ad = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        assertTrue(ad instanceof EmptyGeneratedAdvertisement);
    }


    @Test
    public void selectAdvertisement_oneAd_returnsAd() {
        // Given
        List<AdvertisementContent> contents = Collections.singletonList(CONTENT1);
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(contents);

        TargetingGroup targetingGroup1 = mock(TargetingGroup.class);
        when(targetingGroupDao.get(CONTENT_ID1)).thenReturn(Collections.singletonList(targetingGroup1));
        when(targetingGroupDao.get(CONTENT_ID2)).thenReturn(Collections.emptyList());
        when(targetingGroupDao.get(CONTENT_ID3)).thenReturn(Collections.emptyList());
        when(targetingGroupDao.get(CONTENT_ID4)).thenReturn(Collections.emptyList());

        when(targetingEvaluator.evaluate(targetingGroup1)).thenReturn(TargetingPredicateResult.TRUE);

        when(random.nextInt(anyInt())).thenReturn(0); // Always select the first element

        // When
        GeneratedAdvertisement result = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // Then
        assertNotNull(result);
        assertFalse(result instanceof EmptyGeneratedAdvertisement, "Expected a non-empty GeneratedAdvertisement.");
        assertEquals(CONTENT1.getContentId(), result.getContent().getContentId(), "Expected the content ID to match.");
    }

    @Test
    public void selectAdvertisement_multipleAds_returnsOneRandom() {
        // Given
        List<AdvertisementContent> contents = Arrays.asList(CONTENT1, CONTENT2, CONTENT3);
        when(contentDao.get(MARKETPLACE_ID)).thenReturn(contents);

        TargetingGroup targetingGroup1 = mock(TargetingGroup.class);
        TargetingGroup targetingGroup2 = mock(TargetingGroup.class);
        TargetingGroup targetingGroup3 = mock(TargetingGroup.class);

        when(targetingGroupDao.get(CONTENT_ID1)).thenReturn(Collections.singletonList(targetingGroup1));
        when(targetingGroupDao.get(CONTENT_ID2)).thenReturn(Collections.singletonList(targetingGroup2));
        when(targetingGroupDao.get(CONTENT_ID3)).thenReturn(Collections.singletonList(targetingGroup3));

        when(targetingEvaluator.evaluate(targetingGroup1)).thenReturn(TargetingPredicateResult.TRUE);
        when(targetingEvaluator.evaluate(targetingGroup2)).thenReturn(TargetingPredicateResult.FALSE);
        when(targetingEvaluator.evaluate(targetingGroup3)).thenReturn(TargetingPredicateResult.TRUE);

        when(random.nextInt(anyInt())).thenReturn(0); // Always select the first element

        // When
        GeneratedAdvertisement result = adSelectionService.selectAdvertisement(CUSTOMER_ID, MARKETPLACE_ID);

        // Then
        assertNotNull(result);
        assertFalse(result instanceof EmptyGeneratedAdvertisement, "Expected a non-empty GeneratedAdvertisement.");
        assertTrue(result.getContent().getContentId().equals(CONTENT1.getContentId()) ||
                        result.getContent().getContentId().equals(CONTENT3.getContentId()),
                "Expected the content ID to match one of the valid contents.");
    }

}
