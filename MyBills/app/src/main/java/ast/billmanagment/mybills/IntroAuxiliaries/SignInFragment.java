package ast.billmanagment.mybills.IntroAuxiliaries;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ast.billmanagment.mybills.AppConfig;
import ast.billmanagment.mybills.IntroActivity;
import ast.billmanagment.mybills.R;
import ast.billmanagment.mybills.Utils.AppConstt;

public class SignInFragment extends Fragment implements View.OnClickListener {

    RelativeLayout rlSignin, rlSignUp, rlForgot;
    EditText edtName, edtPassword;
    RelativeLayout rlBack;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_sign_in, container, false);
        init();
        bindviews(frg);
        return frg;
    }
    private void init() {
    }

    private void bindviews(View frg) {

        rlSignin = frg.findViewById(R.id.frg_sigin_rllogin);
        edtName = frg.findViewById(R.id.frg_signin_editTextTextPersonName);
        edtPassword = frg.findViewById(R.id.frg_signin_editTextTextPassword);

        rlBack = frg.findViewById(R.id.frg_signin_rlToolbar);
        rlBack.setOnClickListener(this);
        rlForgot = frg.findViewById(R.id.frg_sigin_rlforgot);
        rlSignUp = frg.findViewById(R.id.frg_sigin_rlCreate);

        rlSignin.setOnClickListener(this);
        rlSignUp.setOnClickListener(this);
        rlForgot.setOnClickListener(this);


        editTextWatchers();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.frg_sigin_rllogin:

                closeKeyboard();
                checkErrorConditions();

                break;

            case R.id.frg_signin_rlToolbar:
                ((IntroActivity) getActivity()).onBackPressed();
                break;

            case R.id.frg_sigin_rlforgot:
                navToForgotPasswordFragment();
                break;

            case R.id.frg_sigin_rlCreate:
                navToSignUpFragment();
                break;



        }
    }

    private void checkErrorConditions() {
        if (checkPasswordError()) {


//                JsonObject jsonObject = new JsonObject();
//                jsonObject.addProperty("username", edtName.getText().toString());
//                jsonObject.addProperty("password", edtPassword.getText().toString());
//                jsonObject.addProperty("appVersion", "1.0");

//                requestUserSigin(jsonObject.toString());


            AppConfig.getInstance().mUser.isLoggedIn = true;
            AppConfig.getInstance().saveUserProfile();
            navToMainActivity();
        }
    }

    private boolean checkPasswordError() {
        if (!edtName.getText().toString().equalsIgnoreCase("") && !edtPassword.getText().toString().isEmpty()) {
            return true;
        } else {
            Toast.makeText(getContext(), "Enter all fields", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    private void closeKeyboard() {
        AppConfig.getInstance().closeKeyboard(getActivity());
    }

    private void editTextWatchers() {

        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith(" ")) {
                    edtName.setText("");
                }
            }
        });
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().startsWith(" ")) {
                    edtPassword.setText("");
                }
            }
        });

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

    private void navToMainActivity() {
        ((IntroActivity) getActivity()).navtoMainActivity();

    }


}

