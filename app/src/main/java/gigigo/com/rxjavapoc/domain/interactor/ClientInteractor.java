package gigigo.com.rxjavapoc.domain.interactor;

import gigigo.com.kbase.domain.KInteractor;
//import gigigo.com.kbase.network.GenerateService;
import gigigo.com.kretofitmanager.ServiceClient;
import gigigo.com.rxjavapoc.domain.entity.ClientResponse;
import gigigo.com.rxjavapoc.network.IpirangaService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
//import com.gigigo.kretrofitmanager.ServiceClient;

/**
 * Created by carlosgarcia on 13/12/17.
 */

public class ClientInteractor extends KInteractor {

    /*public Observable<ClientResponse> client() {
        String urlBase = "https://api-sandbox.kmdevantagens.com.br/kmv/api/v1/";

        return GenerateService.generateService(urlBase, 40)
                .client()
                .subscribeOn(Schedulers.io());
    }*/
    public Observable<ClientResponse> client() {


        //IpirangaService ipirangaService = new ServiceClient().createService(IpirangaService.class);
        return new ServiceClient().createService(IpirangaService.class)
                .client("53114150705","37b00b3767924097913a66004c909507", "87a58b789e4b4c4d963DF835D6426765",
                        "NTMxMTQxNTA3MDU6OTI0NzMx")
                .subscribeOn(Schedulers.io());
    }
}
