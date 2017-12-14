package gigigo.com.kretofitmanager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Juan Godínez Vera - 5/10/2017.
 */
public class ServiceClient {

    @Inject
    public ServiceClient() {}

    private static ServiceClientSettings serviceClientSettings;

    public static ServiceClientSettings init() {
        serviceClientSettings = new ServiceClientSettings();
        return serviceClientSettings;
    }

    public static ServiceClientSettings getSettings() {
        return serviceClientSettings;
    }

    public static <T> T createService(Class<T> classType) {
        return createService(classType, 0, ServiceTimeoutSettings.DEFAULT());
    }

    public static <T> T createService(Class<T> classType, int endpointIndex) {
        return createService(classType, endpointIndex, ServiceTimeoutSettings.DEFAULT());
    }

    public static <T> T createService(Class<T> classType, int endpointIndex,
                                      ServiceTimeoutSettings serviceTimeoutSettings) {
        String endpoint = ServiceClient.getSettings()
                .getEndpoint(endpointIndex);

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        okHttpBuilder.connectTimeout(serviceTimeoutSettings.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(serviceTimeoutSettings.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(serviceTimeoutSettings.getReadTimeout(), TimeUnit.SECONDS);

        if(ServiceClient.getSettings().getLoggingInterceptor() != null) {
            okHttpBuilder.interceptors().add(ServiceClient.getSettings().getLoggingInterceptor() );
        }

        if(ServiceClient.getSettings().getConnectivityInterceptor() != null) {
            okHttpBuilder.interceptors().add(ServiceClient.getSettings().getConnectivityInterceptor());
        }

        OkHttpClient okHttpClient = okHttpBuilder.build();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient);

        if(ServiceClient.getSettings().hasConverterFactories()) {
            for(Converter.Factory factory : ServiceClient.getSettings().getConverterFactories()) {
                retrofitBuilder.addConverterFactory(factory);
            }
        }

        if(ServiceClient.getSettings().getCallAdapterFactory() != null) {
            retrofitBuilder.addCallAdapterFactory(ServiceClient.getSettings().getCallAdapterFactory());
        }

        return retrofitBuilder.build()
                .create(classType);
    }
}
