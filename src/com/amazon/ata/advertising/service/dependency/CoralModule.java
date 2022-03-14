//package com.amazon.ata.advertising.service.dependency;
//
//import com.amazon.ata.test.tct.instrospection.ExecuteTctSuiteHelper;
//
//import com.amazon.coral.dagger.service.ActivityHandlerModule;
//import com.amazon.coral.metrics.MetricsFactory;
//import com.amazon.coral.service.ActivityHandler;
//import com.amazon.coral.service.lambda.LambdaEndpoint;
//import com.amazon.coral.service.lambda.LambdaEndpointConfig;
//import dagger.Module;
//import dagger.Provides;
//
//import javax.inject.Singleton;
//
//import static com.amazon.coral.service.lambda.LambdaEndpointConfig.Protocol.rest;
//import static com.amazon.coral.service.lambda.LambdaEndpointConfig.SerializationFormat.AWS_JSON_11;
//
//@Module(includes = {
//        ActivityHandlerModule.class,
//        MetricsModule.class,
//        InterceptorModule.class,
//        ExternalServiceModule.class,
//        DaoModule.class,
//        DynamoDBModule.class
//})
//public final class CoralModule {
//
//    /**
//     * The LambdaEndpoint to connect the lambda to Coral.
//     * @param metricsFactory Allows the service to create new Metrics
//     * @param activityHandler Discovers the Activity classes in our service and connects the right one to each request
//     * @return a LambdaEndpoint
//     */
//    @Provides
//    @Singleton
//    static LambdaEndpoint provideLambdaEndpoint(MetricsFactory metricsFactory, ActivityHandler activityHandler) {
//        LambdaEndpointConfig config = LambdaEndpointConfig.builder()
//                .protocol(rest(AWS_JSON_11))
//                .metricsFactory(metricsFactory)
//                .activityHandler(activityHandler)
//                .build();
//
//        return new LambdaEndpoint(config);
//    }
//
//    /**
//     * Provide a helper to execute TCTs.
//     * @return executes TCTs
//     */
//    @Provides
//    public ExecuteTctSuiteHelper provideExecuteTctSuiteHelper() {
//        return new ExecuteTctSuiteHelper();
//    }
//
//}
