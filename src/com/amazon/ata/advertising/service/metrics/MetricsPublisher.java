//package com.amazon.ata.advertising.service.metrics;
//
//import com.amazon.coralx.threadlocal.ThreadLocalContext;
//
//import java.time.Duration;
//import javax.inject.Inject;
//import javax.measure.unit.SI;
//import javax.measure.unit.Unit;
//
///**
// * Class that includes methods for publishing metrics.
// */
//public class MetricsPublisher {
//
//    /**
//     * Publishes metrics for each request.
//     */
//    @Inject
//    public MetricsPublisher() {}
//
//    /**
//     * Publish count metric. This version is useful for tracking rates.
//     *
//     * @param name of count metric.
//     * @param value publishes a 1 if true, and a 0 if false.
//     */
//    public void addCount(String name, boolean value) {
//        ThreadLocalContext.get().get().getMetrics().addCount(name, value);
//    }
//
//    /**
//     * Publish count metric.
//     *
//     * @param name of count metric.
//     * @param value of count metric.
//     * @param unit of count metric.
//     */
//    public void addCount(String name, double value, Unit<?> unit) {
//        ThreadLocalContext.get().get().getMetrics().addCount(name, value, unit);
//    }
//
//    /**
//     * Publish time metric in milliseconds.
//     *
//     * @param name of time metric.
//     * @param value of time metric.
//     */
//    public void addTime(String name, Duration value) {
//        addTime(name, value.toMillis(), SI.MILLI(SI.SECOND));
//    }
//
//    /**
//     * Publish time metric.
//     *
//     * @param name of time metric.
//     * @param value of time metric.
//     * @param unit of time metric.
//     */
//    public void addTime(String name, double value, Unit<?> unit) {
//        ThreadLocalContext.get().get().getMetrics().addTime(name, value, unit);
//    }
//}
