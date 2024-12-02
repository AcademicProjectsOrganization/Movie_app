package com.iti.iti_project_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.iti_project_java.databinding.ActivityMain3Binding;
import com.squareup.picasso.Picasso;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Bundle bundle = getIntent().getExtras();
        String img = bundle.getString("img_url");
        String details = bundle.getString("details");
        String date = bundle.getString("release_date");




        TextView t = findViewById(R.id.desc);
        t.setText(details);

        TextView t2 = findViewById(R.id.release_date);
        t2.setText(date);

        ImageView i = findViewById(R.id.imageView2);
        Picasso.with(getApplicationContext()).load(IMAGE_BASE_URL + img).into(i);

    }
}