# ⛂ Jeu de Dames 10x10 (Web)

## 📝 Description
Un jeu de dames interactif sur un plateau 10x10, jouable directement dans le navigateur. Ce projet a été développé de A à Z en utilisant uniquement des technologies web standards (HTML, CSS et JavaScript Vanilla), sans aucun framework externe.

## ✨ Fonctionnalités
* **Interface claire** : Menu d'accueil interactif et plateau de jeu stylisé.
* **Moteur de jeu** : Gestion stricte des tours (Blancs/Noirs) et des déplacements en diagonale.
* **Aide visuelle** : Mise en surbrillance (en vert clair) des cases de destination autorisées lorsqu'un pion est sélectionné.
* **Système de prise** : Capture simple des pions adverses par saut.
* **Promotion** : Transformation automatique des pions en Dames (avec indicateur visuel rouge) lorsqu'ils atteignent la dernière ligne adverse.

## 📁 Architecture du projet
Le code respecte la bonne pratique de séparation des responsabilités :
* `index.html` : Page du menu principal.
* `game.html` : Structure de l'interface de jeu et du plateau.
* `style.css` : Design, couleurs et animations pour l'ensemble du projet.
* `script.js` : Logique mathématique, vérification des mouvements et manipulation du DOM.

## 🚀 Comment jouer ?
Aucune installation de serveur n'est requise.
1. Téléchargez les fichiers du projet dans un même dossier.
2. Double-cliquez sur le fichier `index.html` pour l'ouvrir dans n'importe quel navigateur web moderne (Chrome, Firefox, Safari...).
3. Cliquez sur "Jouer" et profitez de la partie !

## 🛠️ Pistes d'améliorations (À faire)
Ce projet est une excellente base qui peut être enrichie pour respecter à 100% les règles internationales officielles :
* Ajout de la prise en arrière pour les pions classiques.
* Implémentation des "rafles" (prises multiples obligatoires dans un même tour).
* Ajout de la règle de la "longue diagonale" pour le déplacement des Dames.