//package com.amazon.ata.advertising.service.metrics;
//
//import com.amazon.ata.advertising.service.dao.ReadableDao;
//import com.amazon.ata.advertising.service.targeting.predicate.TargetingPredicate;
//
//import org.reflections.Reflections;
//
//import java.lang.reflect.Field;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public final class MetricsConstants {
//    public static final String GEN_AD_EXCEPTION = "GenerateAdvertisementException";
//    public static final String EMPTY_ADS = "EmptyAds";
//    public static final String AD_SELECTION = "AdvertisementSelection";
//    public static final String TARGETING_EVALUATION = "TargetingEvaluation";
//
//    /**
//     * Returns all of the metric names used in the service.
//     * @return metric names
//     */
//    public static List<String> getMetricNames() {
//        List<String> daoMetrics = new Reflections("com.amazon.ata.advertising.service.dao")
//                .getSubTypesOf(ReadableDao.class)
//                .stream()
//                .map(Class::getSimpleName)
//                .collect(Collectors.toList());
//        List<String> daoFailureMetrics = new Reflections("com.amazon.ata.advertising.service.dao")
//                .getSubTypesOf(ReadableDao.class)
//                .stream()
//                .map(Class::getSimpleName)
//                .map(string -> string + "Failures")
//                .collect(Collectors.toList());
//
//        List<String> predicateMetrics = new Reflections("com.amazon.ata.advertising.service.targeting.predicate")
//                .getSubTypesOf(TargetingPredicate.class)
//                .stream()
//                .map(Class::getSimpleName)
//                .collect(Collectors.toList());
//        List<String> passRateMetrics = new Reflections("com.amazon.ata.advertising.service.targeting.predicate")
//                .getSubTypesOf(TargetingPredicate.class)
//                .stream()
//                .map(Class::getSimpleName)
//                .map(string -> string + "PassRate")
//                .collect(Collectors.toList());
//
//        List<String> otherMetrics = Arrays.stream(MetricsConstants.class.getDeclaredFields())
//                .map(MetricsConstants::get)
//                .filter(Objects::nonNull)
//                .map(object -> (String) object)
//                .collect(Collectors.toList());
//
//        return Stream.of(daoMetrics, daoFailureMetrics, predicateMetrics, passRateMetrics, otherMetrics)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//    }
//
//    private static Object get(Field type) {
//        try {
//            return type.get(null);
//        } catch (IllegalAccessException e) {
//            return null;
//        }
//    }
//
//}
