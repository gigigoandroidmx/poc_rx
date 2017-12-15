package gigigo.com.rxjavapoc.presentation;

import android.util.Log;

import gigigo.com.kmvp.KPresenter;
import gigigo.com.rxjavapoc.domain.entity.ClientResponse;
import gigigo.com.rxjavapoc.domain.interactor.ClientInteractor;
import gigigo.com.rxjavapoc.modules.main.MainView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by carlosgarcia on 13/12/17.
 */

public class MainPresenter extends KPresenter<MainView> {

    //MainView view;
    ClientInteractor interactor;

    //@Inject
    //ClientInteractor interactor;
    //public MainPresenter(MainView view) {
    public MainPresenter() {
        //this.view = view;
        interactor = new ClientInteractor();

        interactor.setParams("53114150705", "37b00b3767924097913a66004c909507", "87a58b789e4b4c4d963DF835D6426765", "NTMxMTQxNTA3MDU6OTI0NzMx");
    }

    public void getClient() {

        System.out.println("En la clase Presenter");
        interactor
            .client()
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ClientResponse>() {
                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onError(Throwable e) {
                            //Timber.e("Error on food search:%s", e.getMessage());
                            Log.i("request Client", "error " + e.getMessage());
                            getView().showMessage("error: " + e.getMessage());
                        }

                        @Override
                        public void onNext(ClientResponse clientResponse) {
                            //Timber.e("Models received:%s", baseResponseModel.toString());
                            //if (doIfView())
                            //    route2dListView.get().presentFoodSearch(baseResponseModel);
                            Log.i("request Client", "resultado ok");
                            //view.showMessage("resultado ok");
                            getView().showMessage("resultado ok");
                        }

                    });

    }

    @Override
    public void onBusRegister() {}

    @Override
    public void onBusUnregister() {}
}
