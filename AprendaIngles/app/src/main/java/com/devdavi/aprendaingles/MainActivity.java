package com.devdavi.aprendaingles;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.devdavi.aprendaingles.adapter.VPadapter;
import com.devdavi.aprendaingles.fragments.BichosFragment;
import com.devdavi.aprendaingles.fragments.NumerosFragment;
import com.devdavi.aprendaingles.fragments.VogaisFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPadapter adapter = new VPadapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new BichosFragment(), "Bichos");
        adapter.addFragment(new NumerosFragment(), "Números");
        adapter.addFragment(new VogaisFragment(), "Vogais");
        viewPager.setAdapter(adapter);
    }
}