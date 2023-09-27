package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    String currEmail, historyEmail;
    String emailSimpan;
    TextView history;
    ListView lv;
    List<String> title = new ArrayList<>();

//    String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().hide();

        lv = findViewById(R.id.listView);

        Intent in = getIntent();
        Bundle bundle = in.getExtras();

        history = findViewById(R.id.history_txt);

        emailSimpan = (String) bundle.get("email");

        checkCredentials();
        checkCredentials2();

        MainAdapter3 adapter;

        adapter = new MainAdapter3(HistoryActivity.this, title);
        lv.setAdapter(adapter);
    }

    private boolean checkCredentials() {
        File directory = getExternalFilesDir(null);
        if (directory != null) {
            File file = new File(directory, "user_data.txt");
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] userData = line.split(",");

                    if (userData.length == 3&&userData[2].equals(emailSimpan)) {
                        currEmail = userData[2];
                        bufferedReader.close();
                        return true;
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(HistoryActivity.this, "Failed to read user data!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    private boolean checkCredentials2() {
        File directory = getExternalFilesDir(null);
        if (directory != null) {
            File file = new File(directory, "history.txt");
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] userData = line.split(",");

//                    title.add(userData[1]);

                    if (userData[0].equals(currEmail)) {

//                        history.setText(userData[1]);
                        title.add(userData[1]);
//                        bufferedReader.close();
//                        return true;
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(HistoryActivity.this, "Failed to read user data!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    public static class MainAdapter3 extends BaseAdapter {

        HistoryActivity mainActivity;
        List<String> title;

        public MainAdapter3(HistoryActivity mainActivity, List<String> title){
            this.mainActivity = mainActivity;
            this.title = title;
        }

        @Override
        public int getCount() {
            return title.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(mainActivity).inflate(R.layout.new_item_layout2, parent, false);

            TextView textView = convertView.findViewById(R.id.textview);
            RelativeLayout ll_bg = convertView.findViewById(R.id.ll_bg);

            textView.setText(title.get(position));
            ll_bg.setBackground(ContextCompat.getDrawable(mainActivity, R.drawable.rectangle_35_shape));

            return convertView;
        }
    }
}