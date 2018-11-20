package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class softwareIntroduction extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("설립 목적 및 비전\n" + "최근 모바일 기기, IoT, 빅데이터, 기계학습, 4차산업혁명등이 화두가 되면서 IT 빅뱅이 진행되고 있습니다. 이에 따라, 고수준의 SW 개발 능력이 국가 및 기업경쟁력 강화에 중요한 요인으로 등장하고 있습니다. 이는 SW가 앞으로 전세계의 IT 제조업은 물론 비 IT 제조업의 경쟁력을 좌우하는 시대가 도래하고 있다는 의미입니다. 실제 항공기 값의 절반이 소프트웨어입니다. 수년 뒤에 SW는 제품가격의 90%이상을 차지할 것이라고 합니다. 정부에서도 이에 발맞춰 SW를 창조경제의 핵심 산업 군으로 꼽고 앞으로 소프트웨어 인력 22만 대군을 양병할 계획이라고 밝힌 바 있습니다. 중앙대학교는 이러한 시대조류에 맞추어 2015년부터 컴퓨터공학부내에 소프트웨어 전공을 신설하고 우수한 신입생을 유치하여 왔습니다. 기업체의 수요가 반영된 커리큘럼과 산학협력을 통해서 학생들이 리더급 글로벌 SW엔지니어 및 연구자로 양성될 수 있도록 국내외 초일류 학과로 키울 예정입니다.\n" +
        "\n모집정원\n" + "40명\n" + "\n교육 방식\n" + "   수요기반의 산학협력 중심 커리큘럼을 통해 기업에 특화된 소프트웨어 전공 집중 교육\n" +
                "   기업체의 수요가 반영된 커리큘럼 수립\n" +
                "   - 1,2 학년: 전공기본역량 강화\n" +
                "   - 3,4 학년: 기업에 특화된 프로그램 운영 (산업체 전문가 출강, 멘토링, 기업 인턴쉽 등)\n" +
                "\n지원사업\n" + "소프트웨어중심의 Global-Oriented 컴퓨터공학부 사업\n" +
        "\n서울어코드 학부 교육 지원사업\n" + "미래창조과학부가 IT선진화교육모델을 구축하기 위해 예산을 지원하는 사업으로, 중앙대학교 컴퓨터공학부는 2012년 서울어코드활성화사업 수행대학으로 선정되어 2019년까지 7년간 총 약 40억원의 사업비를 지원받을 예정이다. 현재 2013년에는 지원사업에서 컴퓨터공학부 학부교육에 4억 4천만원을 지원하고 있다.(아래 서울어코드지원 내역 상세표 참조)\n" +
                "\n" + "프랑스 대학 교환학생 45명 본 학부 수강\n" + "2014-2학기에는 EPITECH 대학(프랑스) 교환학생 46명이 재학(3,4학년) 중이며 재학생들과 함께 수업 및 프로젝트를 수행하고 있다. 내년에는50여명으로 인원이 더 증원되어 수학예정이다.\n" +
                "\n" + "삼성전자 장학생 선발 및 취업 특전\n" + "삼성전자와 맞춤형 인재 육성을 위한 SST(Samsung Software Track), SCSC(Samsung Convergence Software Course) 운영 협약을 맺고 삼성전자로부터 교과과정개발 및 기자재구입 등의 지원과, 장학생 및 인턴 선발 등 취업 특전이 이루어지고 있다.\n" +
                "\n" + "컴퓨터공학부 해외 교환학생 항공료 지원\n" + "컴퓨터공학부 학생이 국제 교환/방문학생으로 선발될 경우 왕복 항공료를 지원하고 있다.\n" +
                "\n" + "지원 내역\n" + "서울어코드 지원내역\n" + " 다양한 외국어 교육지원 혜택\n" +
                " 다양한 장학금 혜택\n" +
                " 다양한 자격증 취득 지원 혜택\n" +
                " 다양한 해외 인턴쉽 및 교육 프로그램 지원") ;

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
