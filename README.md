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
Swagger UI : http://localhost:8080/swagger-ui.html
OpenAPI : http://localhost:8080/v3/api-docs
```

### Distante
```
Base : https://applicationdesuividetutorats.onrender.com/
Swagger UI : https://applicationdesuividetutorats.onrender.com/swagger-ui.html
OpenAPI : https://applicationdesuividetutorats.onrender.com/v3/api-docs
```

---

## Routes et Fonctionnalités

### Interface Web (Thymeleaf)

#### Authentification
- `GET /login` - Page de connexion
- `POST /login` - Validation des identifiants (redirection /dashboard en cas de succès)
- `GET /logout` - Déconnexion et redirection vers /login

#### Dashboard
- `GET /dashboard` - Liste des apprentis du tuteur connecté avec formulaires d'ajout/édition

#### Apprentis
- `GET /apprenti/{id}` - Détails d'un apprenti (profil, coordonnées, programme, visites, entreprise)
- `POST /apprenti/ajouter` - Ajout d'un apprenti via formulaire
- `POST /apprenti/update/{id}` - Mise à jour d'un apprenti
- `POST /apprenti/supprimer/{id}` - Suppression d'un apprenti

---

### API REST

#### Authentification / Session
- `POST /api/auth/login` - Connexion (200: {id, username, role} | 401: Identifiants invalides)
- `POST /api/auth/register` - Inscription (201/200: {username, status} | 400: Inscription impossible)
- `POST /api/auth/logout` - Déconnexion (204 No Content)

**Note** : `/api/auth/*` est public. Le reste de `/api/**` requiert une session valide.
- Non authentifié : 401 `{"message":"Unauthorized"}`
- Non autorisé : 403 `{"message":"Forbidden"}`

#### Dashboard
- `GET /api/dashboard` - Retourne utilisateur, apprentis, programmes et entreprises

#### Apprentis
- `GET /api/apprentis` - Liste des apprentis (query: archived=false, annee=2025)
- `GET /api/apprentis/{id}` - Détails d'un apprenti (404: Apprenti introuvable)
- `POST /api/apprentis` - Création d'apprenti (201/200: créé | 400: e-mail/numéro déjà utilisé)
- `PUT /api/apprentis/{id}` - Mise à jour d'apprenti (200: mis à jour | 400: validation | 404: introuvable)
- `DELETE /api/apprentis/{id}` - Suppression d'apprenti (204: supprimé | 404: introuvable)

#### Recherche
- `GET /api/apprentis/search` - Recherche avancée (query: nom, entrepriseId, mission, annee, programme, archived)

#### Année académique
- `POST /api/annee/promotion` - Promotion d'année (ADMIN requis)
  - Résultat : `{"promusI1versI2":n, "promusI2versI3":n, "archivesI3":n}`
  - Effets : archivage I3, promotion I2→I3 et I1→I2
- `GET /api/apprentis?archived=true` - Liste des archivés

#### Référentiels
- `GET /api/entreprises` - Liste des entreprises
- `GET /api/tuteurs/by-entreprise/{entrepriseId}` - Tuteurs d'une entreprise

---

## Fonctionnalités Principales

### Authentification & Sécurité
- Login/mot de passe sécurisé avec Spring Security
- Gestion des erreurs de connexion
- Affichage du prénom de l'utilisateur connecté
- Déconnexion sécurisée avec fermeture de session

### Gestion des Apprentis
- Ajout de nouveaux apprentis
- Édition des profils
- Notifications via Snackbar

### Dashboard
- Vue d'ensemble des données
- Filtrage et recherche avancée

### Recherche & Filtrage
- Recherche par entreprise
- Recherche par mission (mot-clé)
- Filtrage par année académique

### Gestion d'année académique
- Suivi des périodes de formation
- Gestion des cycles académiques

### API REST
- Endpoints RESTful pour intégration tierce
- Documentation complète via Swagger

### Gestion des exceptions
- Messages d'erreur clairs et explicites
- Gestion robuste des cas d'erreur

---

## Questions / Réponses

### a) Sur quel aspect du travail attirer l'attention du correcteur ?

- **Vues Thymeleaf** : Templates bien structurés avec séparation des préoccupations
- **Sécurité (Spring Security)** : Authentification robuste et gestion de session sécurisée
- **Mélange vues Thymeleaf et API REST** : Architecture hybride combinant rendu serveur et endpoints API

### b) Quelle est la plus grande difficulté rencontrée ?

**Défi** : Modification tardive du modèle de données pour intégrer la gestion d'année académique

**Contexte** : L'application était très avancée quand cette exigence a été ajoutée pour respecter les consignes

**Solution** : Refactorisation fine de la codebase pour minimiser l'impact sur les modules existants

### c) Contributions par membre

**Sarusman SATKUNARAJAH**
- Sécurité et authentification (Spring Security)
- Gestion des exceptions
- Initiation de la vue dashboard
- Documentation Swagger
- Déploiement opérationnel sur Render

**Vincent PIERRO**
- Implémentation de la gestion d'année académique

**Jean MOTTE**
- Dashboard et ses filtres
- Gestion des apprentis (ajout, édition)
- Interface utilisateur (Snackbar, filtres)
- API REST et documentation Swagger

### d) Trois points clés du cours et du projet

1. **Importance de la modélisation** : Nécessité d'une architecture bien pensée avant de démarrer à coder
2. **Polyvalence de Spring Boot** : Capacité à mixer API REST et vues Thymeleaf SSR
3. **Communication d'équipe** : Essentielle pour coordonner un petit projet dans un court délai

### e) Fonctionnalités non implémentées

Toutes les fonctionnalités prévues ont été implémentées.

### f) Respect des principes SOLID

**Single Responsibility Principle**
- Classes bien segmentées avec responsabilités uniques au niveau du modèle de données et des services
- Note : Approche à la limite d'un anti-pattern d'anemic domain model

**Open/Closed Principle**
- Classes ouvertes à l'extension mais fermées à la modification

**Dependency Inversion Principle**
- Injection de dépendances via interfaces dans les services et contrôleurs
