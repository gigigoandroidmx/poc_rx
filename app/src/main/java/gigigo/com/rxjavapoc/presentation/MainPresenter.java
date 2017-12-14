package gigigo.com.rxjavapoc.presentation;

import android.util.Log;

import java.util.logging.Logger;

import gigigo.com.rxjavapoc.domain.entity.ClientResponse;
import gigigo.com.rxjavapoc.domain.interactor.ClientInteractor;
import gigigo.com.rxjavapoc.modules.main.MainView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by carlosgarcia on 13/12/17.
 */

public class MainPresenter {

    MainView view;
    ClientInteractor interactor;

    //@Inject
    //ClientInteractor interactor;
    public MainPresenter(MainView view) {
        this.view = view;
        interactor = new ClientInteractor();
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
                            Log.i("Route2dListPresenter", "error");
                        }

                        @Override
                        public void onNext(ClientResponse clientResponse) {
                            //Timber.e("Models received:%s", baseResponseModel.toString());
                            //if (doIfView())
                            //    route2dListView.get().presentFoodSearch(baseResponseModel);
                            Log.i("PersonalData", "resultado ok");
                            view.showMessage("resultado ok");
                        }

                    });

    }
}
