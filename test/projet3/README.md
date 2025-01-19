# Test 3 : T'as quel âge ? 

Ce code implémente un programme interactif qui demande à l'utilisateur son âge, puis utilise une classe utilitaire (Utils) pour afficher un message en fonction de l'âge fourni. Ce test illustre la gestion des dépendances dans un projet Java lorsque l’un des fichiers source a été modifié après une compilation précédente.

**Note** : Ce test attend une entrée utilisateur.

## Objectif

Ce test a pour objectif de vérifier que le programme Bake détecte correctement qu'un fichier source a été modifié depuis la dernière compilation et qu'il ne recompile que ce fichier tout en générant un projet fonctionnel.

## Description des fichiers

Bakefile : Contient les règles de compilation pour générer les fichiers .class à partir des fichiers source :

* Compile Utils.java et Main.java si nécessaire.
* Lien entre les fichiers compilés pour exécuter le projet.
* Inclut des règles pour nettoyer (clean) et exécuter (run) le projet.

Main.java : Demande à l'utilisateur son âge via la console et appelle la méthode resultat de la classe Utils pour afficher des messages adaptés en fonction de l'âge.

Utils.java : Implémente la logique qui affiche un message basé sur l'âge fourni. La méthode resultat utilise une série de conditions pour déterminer quel message afficher.

## Étapes attendues pour la compilation

* Lire les dépendances spécifiées dans le Bakefile.
* Comparer les dates de modification des fichiers sources (Main.java, Utils.java) avec leurs fichiers compilés correspondants (Main.class, Utils.class).
* Si les fichiers compilés sont à jour :
    * Aucune recompilation n'est effectuée.
* Si un fichier source est modifié c'est-à-dire plus récent que son fichier compilé :
    * Recompiler uniquement le ou les fichiers concernés.
* Après recompilation, générer un projet fonctionnel prêt à être exécuté.

### Commandes pour exécuter le test

#### 1) Compilation ou rien n'a été modifié :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d 
------------- Mode débogage -------------
Comparaison entre le fichier source : Utils.java et le fichier compilé : Utils.class
Heure de modification du fichier source : 1737286951681
Heure de modification du fichier compilé : 1737295335976
Comparaison entre le fichier source : Main.java et le fichier compilé : Main.class
Heure de modification du fichier source : 1737286951681
Heure de modification du fichier compilé : 1737295336396
--------- Compilation effectuée ! ---------
```
#### 2) Compiler ou le résultat existe déjà et que le fichier source a été modifié :

```bash
Bamba@Bamba projet3 $ java -cp ../../build fr.iutfbleau.projet.Bake -d 
------------- Mode débogage -------------
Comparaison entre le fichier source : Utils.java et le fichier compilé : Utils.class
Heure de modification du fichier source : 1737295433126
Heure de modification du fichier compilé : 1737295430860
javac Utils.java
Comparaison entre le fichier source : Main.java et le fichier compilé : Main.class
Heure de modification du fichier source : 1737295426496
Heure de modification du fichier compilé : 1737295431226
--------- Compilation effectuée ! ---------
```

**Que se passe-t-il ?** Lors de cette exécution en mode débogage, le programme Bake a détecté que certains fichiers compilés étaient obsolètes par rapport à leurs fichiers sources. Voici ce qui s'est passé :

   * Pour Utils.java, la date de modification du fichier source était plus récente que celle du fichier compilé (Utils.class). Cela signifie que le fichier source a été modifié depuis la dernière compilation. Bake a donc recompilé Utils.java en exécutant la commande javac Utils.java.

   * Pour Main.java, la date de modification du fichier source était antérieure à celle du fichier compilé (Main.class). Cela indique que Main.java n'a pas été modifié depuis la dernière compilation, donc aucune recompilation n'était nécessaire pour ce fichier.

#### 3) Exécuter le programme après compilation :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d run
```

Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d run
------------- Mode débogage -------------
java Main
Quel âge as-tu ? 19
T'es normal!
affiché depuis la classe Utils.
```

### Recompile le projet pour revenir à l'état initial (le résultat existe déjà ) :

Pour revenir a l'état initial : 

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
Pour celui-là, il suffit qu'un seul fichier soit modifié.