node('master') {
  stage('Poll') {
    checkout scm
     echo "Poll"
  }
  stage('Build & Unit test') {
    withMaven(maven: 'M3') {
        sh 'mvn clean verify test-compile surefire:test';
    }
    junit '**/target/surefire-reports/TEST-*.xml'
    archive 'target/*.jar'
    echo "Build & Unit test"
  }
  stage('Static Code Analysis') {
    withMaven(maven: 'M3') {
        sh 'mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectName=oven -Dsonar.projectKey=oven -Dsonar.projectVersion=$BUILD_NUMBER'
    }
    echo "Static Code Analysis"
  }
  stage('Integration Test') {
    withMaven(maven: 'M3') {
        sh 'mvn clean test-compile failsafe:integration-test';
    }
    junit '**/target/failsafe-reports/TEST-*.xml'
    archive 'target/*.jar'
    echo "Integration Test"
  }
}
node('docker_pt') {
  stage ('Start Tomcat'){
    sh '''cd /home/jenkins/tomcat/bin
    ./startup.sh''';
  }
  stage ('Deploy'){
    unstash 'binary'
    sh 'cp target/oven'
  }
}

