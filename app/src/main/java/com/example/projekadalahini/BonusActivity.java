package com.example.projekadalahini;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BonusActivity extends AppCompatActivity {
    String currEmail, historyEmail;
    String emailSimpan;
    TextView history;
    ListView lv;
    List<String> title = new ArrayList<>();
    static List<String> title2 = new ArrayList<>();
    int counter=0;

//    String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        getSupportActionBar().hide();

        lv = findViewById(R.id.listView);

        Intent in = getIntent();
        Bundle bundle = in.getExtras();

        history = findViewById(R.id.history_txt);

        emailSimpan = (String) bundle.get("email");

        checkCredentials();
        checkCredentials2();

        MainAdapter3 adapter;

        title2.add("5");
        title2.add("10");
        title2.add("25");
        title2.add("50");
        title2.add("100");
        title2.add("200");
        title2.add("500");
        title2.add("1000");

        if(counter>1000){
            title.clear();
            title.add("5");
            title.add("10");
            title.add("25");
            title.add("50");
            title.add("100");
            title.add("200");
            title.add("500");
            title.add("1000");
        }else if(counter<=5){
            title.clear();
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if (counter<=10) {
            title.clear();
            title.add("5");
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if(counter<=25){
            title.clear();
            title.add("5");
            title.add("10");
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if(counter<=50){
            title.clear();
            title.add("5");
            title.add("10");
            title.add("25");
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if(counter<=100){
            title.clear();
            title.add("5");
            title.add("10");
            title.add("25");
            title.add("50");
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if(counter<=200){
            title.clear();
            title.add("5");
            title.add("10");
            title.add("25");
            title.add("50");
            title.add("100");
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if(counter<=500){
            title.clear();
            title.add("5");
            title.add("10");
            title.add("25");
            title.add("50");
            title.add("100");
            title.add("200");
            title.add(Integer.toString(counter));
            title.add(Integer.toString(counter));
        }else if(counter<=1000){
            title.clear();
            title.add("5");
            title.add("10");
            title.add("25");
            title.add("50");
            title.add("100");
            title.add("200");
            title.add("500");
            title.add(Integer.toString(counter));
        }

        adapter = new MainAdapter3(BonusActivity.this, title);
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
                Toast.makeText(BonusActivity.this, "Failed to read user data!", Toast.LENGTH_SHORT).show();
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
                    if (userData[0].equals(currEmail)) {
                        counter+=1;
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(BonusActivity.this, "Failed to read user data!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    public static class MainAdapter3 extends BaseAdapter {

        BonusActivity mainActivity;
        List<String> title;

        public MainAdapter3(BonusActivity mainActivity, List<String> title){
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

            convertView = LayoutInflater.from(mainActivity).inflate(R.layout.new_item_layout3, parent, false);

            TextView textView = convertView.findViewById(R.id.textview);
            RelativeLayout ll_bg = convertView.findViewById(R.id.ll_bg);

            textView.setText(title.get(position)+"/"+title2.get(position));
            ll_bg.setBackground(ContextCompat.getDrawable(mainActivity, R.drawable.rectangle_35_shape));

            return convertView;
        }
    }
}