node('master') {
  stage('Poll') {
    checkout scm
     echo "Poll"
  }
  stage('Build & Unit test') {
    withMaven(maven: 'M3') {
        sh 'mvn clean verify -DskipIts=true';
    }
    junit '**/target/surefire-reports/TEST-*.xml'
    archive 'target/*.jar'
    echo "Build & Unit test"
  }
  stage('Static Code Analysis') {
    withMaven(maven: 'M3') {
        sh 'mvn clean verify sonar:sonar -Dsonar.host.url=http://192.168.25.61:9000 -Dsonar.projectName=oven -Dsonar.projectKey=oven -Dsonar.projectVersion=$BUILD_NUMBER'
    }
    echo "Static Code Analysis"
  }
  stage('Integration Test') {
    withMaven(maven: 'M3') {
        sh 'mvn clean verify -Dsurefire.skip=true';
    }
    junit '**/target/surefire-reports/TEST-*.xml'
    archive 'target/*.jar'
    echo "Integration Test"
  }
}


