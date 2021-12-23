package ast.billmanagment.mybills.NewDesignAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.CircleImageView;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private IBadgeUpdateListener mBadgeUpdateListener;
CircleImageView editprofile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_home, container, false);


        init();
        bindViews(frg);



        return frg;
    }
    //region INIT
    private void init() {
        setToolbar();


    }
    private void bindViews(View frg) {

    }





    void setToolbar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_VISIBLE);
            mBadgeUpdateListener.setHeaderTitle("Monitoring Dashboard");

        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            //setToolbar();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//
//            case R.id.profile:
//
//
//
//                break;
        }
    }
}