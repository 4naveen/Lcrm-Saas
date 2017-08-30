package com.project.lorvent.way2freshers;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.share.widget.LikeView;
import com.google.common.io.BaseEncoding;
import com.project.lorvent.way2freshers.activities.AboutUsActivity;
import com.project.lorvent.way2freshers.activities.HelpActivity;
import com.project.lorvent.way2freshers.activities.NetworkErrorActivity;
import com.project.lorvent.way2freshers.fragments.CategoriesFragment;
import com.project.lorvent.way2freshers.fragments.DashboardFragment;
import com.project.lorvent.way2freshers.fragments.EducationFragment;
import com.project.lorvent.way2freshers.fragments.ExamsFragment;
import com.project.lorvent.way2freshers.fragments.HomeFragment;
import com.project.lorvent.way2freshers.fragments.InterviewFragment;
import com.project.lorvent.way2freshers.fragments.LoanFragment;
import com.project.lorvent.way2freshers.fragments.PlacementsFragment;
import com.project.lorvent.way2freshers.fragments.SchlorshipFragment;
import com.project.lorvent.way2freshers.utils.NetworkStatus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawer;
    Button profile;
    private CallbackManager callbackManager;
    LikeView likeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!NetworkStatus.isConnected(MainActivity.this))
        {
            Intent i = new Intent(MainActivity.this, NetworkErrorActivity.class);
            startActivity(i);
            finish();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        String packageName = this.getPackageName();
        String SHA1=getSHA1(packageName,this);
        Log.i("sha1 key--",SHA1);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View v1 = navigationView.inflateHeaderView(R.layout.nav_header_home);
        likeView = (LikeView) v1.findViewById(R.id.likeview);
        callbackManager = CallbackManager.Factory.create();
        likeView.setLikeViewStyle(LikeView.Style.STANDARD);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);
        likeView.setObjectIdAndType("https://www.facebook.com/way2freshers/", LikeView.ObjectType.PAGE);
        Fragment fragment1 = new DashboardFragment();
        FragmentTransaction trans1 = getSupportFragmentManager().beginTransaction();
        trans1.replace(R.id.frame, fragment1);
        trans1.addToBackStack(null);
        trans1.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;
        navigationView.getMenu().findItem(item.getItemId()).setChecked(true);

        switch (item.getItemId()) {
            case R.id.dashboard: {
                fragment = new DashboardFragment();

                break;
            }
            case R.id.home: {
                fragment = new HomeFragment();

                break;
            }
            case R.id.categories: {

                fragment = new CategoriesFragment();

                break;
            }
            case R.id.placement: {

                fragment = new PlacementsFragment();

                break;
            }
            case R.id.ques: {

                fragment = new InterviewFragment();

                break;
            }
            case R.id.loans: {

                fragment = new LoanFragment();

                break;
            } case R.id.schlorship: {

                fragment = new SchlorshipFragment();

                break;
            }
            case R.id.education: {

                fragment = new EducationFragment();
                break;
            }
            case R.id.exams: {
                fragment = new ExamsFragment();
                break;
            }
            case R.id.share: {
                fragment = new DashboardFragment();
                Intent intentShare=new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.project.lorvent.way2freshers");
                //startActivity(Intent.createChooser(intentShare, "Select an action"));
                startActivityForResult(Intent.createChooser(intentShare, "Select an action"),0);
                break;
            }
        }
        transaction2.replace(R.id.frame, fragment);
        transaction2.addToBackStack(null);
        transaction2.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_actionbar,menu);
        // this.menu=menu;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.help)
        {
            Intent i=new Intent(MainActivity.this,HelpActivity.class);
            startActivity(i);
        }
        if (item.getItemId()==R.id.about)
        {
            Intent i=new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        navigationView.getMenu().findItem(R.id.dashboard).setChecked(true);
        callbackManager.onActivityResult(requestCode, resultCode, data);

       // super.onActivityResult(requestCode, resultCode, data);
    }

    private String getSHA1(String packageName, Context context){
        try {
            Signature[] signatures = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures;
            for (Signature signature: signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA-1");
                md.update(signature.toByteArray());
                return BaseEncoding.base16().encode(md.digest());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
          onCreate(savedInstanceState);
    }
}
