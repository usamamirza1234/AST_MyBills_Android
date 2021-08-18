package ast.billmanagment.mybills.MainAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ast.billmanagment.mybills.AppConfig;
import ast.billmanagment.mybills.IntroActivity;
import ast.billmanagment.mybills.IntroAuxiliaries.ForgotPasswordFragment;
import ast.billmanagment.mybills.IntroAuxiliaries.SignUpFragment;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class BillTypeFragment extends Fragment implements View.OnClickListener {

    TextView txvWater, txvGas, txvElecticity, txvInternet;
    IBadgeUpdateListener mBadgeUpdateListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_bill_type, container, false);

        init();
        bindviews(frg);


        return frg;
    }

    private void init() {
        setBottomBar();
    }

    private void bindviews(View view) {
        txvWater = view.findViewById(R.id.frg_bill_type_txv_view_water);
        txvGas = view.findViewById(R.id.frg_bill_type_txv_view_gas);
        txvElecticity = view.findViewById(R.id.frg_bill_type_txv_view_electricty);
        txvInternet = view.findViewById(R.id.frg_bill_type_txv_view_internet);


        txvWater.setOnClickListener(this);
        txvGas.setOnClickListener(this);
        txvElecticity.setOnClickListener(this);
        txvInternet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.frg_bill_type_txv_view_electricty:
                navToElectricityHomeFragment();
                break;

        }
    }

    void setBottomBar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_BACK_HIDDEN);
            mBadgeUpdateListener.setHeaderTitle("View Bills");

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            setBottomBar();
        }
    }

    private void navToElectricityHomeFragment() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new ElectricityHomeFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_ElectricityHomeFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_ElectricityHomeFragment);
        ft.hide(this);
        ft.commit();
    }
}
