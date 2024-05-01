package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CreateTask extends AppCompatActivity {

    TextInputEditText etTaskTitle, etTaskDesc, etTaskDate, etTaskPriority;
    Button btnCancleTask, btnCreate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_task);
        init();

        btnCancleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateTask.this, MainActivity.class));
                finish();
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTaskTitle.getText().toString().trim();
                String desc = etTaskDesc.getText().toString().trim();
                String dueDate = etTaskDate.getText().toString().trim();
                String priorityStr = etTaskPriority.getText().toString().trim();

                if (title.isEmpty() || desc.isEmpty() || dueDate.isEmpty() || priorityStr.isEmpty()) {
                    Toast.makeText(CreateTask.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer priority;
                try {
                    priority = Integer.parseInt(priorityStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(CreateTask.this, "Invalid priority format", Toast.LENGTH_SHORT).show();
                    return;
                }

                Tasks t = new Tasks(title, desc, dueDate, priority);
                TasksData.Data.add(t);
                Toast.makeText(CreateTask.this, "Task created", Toast.LENGTH_SHORT).show();


            }
        });


    }

    private void init()
    {
        etTaskTitle = findViewById(R.id.etTaskTitle);
        etTaskDesc = findViewById(R.id.etTaskDesc);
        etTaskDate = findViewById(R.id.etTaskDate);
        etTaskPriority = findViewById(R.id.etTaskPriority);

        btnCancleTask = findViewById(R.id.btnCancelTask);
        btnCreate = findViewById(R.id.btnCreate);
    }

}