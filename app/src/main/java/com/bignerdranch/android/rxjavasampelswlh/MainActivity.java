package com.bignerdranch.android.rxjavasampelswlh;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bignerdranch.android.rxjavasampelswlh.module.CacheFragment;
import com.bignerdranch.android.rxjavasampelswlh.module.ElementaryFragment;
import com.bignerdranch.android.rxjavasampelswlh.module.MapFragment;
import com.bignerdranch.android.rxjavasampelswlh.module.TokenAdvanceFragment;
import com.bignerdranch.android.rxjavasampelswlh.module.TokenFragment;
import com.bignerdranch.android.rxjavasampelswlh.module.ZipFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.tab_layout) TabLayout mTabLayout;
    @Bind(R.id.view_pager) ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new ElementaryFragment();
                    case 1:
                        return new MapFragment();
                    case 2:
                        return new ZipFragment();
                    case 3:
                        return new TokenFragment();
                    case 4:
                        return new TokenAdvanceFragment();
                    case 5:
                        return new CacheFragment();
                    default:
                        return new ElementaryFragment();
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.title_elementary);
                    case 1:
                        return getString(R.string.title_map);
                    case 2:
                        return getString(R.string.title_zip);
                    case 3:
                        return getString(R.string.title_token);
                    case 4:
                        return getString(R.string.title_token_advanced);
                    case 5:
                        return getString(R.string.title_cache);
                    default:
                        return getString(R.string.title_elementary);
                }
            }

            @Override
            public int getCount() {
                return 6;
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}