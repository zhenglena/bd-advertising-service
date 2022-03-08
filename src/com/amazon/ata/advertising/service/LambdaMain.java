package com.amazon.ata.advertising.service;

import com.amazon.ata.advertising.service.dependency.DaggerGeneratedCoralComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;

import com.amazon.coral.service.lambda.ApiGatewayRequest;
import com.amazon.coral.service.lambda.ApiGatewayResponse;
import com.amazon.coral.service.lambda.LambdaEndpoint;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Lambda handler:
// com.amazon.ata.advertising.service.LambdaMain::handleRequest
public class LambdaMain implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    private static final Logger LOG = LogManager.getLogger(LambdaMain.class);

    private LambdaEndpoint endpoint;

    /**
     * Connects our API Gateway Lambda requests to the Coral framework using Coral Lambda Endpoints.
     */
    public LambdaMain() {
        LambdaComponent component = DaggerGeneratedCoralComponent.create();
        this.endpoint = component.getLambdaEndpoint();

    }

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest apiGatewayRequest, Context context) {
        return endpoint.handleRequest(apiGatewayRequest, context);
    }
}
