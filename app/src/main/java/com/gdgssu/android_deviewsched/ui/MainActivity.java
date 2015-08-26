package com.gdgssu.android_deviewsched.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.appevents.AppEventsLogger;
import com.gdgssu.android_deviewsched.DeviewSchedApplication;
import com.gdgssu.android_deviewsched.R;
import com.gdgssu.android_deviewsched.example.RecyclerViewFragment;
import com.gdgssu.android_deviewsched.model.User;
import com.gdgssu.android_deviewsched.ui.location.LocationActivity;
import com.gdgssu.android_deviewsched.ui.sche.ScheFragment;
import com.gdgssu.android_deviewsched.ui.deviewstory.DeviewStoryFragment;
import com.gdgssu.android_deviewsched.ui.findfriends.FindFriendsFragment;
import com.gdgssu.android_deviewsched.ui.setting.SettingActivity;
import com.gdgssu.android_deviewsched.util.GlideCircleTransform;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

public class MainActivity extends AppCompatActivity implements DeviewFragment.OnFragmentInteractionListener {

    private DrawerLayout mDrawerLayout;
    private MaterialViewPager mViewPager;
    private Toolbar mToolbar;

    private ImageView avatarImage;
    private TextView nameText;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private User userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        Intent intent = getIntent();
        userInfo = (User) intent.getSerializableExtra("UserInfo");

        initMaterialViewPager();
        initToolbar();
        initNavigationView();
    }

    private void initToolbar() {
        setTitle("");

        mToolbar = mViewPager.getToolbar();

        if (mToolbar != null) {
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
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Selection";
                    case 1:
                        return "Actualités";
                    case 2:
                        return "Professionnel";
                    case 3:
                        return "Divertissement";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.colorPrimary,
                                ContextCompat.getDrawable(getApplicationContext(), R.drawable.backwall1));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.colorPrimary,
                                ContextCompat.getDrawable(getApplicationContext(), R.drawable.backwall2));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.colorPrimary,
                                ContextCompat.getDrawable(getApplicationContext(), R.drawable.backwall3));
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.colorPrimary,
                                ContextCompat.getDrawable(getApplicationContext(), R.drawable.backwall4));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

    }

    private void initNavigationView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null)
            setupDrawerContent(navigationView);

        avatarImage = (ImageView) findViewById(R.id.profile_image);
        nameText = (TextView) findViewById(R.id.profile_name_text);

        Glide.with(getApplicationContext())
                .load(userInfo.picture)
                .transform(new GlideCircleTransform(DeviewSchedApplication.GLOBAL_CONTEXT))
                .override(32, 32) //임의로 결정한 크기임.
                .into(avatarImage);
        nameText.setText(userInfo.name);

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

                    case R.id.nav_location:
                        showLocation(getResources().getText(R.string.location));
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

    public void showLocation(CharSequence title) {

        mDrawerLayout.closeDrawers();

        startActivity(new Intent(MainActivity.this, LocationActivity.class));

    }

    public void showSetting() {

        mDrawerLayout.closeDrawers();

        startActivity(new Intent(MainActivity.this, SettingActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
