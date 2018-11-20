package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class computerCurriculum extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("1학년 1학기 \n" + "Communication in English,글쓰기, 스토리텔링콘텐츠제작실습, 일반물리학(1), 일반물리실험(1), 선형대수학, 창의적설계, 기초컴퓨터프로그래밍\n"+
        "\n1학년 2학기 \n" + "창의와소통, 회계와사회, 스토리텔링의 이해, ICT와경영, 이산수학, 논리회로,프로그래밍 \n" +
                "\n2학년 1학기 \n" + "한국사, 게임아트와 내러티브, 미적분학, 자료구조, 계산미학,컴퓨터시스템및어셈블리언어,소프트웨어프로젝트\n" +
                "\n2학년 2학기 \n" + "ACT, 확률및통계, 오토마타와형식언어, 자료구조설계,객체지향프로그래밍설계, 컴퓨터구조\n" +
                "\n3학년 1학기 \n" + "공업수학, 수치해석, 알고리즘, 독립학습(1), 휴먼ICT소프트웨어공학, 운영체제, 산업체특강(1),컴파일러\n" +
                "\n3학년 2학기 \n" + "LINUX시스템,데이터베이스설계,휴먼ICT인공지능,독립학습(2), 휴먼인터페이스미디어,산업체특강(2), 프로그래밍언어론, 컴퓨터통신, 모바일앱개발, 웹프로그래밍\n" +
                "\n4학년 1학기 \n" + "캡스톤디자인(1), DB시스템및프로그래밍, 휴먼미디어멀티코어컴퓨팅,독립학습(3),컨텐츠기반무선이동통신,네트워크응용설계, 정보보호이론,자동인식,패턴인식,컴퓨터그래픽스\n" +
                "\n4학년 2학기 \n" + "캡스톤디자인(2), 휴먼인터페이스영상, 빅데이터, 독립학습(4), 설계패턴, 내장형시스템설계, 산업경영컴퓨터보안, 영상처리, 휴먼인터페이스컴퓨터게임설계, 정보표준화"
        );

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
