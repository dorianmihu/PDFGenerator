pipeline {
    agent { docker 'maven:3.3.9' }
    stages {
        stage('build') {
            steps {
                sh 'sudo mvn clean install dockerfile:build'
            }
        }
    }
}
