# Test 2 : Viens dire bonjour !

Ce code implémente un programme simple en Java qui affiche un message de bienvenue et appelle une méthode d'une classe utilitaire (Utils) pour afficher un message supplémentaire, illustrant la séparation des responsabilités dans les classes.

## Objectif

Ce test a pour but de vérifier que le programme Bake détecte correctement que les fichiers compilés (Main.class et Utils.class) sont déjà présents et à jour, et qu'il n'exécute aucune recompilation dans ce cas.

## Description des fichiers

Bakefile : Contient les règles de compilation pour générer les fichiers .class à partir des fichiers source : 

* Compile Main.java et Utils.java si nécessaire.
* Inclut des règles supplémentaires pour nettoyer (clean) et exécuter (run) le projet.

Main.java : Contient le point d'entrée du programme. Affiche un message de bienvenue et appelle une méthode utilitaire pour afficher un second message.

Utils.java : Fournit une méthode statique afficherMessage pour afficher un message additionnel, illustrant l'utilisation d'une classe utilitaire.

## Étapes attendues pour la compilation

* Lire les dépendances spécifiées dans le Bakefile.
* Comparer les dates de modification des fichiers source (Main.java, Utils.java) et des fichiers compilés correspondants (Main.class, Utils.class).
* Si les fichiers compilés sont à jour :
    * Aucune recompilation n'est effectuée.

### Commandes pour exécuter le test

#### 1) Compiler ou le résultat existe déjà :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d 
------------- Mode débogage -------------
Comparaison entre le fichier source : Utils.java et le fichier compilé : Utils.class
Heure de modification du fichier source : 1737286951681
Heure de modification du fichier compilé : 1737288278991
Comparaison entre le fichier source : Main.java et le fichier compilé : Main.class
Heure de modification du fichier source : 1737286951681
Heure de modification du fichier compilé : 1737288279321
--------- Compilation effectuée ! ---------
```

**Que se passe-t-il ?** Lors de cette exécution en mode débogage, le programme Bake a analysé les dépendances spécifiées dans le Bakefile et comparé les dates de modification des fichiers sources (Utils.java et Main.java) avec leurs fichiers compilés correspondants (Utils.class et Main.class).

* Les fichiers compilés avaient une date de modification plus récente que les fichiers sources, indiquant qu'ils étaient déjà à jour.

* Par conséquent, aucune recompilation n'a été effectuée, et Bake a simplement confirmé que tous les fichiers nécessaires étaient déjà prêts pour l'exécution.

* Ce comportement montre que Bake est capable d'éviter des étapes inutiles, ce qui optimise le temps de compilation.

#### 2) Exécuter le programme après compilation :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d run
```

Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d run
------------- Mode débogage -------------
java Main
Bienvenue dans le mini projet Java !
Message affiché depuis la classe Utils. 
```

### Recompile le projet pour revenir à l'état initial (le résultat existe déjà ) :

Pour revenir a l'état initial : 

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
