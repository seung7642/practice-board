# practice-board

해당 프로젝트는 웹 서비스 전반을 이해하기 위한 목적으로 만든 연습용 개인 프로젝트입니다.

참고로, 프로젝트 배포용 웹 서버는 AWS EC2(Linux), 데이터베이스는 AWS RDS(MariaDB)를 이용했습니다.

<br />

## 프로젝트 환경

- Java8
- Spring Boot 2.1.x
- Gradle 5.6

<br />

## 프로젝트 구조

### 무중단 배포 구조

<img src="https://user-images.githubusercontent.com/31037742/89186370-a3708780-d5d6-11ea-82ba-4f988e84fbdf.png" width="90%" height="90%">

### 패키지 구조

<img src="https://user-images.githubusercontent.com/31037742/87685638-98d48680-c7be-11ea-807a-cb46f5b7a346.png" width="60%" height="60%">

<br />

## 기능

- 게시판
  - 게시글 조회/등록/수정/삭제
  - 파일 업로드/다운로드
- 회원
  - 구글/네이버 로그인
  - 로그인한 사용자 글 작성 권한
  - 본인 작성 글에 대한 권한 관리