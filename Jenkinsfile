pipeline {
    agent none
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                echo 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'mvn test'
            }
        }
        stage('Deliver') { 
            steps {
                echo './jenkins/scripts/deliver.sh' 
            }
        }
    }
}
