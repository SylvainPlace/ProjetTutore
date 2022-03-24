# Un "MonoRepo" avec Spring Boot, vue.js et Bootstrap

## Project setup

```
frontback
├─┬ backend     → backend module with Spring Boot code
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js code
│ ├── src
│ └── pom.xml
└── pom.xml     → Maven parent pom managing both modules
```

## Pour exécuter le projet

A la racine du projet: 

```
mvn clean install
```
Va construire le frontend, recopier les fichiers dans le backend.

Exécution de l'application "fullstack":

```
mvn --projects backend spring-boot:run
```

Ouvrir http://localhost:8989/ pour accéder à l'application.

## Utilisation des outils de développement "front-end"

On peut lancer webpack-dev-server, qui "rafraîchit" automatiquement le front-end à chaque changement dans le code ! Pour celà, se positionner dans le répertoire `frontend` et lancer :

```
npm run serve
```

## Browser developer tools extension

Installer vue-devtools Browser extension https://devtools.vuejs.org/  dans votre navigateur pour débugger plus facilement le front-end.
