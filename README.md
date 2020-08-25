# practice-board

[![GitHub release](https://img.shields.io/badge/version-1.0.0-blue)](https://GitHub.com/Naereen/StrapDown.js/releases/)[![License: MIT](https://img.shields.io/badge/License-MIT-green)](https://opensource.org/licenses/MIT)

해당 프로젝트는 웹 서비스 전반을 이해하기 위한 목적으로 만든 연습용 개인 프로젝트입니다.

프로젝트 배포용 웹 서버는 AWS EC2(Linux), 데이터베이스는 AWS RDS(MariaDB)를 이용했습니다.

<br />

## About the Project

먼저, 프로젝트의 기본 환경입니다. (기타 라이브러리 사용여부는 build.gradle을 확인하세요 !)

- Java 8
- Spring Boot 2.1.x
- Spring Data JPA
- OAuth 2.0
- Gradle

<br />

다음으로 역할에 따른 계층은 아래와 같이 잡았고, 패키지 구조는 트랜잭션 스크립트 모델, 도메인 모델 두 가지 중 도메인 모델을 적용해보았습니다. (처음 적용해보는거라 모델 간 경계가 불분명할 수 있습니다.)

<img src="https://user-images.githubusercontent.com/31037742/87685638-98d48680-c7be-11ea-807a-cb46f5b7a346.png" width="60%" height="60%">

<br />

다음으로는 제가 개발 시 이용했던 무중단 배포 구조입니다. (참고만 하세요 !)

<img src="https://user-images.githubusercontent.com/31037742/89186370-a3708780-d5d6-11ea-82ba-4f988e84fbdf.png" width="90%" height="90%">

<br />

## Getting Started

```git clone https://github.com/seung7642/practice-board.git```

<br />

## Function

해당 프로젝트에서 구현된 기능별 목록은 아래와 같습니다.

- 게시판
  - 게시글 CRUD
  - 파일 업로드/다운로드
- 회원 관리
  - OAuth 2.0 로그인

<br />

## License

This is released under the MIT license. See [LICENSE](https://github.com/1ilsang/java-mvc-chatting/blob/master/LICENSE) for details.