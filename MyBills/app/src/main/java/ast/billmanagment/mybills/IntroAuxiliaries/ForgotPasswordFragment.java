package ast.billmanagment.mybills.IntroAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ast.billmanagment.mybills.IntroActivity;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rlSignin, rlSignUp, rlReset;
    RelativeLayout rlBack;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_forgot_password, container, false);


        rlBack = frg.findViewById(R.id.frg_forgot_password_rlToolbar);
        rlSignin = frg.findViewById(R.id.frg_forgot_password_rlLogin);
        rlReset = frg.findViewById(R.id.frg_forgot_password_reset);
        rlSignUp = frg.findViewById(R.id.frg_forgot_password_rlCreate);


        rlBack.setOnClickListener(this);
        rlSignin.setOnClickListener(this);
        rlSignUp.setOnClickListener(this);
        rlReset.setOnClickListener(this);


        return frg;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.frg_forgot_password_rlLogin:
                navToSignInFragment();
                break;

            case R.id.frg_forgot_password_reset:
                navToSignInFragment();
                break;

            case R.id.frg_forgot_password_rlCreate:
                navToSignUpFragment();
                break;
            case R.id.frg_forgot_password_rlToolbar:
                ((IntroActivity) getActivity()).onBackPressed();
                break;
        }
    }

    private void navToSignUpFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment frag = new SignUpFragment();
        ft.add(R.id.act_intro_content_frg, frag, AppConstt.FragTag.FN_SignUpFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_SignUpFragment);
        ft.hide(this);
        ft.commit();
    }


    private void navToSignInFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment frag = new SignInFragment();
        ft.replace(R.id.act_intro_content_frg, frag, AppConstt.FragTag.FN_SignInFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_SignInFragment);
        ft.hide(this);
        ft.commit();
    }

}
