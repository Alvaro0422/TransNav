package com.example.projekadalahini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ForumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        getSupportActionBar().hide();


        ViewPager vp = findViewById(R.id.vp);
        vp.setAdapter(new MyAdapter2(getSupportFragmentManager()));

        TabLayout tabsMain = findViewById(R.id.tl);
        tabsMain.setupWithViewPager(vp);
    }
}

class MyAdapter2 extends FragmentStatePagerAdapter {

    public MyAdapter2(@NonNull FragmentManager fm) {
        super(fm);
    }

    Fragment[] fragments={
            new FragmentFeatured(),
            new FragmentRecent(),
            new FragmentPopular()
    };

    private String[] titles={
            "featured",
            "recent",
            "Popular"
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