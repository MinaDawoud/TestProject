package com.example.pascal.Mannitok;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    public static Activity accueil;
    public final int nbOptions=4;//nb of tabs
    TabLayout options;
    ViewPager contenuBox;
    TextView nomApp,pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accueil=this;
        setContentView(R.layout.activity_user);
        //nomApp=(TextView)findViewById(R.id.app);
        //nomApp.setText("StudyAmi");

        options = (TabLayout)findViewById(R.id.options);
        contenuBox = (ViewPager) findViewById(R.id.contenuBox);
        SetContent adapter=new SetContent(getSupportFragmentManager());
        contenuBox.setAdapter(adapter);
        options.setupWithViewPager(contenuBox);

        setupTabLayout(options);
        actionBarSetup();

    }

    // Partie pour initialiser le sous titre ou pseudo dans notre cas.
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void actionBarSetup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            android.support.v7.app.ActionBar ab = getSupportActionBar();
            ab.setSubtitle(UserInformation.getUsername());
        }
    }

    //Partie pour initialiser les icones dans le TabLayout
    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setupWithViewPager(contenuBox);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_wallet_travel_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_playlist_add_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_business_white_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_grade_48pt);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settings){
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public class SetContent extends FragmentPagerAdapter {

        public SetContent(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            UserContentFragment fragment=new UserContentFragment();
            Bundle choix = new Bundle();
            choix.putInt("choix",position);
            fragment.setArguments(choix);
            return fragment;
        }

        @Override
        public int getCount() {
            return nbOptions;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String titre;
            if(position==0)
                titre="  Transactions  ";
            else if(position==1)
                titre="  Articles  ";
            else if(position==2)
                titre="  Entreprises  ";
            else
                titre="  Panier  ";
            return titre;
        }

    }
}
