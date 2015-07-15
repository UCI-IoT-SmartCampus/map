package com.example.hjlee.mymaps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class informationActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("arr_text");

        TextView sc_t = (TextView) findViewById(R.id.Text);
        sc_t.setText(text);
    }
}
