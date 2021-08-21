package ast.billmanagment.mybills.HomeExtraAuxilliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class AlertsFragment extends Fragment implements View.OnClickListener {


    IBadgeUpdateListener mBadgeUpdateListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_alerts, container, false);

        init();
        bindviews(frg);


        return frg;
    }

    private void init() {
        setBottomBar();
    }

    private void bindviews(View view) {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

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
            mBadgeUpdateListener.setHeaderTitle(getString(R.string.AlertsFragment));

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            setBottomBar();
        }
    }


}