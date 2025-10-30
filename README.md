# Application Suivi Tutorat (AST)

## Auteurs
- Jean MOTTE
- Vincent PIERRO
- Sarusman SATKUNARAJAH

---

## Stack Technique

**IDE** : IntelliJ IDEA  
**Build Tool** : Maven  
**Langage** : Java 21  
**Base de données locale** : MariaDB  
**Base de données distante** : PostgreSQL  
**Déploiement** : Docker + Render  

---

## Installation Locale

### Prérequis
- Java 21
- IntelliJ IDEA
- XAMPP ou MySQL Server

### Étape 1 : Configurer la connexion à la base de données

1. Dans IntelliJ, accédez à la configuration de connexion MySQL
2. Paramétrez les identifiants de votre serveur local
3. Renseignez le nom de la base de données dans les paramètres

Script SQL local : [MYSQLDB_LOCAL.sql](https://github.com/sarusman/ApplicationDeSuiviDeTutorats/blob/master/src/main/resources/SQLRequests/MYSQLDB_LOCAL.sql)

### Étape 2 : Lancer l'application

Exécutez l'application depuis IntelliJ via le bouton de lancement

### Étape 3 : Accéder à l'application

Rendez-vous sur **http://localhost:8080**

---

## Accès et Connexion

### Identifiants par défaut
- **ID utilisateur** : tuteur
- **Mot de passe** : 1234

**Note** : Le mot de passe est chiffré en SHA256. Il est inutile de créer un utilisateur manuellement en base de données.

### Créer un nouvel utilisateur
- Local : http://localhost:8080/register
- Distante : https://applicationdesuividetutorats.onrender.com/register

### Persistance de session
L'application utilise les cookies du navigateur. Vous resterez connecté après un redémarrage.

---

## Déploiement

**URL déployée** : https://applicationdesuividetutorats.onrender.com/

L'application est déployée sur Render via une image Docker.

Script PostgreSQL pour Render : [POSTGRESQL_RENDER.sql](https://github.com/sarusman/ApplicationDeSuiviDeTutorats/blob/master/src/main/resources/SQLRequests/POSTGRESQL_RENDER.sql)

---

## Documentation API

**Swagger local** : http://localhost:8080/swagger-ui.html  
**Swagger Render** : https://applicationdesuividetutorats.onrender.com/swagger-ui.html  
**API Docs** : https://applicationdesuividetutorats.onrender.com/v3/api-docs

---

## Fonctionnalités Principales

### Authentification & Sécurité (Sarusman)
- Login/mot de passe sécurisé via Spring Security
- Gestion des erreurs de connexion
- Affichage du prénom de l'utilisateur connecté
- Déconnexion avec fermeture de session

### Gestion des Apprentis (Jean)
- Ajout d'apprentis
- Édition de profils
- Notifications via Snackbar

### Dashboard (Jean)
- Vue d'ensemble des données
- Filtrage et recherche avancée

### Recherche & Filtrage (Sarusman)
- Recherche par entreprise
- Recherche par mission (mot-clé)
- Filtrage par année académique

### Gestion d'année académique (Vincent)
- Suivi des périodes de formation
- Gestion des cycles académiques

### API REST (Jean)
- Endpoints pour intégration tierce
- Documentation complète via Swagger

### Gestion des exceptions (Sarusman)
- Messages d'erreur clairs et explicites

---

## Architecture & Principes SOLID

### Single Responsibility Principle
Classes bien segmentées avec responsabilités uniques au niveau du modèle de données et des services.

### Open/Closed Principle
Classes ouvertes à l'extension, fermées à la modification.

### Dependency Inversion Principle
Injection de dépendances via interfaces dans les services et contrôleurs.

---

## Défis & Solutions

**Défi majeur** : Modification tardive du modèle de données pour intégrer la gestion d'année académique  
**Solution** : Refactorisation fine de la codebase pour minimiser l'impact sur les modules existants

---

## Points Clés du Projet

1. **Modélisation avant développement** : L'importance d'une architecture bien pensée en amont
2. **Polyvalence de Spring Boot** : Capacité à mixer API REST et vues Thymeleaf SSR
3. **Communication d'équipe** : Essentielle pour coordonner un petit projet dans un court délai

---

## Contributions par Membre

**Sarusman SATKUNARAJAH**
- Authentification sécurisée et gestion de session
- Recherche par entreprise, mission et année académique
- Documentation Swagger
- Déploiement opérationnel
- Gestion des exceptions

**Vincent PIERRO**
- Implémentation de la gestion d'année académique

**Jean MOTTE**
- Dashboard et filtrage
- Gestion des apprentis (ajout, édition)
- Interface utilisateur (Snackbar, filtres)
- API REST et documentation Swagger
