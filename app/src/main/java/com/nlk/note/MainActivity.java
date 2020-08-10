package com.nlk.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public class ClickProxy {

        //更换主题
        public void changeTheme() {
            //nav().navigate(R.id.action_mainFragment_to_loginFragment);
        }
    }

}