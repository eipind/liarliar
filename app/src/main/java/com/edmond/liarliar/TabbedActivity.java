package com.edmond.liarliar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TabHost;
import android.widget.TextView;

public class TabbedActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Instructions");
        setSupportActionBar(toolbar);
        Utils.setUpBackIndicator(this);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, MainActivity.class));
        return super.onSupportNavigateUp();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class InstructionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public InstructionFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static InstructionFragment newInstance(int sectionNumber) {
            InstructionFragment fragment = new InstructionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.instruction_textbox);
            textView.setText("Smoke bare loud to the dome (flavours!).\nSmoke bare loud to the dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\nSmoke bare loud to the dome.\n");

            TabHost tab = (TabHost) rootView.findViewById(R.id.tabHost);
            tab.setup();

            TabHost.TabSpec spec1 = tab.newTabSpec("Tab 1");
            spec1.setIndicator("Tab 1");
            spec1.setContent(R.id.Tab1);
            tab.addTab(spec1);

            TabHost.TabSpec spec2 = tab.newTabSpec("Tab 2");
            spec2.setIndicator("Tab 2");
            spec2.setContent(R.id.Tab2);
            tab.addTab(spec2);

            TabHost.TabSpec spec3 = tab.newTabSpec("Tab 3");
            spec3 .setIndicator("Tab 3");
            spec3 .setContent(R.id.Tab3);
            tab.addTab(spec3);

            TabHost.TabSpec spec4 = tab.newTabSpec("Tab 4");
            spec4 .setIndicator("Tab 4");
            spec4 .setContent(R.id.Tab4);
            tab.addTab(spec4);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a InstructionFragment (defined as a static inner class below).
            return InstructionFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
