pipeline {
    agent {
        label 'jdk21 && linux'
    }

    tools {
        git 'Default'
    }

    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                }
            }
        }
        stage('Check') {
            steps {
                script {
                    sh './gradlew check'
                }
            }
        }
        stage('Package') {
            steps {
                script {
                    sh './gradlew build'
                }
            }
        }
        stage('JaCoCo Report') {
            steps {
                script {
                    sh './gradlew jacocoTestReport'
                }
            }
        }
        stage('JaCoCo Verification') {
            steps {
                script {
                    sh './gradlew jacocoTestCoverageVerification'
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t job4j_devops .'
                }
            }
        }
        stage('Update DB') {
            steps {
                script {
                    sh './gradlew update -P"dotenv.filename"="/var/agent-jdk21/env/.env.develop"'
                }
            }
        }
    }

    post {
        always {
                script {
                    // Use a string for build information
                    def buildInfo = "Build number: ${currentBuild.number}\n" +
                                    "Build status: ${currentBuild.currentResult}\n" +
                                    "Started at: ${new Date(currentBuild.startTimeInMillis)}\n" +
                                    "Duration so far: ${currentBuild.durationString}"

                    // Send the information to Telegram
                    telegramSend(message: buildInfo)
                }
        }
    }
}
