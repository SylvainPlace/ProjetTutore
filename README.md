# Un "MonoRepo" avec Spring Boot, vue.js et Bootstrap

## Description du projet
Le principe de ce projet est de permettre à un patient d’avoir un semainier en ligne. Ce serait un outil utile lors du remplissage du semainier physique. Il permettrait également de s’informer sur la maladie et sur la prise du médicament. De plus, il donnerait des indications sur la posologie afin de ne pas l’oublier, et sur les conseils de contre-indications, en guise de rappel.
S'il y a plusieurs ordonnances de plusieurs médecins différents, le projet nous permettrait de gérer ce problème. Il aiderait aussi les infirmières qui l’utiliseraient à mieux connaître les différentes maladies du patient : qu’elles n’aient pas à faire des recherches pour connaître une maladie.

## Team
Mathilde Raynal, Florian Chavernac, Manon Mouric, Océane Chabus, Sylvain Place


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
