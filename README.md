# doodle

# A propos

Un site web permettant de créer et voter sur des sondages, aussi appelé "Doodle", afin de faciliter l'organisation des évènements collectifs.

# Description
## API

Le site web utilise une API développée en Java (spring-boot) afin de communiquer avec la base de données.

> Documentation de l'API https://doodle-api.netlify.app/

## Site web

Le site web lui-même est développé en JavaScript (Vue.js).

## Fonctionnalités

* Inscription de l'utilisateur
* Connexion de l'utilisateur
* Création des sondages
* Vote sur des sondages

# Installation/déploiement

## Prérequis

* Java SE 11 ou plus récent
* PostgreSQL 10
* Node.js 12 (LTS) ou plus récent

## Configuration par défaut

* L'API Spring boot utilise le port 7070
* Le site utilise le port 8080
* La base de données est supposée tourner sur le port 5432

## Configuration personalisée

Voici les fichiers de configuration à modifier pour les services concernés :

* L'API Spring boot : 
  * la valeur de propriété `server.port` dans le fichier `src/main/ressources/applications.properties`
  * la valeur de `axios.defaults.baseURL` dans le fichier `src/main/vuejs/src/main.js`
* La base de données : `src/main/ressources/applications.properties`

## Déploiement

* Lancer l'API : `mvnw spring-boot:run`
* Lancer le serveur de vuejs : 
  * se déplacer dans le dossier de vuejs : `cd src/main/vuejs`
  * installer les dépendances JavaScript : `npm install`
  * lancer le serveur : `npm run serve`

# Equipe de développement

* Léo LEFFY
* Sory DIARRA
* Orka Arnest CRUZE

Classe 4A-UFA-41