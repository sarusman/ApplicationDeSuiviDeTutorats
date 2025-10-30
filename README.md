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

## Diagramme Cas d'Utilisation

![Diagramme des cas d'utilisation](https://github.com/user-attachments/assets/8a3fb733-efc0-431a-aa9c-67e96ef64981)

---

## Installation Locale

### Prérequis
- Java 21 installé
- IntelliJ IDEA
- XAMPP ou MySQL Server lancé

## Lancement en local

### Étape 1 : Configurer la connexion à la base de données

**1a - Ouvrir le gestionnaire de bases de données** :
![Configuration 1](https://github.com/user-attachments/assets/5f7da6e2-888a-4109-b2b8-95e0c4b70379)

**1b - Ajouter une nouvelle connexion MySQL** :
![Configuration 2](https://github.com/user-attachments/assets/db45135e-361c-40e3-8530-e97c6531b70a)

**1c - Renseigner le nom de la base de données** :
![Configuration 3](https://github.com/user-attachments/assets/419fc555-d8ea-4815-b6c2-4927f13ccad4)

Utilisez le script SQL pour créer la BDD locale : [MYSQLDB_LOCAL.sql](https://github.com/sarusman/ApplicationDeSuiviDeTutorats/blob/master/src/main/resources/SQLRequests/MYSQLDB_LOCAL.sql)

### Étape 2 : Lancer l'application

Cliquez sur le bouton de lancement de l'application dans IntelliJ :
![Lancer l'application](https://github.com/user-attachments/assets/1b426351-cd0d-4a4e-93ea-e7bddf549ece)

### Étape 3 : Accéder à l'application

Une fois l'application démarrée, rendez-vous sur :
- **Local** : http://localhost:8080

---

## Connexion à l'Application

### Identifiants par défaut (Local & Distante)
- **Identifiant** : `tuteur`
- **Mot de passe** : `1234`

**⚠️ Important** : Le mot de passe est chiffré en SHA256 en base de données. Il est inutile de créer manuellement un utilisateur SQL.

### Créer un nouvel utilisateur

**Local** :
```
http://localhost:8080/register
```

**Distante** :
```
https://applicationdesuividetutorats.onrender.com/register
```

### Gestion de session
L'application utilise les cookies du navigateur pour maintenir votre session. Vous resterez connecté après un redémarrage du navigateur.

---

## Déploiement

### URL de l'application déployée
```
https://applicationdesuividetutorats.onrender.com/
```

**Identifiants** : `tuteur` / `1234`

L'application est déployée sur Render via une image Docker.

Script PostgreSQL pour Render : [POSTGRESQL_RENDER.sql](https://github.com/sarusman/ApplicationDeSuiviDeTutorats/blob/master/src/main/resources/SQLRequests/POSTGRESQL_RENDER.sql)

---

## Documentation API (Swagger)

### Local
```
http://localhost:8080/swagger-ui.html
```

### Distante
```
https://applicationdesuividetutorats.onrender.com/swagger-ui.html
```

### API Docs JSON
```
https://applicationdesuividetutorats.onrender.com/v3/api-docs
```

---

## Fonctionnalités Principales

### Authentification & Sécurité (Sarusman)
- Login/mot de passe sécurisé avec Spring Security
- Gestion des erreurs de connexion
- Affichage du prénom de l'utilisateur connecté
- Déconnexion sécurisée avec fermeture de session

### Gestion des Apprentis (Jean)
- Ajout de nouveaux apprentis
- Édition des profils
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
- Endpoints RESTful pour intégration tierce
- Documentation complète via Swagger

### Gestion des exceptions (Sarusman)
- Messages d'erreur clairs et explicites
- Gestion robuste des cas d'erreur

---

## Architecture & Principes SOLID

### Single Responsibility Principle
Classes bien segmentées avec responsabilités uniques au niveau du modèle de données et des services.

### Open/Closed Principle
Classes ouvertes à l'extension, fermées à la modification.

### Dependency Inversion Principle
Injection de dépendances via interfaces dans les services et contrôleurs.

---

## Défis Rencontrés & Solutions

**Défi majeur** : Modification tardive du modèle de données pour intégrer la gestion d'année académique  
**Impact** : L'application était avancée, ajout de cette section pour respecter les consignes  
**Solution** : Refactorisation fine de la codebase pour minimiser l'impact sur les modules existants

---

## Points Clés du Projet

1. **Modélisation avant développement** : L'importance d'une architecture bien pensée en amont
2. **Polyvalence de Spring Boot** : Capacité à mixer API REST et vues Thymeleaf SSR
3. **Communication d'équipe** : Essentielle pour coordonner un petit projet dans un court délai

---

## Contributions par Membre

### Sarusman SATKUNARAJAH
- Authentification sécurisée et gestion de session
- Recherche par entreprise, mission et année académique
- Documentation Swagger
- Déploiement opérationnel sur Render
- Gestion des exceptions

### Vincent PIERRO
- Implémentation de la gestion d'année académique
- Architecture du module académique

### Jean MOTTE
- Dashboard et ses filtres
- Gestion des apprentis (ajout, édition)
- Interface utilisateur (Snackbar, filtres)
- API REST et documentation Swagger
- Interface utilisateur globale
