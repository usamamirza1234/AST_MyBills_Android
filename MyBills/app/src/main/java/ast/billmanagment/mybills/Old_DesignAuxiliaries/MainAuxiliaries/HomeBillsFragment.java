package ast.billmanagment.mybills.Old_DesignAuxiliaries.MainAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeExtraAuxilliaries.AlertsFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeExtraAuxilliaries.MaintenanceScheduleFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeExtraAuxilliaries.OutageScheduleFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeExtraAuxilliaries.TermsConditionsFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeAuxiliaries.ElectricityHomeFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeAuxiliaries.GasHomeFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeAuxiliaries.InternetHomeFragment;
import ast.billmanagment.mybills.Old_DesignAuxiliaries.HomeAuxiliaries.WaterHomeFragment;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class HomeBillsFragment extends Fragment implements View.OnClickListener {

    LinearLayout llTermCond, llOutage, llMaintanence, llAlerts;
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

        llTermCond = view.findViewById(R.id.frg_bill_type_llTermCond);
        llOutage = view.findViewById(R.id.frg_bill_type_llOutage);
        llAlerts = view.findViewById(R.id.frg_bill_type_llAlerts);
        llMaintanence = view.findViewById(R.id.frg_bill_type_llMaintanences);
        llTermCond.setOnClickListener(this);
        llMaintanence.setOnClickListener(this);
        llAlerts.setOnClickListener(this);
        llOutage.setOnClickListener(this);

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
            case R.id.frg_bill_type_txv_view_internet:
//                navToInternetHomeFragment();
                break;
            case R.id.frg_bill_type_txv_view_gas:
//                navToGasHomeFragment();
                break;
            case R.id.frg_bill_type_txv_view_water:
//                navToWaterHomeFragment();
                break;
            case R.id.frg_bill_type_llTermCond:
                navToTermsConditionFragment();
                break;

            case R.id.frg_bill_type_llAlerts:
                navToAlertsFragment();
                break;

            case R.id.frg_bill_type_llMaintanences:
                navToMaintenanceFragment();
                break;


            case R.id.frg_bill_type_llOutage:
                navToOutageFragment();
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

    private void navToTermsConditionFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new TermsConditionsFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_TermsConditionsFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_TermsConditionsFragment);
        ft.hide(this);
        ft.commit();
    }

    private void navToMaintenanceFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new MaintenanceScheduleFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_MaintenanceScheduleFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_MaintenanceScheduleFragment);
        ft.hide(this);
        ft.commit();
    }


    private void navToOutageFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new OutageScheduleFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_OutageScheduleFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_OutageScheduleFragment);
        ft.hide(this);
        ft.commit();
    }

    private void navToAlertsFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new AlertsFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_AlertsFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_AlertsFragment);
        ft.hide(this);
        ft.commit();
    }

    private void navToInternetHomeFragment() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new InternetHomeFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_InternetHomeFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_InternetHomeFragment);
        ft.hide(this);
        ft.commit();
    }


    private void navToGasHomeFragment() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new GasHomeFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_GasHomeFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_GasHomeFragment);
        ft.hide(this);
        ft.commit();
    }


    private void navToWaterHomeFragment() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft;
        Fragment frg = new WaterHomeFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_WaterHomeFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_WaterHomeFragment);
        ft.hide(this);
        ft.commit();
    }


}
