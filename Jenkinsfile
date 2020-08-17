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
        sh 'mvn clean verify sonar:sonar -Dsonar.host.url=192.168.25.61 -Dsonar.projectName=oven -Dsonar.projectKey=oven -Dsonar.projectVersion=$BUILD_NUMBER'
    }
    echo "Static Code Analysis"
  }
  stage('Integration Test') {
    withMaven(maven: 'M3') {
        sh 'mvn clean verify -Dsurefire.skip=true';
    }
    junit '**/target/failsafe-reports/TEST-*.xml'
    archive 'target/*.jar'
    echo "SIntegration Test"
  }
  stage('Publish'){
    def server = Artifactory.server 'Default Artifactory server'
    def uploadSpec = """ {
        "file" : [
            {
                "pattern": "target/oven.0.0.1-SNAPSHOT.jar"
                "target": "oven/${$BUILD_NUMBER}/"
                "props": "Integration-Tested=Yes;Performance-Tested=No"
            }
        ]
    }"""
    echo "Publish"
    server.upload(uploadSpec)
  }
}


