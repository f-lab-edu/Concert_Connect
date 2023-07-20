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
