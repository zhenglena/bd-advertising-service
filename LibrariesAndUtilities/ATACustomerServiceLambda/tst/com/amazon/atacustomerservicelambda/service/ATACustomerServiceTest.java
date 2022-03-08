package com.amazon.atacustomerservicelambda.service;

import com.amazon.ata.customerservice.GetCustomerProfileRequest;
import com.amazon.ata.customerservice.GetCustomerProfileResponse;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesRequest;
import com.amazon.ata.customerservice.GetCustomerSpendCategoriesResponse;
import com.amazon.ata.customerservice.InvalidParameterException;

import com.amazon.atacustomerservicelambda.activity.GetCustomerProfileActivity;
import com.amazon.atacustomerservicelambda.activity.GetCustomerSpendCategoriesActivity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ATACustomerServiceTest {

    private ATACustomerService service = new ATACustomerService();

    @Test
    public void getCustomerProfile_validRequest_returnsCustomerProfile() {
        // GIVEN
        GetCustomerProfileRequest request = GetCustomerProfileRequest.builder().withCustomerId("customerId").build();
        GetCustomerProfileResponse expectedResponse = GetCustomerProfileResponse.builder()
            .withCustomerProfile(GetCustomerProfileActivity.UNDER_46).build();

        // WHEN
        GetCustomerProfileResponse response = service.getCustomerProfile(request);
        System.out.println(response.getCustomerProfile().getAgeRange());

        // THEN
        assertEquals(expectedResponse, response);
    }

    @Test(expected = InvalidParameterException.class)
    public void getGetCustomerProfile_invalidRequest_throwsInvalidParameterException() {
        // GIVEN
        GetCustomerProfileRequest request = GetCustomerProfileRequest.builder().build();

        // WHEN
        service.getCustomerProfile(request);
    }

    @Test
    public void getGetCustomerSpendCategories_validRequest_returnsCustomerSpendCategories() {
        // GIVEN
        GetCustomerSpendCategoriesRequest request = GetCustomerSpendCategoriesRequest.builder()
            .withCustomerId("customerId").withMarketplaceId("marketplaceId").build();
        GetCustomerSpendCategoriesResponse expectedResponse = GetCustomerSpendCategoriesResponse.builder()
            .withCustomerSpendCategories(GetCustomerSpendCategoriesActivity.PRIME_VIDEO_SPEND)
                .build();

        // WHEN
        GetCustomerSpendCategoriesResponse response = service.getCustomerSpendCategories(request);

        // THEN
        assertEquals(expectedResponse, response);
    }

    @Test(expected = InvalidParameterException.class)
    public void getCustomerSpendCategories_invalidRequest_throwsInvalidParameterException() {
        // GIVEN
        GetCustomerSpendCategoriesRequest request = GetCustomerSpendCategoriesRequest.builder().build();

        // WHEN
       service.getCustomerSpendCategories(request);
    }
}
