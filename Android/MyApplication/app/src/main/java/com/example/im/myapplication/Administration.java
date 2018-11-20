package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Administration extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("등록\n" +
                "등록금의 납입 및 납부\n" +
                "수강신청\n" +
                "\n" +
                "수업/교직과정, 부전공, 복수전공의 이수\n" +
                "수업\n" +
                "교직과정 이수\n" +
                "부전공 및 복수전공의 이수\n" +
                "\n" +
                "시험, 성적평가\n" +
                "시험 및 성적평가\n" +
                "시험 블응 신고\n" +
                "계절학기\n" +
                "시험 부정행위자의 처벌\n" +
                "성적열람 및 이의신청\n" +
                "\n" +
                "학적 변동\n" +
                "휴학\n" +
                "제적, 퇴학\n" +
                "복학, 재 입학\n" +
                "전과(부), 편입학\n" +
                "\n" +
                "수업연한/약학대학 및 의과대학 유급, 출교/졸업, 학적등록\n" +
                "수업연한\n" +
                "약학대학 유급 및 출교\n" +
                "의과대학 유급 및 출교\n" +
                "졸업\n" +
                "학점등록"
        );

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
