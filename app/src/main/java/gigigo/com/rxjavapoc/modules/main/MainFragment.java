package gigigo.com.rxjavapoc.modules.main;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gigigo.kbase.presentation.ui.IFragmentListener;
import com.gigigo.kbase.presentation.ui.KFragmentBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import gigigo.com.kmvp.KFragment;
import gigigo.com.rxjavapoc.R;
import gigigo.com.rxjavapoc.presentation.MainPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends KFragmentBase<MainView, MainPresenter>
    implements MainView {

    //private Unbinder unbinder;
    //protected IFragmentListener fragmentListener;

    @BindView(R.id.send_request) public Button requestClientButton;
    //Button requestClientButton;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override protected int getLayoutResourceId() {
        return R.layout.fragment_main;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        requestClientButton = getView().findViewById(R.id.req_client_button);
        requestClientButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.getClient();
            }
        });
    }

    @Override
    protected void onBindView(View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Override
    protected void onUnbindView() {
        //unbindBrandLabel();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.context instanceof IFragmentListener) {
            fragmentListener = (IFragmentListener) context;
        }
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }*/

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override protected MainPresenter createPresenter() {
        //return new RegisterPresenter(getContext(), new LoginInteractor(repository));
        return new MainPresenter();
    }

    @Override public void onResume() {
        super.onResume();
    }

    private void setEnabledButtons(boolean enabled) {
        requestClientButton.setEnabled(true);
    }


    @OnClick(R.id.send_request)
    public void onClickView() {
        System.out.println("Dentro de click");
        //switch (view.getId()) {
        //    case R.id.req_client_button:
                presenter.getClient();
        //        break;
        //}
    }

}
