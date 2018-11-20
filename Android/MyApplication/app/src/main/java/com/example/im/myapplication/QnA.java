package com.example.im.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QnA extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.text, null);
        TextView textView1 = (TextView) view.findViewById(R.id.textView) ;
        textView1.setText("입학\n" + "\nQ. 컴퓨터공학부 소프트웨어전공에서 원하는 학생상(인재상)은 무엇인가요?\n"
                +"A. 열정, 창의성과 논리적 사고방식, 성실성을 가진 인재를 원합니다. 좀 더 자세히 말하자면,\n" +
                "(i) 첫째, '열정'입니다. 무엇보다 소프트웨어 기술에 대한 관심과 열정, 그리고 배우고 실생활에 적용해 보고자 하는 의지가 가장 중요합니다.\n" +
                "(ii) 둘째, '창의성'과 '논리적 사고방식'입니다. 상상속의 아이디어를 소프트웨어로 실현하는 경우가 많기 때문에 제조업보다 훨씬 더 창의성과 독창성이 중요합니다.\n" +
                "그러나, 소프트웨어는 엄밀한 논리의 흐름 체계 안에서만 동작하기 때문에 창의성과 동시에 엄밀한 논리적 사고방식이 요구됩니다.\n" +
                "(iii) 셋째, '성실성'입니다. 소프트웨어 분야는 첨단 학문으로 새로운 이론과 기술을 지속적으로 익히고 적용하기 위한 성실함이 요구됩니다.\n" +
                "\nQ. 소프트웨어전공에 지원하고 싶어하는 학생들에게 주는 Tip이 있다면 무엇인가요?\n"
                +"A. 소프트웨어 분야에 대한 관심과 열정 그리고 의지가 강한 학생들이 본 전공에서 성공가능성이 높다고 봅니다.\n" +
                "단순히 수능점수에 맞추어 지원하거나, 본 소프트웨어 전공에서 제공하는 장학금, 취업기회 등의 혜택만을 기대하고 지원하는 것은 본인의 인생을 전체적으로 볼 때 안좋은 결과로 이어질 수 있으니, 지원할 때 참고하여야 합니다.\n" +
                "\nQ. 어떤 장점/경력/특기를 가진 학생들이 지원하는 것이 좋을까요?\n"
                +"A. 본 전공은 특출한 IT영재 뿐만 아니라 학생의 잠재력을 끌어올려 차세대 소프트웨어 리더로 길러내는데 역량을 집중하고 있으므로, 고등학교 과정을 우수하게 이수한 학생 중 기초 수학과 소프트웨어 분야에 대한 열정이 강한 학생이 지원하는 것이 좋습니다.\n" +
                "수학은 소프트웨어 개발에 논리적으로 판단하는 기준이 될 수 있어 매우 중시됩니다.\n" +
                "\n" +
                "또한, 어려서부터 컴퓨터 프로그래밍과 소프트웨어 개발에 재능을 가지고 꾸준히 연습했거나 정보올림피아드와 같은 대회에서 입상 경력이 있는 IT영재들이 지원하면 더욱 좋을 것입니다.\n" +
                "무엇보다도 프로그래밍은 마치 생활계획표를 작성하는 것과 매우 흡사합니다.\n" +
                "어떤 절차대로 수행하고 또한 새로운 조건을 만족할 때 다른 절차를 수행하는 그러한 생활계획표를 잘 짜는 학생들이라면 누구다 프로그래밍 능력이 있다고 생각하니 한번 도전해 보십시오.\n"
                +"\nQ. 평소에 어떤 분야에 관심을 두는 것이 소프트웨어전공을 이수하는 데에 도움이 될까요?\n"
                +"A. 아무래도 소프트웨어전공에서 배우는 내용들이 궁극적으로는 프로그램을 잘 짜보자는 목적을 가지는 면이 있으므로, 기본적으로 프로그래밍을 잘 해야 전공 과목을 이수하는데 도움이 됩니다.\n" +
                "\n" +
                "저희 전공프로그램에 들어오시면 프로그래밍 능력을 기초부터 탄탄히 공부할 수 있습니다.\n" +
                "따라서, 자유로운 상상력을 바탕으로 평소에 프로그래밍 연습을 많이 해보는 것을 추천합니다.\n" +
                "또한, 융복합 기술이 강조되는 시대이므로 인간, 사회, 예술 등의 분야에도 기본적인 관심을 가지는 것이 유리합니다.\n"
                +"\n\n전공\n" + "\nQ. 소프트웨어전공이란 무엇이며, 어떤 이유로 새롭게 출범했다고 생각하시나요?\n"
                +"A. 최근 스마트폰의 등장으로 개인이 이용가능한 정보기기의 종류, 또한 어플리케이션의 다양성 그리고 정보량 등으로 인해서 IT 빅뱅이 진행되고 있습니다. 이에 따라, 고수준의 SW 개발 능력이 국가 및 기업경쟁력 강화에 중요한 요인으로 등장하고 있습니다. 이는 SW가 앞으로 전세계의 IT 제조업은 물론 비 IT 제조업의 경쟁력을 좌우하는 시대가 도래하고 있다는 의미죠. 실제 항공기 값의 절반이 소프트웨어입니다. 수년 뒤에 SW는 제품가격의 90%이상을 차지할 것이라고 합니다. 정부에서도 이에 발맞춰 SW를 창조경제의 핵심 산업 군으로 꼽고 앞으로 소프트웨어 인력 22만 대군을 양병할 계획이라고 밝힌 바 있습니다.\n" +
                "\n" +
                "중앙대학교는 이러한 시대조류에 맞추어 2015년부터 컴퓨터공학부내에 소프트웨어 전공을 신설하고 우수한 신입생을 유치할 준비를 다하였습니다. 1,2학년때는 학교에서 40명 전원 전액장학금을 지급하고 3,4학년에는 대부분의 학생들이 삼성전자, LG전자와의 협약에 의해 전액장학금을 받고 해당기업에 취업을 보장받는 양해각서를 이미 체결/완료하였습니다. 여기서 대부분이라고 말씀드린 이유는 학생 중에 유학이나 고시준비 등 다른 진로를 선택하는 경우가 있기 때문이고 향후 삼성전자 LG전자 이외에 현대자동차, 대한항공 등 다양한 산업군의 대기업들과 비슷한 규모의 협약을 진행하여 국내외 초일류 학과로 키울 예정입니다.\n"
                +"\nQ. 기존의 컴퓨터공학부 전공과 차별화된 소프트웨어전공의 특징이 있다면 어떤 것이 있나요?\n"
                +"A. 우선 크게 Global Top 소프트웨어 인력을 키우기 위해 그 교과과정부터 다릅니다. 기업의 수요를 충분히 반영하여 전공과정을 신설합니다. 3학년 1학기까지는 공통 기초 핵심과목을 이수하고, 3학년 2학기부터는 기업이 요구하는 심화과정 이수하고, 4학년에는 1학기 동안 Co-Op 등을 통해 기업 개발 프로젝트에 직접 참여할 수 있는 기회를 제공하는 기업 밀착형과정입니다.\n" +
                "특히 기초 수학 및 과학 교육을 강화하여 급변하는 SW산업에서 지속 가능한 전문인력의 양성을 목표로 하며, 이와 동시에 산업계의 요구에 따른 트랙 제도를 운영하여 실제 문제의 해결능력을 겸비한 고급 소프트웨어 인력양성을 추구합니다.\n" +
                "\n" +
                "최근 각광받고 있는 MOOC 교육 시스템을 전통적 오프라인 교육과 융합하여 개인별 학습능력을 최대로 이끌어내는 혁신적 교수법을 도입할 예정입니다.\n" +
                "교수와 조교에 의해 진행되던 천편 일률적인 기존 실습 교육을 개혁하여 개인별로 오픈소스 프로젝트에 참여토록 하고 이를 체계적으로 감독 및 1:1 지도하여 글로벌 SW 기업에서 요구하는 수준 높은 개발능력을 갖도록 교육시킬 예정입니다.\n"
                +"\nQ. 소프트웨어전공에서는 어떤 내용을 공부하나요?(전반적인 내용, 특징적인 내용 위주)\n"
                +"A. 학년 1학기까지 기본 과목을 마스터하게 되어 있는데 SW기초수학1부터 SW기초수학4까지 4학기에 걸쳐 소프트웨어전공 전용 기초수학 교과목, SW 엔지니어에게 요구되는 인문사회예술 및 경영 전문교양 프로그램으로 소셜네트워크의이해, 특허의이해, IT경영, UX디자인, 산업디자인, 실전창업, 글로벌리더쉽 교과목, 프로그래밍 능력 강화로 1학년~3학년 동안 6학기에 걸쳐 체계적인 프로그래밍 역량 강화, 소프트웨어 핵심교과목으로 자료구조, 컴퓨터구조, 운영체제, 알고리즘, 프로그래밍언어론, 소프트웨어공학,컴퓨터통신, 데이터베이스 교과목이 있습니다.\n" +
                "\n" +
                "3학년 2학기부터 졸업까지는 SW 전공심화과정 운영하는데 모바일 및 웹 분야로 웹프로그래밍, 모바일앱개발, 오픈소스SW응용, 네트워크응용설계, 무선이동통신, 데이터마이닝 및 웹검색, 영상 처리 분야로 영상처리와인식, 사용자인터페이스, 컴퓨터그래픽스, 시스템 및 보안 분야로 내장형시스템설계, 암호학 및 정보보호, 컴퓨터보안, 리눅스시스템, 산학연계 분야: 산업체특강, 프로젝트멘토링, Co-Op프로젝트(기업인턴쉽,15학점), 프로젝트관리, 캡스톤프로젝트 I, II을 수강하실 수 있습니다.\n" +
                "\n" +
                "집중적 프로그래밍 교육을 위해 SW 프로그래밍 1과 2, 소프트웨어프로젝트, 객체지향프로그래밍설계, 웹 플랫폼 이해, HTML이해. JSP, AJAX, HTML5, 웹서버/클라이언트 프로그래밍 프로젝트 수행하는 웹프로그래밍, Android와 같은 모바일 운영체제상에서 응용 프로그램 개발 실습하는 모바일앱개발 교과목을 운영할 예정입니다.\n"
                +"\nQ. 소프트웨어전공의 특별한 프로그램이나 학생들의 활동이 있다면 소개해 주세요.\n"
                +"A. 저희 컴퓨터공학부는 소프트웨어 전공을 위해 지난 기간동안 다양한 학부 중심/연구중심을 위한 국책사업을 수주하였습니다. 정부에서 추진하는 4개 국책과제 글랜드 슬램을 달성하였습니다. \n" +
                "\n" +
                "CK-II라고하는 수도권 특성화 사업, 산학협력 선도를 위한 LINC사업, 학부 교육 내실화를 위한 ACE사업, 연구중심의 BK21Plus사업으로 매년 약 60억 이상을 지원받게 되었습니다. 또한 미래창조과학부의 IT선진화교육모델을 구축하기 위해 7년간 약 40억의 사업비를 지원받고 있습니다. 이러한 재원을 통하여 외국어교육 응시료, 수강료 지원, 전공자격증 및 교육지원, 동아리 지원, 멘토링/튜터링 지원, 경진대회지원, 해외 교환학생으로 진출하기 위한 지원 및 다양한 기자재 지원 등 다양한 교육 시스템을 확보하였습니다. 또한 프랑스 대학에서 저희 학부에 매년 약 50여명의 규모로 교환학생으로 오고 있는데 소프트웨어 전공으로 오시면 이들과 수업 및 다양한 프로젝트를 경험하시게 될 것입니다.\n"
                +"\n\n 진로\n" + "\nQ. 소프트웨어전공을 통해 어떤 방면으로 사회에 진출할 수 있을까요?(구체적인 분야를 나열, 설명해 주시면 될 것 같습니다.)\n"
                +"A. 소프트웨어는 현대인의 생활과 더 이상 분리될 수 없는 우리 생활에서 필수적인 요수가 되었습니다. 스마트폰은 어느덧 생활필수품이 되어 가고 있고 컴퓨터는 대부분의 업무에서 활용되고 있습니다.\n" +
                "\n" +
                "이러한 스마트폰과 컴퓨터에게 생명을 불어넣어 주는 것이 바로 소프트웨어입니다. 하지만 점차로 그 응용범위는 더욱 확대되어서 자동차, 비행기, 선박, 기차, TV, 냉장고 할 것 없이 우리 생활에 가까이 있는 많은 것들에서 소프트웨어가 차지하는 비중이 점차로 확대되고 있습니다. 즉, 앞으로 소프트웨어는 우리 생활과 매우 밀접하게 되어 가고 그 만큼 그 중요성은 커지게 될 것입니다.\n" +
                "\n" +
                "특별히 IT강국인 대한민국에서 소프트웨어 산업은 국가적인 전략산업이며 향후 국가경제를 지탱하는 주요 산업으로 뻗어가는 길목에 있습니다. 또한 소프트웨어 분야는 앞선 기술력과 혁신적인 아이디어만 있으면 누구나 창업을 할 수 있고 그 안에서 많은 성공사례들이 나오고 있습니다.\n" +
                "\n" +
                "그렇기 때문에 소프트웨어 기술을 가지고 있으면 대기업, 국가기관, 대학 가릴 것 없이 취업에 유리하고 또한 창업의 기회도 상대적으로 많이 가질 수 있으며 IT분야 뿐 아니라 다양한 산업분야에서 활용될 수 있기 때문에 앞으로 매우 전망이 좋은 전공이라고 볼 수 있습니다. 대표적으로 최근 사회적으로 관심을 받고 있는 보안이나 게임, 그리고 인공지능을 탑재한 자동차나 가전제품 또는 의료기기 개발에 모두 진출할 수 있습니다. 실례로 소프트웨어분야는 미국대학 졸업생 연봉순위 2위 및 중앙대 취업률 현황에서 항상 5위안에 드는 분야입니다.\n"
                +"\nQ. 그러한 방면들로 진출하기에 어떤 이점이 있나요?\n"
                +"A. 2014년도 미국의 포춘지가 선정한 가장 일하기 좋은 100대 기업에서 수없이 많은 기업들 중에서 1위는 구글이 차지했고 2위는 SAS라는 IT회사가 차지하였습니다. 두 기업 모두 소프트웨어 개발을 주로 하고 있습니다.\n" +
                "\n" +
                "국내에서도 취업선호도 1위의 업체는 삼성전자입니다. 이처럼 국내외를 막론하고 IT기업이 일하고 싶은 회사로 인식되는 이유 중 하나는 실력과 창의적인 아이디어만 있으면 성공할 수 있는 비전을 제시하고 있으며 우리 삶이 점차적으로 IT와 더욱 밀접하게 연관되어 가는 정보화 사회의 가속화를 들 수 있을 것 같습니다. 하지만 IT분야에서 성공하기 위해서는 탄탄한 기술력과 혁신적인 아이디어를 갖추어야 하는데 저희 중앙대학교 소프트웨어 전공에서는 이론과 실제를 겸비한 창의적인 인재를 양성하는 것을 목표로 하고 있습니다.\n" +
                "\n" +
                "그래서 교육과정을 통해서 기초적인 이론과 최신 기술을 갖추고 산업체연계 프로그램을 통해서 최신 산업체의 동향을 파악할 수 있도록 하고 있습니다. 따라서 소프트웨어전공의 교육과정을 통해서 많은 기업들이 선호하는 그런 창의적이고 혁신적인 실력 있는 인재를 양성하는 것을 목표로 하고 있습니다. 그리고 특별히 요즘과 같은 청년취업이 어려운 시기에 소프트웨어전공의 졸업생은 졸업 후 우리나라의 대표적인 기업인 삼성전자와 LG전자에 취업이 보장되는 특별한 프로그램을 운영하고 있습니다."
        );

        textView1.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }
}
