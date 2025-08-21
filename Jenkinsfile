
pipeline{
agent any

   stages{
     stage('Build Jar'){
      steps{
        bat "mvn clean package -DskipTest"
      }
     }
     stage('Build Image'){
      steps{
         bat "docker build -t=vsb/selenium-docker ."
      }
     }
     stage('Push Image'){
     steps{
     bat "docker push vsb/selenium-docker ."
     }
     }
   }

}