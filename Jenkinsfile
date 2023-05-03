pipeline {
    agent any
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }

stages {
    stage('Clone repository') {
        steps {
            git branch: 'developELA', url: 'https://github.com/YosraTlili/DevOps.git'
        }
    }
  
    stage('Verify environment') {
        steps {
            sh 'mvn --version'
            sh 'java --version'
        }
    }
    stage('Build and Run Services') {
      steps {
        sh 'docker-compose --version'
        sh 'docker-compose build'
        sh 'docker-compose up -d --no-recreate'
      }
    }
     
    stage('Build') {
        steps {
            sh 'mvn clean package'
        }
    }
    
    stage('Unit tests') {
        steps {
            sh 'mvn test'
        }
    }
    
     stage('Deploy') {
        steps {
            sh "mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8081/repository/devOpsPrj/ -Dusername=admin -Dpassword=nexus"
        }
    }
   
    stage('Static code analysis') {
      steps {
        sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dsonar.host.url=http://localhost:9000"
      }
    }
    
  stage("Build Docker image") {
      steps {
        script {
          docker.build("lkhleya/docker_devops_rep")
        }
      }
    }
    
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }

 stage("Push Docker image") {
  steps {
    script {
      docker.image("lkhleya/docker_devops_rep:latest").push()
    }
  }
}


}
    post {
    always {
      sh 'docker logout'
    }
  }
}
