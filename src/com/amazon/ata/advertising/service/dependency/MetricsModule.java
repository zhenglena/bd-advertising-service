//package com.amazon.ata.advertising.service.dependency;
//
//import com.amazon.ata.advertising.service.metrics.MetricsConstants;
//
//import com.amazon.aws.cloudwatch.reporter.CloudWatchReporterFactory;
//import com.amazon.aws.cloudwatch.reporter.DimensionNameFilter;
//import com.amazon.aws.cloudwatch.reporter.MetricNameFilter;
//import com.amazon.aws.cloudwatch.reporter.ReporterFilter;
//import com.amazon.aws.cloudwatch.reporter.ReporterFilterChain;
//import com.amazon.coral.metrics.DefaultMetricsFactory;
//import com.amazon.coral.metrics.MetricsFactory;
//import com.amazon.coral.metrics.reporter.ReporterFactory;
//import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
//import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
//import com.google.common.collect.Sets;
//import dagger.Module;
//import dagger.Provides;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//import javax.inject.Singleton;
//
//@Module
//public class MetricsModule {
//    private static final Regions METRICS_REGION  = Regions.US_WEST_2;
//    private static final String SERVICE_NAME = "ATACurriculumAdvertisingService";
//
//    // Whitelist of metric names. When adding a new custom metric, its name must be included here in order for it to
//    // appear in CloudWatch.
//    private static final Set<String> metricNames = Collections.unmodifiableSet(Sets.newHashSet(
//            // Top Level Metrics
//            "Time",
//            // ServiceMetrics Group Metrics
//            "Fault",
//            "Error",
//            "Failure",
//            "Remote",
//            "TransmuterTime",
//            "OutstandingRequests",
//            "Timeout:Critical",
//            // JMX Metrics
//            "FileDescriptorUse",
//            "Threads",
//            "HeapMemoryUse",
//            "HeapMemoryAfterGCUse",
//            "NonHeapMemoryUse",
//            "GarbageCollection"
//    ));
//
//    private static final Set<String> dimensionNames = Collections.unmodifiableSet(Sets.newHashSet(
//            "ServiceName",
//            "Program",
//            "Service",
//            "Operation"
//    ));
//
//    /**
//     * Get a configured MetricsFactory instance that creates new Metrics objects.
//     *
//     * @return a configured MetricsFactory instance
//     */
//    @Provides
//    @Singleton
//    static MetricsFactory provideMetricsFactory() {
//        AmazonCloudWatch amazonCloudWatch = getAmazonCloudWatchClient(METRICS_REGION);
//        ReporterFactory reporterFactory = getMetricReporterFactory(amazonCloudWatch, SERVICE_NAME);
//
//        return new DefaultMetricsFactory(reporterFactory);
//    }
//
//    /**
//     * Get a configured ReporterFactory instance that creates new reporters to filter out unwanted
//     * data with the set {@link ReporterFilter} when adding metrics.
//     *
//     * @param amazonCloudWatch AmazonCloudWatch to access CloudWatch.
//     * @param nameSpace        the Namespace of your metrics published to CloudWatch
//     * @return a configured ReporterFactory instance
//     */
//    private static ReporterFactory getMetricReporterFactory(AmazonCloudWatch amazonCloudWatch, String nameSpace) {
//        ReporterFilter reporterFilter = getReporterFilterChain();
//
//        return new CloudWatchReporterFactory()
//                .withCloudWatchClient(amazonCloudWatch)
//                .withNamespace(nameSpace)
//                .withReporterFilter(reporterFilter);
//    }
//
//    /**
//     * Get a configured AmazonCloudWatch instance to access CloudWatch.
//     *
//     * @param awsRegion AWS Region that you'd like to use for your AmazonCloudWatch
//     * @return a configured AmazonCloudWatch to access CloudWatch.
//     */
//    private static AmazonCloudWatch getAmazonCloudWatchClient(Regions awsRegion) {
//        return AmazonCloudWatchClientBuilder.standard()
//                .withRegion(awsRegion)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//    }
//
//    private static ReporterFilterChain getReporterFilterChain() {
//        final ReporterFilterChain chain = new ReporterFilterChain();
//
//        DimensionNameFilter dimensionNameFilter = new DimensionNameFilter();
//        dimensionNameFilter.setDimensionNames(dimensionNames);
//
//        MetricNameFilter metricNameFilter = new MetricNameFilter();
//        Set<String> allMetricNames = new HashSet<>(MetricsConstants.getMetricNames());
//        allMetricNames.addAll(metricNames);
//        metricNameFilter.setMetricNames(allMetricNames);
//
//        chain.setFilters(com.google.common.collect.ImmutableList.of(dimensionNameFilter, metricNameFilter));
//
//        return chain;
//    }
//}
