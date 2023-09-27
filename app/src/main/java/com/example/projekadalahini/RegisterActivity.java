package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextConfPassword, editTextEmail;
    private String registeredUsername, registeredPassword, registeredCofpassword, registeredEmail;


    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfPassword = findViewById(R.id.editTextConfPassword);
        editTextEmail = findViewById(R.id.editTextEmail);

        View tombol = findViewById(R.id.rectangle_13);
        ImageView google = findViewById(R.id.google);
        tombol.setOnClickListener(e->{
            registeredUsername = editTextUsername.getText().toString();
            registeredPassword = editTextPassword.getText().toString();
            registeredCofpassword = editTextConfPassword.getText().toString();
            registeredEmail = editTextEmail.getText().toString();

            saveUserData(registeredUsername, registeredPassword, registeredEmail);

            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("email", registeredEmail);
            startActivity(intent);
        });
        google.setOnClickListener(e->{
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void saveUserData(String username, String password, String email) {
        String userData = username + "," + password + "," + email; // Combine username and password with a separator

        // Get the app-specific directory for storing files
        File directory = getExternalFilesDir(null);

        if (directory != null) {
            // Create a new file within the directory
            File file = new File(directory, "user_data.txt");

            try {
                // Create a FileWriter object with the file
                FileWriter fileWriter = new FileWriter(file, true);

                // Create a BufferedWriter object to write data to the file
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Write the user data to the file
                bufferedWriter.write(userData);
                bufferedWriter.newLine(); // Add a new line for each user

                // Close the BufferedWriter
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
                // Handle any errors that occur during file writing
                Toast.makeText(RegisterActivity.this, "Failed to save user data!", Toast.LENGTH_SHORT).show();
            }
        }
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