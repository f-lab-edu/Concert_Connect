# :pushpin: Concert Connect

> Concert Connect 서비스의 기능,API구현
  

</br>

## Goals
- 티켓 예매 사이트의 구현
- 이론적으로만 학습했던 객체지향 설계를 직접 해보며 경험
- 테스트 코드 커버리지 70% 이상 달성
- 최소 50.000명 이상이 접속해도 거뜬히 처리 할 수 있는 서비스 구현
- 한줄 한줄이 의미 있는 코드 작성
</br>

## 제작 기간 & 참여 인원
- 2023년 06월 20일 ~ 2023년 ??월 ??일
- 개인 프로젝트

</br>

## Git Branch Strategy

</br>

## Code Conventions

</br>

## ERD 
</br>

## 사용 기술
#### `Stack`
  - Java 11
  - Mysql 
</br>

## 기능
전제적인 UI는 프로토 타이핑으로 구현하였다 (수정예정)
<details>
<summary><b>기능 설명 펼치기</b></summary>
<div markdown="1">

### 첫페이지
<img src="https://user-images.githubusercontent.com/118063903/216823650-7726080c-dfad-48a0-95e3-63fe29381ae2.png" width="300" height="500"/><br>
사용자는 포장 또는 매장이용 버튼을 선택할수있다

### 메뉴 선택
<img src="https://user-images.githubusercontent.com/118063903/216823985-ebc21d11-a76d-401c-a52d-34c3ead7b6ac.png" width="400" height="600"/>
<img src="https://user-images.githubusercontent.com/118063903/216824120-899b6bdf-3397-4095-974d-ccbe34120cbe.png" width="400" height="600"/><br>
메뉴 카테고리와 각 카테고리별 메뉴들을 배치

### 장바구니
<img src="https://user-images.githubusercontent.com/118063903/216824299-86867718-38fa-485f-b1da-89a1167d9b6f.png" width="400" height="600"/><br>
선택한 메뉴의 수량과 옵션을 선택하여 장바구니에 전달 , 세부옵션을 추가하여 장바구니에 추가<br>
<img src="https://user-images.githubusercontent.com/118063903/216824339-110e0391-8adc-40f0-bfbe-f6f2769d630c.png" width="400" height="600"/><br>
장바구니에 메뉴 추가 및 삭제 시 변동된 정보를 다시 출력<br>

### 결제
<img src="https://user-images.githubusercontent.com/118063903/216824592-9ec4e4aa-dd4d-405a-8ff4-cca025512acb.png" width="1000" height="650"/><br>
결제 후 결제내역을 Database에 전송

 ### 결제 후
<img src="https://user-images.githubusercontent.com/118063903/216824833-16ea8e53-535e-4104-bc47-7bc624d8fb35.png" width="1000" height="650"/><br>
결제 완료하면 대기번호 창이 뜨고 7초 후, 첫 화면으로 돌아감<br>

간단한 디자인 툴로 레퍼런스 이미지를 정하고 <br>
팀원들과 DB 정규화를 진행한후 Java Swing을 사용하여 <br>
JPanel,JFrame, JButton등의 위치를 잡고 각 버튼의 기능들과 
패널의 기능을 구현하였다


</div>
</details>

</br>

## 프로젝트 진행과정 & 트러블슈팅
깃브랜치전략, 코드컨벤션, Spring Security를 적용한 회원가입, RESTful API 


## 회고 / 느낀점







 



 
