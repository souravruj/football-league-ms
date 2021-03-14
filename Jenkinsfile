pipeline {
    agent any
    stages {
        stage('Check-out sources & clean') {
            steps {
                bat "git clone https://github.com/souravruj/football-league-ms.git"
                bat "mvn clean -f football-league-ms"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f football-league-ms"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f football-league-ms"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f football-league-ms"
            }
        }
    }
}