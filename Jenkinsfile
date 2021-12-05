pipeline{
    agent any
    tools {
       maven 'Maven'
   }
     stages{
        stage('SCM'){
            steps{
                
                git credentialsId: 'ca38f49b-7728-4e5c-ae3c-dabf3d2f636a', 
                      url: 'https://github.com/surwase123/Dazzlepay.git'
            }
        }

  
        stage('maven Build'){
            steps{
              sh "mvn clean package"

        }
     }
  }

}
