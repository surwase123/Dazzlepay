pipeline{
    agent none
    stages{
        stage('gitclone'){
            agent any
            steps{
                git credentialsId: 'be0b456c-9bfc-4151-8cb1-09b94a0b907d', 
                    url: 'https://github.com/surwase123/Dazzlepay.git'
            }
        }
        
        stage('package'){
            agent any
            steps{
                sh 'mvn package'
            }
        
        }
     stage('Build Docker image'){
            agent any
     sh 'docker build -t ashok11/my-app:2.0.0 .'
       
            }
        }
    }
}
