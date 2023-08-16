package io.concertconnect.concerteventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class  ConcertEventManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertEventManagementApplication.class, args);
    }

    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }
}


/*
#Web service
네트워크 상에서 서로 다른 종류의 컴퓨터들 간에 상호작용하기 위한 소프트웨어 시스템

#web Application
온라인 뱅킹 , 포털사이트 같은 웹서비스

REST(REpresentational State Transfer)
Resourec의 Representation에 의한 상태전달
HTTP method룰 통해 Resource를 처리하기 위한 아키텍쳐

#RESTful api
-REST API를 제공하는 웹서비스

REST
HTTP
HTTP Methods(GET,PUT,POST,DELETE)
HTTP Status Codes(200,404 ...)

#
Resource
- URI
- xml, html, json
------------------------------------------------------------------------------------------------------------------------
Spring Boot
간단함 stater
Auto Config
Component Scan

RESTful API설계
이벤트관리
이벤트 생성, 조회, 삭제, 업데이트, 검색 / 이벤트 상세보기
일대 다 관계

모든 이벤트 목록   =>  /event              => get     (전체 이벤트 보기)
이벤트 생성       =>  /event              => post    (이벤트 생성)
이벤트 상세보기    =>  /event/{id}         => get     (이벤트 상세 페이지)  {가변데이터}
이벤트 삭제       =>  /event/{id}         => delete  (이벤트 삭제)        {가변데이터}
이벤트 수정       =>  /event/{id}/modify  => PUT     (이벤트 수정)        {가변데이터}

단점 Http가 지원하는 모든 메서드를 지원하지않고 밑에 4개만지원한다
Get은 select
Post는 insert
Put update
Delete delete
달라질수있지만 이와 같은 기능으로 사용한다
----------------------------------------------------------------------
dispatcher(보내다) servlet

클라이언트의 모든 요청을 한곳으로 받아서 처리
요청에 맞는 Handler로 요청을 전달
Handler의 실행결과를 Htttp response 형태로 만들어서 반환

dispatcher servlet -> handler mapping -> Controller -> modeandview - > view resolver(페이지생성 + 모델포함) -> view

RestController
@Controller + @ResponseBody
view를 갖지않는 REST Data(XML/JSON) 를 반환
---------------------------------------------------------------------------
Path variable
가변데이터의 uri를 사용하는것
@Pathvariable()
--------------------------------------------------------------------------
JPA
자바 ORM(객체 관계 매핑) 기술에 대한 API표준 명세
자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
EntityManager를 통해 CRUD 처리

Hibernate (현재도 ORM을 위한 작업 중에서 가장 많이 선택되는 옵션중 하나)
JPA의 구현체, 인터페이스를 직접 구현한 라이브러리
생산성 유지보수 비종속성
쿼리문을 모른다해도 편리하게 ORM을 할 수 있음 하지만 쿼리문을 제어할수없기때문에 성능 문제에서 단점
유지보수가 쉽고 관계형 데이터 베이스의 관리시스템에 대한 종속성을 줄일수있기때문에 직관적 비즈니스 로직에 집중할수있음

Spring Data JPA
Spring Module (jpa를 쉽고 편하게 사용하기위한 라이브러리 같은)
JPA를 추상화 한 Repository 인터 페이스 제공

*/
/*
TDD란 무엇인가?
regression(회귀버그) 을 막기위해 tdd
유연한 설계를 얻기위해 tdd

test자동화
비결정적인 테스트 : 테스트를 위해 쓰는 비용이 그냥 개발하는 비용보다 더많이 드는경우

tdd를 하기전에 테스트가 가능한 구조에 테스트를 넣어야 한다

왜 필요한가
레거시코드 :  테스트가 없는 코드
regression : 잘돌아가던 코드가 이번 베포로 인해 동작하지 않는 상황
한두번 경험하게되면 배포가 무서워진다
테스트를 도입하면 생산성이 올라간다 regression에 따른 심리적 두려움이 줄어들기 때문
좋은 아키텍쳐란 ? solid를 지키는 코드가 좋은 아키텍쳐일 확룰이 늘어난다
s (srp): 단일책임원칙. 하나의 클래스는 하나의 책임만을 가져야 한다
테스크코드가 많아지게 되면 무슨 목적의 클래스 인지 눈에 안들어오기 때문에 분할하게 되는 지점이 생김
o (ocp) : 개방 폐쇄 원칙. 테스트 컴포넌트, 프로덕션 컴퍼넌트를 나눠
작업하게 됨에 따라 컴포넌트를 자유자제로 탈부착이 가능하게 개발하게됨
l: (lsp) : 라스코프 치환원칙 부모클래스의 역할을 자식클래스가 대체할수있어야한다는 원칙 테스크가 판단해줌
i () : 테스트는 그 자체로 인터페이스를 사용할수있는 환경이여서 블필요한 의존성을 확인할수있다
d () : 의존성 역전 가짜 객체를 이용해서 테스트를 작성 하려면 의존성이 역전 되어야 하는 하는 경우가 생김
테스트는 크게 2개의 관점으로 바라봐야하는데 품질보증(회귀버그방지)과 설계(회귀버그방지, 설계를위한도구)의 관점으로 봐야한다
test의 필요성 회귀버그 방지, 좋은설계 solid와의 연관관계

테스트의 3분류
e2e 5%              API테스트                  대형 : 멀티서버 End to end 가능

integration 15%     통합테스트 통합이란?         중형 : 서버한대 멀티 쓰래드, 프로세스를 사용 할 수 있는 테스트 h2와 같은 테스트 DB를 사용 할 수 있다

unit 80%            단위테스트 단위의 기준은?     소형 : 단일서버 단일 프로세스 단일스레드 Disk I/O( 디스크에서 읽기 또는 쓰기 요청 처리하는 시간),
Blocking call(해당 작업이 완료될 때까지 프로그램이 블로킹되어(blocked) 다음 코드로 진행되지 않는 경우를 의미합니다.)이 있으면 안됨 thread.sleep()있으면 안됨

sut (system under test) : 테스트 하려는 대상
bdd (Behaviour driven development) : given(상황이 주어졌을떄) when(이행동을하면) than(결과가 이렇더라)
상호작용테스트 (Interaction test) : 대상함수의 구현을 호출하지 않으면서 그 함수가 어떻게 호출되는지 검증하는 기법
상태기반검증 (어떤값을 시스템에 넣었을때 나오는 결과 값을 기대값과 비교하는 방식)
행위기반검증 (상호 작용 테스트와 비슷 어떤값을 시스템에 넣었을때 협력객체의 어떤 메서드를 실행하는가)

행위기반 검증이 bdd는아님

테스트 픽스쳐 : 테스트에 필요한 자원을 생성하는것

비욘세 규칙 : 유지하고 싶은 상태나 정책이있다면 알아서 테스트를 만들어야 한다
유지하고 싶은 상태가 있다면 전부 테스트로 작성해라 그게 정책이 된다

Testabillity : 테스트 가능성. 스포트웨어가 테스트 가능한 구조인가?

test double : 테스트 대역

dummy : 아무런동작 x 그저코드가 정상적으로 돌아가기위해 전달하는 객체

fake : 로컬에서 사용하거나 테스트에서 사용하기 위해 만들어진 가짜객체 자체적인 로직이 있다는게 특징

stub : 미리 준비된값을 출력하는 객체 외부 연동하는 컴포넌트들에 주로 사용

mock : 메소드 호출을 확인하기위한 객체, 자가 검증 능력을 갖춤 사실상 테스트 더블과 동일한 의미로 사용됨

spy : 메소드 호출을 전부 기록했다가 나중에 확인하기 위한 객체 다른정보도 기록해 사용하기도함

의존성

의존성 테스트



testability

대역










*/
