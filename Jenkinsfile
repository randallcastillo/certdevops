pipeline {
    agent none

    environment {
        registry = "rocastillou/certdevops" 
        registryCredential = 'dockerhub'
        dockerImage = ''
        branchName = ''
        version = ''
	}
    options {
        timestamps()
        skipDefaultCheckout() // Don't checkout automatically
    }
    stages {

        stage ('Start') {
            agent any
            steps {
                // send build started notifications
                notifyBuild()
            }
        }
        stage('Checkout') {
            agent any
            steps {
                checkout scm
            }
        }
        stage('Compile') {
            agent {
                docker {
                    image 'maven:3-alpine' 
                    args '-v /Users/rocastillou/.m2:/root/.m2' 
                }
            }
            steps {
                echo 'Compilar'
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'maven:3-alpine' 
                    args '-v /Users/rocastillou/.m2:/root/.m2' 
                }
            }
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine' 
                    args '-v /Users/rocastillou/.m2:/root/.m2' 
                }
            }  
            steps {
                echo 'Build'
                sh 'mvn clean package -Dmaven.test.skip=true' 
            }
        }
        stage('Build Docker Image') { 
            agent any
            steps {
                script {

                    if ( env.BRANCH_NAME.equals("main") ) {
                        version = ":$BUILD_NUMBER"
                    } else {
                        version = ":" + env.BRANCH_NAME.replace("/", "-") + "-$BUILD_NUMBER"
                    }

                    dockerImageName = registry + version
                    dockerImage = docker.build "${dockerImageName}"
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }

                    if (env.BRANCH_NAME.equals("master")) {
                        docker.withRegistry( '', registryCredential ) {
                            dockerImage.push('latest')
                        }
                        sh "docker rmi " + ${registry} + "latest"
                    }
                    sh "docker rmi $registry$version"

                    slackSend color: "good", message: "La imagen se ha publicado satisfactoriamente en Docker Hub"
                } 
                
                            
            }
        }
    }
    post {
        always {
            notifyBuild(currentBuild.currentResult)
        }
    }
}

def notifyBuild(String buildStatus = 'STARTED') {
  // Default values
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"

  // Override default values based on build status
  if (buildStatus == 'SUCCESS') { 
    colorCode = 'good' // GREEN
  } else if (buildStatus == 'UNSTABLE' || buildStatus == 'STARTED') {
    colorCode = 'warning' // YELLOW
  } else if (buildStatus == 'FAILURE') {
    colorCode = 'danger' // RED
  } else {
    colorCode = 'danger' // RED
  }

  // Send notifications
  slackSend (color: colorCode, message: summary)

}
