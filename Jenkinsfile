def project = "Mobile Testing"

pipeline {
    agent any
    environment {
            APPIUM_PORT= 4723
        }
    stages{

        stage('Build') {
            steps {
                 echo 'Hi, I am going to do testing!'
                   }
            }

        stage('Preparing for Testing'){
            parallel{
                 stage('Appium Server') {
                                    steps {
                                        echo 'Start Appium Server!'
                                        bat "appium --port ${APPIUM_PORT}"
                                    }
                                }
                  stage("Testing"){
                             steps{
                                 git 'https://github.com/EkoAgustina/MobileTesting_Cucumber_Allure.git'
                             script{
                                 sleep(time: 50, unit: "SECONDS")
                                 bat "mvn clean test"
                                 }
                             script {
                                  echo 'Kill Appium Server!'
                                  bat "tskill node"
                        }
                             }
                   }
            }
        }
          stage('Publish Report'){
                      steps {
                           allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                     }
          }
    }
}