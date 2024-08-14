package com.example.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NameDisplay extends AppCompatActivity {
    public static final String NAME = "name";
    TextView name_display;
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Ref from the layout
        name_display = findViewById(R.id.name_display);
        back_button = findViewById(R.id.back_button);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString(NAME);

        //Success
        if (extras != null) {
            name_display.setText("Hello " + name);
            name_display.setTextSize(20);
            name_display.setTextColor(Color.GREEN);
        }

        //Error
        if (extras == null) {
            name_display.setText("An error occurred");
            name_display.setTextSize(25);
            name_display.setTextColor(Color.RED);
            return;
        }

        //Empty
        if (name.trim().isEmpty()) {
            name_display.setText("Name cannot be empty");
            name_display.setTextSize(20);
            name_display.setTextColor(Color.RED);
            return;
        }

        Intent mainAct = new Intent(this, MainActivity.class);
        back_button.setOnClickListener(v -> {
            startActivity(mainAct);
        });
    }
}