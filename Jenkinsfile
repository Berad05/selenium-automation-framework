
pipeline{
agent any

   stages{
     stage('Build Jar'){
      steps{
        bat "mvn clean package -DskipTests"
      }
     }
     stage('Build Image'){
      steps{
         bat "docker build -t=vsb/selenium-docker ."
      }
     }
     stage('Push Image'){
      environment {
                     DOCKER_HUB = credentials('docker-hub-credential')
                 }

     steps{
     bat 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
     bat "docker push vsb/selenium-docker"
     }
     }
   }
   post{

    always{
    bat "docker logout"
    }

   }
}

