package com.iti.iti_project_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.iti.iti_project_java.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String KEY_NAME = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(View ->{

            String name = binding.editName.getText().toString();
            String password = binding.editPass.getText().toString();
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);

            if(password.equals("123"))
            {
                if(!name.isEmpty())
                {
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "inter your name", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"invalid name or password",Toast.LENGTH_LONG).show();
            }
        });

    }
}