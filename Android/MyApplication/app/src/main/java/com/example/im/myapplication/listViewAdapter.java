package com.example.im.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class listViewAdapter extends BaseAdapter {
    private Context context;
    ArrayList<Boolean> Star = new ArrayList<Boolean>();
    ArrayList<String> Professor = new ArrayList<String>();
    public listViewAdapter(Context context) {
        super();
        this.context = context;
        setArrayData();
        getStarArray();
    }

    @Override
    public int getCount() {
        return Professor.size();
    }

    @Override
    public Object getItem(int position) {
        return Professor.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        ViewHolder holder;
        CheckBox checkStar;
        TextView textProfessor;
        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout)inflater.inflate(R.layout.professor,null);
            holder = new ViewHolder();
            holder.m_Btn = (CheckBox) v.findViewById(R.id.btn_star);
            holder.m_TextView = (TextView) v.findViewById(R.id.content);

            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        holder.m_Btn.setChecked(Star.get(position));
        holder.m_TextView.setText(Professor.get(position));

        holder.m_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = !Star.get(position);
                Star.set(position,isChecked);
                if(isChecked){
                    Collections.swap(Professor,getStar()-1,position);
                }else{
                    Collections.swap(Professor,getStar(),position);
                }
                Collections.sort(Professor.subList(0,getStar()), myComparator);
                Collections.sort(Professor.subList(getStar(),Professor.size()), myComparator);
                Collections.sort(Star, myComparator);
                Collections.reverse(Star);
                Professor = deduplication(Professor);
                notifyDataSetChanged();
            }
        });
        holder.m_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = !Star.get(position);
                Star.set(position,isChecked);
                if(isChecked){
                    Collections.swap(Professor,getStar()-1,position);
                }else{
                    Collections.swap(Professor,getStar(),position);
                }
                Collections.sort(Professor.subList(0,getStar()), myComparator);
                Collections.sort(Professor.subList(getStar(),Professor.size()), myComparator);
                Collections.sort(Star, myComparator);
                Collections.reverse(Star);
                Professor = deduplication(Professor);
                notifyDataSetChanged();

            }
             });

        return v;
    }

    public ArrayList<String> deduplication(ArrayList<String> list) {
        ArrayList resultList = new ArrayList<String>();
        for(int i = 0; i < list.size(); i++) {
            if(!resultList.contains(list.get(i))) {
                resultList.add(list.get(i));
            }
        }
        return  resultList;
    }

    class ViewHolder {
        public TextView m_TextView;
        public CheckBox m_Btn;
    }

    public int getStar() {
        int cnt = 0;
        for(int i = 0; i <27;i++) {
            if(Star.get(i) == true) {
                cnt++;
            }
        }
        return cnt;
    }

    private final static Comparator myComparator= new Comparator() {
        private final Collator collator = Collator.getInstance();
        @Override
        public int compare(Object object1, Object object2) {
            return collator.compare(object1.toString(), object2.toString());
        }
    };

    public void saveArray() {

        SharedPreferences starPrefs = context.getSharedPreferences("star",0);
        SharedPreferences.Editor starEditor = starPrefs.edit();
        SharedPreferences professorPrefs = context.getSharedPreferences("professor",0);
        SharedPreferences.Editor professorEditor = professorPrefs.edit();


        for(int i=0;i<27;i++) {
            starEditor.putBoolean("stars" + "_" + i, Star.get(i));
            professorEditor.putString("professor" + "_" + i, Professor.get(i));
        }
        starEditor.commit();
        professorEditor.commit();
    }

    public void getStarArray() {
        SharedPreferences prefs = context.getSharedPreferences("star",0);
        for(int i=0;i<27;i++) {
            Star.add(i, prefs.getBoolean("stars" + "_" + i, false));
        }
    }

    private void setArrayData() {
        SharedPreferences prefs = context.getSharedPreferences("professor",0);
        SharedPreferences.Editor editor = prefs.edit();

        int first = 1;
        int last = prefs.getInt("init",0);

        if(first>last) {
            Professor.add("강현철 교수");
            Professor.add("권영빈 교수");
            Professor.add("권준석 조교수");
            Professor.add("김대원 교수");
            Professor.add("김무철 조교수");
            Professor.add("김성권 교수");
            Professor.add("김성조 교수");
            Professor.add("김중헌 조교수");
            Professor.add("박경주 부교수");
            Professor.add("박상오 조교수");
            Professor.add("박재현 교수");
            Professor.add("박재화 교수");
            Professor.add("박진완 교수");
            Professor.add("박창윤 교수");
            Professor.add("백정엽 조교수");
            Professor.add("손봉수 부교수");
            Professor.add("손용석 조교수");
            Professor.add("윤경현 교수");
            Professor.add("이재성 조교수");
            Professor.add("이찬근 교수");
            Professor.add("이창하 부교수");
            Professor.add("정재은 부교수");
            Professor.add("조성래 교수");
            Professor.add("최광남 교수");
            Professor.add("하동환 교수");
            Professor.add("홍병우 교수");
            Professor.add("홍현기 교수");

            for (int i = 0; i < 27; i++) {
                editor.putString("professor" + "_" + i, Professor.get(i));
            }
            editor.putInt("init", 27);
            editor.commit();
        }
        for(int i=0;i<27;i++) {
            Professor.add(i, prefs.getString("professor" + "_" + i,""));
        }
    }
}
