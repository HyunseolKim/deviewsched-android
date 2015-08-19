package com.gdgssu.android_deviewsched.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.appevents.AppEventsLogger;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.example.RecyclerViewFragment;
import com.gdgssu.android_deviewsched.ui.sche.ScheFragment;
import com.gdgssu.android_deviewsched.ui.deviewstory.DeviewStoryFragment;
import com.gdgssu.android_deviewsched.ui.findfriends.FindFriendsFragment;
import com.gdgssu.android_deviewsched.ui.setting.SettingActivity;
import com.github.florent37.materialviewpager.MaterialViewPager;

public class MainActivity extends AppCompatActivity implements DeviewFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private MaterialViewPager mViewPager;
    private Toolbar mToolbar;

    private ImageView avatarImage;
    private TextView nameText;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        initMaterialViewPager();
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        setTitle("");

        mToolbar = mViewPager.getToolbar();

        if (mToolbar!=null){
            setSupportActionBar(mToolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            }
        }
    }

    private void initMaterialViewPager() {

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return RecyclerViewFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

    }

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null)
            setupDrawerContent(navigationView);

        avatarImage = (ImageView) findViewById(R.id.profile_image);
        nameText = (TextView) findViewById(R.id.profile_name_text);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        showHome();

                        break;
                    case R.id.nav_all_schedule:
                        showAllSche(getResources().getText(R.string.all_schedule));

                        break;
                    case R.id.nav_my_schedule:
                        showMySche(getResources().getText(R.string.my_schedule));

                        break;
                    case R.id.nav_find_friends:
                        showFindFriends(getResources().getText(R.string.find_friends));

                        break;
                    case R.id.nav_deview_story:
                        showDeviewStory(getResources().getText(R.string.deview_story));

                        break;
                    case R.id.nav_setting:
                        showSetting();

                        break;
                }
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    public void showHome() {

        /**
         * Todo 아래의 메소드가 호출되면 MainActivity위로 있는 모든 Fragment가 소멸됨
         */

        mDrawerLayout.closeDrawers();

        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void showAllSche(CharSequence title) {

        mDrawerLayout.closeDrawers();

        Fragment allScheFragment = ScheFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, allScheFragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void showMySche(CharSequence title) {

        mDrawerLayout.closeDrawers();

        Fragment myScheFragment = ScheFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, myScheFragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

    public void showFindFriends(CharSequence title) {

        mDrawerLayout.closeDrawers();

        Fragment findFriendsFragment = FindFriendsFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, findFriendsFragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

    public void showDeviewStory(CharSequence title) {

        mDrawerLayout.closeDrawers();

        Fragment deviewStoryFragment = DeviewStoryFragment.newInstance(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_container, deviewStoryFragment);
        fragmentTransaction.addToBackStack(null).commit();

    }

    public void showSetting() {

        mDrawerLayout.closeDrawers();

        startActivity(new Intent(MainActivity.this, SettingActivity.class));

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        showHome();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        AppEventsLogger.deactivateApp(this);
    }
}
