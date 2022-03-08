//package com.amazon.ata.advertising.service.dependency;
//
//import com.amazon.coral.dagger.annotations.CoralComponent;
//import com.amazon.coral.service.lambda.LambdaEndpoint;
//
//import javax.inject.Singleton;
//
//@Singleton
//@CoralComponent(
//        modules = {CoralModule.class},
//        // No need to generate a Launcher
//        generateLauncher = false
//)
//public interface LambdaComponent {
//    /**
//     * The LambdaEndpoint to connect the lambda to Coral.
//     * @return a LambdaEndpoint
//     */
//    LambdaEndpoint getLambdaEndpoint();
//
//    /**
//     * Inject's targeting predicates with the DAOs they require.
//     * @return a TargetingPredicateInjector
//     */
//    TargetingPredicateInjector getTargetingPredicateInjector();
//}
