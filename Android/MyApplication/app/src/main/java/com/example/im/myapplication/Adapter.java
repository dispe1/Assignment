package com.example.im.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Adapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> arrayParent = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

    public Adapter(Context context) {
        super();
        this.context = context;
        setArrayData();
    }

    @Override
    public int getGroupCount() {
        return arrayParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayChild.get(arrayParent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayParent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(arrayParent.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String parentName = arrayParent.get(groupPosition);
        View v = convertView;

        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout)inflater.inflate(R.layout.list_parent,null);
        }
        TextView textParent = (TextView) v.findViewById(R.id.textParent);
        textParent.setText(parentName);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childName = arrayChild.get(arrayParent.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = (LinearLayout)inflater.inflate(R.layout.list_child,null);
        }
        TextView textChild = (TextView) v.findViewById(R.id.textChild);
        textChild.setText(childName);
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void setArrayData() {
        arrayParent.add("학부소개");
        arrayParent.add("컴퓨터공학전공");
        arrayParent.add("소프트웨어전공");
        arrayParent.add("일정관리");

        ArrayList<String> arrayIntro = new ArrayList<String>();
        arrayIntro.add("학부소개");
        arrayIntro.add("학부장 인사말");
        arrayIntro.add("학부 교수진");

        ArrayList<String> arrayComputer = new ArrayList<String>();
        arrayComputer.add("학부교과과정");
        arrayComputer.add("공학인증");
        arrayComputer.add("학사행정");

        ArrayList<String> arraySoftware = new ArrayList<String>();
        arraySoftware.add("소개");
        arraySoftware.add("교과과정");
        arraySoftware.add("Q&A");

        ArrayList<String> arraySchedule = new ArrayList<String>();
        arraySchedule.add("일정관리");

        arrayChild.put(arrayParent.get(0), arrayIntro);
        arrayChild.put(arrayParent.get(1), arrayComputer);
        arrayChild.put(arrayParent.get(2), arraySoftware);
        arrayChild.put(arrayParent.get(3), arraySchedule);
    }
}
