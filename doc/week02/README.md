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

- 실습 
1. Periodic 설치
2. Periodic Manager 이동
3. 	
 - Temporary Directory : 임시 경로 ex) boot target 경로
 - Backup schedule (cron) : 백업 스케줄
 cron 사진
 - Maximum backups in location : 백업 개수
 - Store no older than (days) : 보관 날짜
 - File Management Strategy : ConfigOnly는 젠킨스 홈의 xml과 모든 Job의 xml, 즉 설정만 저장한다. 반면 FullBackup은 전부 저장한다.
 - Backup Location : 백업 공간 설정
  먼저 디렉토리를 생성하자. sudo docker exec -it jenkins_prod mkdir /var/jenkins_home/backup
 4. Backup Now! 눌러 백업해보자.
 
 


