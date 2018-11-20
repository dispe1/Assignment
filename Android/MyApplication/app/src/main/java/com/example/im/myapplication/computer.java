package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class computer extends Fragment {
    String[] LIST_MENU = {"학부교과과정", "공학인증", "학사행정"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.list_main, null);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.row, LIST_MENU) ;
        ListView listview = (ListView) view.findViewById(R.id.list_main) ;
        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;

                if(position == 0) {
                    fragment = new computerCurriculum();
                }
                if(position == 1) {
                    fragment = new engineeringCertification();
                }
                if(position == 2) {
                    fragment = new Administration();
                }


                if(fragment != null) {
                    transaction.replace(R.id.main_fragment, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
            }
        }) ;

        return view;
    }

}
