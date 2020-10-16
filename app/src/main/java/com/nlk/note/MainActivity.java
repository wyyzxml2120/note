package com.nlk.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nlk.note.data.EbsViewData;
import com.nlk.note.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //EventBus.getDefault().register(this);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

    public void showBottom(boolean isShow){
        if (isShow){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) binding.navBottom.getLayoutParams();
            params.height=LinearLayout.LayoutParams.WRAP_CONTENT;
            binding.navBottom.setLayoutParams(params);
            binding.navBottom.setVisibility(View.VISIBLE);
        }else {
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) binding.navBottom.getLayoutParams();
            params.height=0;
            binding.navBottom.setLayoutParams(params);
            binding.navBottom.setVisibility(View.INVISIBLE);
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onGetMessage(EbsViewData message) {
//        Log.d("asss","aaa");
//        if (message.isBottomIsShow()){
//            Log.d("asss","aaa");
//            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) binding.navBottom.getLayoutParams();
//            params.height=LinearLayout.LayoutParams.WRAP_CONTENT;
//            binding.navBottom.setLayoutParams(params);
//            binding.navBottom.setVisibility(View.VISIBLE);
//        }else {
//            Log.d("asss","bbb");
//            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) binding.navBottom.getLayoutParams();
//            params.height=0;
//            binding.navBottom.setLayoutParams(params);
//            binding.navBottom.setVisibility(View.INVISIBLE);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

}