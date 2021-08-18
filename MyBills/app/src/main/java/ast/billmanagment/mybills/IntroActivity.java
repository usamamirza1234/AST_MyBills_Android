package ast.billmanagment.mybills;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

import ast.billmanagment.mybills.IntroAuxiliaries.SplashFragment;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;
import ast.billmanagment.mybills.Utils.LocaleHelper;

public class IntroActivity extends AppCompatActivity implements IBadgeUpdateListener, View.OnClickListener {


    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppConfig.getInstance().performLangCheck(getWindow());
        AppConfig.getInstance().regulateFontScale(getResources().getConfiguration(), getBaseContext());
        setContentView(R.layout.activity_intro);

        fm = getSupportFragmentManager();
        getAppVersion();


        if (AppConfig.getInstance().mLanguage.equalsIgnoreCase(AppConstt.AppLang.LANG_UR)) {
            MyApplication.getInstance().setAppLanguage(AppConstt.AppLang.LANG_UR);
        } else {
            MyApplication.getInstance().setAppLanguage(AppConstt.AppLang.LANG_EN);
        }


        if (AppConfig.getInstance().shouldSkipSplash) {
            AppConfig.getInstance().shouldSkipSplash = false;
            navtoMainActivity();
        } else {
            navToSplash();
        }

        setMyScreenSize();

    }

    @Override
    protected void attachBaseContext(final Context baseContext) {
        //Handle custom font settings and screen size
        super.attachBaseContext(LocaleHelper.wrap(AppConfig.getInstance().regulateDisplayScale(baseContext),
                new Locale(AppConfig.getInstance().loadDefLanguage())));
    }

    void getAppVersion() {
        try {

            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            AppConfig.getInstance().currentAppVersion = String.valueOf(pInfo.versionCode);


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //now getIntent() should always return the last received intent
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AppConfig.getInstance() != null)//id some view  !=null => activity in initialized
            performActionAgainstFCM();
    }

    private void performActionAgainstFCM() {
        try {
            Intent intent = getIntent();
            SplashFragment.notificationId = intent.getIntExtra(AppConstt.Notifications.PUSH_TYPE, AppConstt.Notifications.TYPE_NIL);
            SplashFragment.orderId = intent.getIntExtra(AppConstt.Notifications.PUSH_ORDER_ID, AppConstt.Notifications.TYPE_NIL);
//            getIntent().removeExtra("notification_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setMyScreenSize() {

        //For Full screen Mode
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        AppConfig.getInstance().scrnWidth = metrics.widthPixels;
        AppConfig.getInstance().scrnHeight = metrics.heightPixels - getStatusBarHeight();
        Log.d("Screen Width", "" + AppConfig.getInstance().scrnWidth);
        Log.d("Screen Height", "" + AppConfig.getInstance().scrnHeight);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return (int) (result * this.getResources().getDisplayMetrics().density + 0.5f);
    }


    public void clearMyBackStack() {
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStackImmediate();

        }
//        setBackButtonVisibility(View.GONE);
//        txvTitle.setText(getResources().getString(R.string.frg_hom_ttl));
    }

    private void navToSplash() {
        Fragment frg = new SplashFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.act_intro_content_frg, frg, AppConstt.FragTag.FN_SplashFragment);
        ft.commit();
    }

    private void navtoSignInFragment() {
        SplashFragment frg = new SplashFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.act_intro_content_frg, frg, AppConstt.FragTag.FN_SplashFragment);
        ft.commit();
    }

    //region IBadgeUpdateListener

    @Override
    public void setBottomTabVisiblity(int mVisibility) {

    }

    @Override
    public void setToolbarVisiblity(int mVisibility) {

    }

    @Override
    public void setToolbarState(byte mState) {
        switch (mState) {


            case AppConstt.INTRO_ToolbarStates.defualt:

                break;

        }

    }

    @Override
    public void switchStatusBarColor(boolean isDark) {

    }

    @Override
    public boolean setHeaderTitle(String strAppTitle) {
        return false;
    }

    //endregion

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {

            getSupportFragmentManager().popBackStack();
//            if (getSupportFragmentManager().getBackStackEntryCount() == 1)
//            {
//                AppConfig.getInstance().mStateApp = AppConstt.STATE_APP_HOME;
//                txvTitle.setText(getResources().getString(R.string.frg_hom_ttl));
//                this.btnBack.setVisibility(View.GONE);
////            }
        } else {

            super.onBackPressed();
        }
        Log.d("onback", "after pop " + fm.getBackStackEntryCount());
    }

    public void navtoMainActivity() {
//        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();

        if (AppConfig.getInstance().mUser.isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            //   Intent intent = new Intent(this, MainActivityOLD.class);
            startActivity(intent);
            IntroActivity.this.finish();
        } else {
            navToSplash();
        }
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.act_intro_rl_toolbar_logo:
//
//                navToChoiceAppNatureFragment();
//
//                break;


        }
    }
}
