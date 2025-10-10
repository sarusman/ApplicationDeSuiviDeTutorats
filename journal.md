1.11. Lorsque vous cliquez sur le bouton vert pour lancer l'application, quelles sont les étapes du
cycle de vie de l’application (ALM) exécutées par votre IDE ?

Il fait :
COMPILE - BUILD - DEPLOY - RUN
Le build produit des JAR (ou war)

1.12. Dans le log de démarrage de l'application (dans IntelliJ), que signifie :
Tomcat started on port 8080 (http) with context path '/'

Ce message signifie que ton application Spring Boot tourne avec Tomcat et écoute sur le port 8080. Le contexte est l'environnement dans lequel tourne l'apprilcation

La racine de l'application est '/'.

1.14. Quel est le rôle de chacune des instructions surlignées ci-dessus ?
Les annotation permettte de communiquer avec les framework.

@SpringBootApplication :
C'est une annotation de Spring Boot. Elle combine 3 annotations: @Configuration, @EnableAutoConfiguration,
@ComponentScan.
C'est le point de départ de Spring Boot, active l’autoconfiguration et le scan des composants.

SpringApplication.run(TpFilRougeApplication.class, args)
Il permet de démmarer le service.
 -  Configure spring boot
 - Lance la configuration automatique. 

Après chaque modification du code, pensez à redémarrer le serveur ! Sinon les modifications ne seront
pas visibles.
1.15. Selon vous, pourquoi est-il nécessaire de faire cela
Car le code compilé et le contexte Spring et les configurations sont chargés uniquement au démarrage. 
Il faut donc le rédémarrer pour prendre en compte les changements.


2.1. Selon vous, peut-on garder ce code dans le point d’entrée ? Quels sont les risques ?


DispatcherServelet : 