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
    try {
        stages {
        
            notifyBuild('STARTED')

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
    } catch (e) {
        // If there was an exception thrown, the build failed
        currentBuild.result = "FAILED"
        throw e
    } finally {
        // Success or failure, always send notifications
        notifyBuild(currentBuild.result)
    }

}

def notifyBuild(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }

  // Send notifications
  slackSend (color: colorCode, message: summary)

}
