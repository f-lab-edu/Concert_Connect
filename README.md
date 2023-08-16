# :pushpin: Concert Connect

> Concert Connect 서비스의 기능,API구현
  

</br>

## Goals
1. 객체 지향 설계를 적용하여 CleanCode를 목표로 유지보수가 용이한 코드 구현

중복되는 코드들, 불필요하게 수정이 일어날 코드들을 최소화해 확장이 용이하게 노력합니다.
SOLID 원칙과 디자인패턴의 이해를 바탕을 하여 최대한 도메인 주도 설계를 하기 위해 노력합니다.

- 최소 50.000명 이상이 접속해도 거뜬히 처리 할 수 있는 서비스 구현
- 한줄 한줄이 의미 있는 코드 작성
</br>

## 제작 기간 & 참여 인원
- 2023년 06월 20일 ~ 
- 개인 프로젝트

</br>

## Git Branch Strategy
개인 프로젝트 이기 때문에 GitHub Flow를 선택했다.

<br>

![Github_Flow](https://github.com/HeesuYeo/Concert_Connect/assets/118063903/4dda59db-a3f4-49ff-a055-6910fb5bf04c)

<br>

Main Branch 항상 최신의 상태이며, stable 상태로 Product에 배포되는 브랜치이다.<br>
<br>
Main Branch외에 다른 Branch는 목적과 기능에 따라 Branch를 생성해서 목적의 기능이 끝났을때 Main 브랜치로 Merge한다.<br>
<br>
항상 Main 브랜치에 Merge하기전 PR(Pull Request)을 통해 Approve 받고 Merge한다.<br>

</br>

## Git Commit Conventions
<details>
<summary><b>펼치기</b></summary>
<div markdown="1">

### Git Commit Conventions 구조

EX)  <br>
Feat: "로그인 기능 구현"

OAUTH2 기능 추가구현

Resolves: #123 <br>
Ref: #456 <br>
Related to: #48, #45

### Commit Type
타입은 태그와 제목으로 구성, 태그는 영어로 쓰되 첫 문자는 대문자. <br>

EX)  <br>

Feat : 새로운 기능 추가 <br>
Fix : 버그 수정 <br>
Docs : 문서 수정 <br>
Style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우 <br>
Refactor : 코드 리펙토링 <br>
Test : 테스트 코드, 리펙토링 테스트 코드 추가 <br>
Chore : 빌드 업무 수정, 패키지 매니저 수정 <br>

###  Subject
제목은 최대 50글자가 넘지 않도록 하고 마침표 및 특수기호는 사용하지 않는다. <br>
영문으로 표기하는 경우 동사(원형)를 가장 앞에 두고 첫 글자는 대문자로 표기한다.(과거 시제를 사용하지 않는다.) <br>
제목은 개조식 구문으로 작성한다. --> 완전한 서술형 문장이 아니라, 간결하고 요점적인 서술을 의미. <br>

EX)  <br>
* Fixed --> Fix
* Added --> Add
* Modified --> Modify

###  Body
본문은 다음의 규칙을 지킨다. <br>

본문은 한 줄 당 72자 내로 작성한다. <br>
본문 내용은 양에 구애받지 않고 최대한 상세히 작성한다. <br>
본문 내용은 어떻게 변경했는지 보다 무엇을 변경했는지 또는 왜 변경했는지를 설명한다. <br>

 ### footer
꼬릿말은 다음의 규칙을 지킨다. <br>

꼬리말은 optional이고 이슈 트래커 ID를 작성한다. <br>
꼬리말은 "유형: #이슈 번호" 형식으로 사용한다. <br>
여러 개의 이슈 번호를 적을 때는 쉼표(,)로 구분한다. <br>
이슈 트래커 유형은 다음 중 하나를 사용한다. <br>

- Fixes: 이슈 수정중 (아직 해결되지 않은 경우)
- Resolves: 이슈를 해결했을 때 사용
- Ref: 참고할 이슈가 있을 때 사용
- Related to: 해당 커밋에 관련된 이슈번호 (아직 해결되지 않은 경우)
ex) Fixes: #45 Related to: #34, #23 <br>


</div>
</details>


## 사용 기술
#### `Stack`
  - Java Jdk 11
  - Mysql
  - Jenkins
  - Docker
  - Maven
  - JPA
</br>

## 기능

전제적인 프론트앤드 UI는 프로토 타이핑으로 구현하였다 (수정예정)
<details>
<summary><b>기능 설명 펼치기</b></summary>
<div markdown="1">



</div>
</details>

</br>

 



 
