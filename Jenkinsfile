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
                sh "docker build . -t ashok11/mpls:0.0.1"
                
                     }
            }
        
        stage('DockerHub Push'){
            steps{
                withCredentials([string(credentialsId: 'Docker-hub', variable: 'dockerhubPwd')]) {
                  sh "docker login -u ashok11 -p ${dockerhubPwd}"
                }
                
                sh "docker push ashok11/mpls:0.0.1"
    
                
                     }
        }
        
        stage('Docker Deploy'){
            steps{
              ansiblePlaybook credentialsId: 'dev-server', disableHostKeyChecking: true, installation: 'ansible', inventory: 'dev.in', playbook: 'deploy-docker.yml'
            }
        }
    }
}
