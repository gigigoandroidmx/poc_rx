package gigigo.com.rxjavapoc.domain.interactor;

import com.gigigo.kbase.domain.KInteractor;
import com.gigigo.kretrofitmanager.ServiceClient;

import java.util.concurrent.TimeUnit;

import gigigo.com.rxjavapoc.domain.entity.ClientResponse;
import gigigo.com.rxjavapoc.network.IpirangaService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by carlosgarcia on 13/12/17.
 */

public class ClientInteractor extends KInteractor {

    private final int maxRetries = 3;
    private int retryCount;

    public Observable<ClientResponse> client() {

        String numClient = tryGetParamValueAs(String.class, 0);
        String clientId = tryGetParamValueAs(String.class, 1);
        String clientSecret = tryGetParamValueAs(String.class, 2);
        String userCredentials = tryGetParamValueAs(String.class, 3);
        retryCount = 0;

        return new ServiceClient().createService(IpirangaService.class)
                .client(numClient, clientId, clientSecret, userCredentials)
                //.retry()
                /*.retryWhen(attempts -> {
                    return attempts.switchMap(o -> {
                        if (++retryCount < maxRetries) {
                            // When this Observable calls onNext, the original
                            // Observable will be retried (i.e. re-subscribed).
                            return Observable.timer(4, TimeUnit.SECONDS);
                        }
                        // Max retries hit. Just pass the error along.
                        return Observable.error(new Throwable("Error de red", o.getCause()));
                    });
                })*/
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void run() {}
}
