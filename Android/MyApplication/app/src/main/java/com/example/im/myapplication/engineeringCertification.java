package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class engineeringCertification extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("교육 목표\n" + "1. 컴퓨터공학 기초지식 및 이론교육, 공학적 사고 능력과 창의력을 겸비한 인력 양성\n" +
                "2. 실무 능력에 필수적인 실험 교육을 바탕으로 IT산업 현장에 필요한 인력 양성\n" +
                "3. IT산업 관련 제반 문제점 해결 및 신기술 접목 능력을 지닌 인력양성\n" +
                "4. 독립 및 공동 연구 개발이 가능한 미래 지향적 전문 인력양성\n" +
                "5. 정보화 사회의 책임감 있고, 국제적 감각이 있는 리더 인력 양성\n" +
                "\n특성\n" + "1. 컴퓨터공학의 기술과 이론연구\n" +
                "컴퓨터공학이란 컴퓨터를 구성하는 하드웨어 및 소프트웨어에 대한 지식을 습득하고, 설계 및 개발능력을 배양시키는 학문이다.\n" +
                "\n2. 전문적/창조적 능력을 갖춘 인재 양성\n" +
                "4년간의 학부교육 후에는 취업하여 컴퓨터에 관련된 분야를 개척해 나가는 선구자적인 엔지니어로 근무하거나, 대학원 등에 진학하여 학계나 연구소에서 학문의 길을 걷게 된다. 공학교육인증 프로그램을 통하여 교과과정을 이수한 졸업생에게는 컴퓨터공학전문 프로그램을 이수했음을 확인해 준다."
        );

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
