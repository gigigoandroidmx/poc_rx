package gigigo.com.rxjavapoc.domain.interactor;

import com.gigigo.kbase.domain.KInteractor;
import com.gigigo.kretrofitmanager.ServiceClient;

import gigigo.com.rxjavapoc.domain.entity.ClientResponse;
import gigigo.com.rxjavapoc.network.IpirangaService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by carlosgarcia on 13/12/17.
 */

public class ClientInteractor extends KInteractor {

    public Observable<ClientResponse> client() {

        String numClient = tryGetParamValueAs(String.class, 0);
        String clientId = tryGetParamValueAs(String.class, 1);
        String clientSecret = tryGetParamValueAs(String.class, 2);
        String userCredentials = tryGetParamValueAs(String.class, 3);

        return new ServiceClient().createService(IpirangaService.class)
                .client(numClient, clientId, clientSecret, userCredentials)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void run() {}
}
