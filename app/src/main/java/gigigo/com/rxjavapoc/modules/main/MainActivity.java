package gigigo.com.rxjavapoc.modules.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gigigo.kbase.presentation.ui.IFragmentListener;
import com.gigigo.kbase.presentation.ui.KActivityBase;

import gigigo.com.kmvp.KActivity;
import gigigo.com.kmvp.KNavigationManager;
import gigigo.com.rxjavapoc.R;
import gigigo.com.rxjavapoc.presentation.MainPresenter;

public class MainActivity extends KActivityBase implements IFragmentListener {

    //MainPresenter presenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        if (presenter != null)
            presenter.getClient();
    }*/

    @Override
    protected void onInitialize() {
        super.onInitialize();
        //presenter = new MainPresenter();
        //if (presenter != null)
        //    presenter.getClient();
        setNavigationManager(new KNavigationManager(getSupportFragmentManager(), R.id.container));
        MainFragment fragment = MainFragment.newInstance();
        getNavigationManager().openAsRoot(fragment);

    }

    /*@Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }*/

    @Override
    public void showError(Throwable exception) {

    }

    @Override
    public void showProgress(boolean active) {

    }

    @Override
    public void onFragmentActionComplete(Object data) {

    }
}
