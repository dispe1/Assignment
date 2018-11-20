package com.example.im.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ProfessorFragment extends Fragment {
    ListView listview;
    listViewAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.list_main, null);

        adapter = new listViewAdapter(getActivity());
        listview = (ListView) view.findViewById(R.id.list_main) ;
        listview.setAdapter(adapter);

        return view;

    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.saveArray();
    }


}
