package com.example.projekadalahini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();


        ViewPager vp = findViewById(R.id.vp);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));

        TabLayout tabsMain = findViewById(R.id.tl);
        tabsMain.setupWithViewPager(vp);
    }
}

class MyAdapter extends FragmentStatePagerAdapter{

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    Fragment[] fragments={
            new FragmentGroup(),
            new FragmentPrivate()
    };

    private String[] titles={
            "Group Chat",
            "Private Chat"
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}