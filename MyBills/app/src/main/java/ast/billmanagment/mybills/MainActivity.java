package ast.billmanagment.mybills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import ast.billmanagment.mybills.CalenderAuxiliaries.CustomCalenderFragment;
import ast.billmanagment.mybills.MainAuxiliaries.EditProfileFragment;
import ast.billmanagment.mybills.MainAuxiliaries.HistoryFragment;
import ast.billmanagment.mybills.MainAuxiliaries.HomeBillsFragment;
import ast.billmanagment.mybills.MainAuxiliaries.MyBillsFragment;
import ast.billmanagment.mybills.MainAuxiliaries.SettingsFragment;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class MainActivity extends AppCompatActivity implements IBadgeUpdateListener, View.OnClickListener {
    public DrawerLayout drawer;
    NavigationView navigationView;
    private FragmentTransaction ft;
    private FragmentManager fm;
    RelativeLayout rlToolbar, rlBack, rlMenu;
    LinearLayout llImportantDates, llMyBills, llProfile, llLogout, llHistory, llSettings;
    TextView txvTitleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        AppConfig.getInstance().performLangCheck(getWindow());
        if (savedInstanceState != null) {
            return;
        }

        initData();
        bindViews();

        setFirstFragment();
    }


    private void bindViews() {
        drawer = findViewById(R.id.act_main_drawr);
        navigationView = drawer.findViewById(R.id.act_main_navview);
        rlToolbar = findViewById(R.id.act_main_rl_toolbar);
        txvTitleBar = findViewById(R.id.act_intro_txv_title);
        rlBack = findViewById(R.id.act_intro_rl_toolbar_back);
        rlMenu = findViewById(R.id.act_intro_rl_toolbar_menu);


        llImportantDates = findViewById(R.id.lay_navigationview_llImportantDates);
        llProfile = findViewById(R.id.lay_navigationview_llEditProfile);
        llMyBills = findViewById(R.id.lay_navigationview_llMyBills);
        llLogout = findViewById(R.id.lay_navigationview_llLogout);
        llHistory = findViewById(R.id.lay_navigationview_llHistory);
        llSettings = findViewById(R.id.lay_navigationview_llSettings);

        rlBack.setOnClickListener(this);
        rlMenu.setOnClickListener(this);

        llImportantDates.setOnClickListener(this);
        llProfile.setOnClickListener(this);
        llMyBills.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        llHistory.setOnClickListener(this);
        llSettings.setOnClickListener(this);


        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // Whatever you want
//                changeDrawerItemState();
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // Whatever you want
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // Whatever you want
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Whatever you want
            }
        });


    }

    private void initData() {
        fm = getSupportFragmentManager();
        AppConfig.getInstance().regulateFontScale(getResources().getConfiguration(), getBaseContext());
        AppConfig.getInstance().loadUserProfile();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_intro_rl_toolbar_menu:
                openDrawar();
                break;

            case R.id.act_intro_rl_toolbar_back:
                onBackPressed();
                break;
            case R.id.lay_navigationview_llImportantDates:
                navToCustomCalenderFragment();
                break;
            case R.id.lay_navigationview_llEditProfile:
                navToEditProfileFragment();
                break;
            case R.id.lay_navigationview_llMyBills:
                navToMyBillsFragment();
                break;

            case R.id.lay_navigationview_llLogout:
                AppConfig.getInstance().navtoLogin();
                break;
            case R.id.lay_navigationview_llHistory:
                navToHistoryFragment();
                break;
            case R.id.lay_navigationview_llSettings:
                navToSettingsFragment();
                break;
        }
    }


    @Override
    public void setBottomTabVisiblity(int mVisibility) {

    }

    @Override
    public void setToolbarVisiblity(int mVisibility) {
        rlToolbar.setVisibility(mVisibility);
    }

    @Override
    public void setToolbarState(byte mState) {
        switch (mState) {
            case AppConstt.ToolbarState.TOOLBAR_BACK_HIDDEN:
                closeDrawar();
                rlBack.setVisibility(View.GONE);
                rlMenu.setVisibility(View.VISIBLE);
                txvTitleBar.setVisibility(View.VISIBLE);
                break;

            case AppConstt.ToolbarState.TOOLBAR_VISIBLE:
                closeDrawar();
                rlBack.setVisibility(View.VISIBLE);
                rlMenu.setVisibility(View.VISIBLE);
                txvTitleBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void switchStatusBarColor(boolean isDark) {

    }

    @Override
    public boolean setHeaderTitle(String strAppTitle) {
        txvTitleBar.setText(strAppTitle + "");

        return false;
    }


    public void openDrawar() {

        drawer.openDrawer(GravityCompat.END);

    }

    public void closeDrawar() {

//        drawer.closeDrawer(GravityCompat.END);
        if (this.drawer.isDrawerOpen(GravityCompat.END)) {
            this.drawer.closeDrawer(GravityCompat.END);
        }
    }

    public void lockDrawar() {

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockDrawar() {

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void onBackPressed() {


        String tag = returnStackFragmentTag();

        AppConfig.getInstance().closeKeyboard(this);

        if (this.drawer.isDrawerOpen(GravityCompat.END)) {
            this.drawer.closeDrawer(GravityCompat.END);
        } else {
            Log.d("whileOnBackPress", " Tag " + tag);
            if ((tag.equalsIgnoreCase(AppConstt.FragTag.FN_MyBillsFragment)) ||
                    (tag.equalsIgnoreCase(AppConstt.FragTag.FN_EditProfileFragment))
                    || (tag.equalsIgnoreCase(AppConstt.FragTag.FN_HistoryFragment))
                    || (tag.equalsIgnoreCase(AppConstt.FragTag.FN_CustomCalenderFragment))


            ) {
                setFirstFragment();
            } else {
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    getSupportFragmentManager().popBackStack();
                    Log.d("whileOnBackPress", " 123 " + tag);
                } else {
                    Log.d("whileOnBackPress", " 456 " + tag);
                    super.onBackPressed();
                }
            }
        }


    }


    //region Navigations
    public void setFirstFragment() {
        navToBillTypeFragment();
    }

    public String returnStackFragmentTag() {
        int index = fm.getBackStackEntryCount() - 1;
        FragmentManager.BackStackEntry backEntry = null;
        String tag = "";
        if (index >= 0) {
            backEntry = fm.getBackStackEntryAt(index);
            tag = backEntry.getName();
        }
        return tag;
    }

    private void navToBillTypeFragment() {
        clearMyBackStack();
        Fragment frg = new HomeBillsFragment();
        ft = fm.beginTransaction();
        ft.replace(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_HomeFragment);
        ft.commit();
    }


    private void navToSettingsFragment() {
        Fragment frg = new SettingsFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_SettingsFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_SettingsFragment);
        hideLastStackFragment(ft);
        ft.commit();
    }


    private void navToMyBillsFragment() {
        Fragment frg = new MyBillsFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_MyBillsFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_MyBillsFragment);
        hideLastStackFragment(ft);
        ft.commit();
    }

    private void navToHistoryFragment() {
        Fragment frg = new HistoryFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_HistoryFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_HistoryFragment);
        hideLastStackFragment(ft);
        ft.commit();
    }

    private void navToEditProfileFragment() {

        Fragment frg = new EditProfileFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_EditProfileFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_EditProfileFragment);
        hideLastStackFragment(ft);
        ft.commit();
    }

    private void navToCustomCalenderFragment() {

        Fragment frg = new CustomCalenderFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_CustomCalenderFragment);
        ft.addToBackStack(AppConstt.FragTag.FN_CustomCalenderFragment);
        hideLastStackFragment(ft);
        ft.commit();
    }

    public void hideLastStackFragment(FragmentTransaction ft) {
        Fragment frg = null;
        frg = getSupportFragmentManager().findFragmentById(R.id.act_main_content_frg);

        if (frg != null) {
            if (frg instanceof HomeBillsFragment && frg.isVisible()) {
                ft.hide(frg);
            }

            if (frg instanceof EditProfileFragment && frg.isVisible()) {
                ft.hide(frg);
            } else if (frg instanceof CustomCalenderFragment && frg.isVisible()) {
                ft.hide(frg);
            } else if (frg instanceof MyBillsFragment && frg.isVisible()) {
                ft.hide(frg);
            } else if (frg instanceof HistoryFragment && frg.isVisible()) {
                ft.hide(frg);
            } else if (frg instanceof SettingsFragment && frg.isVisible()) {
                ft.hide(frg);
            }
        }

    }


    public void clearMyBackStack() {
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStackImmediate();

        }

    }

    //endregion
}
