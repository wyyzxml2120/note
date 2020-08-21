package com.nlk.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nlk.note.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }


}