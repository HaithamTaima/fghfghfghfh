package com.example.fainl_android.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.fainl_android.R;
import com.example.fainl_android.Receiver.HeadSetReceiver;
import com.example.fainl_android.databinding.ActivityMainDrawerBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainDrawerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //سماعات الاذن
        HeadSetReceiver HeadSetreceiver =new HeadSetReceiver();
        IntentFilter filter =new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(HeadSetreceiver,filter);

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.Broad_Casts, R.id.show)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu: {
                Intent intent = new Intent(getBaseContext(), ShowsActivity.class);
                startActivity(intent);

            }
//            case R.id.menuBroad: {
//                Intent intent = new Intent(getBaseContext(), BroadCastsActivity.class);
//                startActivity(intent);
//            }
//            case R.id.menuMap: {
//                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
//                startActivity(intent);
//            }
//            case R.id.RwN: {
//                Intent intent = new Intent(getBaseContext(), ProductActivity.class);
//                startActivity(intent);
//            }
            case R.id.menuEx: {
                finish();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}