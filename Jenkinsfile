pipeline {
    agent { label 'agent1' }

    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                }
            }
        }
        stage('Checkstyle Main') {
            steps {
                script {
                    sh './gradlew checkstyleMain'
                }
            }
        }
        stage('Checkstyle Test') {
            steps {
                script {
                    sh './gradlew checkstyleTest'
                }
            }
        }
        stage('Compile') {
            steps {
                script {
                    sh './gradlew compileJava'
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh './gradlew test'
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
