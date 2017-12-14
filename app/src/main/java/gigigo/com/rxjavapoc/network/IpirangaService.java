package gigigo.com.rxjavapoc.network;

import gigigo.com.rxjavapoc.domain.entity.ClientResponse;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by carlosgarcia on 13/12/17.
 */

public interface IpirangaService {

    @Headers({"Content-Type: application/json",
            "Accept: application/json"
    })
    @GET("cliente/{num_cliente}")
    Observable<ClientResponse> client (@Path("num_cliente") String numClient,
                                       @Query("client_id") String client_id,
                                       @Query("client_secret") String client_secret,
                                       @Header("user_credentials") String user_credentials);

}
