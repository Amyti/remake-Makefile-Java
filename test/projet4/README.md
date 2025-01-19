# Test 4 :  Besoin d'une calculatrice ?

Ce code implémente une calculatrice simple en C qui effectue des opérations de base (addition, soustraction, multiplication, division) en utilisant des fonctions définies dans le fichier operation.c et déclarées dans operation.h.

## Objectif

Ce test n'a pas d'objectif, il est simplement là particulier.

## Description des fichiers

Bakefile : Contient les règles de compilation pour générer les fichiers .class à partir des fichiers source :

* Compile operation.c en operation.o.
* Compile main.c, en incluant operation.o.
* Lie les fichiers objets pour produire l'exécutable calculatrice.
* Inclut des règles pour nettoyer (clean) et exécuter (run) le projet.

operation.c : Implémente les fonctions pour effectuer les quatre opérations principales : addition, soustraction, multiplication et division.

operation.h : Fichier d'en-tête déclarant les fonctions disponibles dans operation.c.

## Étapes attendues pour la compilation

* Lire les dépendances spécifiées dans le Bakefile.
* Compiler operation.c en fichier objet operation.o.
* Compiler main.c en fichier objet main.o, tout en utilisant operation.o comme dépendance.
* Lier les fichiers objets pour produire l'exécutable calculatrice.

### Commandes pour exécuter le test

#### 1) Compilation ou rien n'a été modifié :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d 
------------- Mode débogage -------------
Comparaison entre le fichier source : operation.c et le fichier compilé : operation.o
Heure de modification du fichier source : 1737286951681
Heure de modification du fichier compilé : 1737295901394
Comparaison entre le fichier source : main.c et le fichier compilé : main.o
Heure de modification du fichier source : 1737286951681
Heure de modification du fichier compilé : 1737295901417
gcc -Wall -o calculatrice main.o operation.o
--------- Compilation effectuée ! ---------

```

#### 3) Exécuter le programme après compilation :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d run
```

Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d run
------------- Mode débogage -------------
./calculatrice
Bienvenue dans la calculatrice
1. Addition
2. Soustraction
3. Multiplication
4. Division
Entrez votre choix: 3
Entrez deux nombres: 15 15
Résultat: 225
```

### Nettoyer le projet  :

Pour revenir a l'état initial : 

```bash
java -cp ../../build fr.iutfbleau.projet.Bake clean
```

Résultat : 

```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d clean
------------- Mode débogage -------------
rm -rf calculatrice operation.o main.o

```
