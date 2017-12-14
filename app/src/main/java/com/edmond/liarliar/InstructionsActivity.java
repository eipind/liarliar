package com.edmond.liarliar;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_instructions);
        setSupportActionBar(toolbar);
        Utils.setUpBackIndicator(this);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.container);
        pager.setAdapter(adapter);

        TabLayout tl = (TabLayout) findViewById(R.id.tab_layout);
        tl.setupWithViewPager(pager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, MainActivity.class));
        return super.onSupportNavigateUp();
    }

    public static class InstructionFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public InstructionFragment() {}

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
            View rootView = inflater.inflate(R.layout.fragment_instructions, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.instruction_textbox);
            setInstructionContent(textView, (int) getArguments().get(ARG_SECTION_NUMBER));
            return rootView;
        }

        private void setInstructionContent(TextView tv, int sectionNumber) {

            switch (sectionNumber){
                case 0:
                    tv.setText(Html.fromHtml(getString(R.string.rules_formatted)));
                    break;
                case 1:
                    tv.setText(Html.fromHtml(getString(R.string.tips_formatted)));
                    break;
                case 2:
                    tv.setText(Html.fromHtml(getString(R.string.example_formatted)));
                    break;
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return InstructionFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return getString(R.string.instructions_rules_fragment_title);
                case 1:
                    return getString(R.string.instructions_tips_fragment_title);
                case 2:
                    return getString(R.string.instructions_example_fragment_title);
            }
            return null;
        }
    }
}
