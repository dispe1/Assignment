package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class greetings extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("컴퓨터공학은 개인과 기업의 발전은 물론, 국가의 미래를 좌우하는 핵심 지식과 기술에 관한 학문입니다. \n" +
                "\n" +
                "중앙대학교 소프트웨어학부는 \"의에 죽고 참에 살자\"라는 교훈 아래 1972년부터 참된 인성과 최고의 전문성을 갖춘 컴퓨터공학 인재를 양성해왔습니다. \n" +
                "\n" +
                "학부 3300여명, 대학원 900여명에 달하는 우리 졸업생들은 국내외 산업체, 학계, 연구소 등에서 중추적 역할을 하고 있습니다. 최근에는 전통적 컴퓨터 분야의 수요 외에도 다양한 서비스 및 최첨단 기술과의 융합 요구가 급격히 증대되어, 그 어느 때보다 고급 컴퓨터공학 인력 양성에 대한 필요성이 높습니다. 중앙대학교 소프트웨어학부는 이러한 사회적 요구를 반영하여 다음의 목표를 갖고 국가 인재를 육성하고자 합니다. \n" +
                "\n" +
                "(1) 컴퓨터공학 기초지식 및 핵심이론, 공학적 사고능력과 창의력을 갖춘 인재 양성 \n" +
                "(2) 실전적 교육을 바탕으로 산업계가 요구하는 실무 능력을 갖춘 인재 양성 \n" +
                "(3) 다양한 학문 및 최첨단 기술과 접목 능력을 지닌 융합적 인재 양성 \n" +
                "(4) 책임감있고 조직과의 융화를 이끌 수 있는 미래 지향적 리더급 인재 양성 \n" +
                "(5) 국제화 교육을 통해 세계적 전문인력과 소통 및 협업이 가능한 글로벌 인재 양성 \n" +
                "\n" +
                "세계를 향해 힘차게 전진하는 중앙대 소프트웨어학부를 주목해 주십시오. \n" +
                "여러분들의 많은 성원과 참여를 부탁드립니다. 감사합니다. ") ;

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
