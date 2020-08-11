# 6장
# 소나큐브와 아티팩토리

## 소나큐브
> 소나큐브란?
* 20개 이상의 프로그래밍 언어에서 버그, 코드 스멜, 보안 취약점을 발견할 목적으로 정적 코드 분석으로 자동 리뷰를 수행하기 위한 지속적인 코드 품질 검사용 오픈 소스 플랫폼
* 코드가 커밋될 때마다 해당 코드에 대해 코드 분석이 수행
* 총 7 종류의 코드 품질을 관리하는 파라미터 존재
    * 아키텍처 및 디자인
    * 중복
    * 단위 테스트
    * 복잡성
    * 잠재적 버그
    * 코딩규칙
    * 주석

실습 시작!!

1. docker로 소나큐브 설치
```java
docker pull sonarqube 
docker images
docker run -d --name sonarqube -p 9000:9000 sonarqube
"http://localhost:9000/" // id : admin, ps : admin
```

2. 빌드브레이커 플러그인 설치
https://github.com/adnovum/sonar-build-breaker/releases

/opt/sonarqube/extensions/plugins


wget https://github.com/adnovum/sonar-build-breaker/releases/download/2.3.1/sonar-build-breaker-plugin-2.3.1.347.jar

sudo docker restart sonarqube

3. 퀄리티 게이트 생성

