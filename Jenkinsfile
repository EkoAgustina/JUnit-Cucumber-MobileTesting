pipeline{
    agent any
    parameters{
        string(name: "Repositories", defaultValue: "", trim: true, description: "Please enter your Repositories")
        string(name: "APPIUM_PORT", defaultValue: "", trim: true, description: "Please enter Appium Port")
        string(name: "Device_Name", defaultValue: "", trim: true, description: "Please enter Device Name")
        choice(name: "Apps", choices: ["wdio.apk", "app-clock.apk"], description: "Please selec APK")
        string(name: "Tags", defaultValue: "", trim: true, description: "Please enter the desired tags")
    }
    stages{
        stage('Build'){
            steps{
                echo 'Hi, i am going to do testing!'
            }
        }
        stage('Preparing for testing'){
            parallel{
                stage('Appium Server'){
                    steps{
                        echo 'Start Appium Server!'
                        bat "appium --port $params.APPIUM_PORT"
                    }
                }
                stage('Testing'){
                    steps{
                        git "$params.Repositories"
                        script{
                            sleep(time: 8, unit: "SECONDS")
                            bat """
                            mvn clean test -DdeviceName=$Device_Name -Dapps=$Apps -Dcucumber.filter.tags=$Tags
                            """
                        }
                        script{
                            echo 'Kill Appium Server!'
                            bat "tskill node"
                        }
                    }
                }
            }
        }
        stage('Publish Report'){
            steps{
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }
}