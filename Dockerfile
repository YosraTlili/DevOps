
# FROM permet de définir depuis quelle base votre image va être créée
FROM openjdk:8-jre-alpine
# ADD permet de copier un fichier depuis la machine hôte ou depuis une URL
ADD target/*.jar app.jar
# EXPOSE permet d’exposer un port du container vers l’extérieur
EXPOSE 8089
# CMD détermine la commande qui sera exécutée lorsque le container démarrera
CMD ["java", "-jar", "/app.jar"]