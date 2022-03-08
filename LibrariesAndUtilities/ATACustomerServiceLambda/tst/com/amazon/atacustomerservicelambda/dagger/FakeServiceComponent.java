
package com.amazon.atacustomerservicelambda.dagger;

import com.amazon.atacustomerservicelambda.activity.GetCustomerProfileActivity;
import com.amazon.atacustomerservicelambda.activity.GetCustomerSpendCategoriesActivity;
import com.amazon.atacustomerservicelambda.metrics.MetricsHandler;
import com.amazon.coral.metrics.DefaultMetricsFactory;
import com.amazon.coral.metrics.MetricsFactory;
import com.amazon.coral.metrics.reporter.Reporter;
import com.amazon.coral.metrics.reporter.ReporterFactory;
import com.amazon.metrics.declarative.DefaultMetricsManager;
import com.amazon.metrics.declarative.MetricsManager;
import org.mockito.Mockito;

public class FakeServiceComponent implements ServiceComponent {

    @Override
    public MetricsHandler metricsHandler() {
        Reporter reporter = Mockito.mock(Reporter.class);

        ReporterFactory reporterFactory = () -> reporter;
        MetricsFactory metricsFactory = new DefaultMetricsFactory(reporterFactory);
        MetricsManager metricsManager = new DefaultMetricsManager(metricsFactory);

        return new MetricsHandler(metricsManager);
    }

    @Override
    public GetCustomerProfileActivity getCustomerProfileActivity() {
        return new GetCustomerProfileActivity();
    }

    @Override
    public GetCustomerSpendCategoriesActivity getCustomerSpendCategoriesActivity() {
        return new GetCustomerSpendCategoriesActivity();
    }
}
