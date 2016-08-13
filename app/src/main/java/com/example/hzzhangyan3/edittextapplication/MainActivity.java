package com.example.hzzhangyan3.edittextapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IconCenterEditText iconCenterEditText_one ;
    private IconCenterEditText iconCenterEditText_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconCenterEditText_one = (IconCenterEditText) findViewById(R.id.et_search_one);

        iconCenterEditText_one.setOnEditTextListener(new IconCenterEditText.OnEditTextListener() {
            @Override
            public void onEnterKeyAction(View view) {
                Toast.makeText(MainActivity.this,"第一個EnterKey被点击了",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onHasFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第一個得到焦点",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLostFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第一個失去焦点",Toast.LENGTH_LONG).show();
            }

        });


        iconCenterEditText_two = (IconCenterEditText) findViewById(R.id.et_search_two);

        iconCenterEditText_two.setOnEditTextListener(new IconCenterEditText.OnEditTextListener() {
            @Override
            public void onEnterKeyAction(View view) {
                Toast.makeText(MainActivity.this,"第二個EnterKey被点击了",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onHasFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第二個得到焦点",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLostFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第二個失去焦点",Toast.LENGTH_LONG).show();
            }

        });

    }
}
