def project = "Parallel Mobile Testing"

pipeline {
    agent any
    environment {
            APPIUM_PORT= 4723
        }

    stages{

        stage('Build') {
            steps {
                 echo 'Hi, Start testing'
                   }
            }

        stage('Start Appium Server and Execute Test'){
            parallel{
                 stage('Appium Server') {
                                    steps {
                                        bat "appium --port ${APPIUM_PORT}"
                                    }
                                }
                  stage("Test"){
                             steps{
                                 git 'https://github.com/EkoAgustina/MobileTesting_Cucumber_Allure.git'
                             script{
                                 bat """
                                    mvn clean test
                                    kill \$(lsof -t -i :${APPIUM_PORT})
                                 """
                                 }
                             }
                   }
            }
        }


          stage ('Generate Cucumber Report') {

                     steps {
                         cucumber buildStatus: "UNSTABLE",
                             fileIncludePattern: "**/cucumber.json",
                             jsonReportDirectory: 'target'

                     }

                 }
          stage('Generate Allure Report'){
                      steps {
                           allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                     }
          }
    }
}