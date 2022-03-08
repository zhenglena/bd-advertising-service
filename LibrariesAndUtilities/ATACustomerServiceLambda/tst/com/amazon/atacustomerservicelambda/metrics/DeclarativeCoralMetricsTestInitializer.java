
package com.amazon.atacustomerservicelambda.metrics;

import com.amazon.coral.metrics.DefaultMetricsFactory;
import com.amazon.coral.metrics.MetricsFactory;
import com.amazon.coral.metrics.reporter.Reporter;
import com.amazon.coral.metrics.reporter.ReporterFactory;
import com.amazon.metrics.declarative.DefaultMetricsManager;
import com.amazon.metrics.declarative.MetricsFactoriesHelper;
import com.amazon.metrics.declarative.MetricsManager;
import com.amazon.metrics.declarative.aspectj.MetricMethodAspect;
import com.amazon.metrics.declarative.servicemetrics.aspectj.ServiceMetricsMethodAspect;

import org.aspectj.lang.Aspects;
import org.mockito.Mockito;

public class DeclarativeCoralMetricsTestInitializer {

    // We need to do all of this setup to avoid NPEs from empty aspects in our activities.
    public static void initializeDeclarativeCoralMetricsAspects() {
        Reporter reporter = Mockito.mock(Reporter.class);

        ReporterFactory reporterFactory = () -> reporter;
        MetricsFactory metricsFactory = new DefaultMetricsFactory(reporterFactory);
        MetricsManager metricsManager = new DefaultMetricsManager(metricsFactory);

        MetricMethodAspect metricMethodAspect = Aspects.aspectOf(MetricMethodAspect.class);
        metricMethodAspect.setFactories(MetricsFactoriesHelper.defaultMetricFactories());
        metricMethodAspect.setMetricsManager(metricsManager);
        ServiceMetricsMethodAspect serviceMetricsMethodAspect = Aspects.aspectOf(ServiceMetricsMethodAspect.class);
        serviceMetricsMethodAspect.setMetricsManager(metricsManager);
    }
}
