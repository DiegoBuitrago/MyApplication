package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.dd.guerrerobuitrago.fotoAppDigital.fragments.PromotionFragments;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.ServicesFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.SettingsFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.StoreFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private String firstName;
    private String lastName;
    private String password;
    private String typeUser;
    private String userName;
    private String photo;
    private BottomNavigationView buttonNav;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        Person userCurrent = (Person) i.getSerializableExtra("user");
            firstName = userCurrent.getFirstName();
            lastName = userCurrent.getLastName();
            password = userCurrent.getPassword();
            typeUser = userCurrent.getTypeUser();
            userName = userCurrent.getUserName();
            photo = userCurrent.getPhoto();
        init();
    }

    private void init() {
        buttonNav = findViewById(R.id.nav);
        frameLayout = findViewById(R.id.frameLayout);
        buttonNav.setSelectedItemId(R.id.item_promotion);
        Fragment promotionFragment = PromotionFragments.newInstance();
        launchFragment(promotionFragment);
        buttonNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.item_promotion:
                        Fragment promotionFragment = PromotionFragments.newInstance();
                        launchFragment(promotionFragment);
                        break;
                    case R.id.item_store:
                        Fragment storeFragment = StoreFragment.newInstance();
                        launchFragment(storeFragment);
                        break;
                    case R.id.item_service:
                        Fragment serviceFragment = ServicesFragment.newInstance();
                        launchFragment(serviceFragment);
                        break;
                    case R.id.item_settings:
                        Fragment settingsFragment = SettingsFragment.newInstance(firstName, lastName, password, typeUser, userName, photo);
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
