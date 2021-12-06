pipeline{
    agent any
    stages{
        stage('gitclone'){
            agent any
            steps{
                git credentialsId: 'be0b456c-9bfc-4151-8cb1-09b94a0b907d', 
                    url: 'https://github.com/surwase123/Dazzlepay.git'
            }
        }
        
       stage('Maven Build'){
            steps{
                sh "mvn clean package"
                
                     }
               }
        
        stage('Docker Build'){
            steps{
                sh "docker build . -t ashok11/mpls:${DOCKER_TAG} "
         
            }
        }
    }
}
