package ast.billmanagment.mybills.NewDesignAuxiliaries.IntroAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import ast.billmanagment.mybills.IntroActivity;
import ast.billmanagment.mybills.R;

public class SignUpFragment extends Fragment implements View.OnClickListener {


    RelativeLayout rlBack;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_sign_up, container, false);


        rlBack = frg.findViewById(R.id.frg_signup_rlToolbar);
        rlBack.setOnClickListener(this);


        return frg;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.frg_signup_rlToolbar:
                ((IntroActivity) getActivity()).onBackPressed();
                break;
        }
    }

}
