package com.example.sharepreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName, editTextId;
    private Button buttonSave;
    private TextView textViewInfo;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextId = findViewById(R.id.editTextId);
        buttonSave = findViewById(R.id.buttonSave);
        textViewInfo = findViewById(R.id.textViewInfo);

        sharedPreferences = getSharedPreferences("StudentInfo", MODE_PRIVATE);

        displayInfo();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String id = editTextId.getText().toString().trim();

                if (name.isEmpty() || id.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Lưu thông tin địa chỉ
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(id, name);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
                    displayInfo();
                }
            }
        });
    }

    private void displayInfo() {
        StringBuilder info = new StringBuilder("Địa chỉ của toi\n");
        for (String id : sharedPreferences.getAll().keySet()) {
            info.append("Địa chỉ: ").append(id)
                    .append("\nTên: ").append(sharedPreferences.getString(id, ""))
                    .append("\n\n");
        }
        textViewInfo.setText(info.toString());
    }
}
