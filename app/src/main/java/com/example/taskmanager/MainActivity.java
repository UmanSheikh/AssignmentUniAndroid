package com.example.taskmanager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterClass.LongClicked {

    Button btnCreateTask, btnLogout, btnViewTask;
    AdapterClass adapter;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();




        btnCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateTask.class));
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sPref = getSharedPreferences(Auth.FILENAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.clear();
                editor.apply();

                startActivity(new Intent(MainActivity.this, Auth.class));
                finish();
            }
        });

        btnViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    adapter = new AdapterClass(ViewTasks.class.newInstance(), TasksData.Data);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
                ListView lv = view.findViewById(R.id.lv);
                lv.setAdapter(adapter);
            }
        });

    }

    @SuppressLint("ResourceType")
    private void init()
    {
        TasksData.Data = new ArrayList<>();
        btnCreateTask = findViewById(R.id.btnCreateTask);
        btnLogout = findViewById(R.id.btnLogout);
        btnViewTask = findViewById(R.id.btnViewTask);
        view = findViewById(R.layout.view_tasks);
    }

    @Override
    public void deleteTask(int index) {
        TasksData.Data.remove(index);
    }
}