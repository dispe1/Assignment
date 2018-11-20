package com.example.im.myapplication;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Activity act = this;
    GridView gridView;
    ExpandableListView listMain;
    Switch alarmSwitch;
    String alarmFile = "alarmSetting";

    ArrayList<Bitmap> picArr = new ArrayList<Bitmap>();
    ArrayList<String> textArr = new ArrayList<String>();

    mainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (mainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment);
        Log.e("Frag", "Fragment");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences sf = getSharedPreferences(alarmFile, 0);
        boolean saveAlarmSetting = sf.getBoolean("setting",false);
        alarmSwitch = (Switch) findViewById(R.id.alarm_switch);
        alarmSwitch.setChecked(saveAlarmSetting);
        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                SharedPreferences sf = getSharedPreferences(alarmFile, 0);
                SharedPreferences.Editor editor = sf.edit();
                Boolean bool = alarmSwitch.isChecked();
                editor.putBoolean("setting", bool);
                editor.commit();
                if(isChecked){

                }else{

                } } });



        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new gridAdapter());

        listMain = (ExpandableListView) findViewById(R.id.parentListView);
        listMain.setAdapter(new Adapter(this));

        listMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition,long id) {

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        Fragment fragment = null;

                        if(groupPosition == 0) {
                            if(childPosition == 0) {
                                fragment = new introduction();
                            }
                            else if(childPosition == 1) {
                                fragment = new greetings();
                            }
                            else if(childPosition == 2) {
                                fragment = new ProfessorFragment();
                            }
                        }
                        else if(groupPosition == 1) {
                            if(childPosition == 0) {
                                fragment = new computerCurriculum();
                            }
                            else if(childPosition == 1) {
                                fragment = new engineeringCertification();
                            }
                            else if(childPosition == 2) {
                                fragment = new Administration();
                            }
                        }
                        else if(groupPosition == 2) {
                            if(childPosition == 0) {
                                fragment = new softwareCurriculum();
                            }
                            else if(childPosition == 1) {
                                fragment = new softwareIntroduction();
                            }
                            else if(childPosition == 2) {
                                fragment = new QnA();
                            }
                        }
                        else if(groupPosition == 3) {
                            if (childPosition == 0) {
                                fragment = new ScheduleFragment();
                            }
                        }

                        if(fragment != null) {
                            transaction.replace(R.id.main_fragment, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                        }
                        return false;
                    }
                });
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sf = getSharedPreferences(alarmFile, 0);
        SharedPreferences.Editor editor = sf.edit();
        Boolean bool = alarmSwitch.isChecked();
        editor.putBoolean("setting", bool);
        editor.commit();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.back) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override

    public void onConfigurationChanged(Configuration newConfig)

    {

        super.onConfigurationChanged(newConfig);



    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class gridAdapter extends BaseAdapter {
        LayoutInflater inflater;
        public gridAdapter() {
            Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.intoduction);
            Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.computer);
            Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.software);
            Bitmap bm4 = BitmapFactory.decodeResource(getResources(), R.drawable.schedule);

            picArr.add(bm1);
            picArr.add(bm2);
            picArr.add(bm3);
            picArr.add(bm4);

            textArr.add("학부소개");
            textArr.add("컴퓨터공학전공");
            textArr.add("소프트웨어전공");
            textArr.add("일정관리");

            inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
// TODO Auto-generated method stub
            return picArr.size();
        }
        @Override
        public Object getItem(int position) {
// TODO Auto-generated method stub
            return picArr.get(position);
        }
        @Override
        public long getItemId(int position) {
// TODO Auto-generated method stub
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.grid_main, parent, false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            TextView textView = (TextView) convertView.findViewById(R.id.textView1);
            imageView.setImageBitmap(picArr.get(position));
            textView.setText(textArr.get(position));
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
// TODO Auto-generated method stub
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    Fragment fragment = null;

                    if(position == 0) {
                        fragment = new introductionMainFragment();
                    }
                    else if(position == 1) {
                        fragment = new computer();
                    }
                    else if (position == 2) {
                        fragment = new software();
                    }
                    else if(position == 3) {
                        fragment = new ScheduleFragment();
                    }

                    if(fragment != null) {
                        transaction.replace(R.id.main_fragment, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }
            });
            return convertView;
        }
    }

}
