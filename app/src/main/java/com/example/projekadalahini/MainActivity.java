package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String username, email;
    private EditText editTextUsername, editTextPassword;
    private String registeredUsername, registeredPassword;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        ImageView google = findViewById(R.id.googleLogin);

        TextView tombol = findViewById(R.id._don_t_have_an_account__register_now);
        View tombol2 = findViewById(R.id.rectangle_13);
        tombol.setOnClickListener(e->{
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
        tombol2.setOnClickListener(e->{
            registeredUsername = editTextUsername.getText().toString();
            registeredPassword = editTextPassword.getText().toString();

            if (checkCredentials(registeredUsername, registeredPassword)) {
                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
            }
        });
        google.setOnClickListener(e->{
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private boolean checkCredentials(String username, String password) {
        // Get the app-specific directory for storing files
        File directory = getExternalFilesDir(null);

        if (directory != null) {
            // Create a file object within the directory
            File file = new File(directory, "user_data.txt");

            try {
                // Create a FileReader object with the file
                FileReader fileReader = new FileReader(file);
                // Create a BufferedReader object to read data from the file
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;

                // Read each line from the file
                while ((line = bufferedReader.readLine()) != null) {
                    // Split the line into username and password using the separator
                    String[] userData = line.split(",");

                    // Check if the entered username and password match the stored values
                    if (userData.length == 3 && userData[0].equals(username) && userData[1].equals(password)) {
                        email = userData[2];
                        bufferedReader.close();
                        return true;
                    }
                }

                // Close the BufferedReader
                bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
                // Handle any errors that occur during file reading
                Toast.makeText(MainActivity.this, "Failed to read user data!", Toast.LENGTH_SHORT).show();
            }
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                // You can proceed with your code that requires permission
            } else {
                // Permission denied
                // You can handle the denial case or disable the functionality that depends on the permission
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}