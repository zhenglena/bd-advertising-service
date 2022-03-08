//package com.amazon.ata.advertising.service.dependency;
//
//import com.amazon.ata.advertising.service.AdvertisementClientException;
//import com.amazon.ata.advertising.service.AdvertisementServiceException;
//
//import com.amazon.coral.dagger.annotations.GlobalInterceptor;
//import com.amazon.coral.dagger.annotations.GlobalInterceptorOrder;
//import com.amazon.coral.validate.ValidationInterceptor;
//import com.amazon.coralx.exception.DefaultExceptionTranslationDefinition;
//import com.amazon.coralx.exception.ExceptionTranslationDefinition;
//import com.amazon.coralx.exception.ExceptionTranslationInterceptor;
//import com.amazon.coralx.exception.LogLevel;
//import com.amazon.coralx.threadlocal.ThreadLocalContextActivityInterceptor;
//import com.google.common.collect.ImmutableList;
//import dagger.Module;
//import dagger.Provides;
//
//import java.util.List;
//import javax.inject.Singleton;
//
//
///**
// * Module that creates all the interceptors. They are bound to the
// * Orchestrator via annotations.
// */
//@Module
//public final class InterceptorModule {
//
//    /**
//     * Provides a ValidationInterceptor.
//     * @return a ValidationInterceptor.
//     */
//    @Provides
//    @Singleton
//    @GlobalInterceptor
//    @GlobalInterceptorOrder(1)
//    public ValidationInterceptor provideValidationInterceptor() {
//        return new ValidationInterceptor();
//    }
//
//    /**
//     * Provides a ExceptionTranslationInterceptor.
//     * @return a ExceptionTranslationInterceptor.
//     */
//    @Provides
//    @Singleton
//    @GlobalInterceptor
//    @GlobalInterceptorOrder(2)
//    public ExceptionTranslationInterceptor provideExceptionTranslationInterceptor() {
//        List<ExceptionTranslationDefinition> defaultTranslations = ImmutableList.of(
//                new DefaultExceptionTranslationDefinition().withSource(AdvertisementClientException.class)
//                        .withTarget(AdvertisementClientException.class)
//                        .withLogLevel(LogLevel.WARN),
//                new DefaultExceptionTranslationDefinition().withSource(Exception.class)
//                        .withTarget(AdvertisementServiceException.class)
//                        .withLogLevel(LogLevel.FATAL)
//        );
//        ExceptionTranslationInterceptor interceptor = new ExceptionTranslationInterceptor();
//        interceptor.setDefaultTranslations(defaultTranslations);
//        return interceptor;
//    }
//
//    /**
//     * As it says on the tin, provides a thread-local context interceptor.
//     * @return a ThreadLocalContextActivityInterceptor.
//     */
//    @Provides
//    @Singleton
//    @GlobalInterceptor
//    @GlobalInterceptorOrder(3)
//    public ThreadLocalContextActivityInterceptor provideThreadLocalContextActivityInterceptor() {
//        return new ThreadLocalContextActivityInterceptor();
//    }
//
//}
