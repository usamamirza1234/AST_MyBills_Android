package ast.billmanagment.mybills;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import ast.billmanagment.mybills.MainAuxiliaries.HomeFragment;
import ast.billmanagment.mybills.Utils.AppConstt;
import ast.billmanagment.mybills.Utils.IBadgeUpdateListener;

public class MainActivity extends AppCompatActivity implements IBadgeUpdateListener, View.OnClickListener {
    public DrawerLayout drawer;
    NavigationView navigationView;
    RelativeLayout rlToolbar, rlBack, rlMenu, idr;
    LinearLayout llImportantDates, llMyBills, llProfile, llLogout, llHistory, llSettings;
    TextView txvTitleBar;
    private FragmentTransaction ft;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        idr = findViewById(R.id.homebar);

        llLogout = findViewById(R.id.lay_navigationview_llLogout);


        rlBack.setOnClickListener(this);
        rlMenu.setOnClickListener(this);
        idr.setOnClickListener(this);

        llLogout.setOnClickListener(this);

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
            case R.id.lay_navigationview_llLogout:
                AppConfig.getInstance().navtoLogin();
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
                idr.setVisibility(View.VISIBLE);
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

        drawer.openDrawer(GravityCompat.START);

    }

    public void closeDrawar() {

//        drawer.closeDrawer(GravityCompat.END);
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        }
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
        Fragment frg = new HomeFragment();
        ft = fm.beginTransaction();
        ft.replace(R.id.act_main_content_frg, frg, AppConstt.FragTag.FN_HomeFragment);
        ft.commit();
    }


    public void hideLastStackFragment(FragmentTransaction ft) {
        Fragment frg = null;
        frg = getSupportFragmentManager().findFragmentById(R.id.act_main_content_frg);

        if (frg != null) {
            if (frg instanceof HomeFragment && frg.isVisible()) {
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
