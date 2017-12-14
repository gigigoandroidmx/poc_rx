package gigigo.com.rxjavapoc;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import gigigo.com.kretofitmanager.ServiceClient;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by carlosgarcia on 13/12/17.
 */

public class App extends Application {

    //private AppComponent appComponent;
    private static String IPIRANGA_URL_BASE = "https://api-sandbox.kmdevantagens.com.br/kmv/api/v1/";
    //private static final int TIME_OUT = 40;

    @Override
    public void onCreate() {
        super.onCreate();
        //initializeInjector();
        //PrincipalComponent.create();
        initialize();

    }

    private void initialize() {
//        Fabric.with(this, new Crashlytics());

        // Shared preferences
        //SharedPreferencesManager.init(this)
        //        .setSharedPreferencesName(getString(R.string.app_name));

        // Rest api
        HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        /*OkHttpClient httpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(loggerInterceptor)
                .build();*/

        ServiceClient.init()
                .setLoggingInterceptor(loggerInterceptor)
                .setCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.setConnectivityInterceptor(requestInterceptor)
                .addEndpoint(IPIRANGA_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }
}
