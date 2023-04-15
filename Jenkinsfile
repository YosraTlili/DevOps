pipeline {
    agent any

    triggers {
        githubPush() // Utiliser le plugin Jenkins GitHub pour détecter les pushs
    }

    tools {
        maven "mvn"
    }

    stages {
        stage('Checkout') {
            steps {
                // Cloner le dépôt depuis GitHub
                git branch: 'yosra-tlili',
                url: 'https://github.com/YosraTlili/DevOps.git'
            }
        }

        stage('Build') {
            steps {
                // Exécuter le build Maven
                sh 'mvn clean install'
            }
        }

        stage('Tests') {
            steps {
                // Lancer les tests unitaires
                sh 'mvn test'
            }
        }
    }

    post {
        success {
            // Envoyer un e-mail en cas de réussite de la construction
            mail to: 'yosra.tlili@esprit.tn', // Adresse e-mail du destinataire
                 subject: 'Réussite de la construction du projet',
                 body: "La construction du projet a réussi. Vous pouvez vérifier les logs de la construction pour plus d'informations."
        }
        failure {
            // Envoyer un e-mail en cas de construction échouée
            mail to: 'yosra.tlili@esprit.tn', // Adresse e-mail du destinataire
                 subject: 'Échec de la construction du projet',
                 body: "La construction du projet a échoué. Veuillez vérifier les logs de la construction pour plus d'informations."
        }
    }
}
