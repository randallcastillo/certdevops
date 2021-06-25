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
            	} 
            	
                	         
            }
        } 

    }
}