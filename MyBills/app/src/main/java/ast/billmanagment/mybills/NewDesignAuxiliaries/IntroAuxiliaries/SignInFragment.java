package ast.billmanagment.mybills.NewDesignAuxiliaries.IntroAuxiliaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ast.billmanagment.mybills.AppConfig;
import ast.billmanagment.mybills.IntroActivity;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;

public class SignInFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rlSignin, rlSignUp, rlForgot, rlBack;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_sign_in, container, false);


        rlSignin = frg.findViewById(R.id.frg_signin_login);
        rlForgot = frg.findViewById(R.id.frg_sigin_rlforgot);
        rlSignUp = frg.findViewById(R.id.frg_sigin_rlCreate);


        rlBack = frg.findViewById(R.id.frg_signin_rlToolbar);
        rlBack.setOnClickListener(this);


        rlSignin.setOnClickListener(this);
        rlSignUp.setOnClickListener(this);
        rlForgot.setOnClickListener(this);


        return frg;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.frg_signin_login:
                AppConfig.getInstance().mUser.setLoggedIn(true);
                AppConfig.getInstance().saveUserProfile();
                ((IntroActivity) getActivity()).navtoMainActivity();
                break;

            case R.id.frg_sigin_rlforgot:
                navToForgotPasswordFragment();
                break;

            case R.id.frg_sigin_rlCreate:
                navToSignUpFragment();
                break;
            case R.id.frg_signin_rlToolbar:
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

    private void navToForgotPasswordFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment frag = new ForgotPasswordFragment();
        ft.add(R.id.act_intro_content_frg, frag, AppConstt.FragTag.FN_ForgotPasswordFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_ForgotPasswordFragment);
        ft.hide(this);
        ft.commit();
    }
}
