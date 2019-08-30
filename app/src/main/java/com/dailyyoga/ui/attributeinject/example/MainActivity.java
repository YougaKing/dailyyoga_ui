package com.dailyyoga.ui.attributeinject.example;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dailyyoga.ui.example.R;
import com.dailyyoga.ui.widget.AttributeTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText(tv1.toString());

        AttributeTextView tv2 = findViewById(R.id.tv2);
        tv2.setText(tv2.toString());
    }
}
