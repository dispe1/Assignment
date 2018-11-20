package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class softwareCurriculum extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("1학년 1학기 \n" + "Communication in English, 글쓰기, 스토리텔링콘텐츠제작실습, 일반물리학(1), 일반물리실험(1), 선형대수론, 창의융합입문설계, SW프로그래밍(1)\n"+
                "\n1학년 2학기 \n" + "창의와소통, 회계와사회, 스토리텔링의 이해, ICT와경영, 이산수학론, SW프로그래밍(2), 논리회로설계\n" +
                "\n2학년 1학기 \n" + "한국사, 게임아트와 내러티브, 미적분학, 계산미학, 자료구조론, 컴퓨터시스템및어셈블리어, SW프로젝트\n" +
                "\n2학년 2학기 \n" + "ACT, 알고리즘분석, 확률및통계, 객체지향프로그래밍, 프로그래밍언어개론, 컴퓨터구조론\n" +
                "\n3학년 1학기 \n" + "운영체제, 컴퓨터통신, 데이터베이스설계, 휴먼ICT소프트웨어공학, 컴파일러, 산업체특강(1), 수치해석, 공업수학\n" +
                "\n3학년 2학기 \n" + "휴먼ICT인공지능, 모바일앱개발, 웹프로그래밍, 휴먼인터페이스미디어, LINUX시스템, 산업체특강(2)\n" +
                "\n4학년 1학기 \n" + "컴퓨터그래픽스, 정보보호이론, 네트워크응용설계, DB시스템및프로그래밍, 캡스톤디자인(1), 컨텐츠기반무선이동통신, 패턴인식\n" +
                "\n4학년 2학기 \n" + "Coop프로젝트(기업체 인턴쉽), 캡스톤디자인(2), 휴먼인터페이스컴퓨터게임설계, 영상처리, 정보표준화, 내장형시스템설계, 설계패턴,빅데이터"
        );

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
