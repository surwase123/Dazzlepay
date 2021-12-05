pipeline{
    agent any
    tools {
       maven 'Maven'
   }
     
        stage('maven Build'){
            steps{
              sh "mvn clean package"

        }
     }
  }

}
