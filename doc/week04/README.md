# 5장
## 분산 빌드
> * 젠킨스 노드 관리자 개요
> * 스탠드얼론 리눅스 머신에 젠킨스 슬레이브 설치
> * 스탠드얼론 윈도우 머신에 젠킨스 슬레이브 설치
> * 젠킨스 슬레이브를 on-demand로 생성하기 위한 도커 플러그인 설치 및 설정

### 분산 빌드와 테스트
방대한 양의 단위 테스트와 통합 테스트 스위트가 있는 상황을 상상해본다<br>
이를 작은 단위로 나눌 수 있다면 동시에 수행하는 것도 가능해 진다. <br>
병렬로 동시에 처리하려면 빌드와 테스트 머신이 여러개 필요하다.<br>
도커나 다른 기술을 이용해 이를 가능하게 한다면 남은 일은 이를 젠킨스 슬레이브로 만드는 것 뿐이다.

아래 그림은 젠킨스 파이프라인이 분산 빌드와 테스트를 이용해 빌드, 단위 테스트, 통합 테스트를 처리하는 방법을 보여준다. <br>
슬레이브 종류
- 단위테스트를 위한 스텐드얼론 젠킨스 슬레이브
- 통합 테스르를 위한 스탠드얼론 젠킨스 슬레이브

 ![oven](/doc/img/agent_distri_build_test1.jpg)
 
 단위 테스트 슬레이브
 1. 단위 테스트 (1-50)
 2. 단위 테스트 (50-100)
 3. 단위 테스트 (100-150)
 
 통합테스트 슬레이브
 1. 통합 테스트 (1-50)
 2. 통합 테스트 (50-100)
 
 젠킨스 슬레이브 에이전트는 **레이블**을 이용해 분류된다.
 
 도커를 이용해 on demand 젠킨스 슬레이블르 생성하는 것이 쉽고 유리하다. <br>
 젠킨스 슬레이브가 도커 이미지를 이용해 on demand로 생성된다.
 
 아래 그림에서 볼 수 있듯 도커 이미지는 아래 두가지가 있다.
 1. 빌드와 단위 테스트를 위한 도커 이미지
 2. 통합 테스트를 위한 도커 이미지
 
 ![oven](/doc/img/agent_distri_build_test2.jpg)
 
 ## 실습 시간
 ### 젠킨스 노드 관리 페이지
 <details><summary>생성 옵션</summary>
 
 Labels : 스페이스 (_)를 이용해 여러 개의 레이블을 추가할 수 있는데, 다음 화면과 같이 레이블을 이용해 특정 슬레이브에서 파이프라인을 실행할 수 있다.
 예제를 보면 maven-build-1 레이블을 추가해 이 젠킨스 슬레이브가 메이븐 프로젝트를 빌드할 수 있게 설정했다. 
 
 Usage
 - User this node as much as possible : 세상에 자비를 베풀어 모든자가 사용 가능하게 함
 - Only build jobs with label expressions matching this node : 프로젝트 설정에서 레이블이나 노드의 이름 조건에 부합하는 경우에만 허요앻줌
 
 Launch method
 - Launch agent via Java Web Start
 JNLP 파일이 에이전트 머신에 열려 있어 젠킨스 마스터에 TCP 연결이 되어야 한다.
 
 > **다운 로드**  
 <jenkins_server_url>/jnlpJars/agent.jar 
 ```
$ echo <secret_key> > secret-file
$ cat << EOF > start_agent.sh
#!/bin/bash
java -jar agent.jar -jnlpUrl https://jbuildsx.ahnlab.com/computer/test/slave-agent.jnlp -secret @secret-file -workDir "/jenkins" &
EOF
$ chmod +x start_agent.sh
$ ./start_agent.sh
```
 - Launch agent via execution of command on the master
 마스터에서 젠킨스 실행 명령을 받아 에이전트를 실행한다.
 마스터가 다른 머신에 SSH나 remote shell(RSH)을 통해 프로세스를 실행시킬 수 있는 경우 이를 사용
 - Launch agent via SSH
 secure SSH 연결을 통해 명령어를 보내 슬레이블르 시작시킨다.
 해당 머신에 로그인할 수 있는 계정이 있어야 한다. (루트권한 X)
 - Let Jenkins control this Windows slave as a Windows service
 윈도우에 있는 원격 관리 기능을 이용해 윈도우 슬레이브 시작
 
 Availability
- Keep this agent online as much as possible
- Take this agent online and offline at specific times
정기적인 빌드가 있을 경우 괜춘할듯(?)
- Take this agent online when in demand, and offline when idle
조건을 만족할 경우 젠킨스가 해당 에이전트를 온라인 상태로 만든다.
조건은 빌드가 in demand delay에 정의된 시간만큼 큐에 존재하게 되는 경우이다
- 그리고 에이전트는 레이블에 의해 매칭될 경우에서 실행됨
이 에이전트는 다음과 같은 겨웅에 오프라인으로 변경됨
1. 해당 에이전트에서 실행되는 빌드가 더이상 존재하지 않을 경우
2. 해당 에이전트가 Idle delay에 명시된 시간만큼 유휴 상태로 존재할 경우 
</details>

### 젠킨스 슬레이브 추가하기 : 도커 컨테이너
도커 플러그인을 설치 및 설정해 CI 파이프라인에서 젠킨스 슬레이브 (도커 컨테이너)를 On demand로 생성하는 방법을 알아본다.<br>
도커 컨테이너는 CI 파이프라인에 의해 시작되고, 빌드가 완료된 후 제거된다.<br>
자세한건 다음 장에서 (^^ 후니에게)

> 테스트 전제 조건
> 1. 인터넷 연결
> 2. 깃허브 플러그인을 통해 깃 허브에 연결 가능
> 3. 젠킨스 서버에 자바, 깃, 메이븐도 설정해야 함
> 4. 도커 서버

도커 원격 API 활성화

젠킨스는 도커 플러그인을 통해 도커 원격 API를 사용해 도커 서버와 통신할 수 있다.<br> 
도커 원격 API는 외부 애플리케이션이 REST API를 이용해 도커 서버와 통신할 수 있게 해준다.<br>
또한 도커 원격 API는 도커 서버 안에 실행되고 있는 컨테이너의 정볼르 알기 위해서도 사용된다.

**docker.conf 파일 수정하기**<br>
젠킨스가 도커 호스트와 통신하려면 이 설정이 매우 중요함
 ```
## 도커 서버를 유닉스 소켓과 TCP 포트 4243.0.0.0.0에 연결시킨다
## 이를 통해 도커 엔진이 외부에서 접속 가능해짐
## 젠킨스 서버에서만 도커 서버에 접속할 수 있게 하ㅏ려면 0.0.0.0을 젠킨스 서버 IP로 변경하면 된다.

## linux
$ sudo vi /etc/init/docker.conf

## mac
$ /etc/init.d/docker

## pre-start 스크립트 영역의 DOCKER_OPTS
DOCKER_OPTS='-H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock'

$ sudo service docker restart
$ curl -X GET http://<Docker server IP>:4243/images/json
```

**도커 플러그인 설치**

**도커 플러그인 설정**

```
$ sudo docker run -i -t ubuntu /bin/bash

## 이 곳은 우분투 컨테이너 안입니다.
# adduser jenkins
# cat /etc/passwd | tail -n 1
# apt-get update
# apt-get install openssh-server
# mkdir /var/run/sshd

# apt-get install git
# apt-get install openjdk-8-jdk
# apt-get install maven
# exit

## 이 곳은 컨테이너에서 벗어난 곳입니다.
$ sudo docker ps -a
$ sudo docker commit <CONTAINER ID> <new name for the container>
```

**젠킨스에 도커 설정 업데이트**
