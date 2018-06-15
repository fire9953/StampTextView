package com.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.klib.widget.ChatTextStampView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ChatTextStampView chatTextStampView1 = findViewById(R.id.text1);
        chatTextStampView1.setTextAndTime("abcdef", "10:20PM");

        ChatTextStampView chatTextStampView2 = findViewById(R.id.text2);
        chatTextStampView2.setTextAndTime("abcdefabcdefabcdefabcdefabcdefabcdefa", "10:21PM");

        ChatTextStampView chatTextStampView3 = findViewById(R.id.text3);
        chatTextStampView3.setTextAndTime("abcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdefabcdef", "10:22PM");

    }
}
