package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.PromotionFragments;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.ServicesFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.SettingsFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.StoreFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private BottomNavigationView buttonNav;
    private FrameLayout frameLayout;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        AndroidNetworking.initialize(getApplicationContext());
        Intent i = this.getIntent();
        //Bundle bundle = i.getExtras();
        //this.person = (Person) bundle.getSerializable("user");
        this.person = (Person) i.getSerializableExtra("user");
        init();
    }

    private void init() {
        buttonNav = findViewById(R.id.nav);
        frameLayout = findViewById(R.id.frameLayout);
        buttonNav.setSelectedItemId(R.id.item_promotion);
        Fragment promotionFragment = PromotionFragments.newInstance(person);
        launchFragment(promotionFragment);
        buttonNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.item_promotion:
                        Fragment promotionFragment = PromotionFragments.newInstance(person);
                        launchFragment(promotionFragment);
                        break;
                    case R.id.item_store:
                        Fragment storeFragment = StoreFragment.newInstance(person);
                        launchFragment(storeFragment);
                        break;
                    case R.id.item_service:
                        Fragment serviceFragment = ServicesFragment.newInstance(person);
                        launchFragment(serviceFragment);
                        break;
                    case R.id.item_settings:
                        Fragment settingsFragment = SettingsFragment.newInstance(person);
                        launchFragment(settingsFragment);
                        break;
                }
                return true;
            }
        });
    }

    private void launchFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameLayout.getId(), fragment);
        transaction.commit();
    }
}
