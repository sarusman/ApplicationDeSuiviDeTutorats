# Application Suivi Tutorat (AST)

### _Réalisé par Jean MOTTE ; Vincent PIERRO ; Sarusman SATKUNARAJAH_

## Diagramme Cas D'utilisation

<img width="2162" height="1254" alt="ZLPDJXin5Dxx54_f0X0ALUcY5Q8H8QGgKa6eeKXTLSxCI-8oOwV-IK5Ljpr0Jq7EaPlq97ssPuQPIQ1XW_tcUt_V-t5EZENQkZnZE_3k-Jz2d0a-rJpF4LA4X1j8TvmLcJ3S2YMX2_tHTNyBIop3YGMhG8lfp48gD2RU0ZEtGiuvkO0vJsxv54VsFaFmdxcSPbW3E8CK0S9F1d2gT8fwe3AbuSKR_jQBI7-gbPDf8KQSi5-CySJIhNFjB3" src="https://github.com/user-attachments/assets/8a3fb733-efc0-431a-aa9c-67e96ef64981" />

## Script SQL de lancement
- Se trouver à l'emplacement `src/main/resources/seed.sql`

## Usage de l'application
- Lien d'accès à l'application déployée : _TBD_
- Identifiants de connexion :
    - username : test
    - password : 1234

## Tooling
- IDE : IntelliJ IDEA
- Build Tool : Maven
- Langage : Java 21
- SGBD locale : mariadb
- SGBD distante : _TBD_

## Swagger

- Lien vers la documentation Swagger : [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- À noter que le swagger ne recense que les API REST ; et donc pas les vues thymeleaf

## Questions / Réponses
a) Sur quel aspect de votre travail souhaitez-vous attirer l'attention du correcteur ?
- Vues Thymeleaf
- Sécurité (Spring Security)
- Mélange vues thymeleaf et API REST

b) Quelle est la plus grande difficulté que vous avez rencontrée ? Comment avez-vous
géré/solutionné/contourné cette difficulté ?
- Modification du modèle de données tardivement dans le projet afin de gérer l'année académique et sa gestion
- L'application était très avancée, et nous avons dû ajouter cette section afin de répondre aux consignes
- Il a donc fallu ajouter cette section de manière fine afin de ne pas avoir à modifier trop en profondeur la codebase

c) Quelle a été la contribution de chaque membre de l'équipe ?
- Saru : Sécurité, SSO, gestion des exceptions et initiation de la vue dashboard
- Vincent : Implémentation de la feature de gestion d'année académique
- Jean : Dashboard, filtre dashboard, ajout d'apprenti, édition d'apprenti, snackbar, filtre , API REST, documentation swagger

d) Si vous deviez retenir trois points de ce cours en général et de ce projet en particulier,
quels seraient ces trois points ?
- Importance de la modélisation avant de démarrer à coder
- Spring boot est très versatile, permettant de mélanger API REST et vues thymeleaf SSR
- Importance de la communication sur un projet de petite ampleur, à réaliser dans un lapse de temps court

e) Les fonctionnalités que vous n'avez pas eu le temps de mettre en œuvre et pourquoi.
- NA

f) A quel niveau, dans votre projet, avez-vous réussi à respecter entièrement ou
partiellement les principes SOLID ?
- Single Responsability Principle au niveau des classes définies + modèle de données. Les classes sont limitées ; mais j'ai l'impression qu'on entre dans un anti-pattern d'anemic domain model
- Open Closed Principle : Les classes sont ouvertes à l'extension mais fermées à la modification
- Dependency Inversion Principle : nous avons injecté les dépendances via des interfaces dans les services et les contrôleurs