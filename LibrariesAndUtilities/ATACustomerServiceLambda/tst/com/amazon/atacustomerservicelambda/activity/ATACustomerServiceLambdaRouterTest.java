package com.amazon.atacustomerservicelambda.activity;

import com.amazon.atacustomerservicelambda.dagger.FakeServiceComponent;

import org.junit.Test;

import com.amazon.bones.lambdarouter.handlers.Chain;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class ATACustomerServiceLambdaRouterTest {

    @Test
    public void WHEN_anInstanceIsCreatedAndInitialized_THEN_ChainIsCreated() {
        ATACustomerServiceLambdaRouter instance = new ATACustomerServiceLambdaRouter();
        
        FakeServiceComponent fakeServiceComponent = new FakeServiceComponent();
        instance.setServiceComponent(fakeServiceComponent);
        
        instance.initialize();
        Chain chain = instance.getChain();
        assertThat(chain.getHandlers(), hasSize(6));
    }
}