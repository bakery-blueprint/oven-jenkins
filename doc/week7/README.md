# 젠킨스를 이용한 CD

## 브랜치 전략

CI가 통합, 개발 기능 브랜치에서 수행되는 반면에 CD 는 통합과 릴리스 브랜치에서만 수행된다.

```
릴리스 브랜치 
* 테스트가 완료된 코드가 마스터 브랜치에서 프로덕션 브랜치로 배포된 후 생성됨
* 해당 릴리스의 버그를 수정하기 쉽게 하기 위해 릴리스 브랜치를 둠
```


## CD 파이프라인
324p CI 파이프라인 + 6, 7, 8 번




## 성능 측정 머신 만들기
```

// ubuntu 이미지 및 컨테이너 실행
sudo docker run -i -t ubuntu /bin/bash 

// jenkins 유저 생성
# adduser jenkins
# su jenkins
$ exit

//  OpenSHH 설치
# apt-get update
# apt-get install openssh-server
# mkdir /var/run/sshd

// 자바 설치 
# apt-get update
# apt-get install default-jre

// 톰캣 설치 (war 배포시 사용, boot 사용시 필수는 아님)
# cd /tmp
# wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.11/bin/apache-tomcat-8.5.11.tar.gz
# su jenkins
$ mkdir /home/jenkins/tomcat
$ tar xzvf apache-tomcat-8*tar.gz -C /home/jenkins/tomcat --strip-component=1
$ exit


// jmiter 설치
# cd /tmp
# wget https://archive.apache.org/dist/jmeter/binaries/apache-jmeter-3.1.tgz
# mkdir /opt/jmeter
# tar xzvf apache-jmeter-3*.tgz -C /opt/jmeter/ --strip-components=1
# chown -R jenkins:jenkins /opt/jmeter/
# chmod -R 777 /opt/jmeter/


// curl 설치
# apt-get install curl
# exit


// 컨테이너 아이디 기록!!
$ sudo docker ps -a 
$ sudo docker commit <docker container id> performance-test-agent-0.1


```

##  테스트 케이스 만들기
```
// 위 머신 만들기에서 jmiter 다운까지 동일
// 설치 후 
bin 폴더로 가서 ./jmeter  실행
```
