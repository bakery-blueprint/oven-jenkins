# 새로운 젠킨스

젠킨스 기능의 대부분은 플러그인에서 나온다. 

### 플러그인 설치, 업데이트, 삭제

Available 탭에서 설치, Installed 탭에서 업데이트, 삭제할 수 있다. 

- 수작업으로 젠킨스 플러그인 설치하기 (실습)
1. http://updates.jenkins-ci.org/download/plugins/
2. logstatsh 다운로드 
3. 플러그인에 의존성이 존재할 경우 다른 플러그인을 먼저 설치해야 한다. 
  
  
## 젠킨스 백업과 복원

사실 누군가 실수로 젠킨스 설정을 지웠다면, 신입일 경우 퇴사... 혹은 찍힌거다.

누군가라기 보다 자신이 실수로 지웠을 경우 살려야 한다. (사실 실수로 지울 가능성은 제로다.)

젠킨스 설정 자체가 설치 환경에 따라 경로가 정해져 있다. (리눅스의 경우 /var/lib/jenkins)


### Periodic Backup 플러그인

Periodic.. 주기적인이라는 뜻

- *실습* 
1. Periodic 설치
2. Periodic Manager 이동
3. 환경설정	
 - Temporary Directory : 임시 경로 ex) boot target 경로
 - Backup schedule (cron) : 백업 
 - Maximum backups in location : 백업 개수
 - Store no older than (days) : 보관 날짜
 - File Management Strategy : ConfigOnly는 젠킨스 홈의 xml과 모든 Job의 xml, 즉 설정만 저장한다. 반면 FullBackup은 전부 저장한다.
 - Backup Location : 백업 공간 설정
  먼저 디렉토리를 생성하자. sudo docker exec -it jenkins_prod mkdir /var/jenkins_home/backup
 4. Backup Now! 눌러 백업해보자. (시간이 상당히 오래걸림)
 5. System.log를 통해 확인하자.
 6. Configure System에서 설정으 변경하고 백업해보자.

### 젠킨스 업데이트

언제든지 새로운 버전으로 업데이트가 가능하다.

이하 생략

## 사용자 관리 

인증과 권한은 중요하다.

Configure Global Security Enable security을 끄면 url을 알고 있으면 젠킨스에 접속 가능

오히려 이부분이 좋을 수도 있다.

### 인증

- 서블릿 컨테이너 
tomcat-user.xml에 설정한 사용자가 접근 가능하다.

- 젠킨스 자체 DB
Allow users to sing up 옵션을 통해 스스로 가입가능

- LDAP
가장 많이 사용하는 방식 중 하나이다. 


- 실습 

젠킨스 자체 DB 실습을 진행한다. 

젠킨스 내부에 새로운 사용자 생성

1. Manage Users 

2. Create User

3. People 페이지 확인


### 권한

- Anyone can do anything
익명사용자, 사용자에게 어떤 권한권한 관련 제한하지 않는다. 

- Legacy mode

1.164 버전으로 동작한다. Admin 유저를 찾아 관리자 권한을 주고, 이외의 사용자는 익명 사용자로 취급된다. (구린 옵션)

- Logged-in users can do anything

로그인한 사용자는 관리자가 된다. default 옵션

Allow anonymous read access 익명 사용자에게 읽기 권한을 준다.

- Matrix-based security

각 사용자에게 권한 설정을 Custom하게 한다. 많이 사용하는 방식

젠킨스가 점점 커지고 사용자가 많아지면서 관리가 어렵기 때문에 Project-based를 사용하기도 한다.

- 실습 
Matrix-based security 생성한 User에게 부여해보자.



