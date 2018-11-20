package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.NoCopySpan;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class introduction extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("학사과정 설립 직후부터 우리나라 컴퓨터산업의 중추적 인력을 배출해온 중앙대학교 소프트웨어학부는 각각 1977년과 1983년에 국내최초의 석사과정, 박사과정을 신설하여 컴퓨터공학 교수 및 연구 인력 양성에서도 선도적 역할을 하였습니다. 더욱 폭넓은 교육과 연구를 위해 1992년에는 컴퓨터공학과로, 2003년에는 컴퓨터공학부로 승격되었으며, 2015년에는 소프트웨어전공을 추가로 설치하여 기존 컴퓨터공학전공과 함께 두개의 전공과정을 가진 학부제를 시행하였고 2018년 소프트웨어학부로 개칭되었습니다. \n" +
                "\n" +
                "중앙대학교 소프트웨어학부는 중앙대학교 입학생 중 의학계열을 제외한 최우수 학생들로 구성되어 있고, 2018년 현재 전임 교수 25명, 별정제 교수 4명, 석좌교수 1명, 특임교수 3명, 겸임교수4명, 명예교수 4명과 대학원생 40여명, 학부생 500여명이 소속되어 있습니다. 컴퓨터공학은 컴퓨터를 이용해 정보를 처리하는 것과 관련된 제반사항을 연구하는 학문으로서, 과학적인 측면과 공학적인 측면이 모두 고려되는 복합적인 성격을 가지고 있습니다. 이러한 컴퓨터공학에서는 소프트웨어 및 하드웨어의 설계 및 개발, 프로그래밍을 위한 원리, 서로 다른 정보처리를 위한 효과적인 방법, 컴퓨터의 특성과 제약점을 규명할 수 있는 이론과 구현, 인접학문이나 실생활에서의 응용 등을 핵심적인 연구대상으로 삼고 있습니다. \n" +
                "\n" +
                "본 소프트웨어학부의 주요 연구분야로는 소프트웨어 공학, 시스템 소프트웨어, 인공지능, 데이터베이스, 컴퓨터 비전, 멀티미디어, 컴퓨터 그래픽스, 컴퓨터 네트워크, 전자상거래, 정보보안, 초고속 네트워크, 패턴인식, 유비쿼터스 컴퓨팅, 빅데이터 등이며, 국내외 연구기관 및 산업체와의 활발한 산·학·연 공동 연구체제를 구축하고 있습니다. 소프트웨어학부의 커리큘럼은 1, 2학년에 프로그래밍 및 컴퓨터 기본구조 등을 위주로 공부하고 3, 4학년에서는 알고리즘, 소프트웨어 공학, 운영체제, 인공지능, 데이터베이스, 모바일 앱, 컴퓨터 그래픽스, 인터넷 및 컴퓨터 네트워크, 컴퓨터 보안, 빅데이터 , 임베디드 시스템, 영상처리 등의 전문분야를 공부합니다. 우리 소프트웨어학부의 커리큘럼은 이론적 기반과 실용적 기술을 겸비하도록 구성되어 있으며, 졸업생들의 관련 산업분야 취업과 대학원 진학 실적이 우수합니다. \n" +
                "\n" +
                "중앙대학교 소프트웨어학부는 컴퓨터공학 이론과 기술을 세계적 수준에서 연구하며 산업계가 요구하는 실전적 교육 체계를 갖추고 있습니다. 이를 통해 정보화 사회에서 중추적 역할을 수행할 전문적이고 창조적인 능력을 갖춘 인재들을 양성함으로써 국가와 인류의 번영에 기여함을 목적으로 합니다.") ;

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}