# Test 1 : 5 + 10 = ?

Ce code implémente un programme simple en C qui affiche la somme de deux nombres en déléguant le calcul à une fonction somme définie dans le fichier utils.c et déclarée dans le fichier d'en-tête utils.h, illustrant la séparation des responsabilités entre les fichiers source.

**Note** : Ce test n’attend aucune entrée de l'utilisateur. Les valeurs utilisées (5 et 10) sont codées en dur dans le fichier main.c.

## Objectif
Ce test doit vérifier que le programme Bake peut compiler correctement un projet complet lorsque aucun fichier compilé (.o ou exécutable) n'existe encore. Il s'assure que toutes les étapes de compilation sont exécutées dans le bon ordre. 

## Description des fichiers

Bakefile : Contient les règles de compilation pour générer l'exécutable app à partir des fichiers source : 

* Compile les fichiers main.c et utils.c en fichiers objets (main.o, utils.o).
* Lien des fichiers objets pour produire l'exécutable app.
* Inclut des règles supplémentaires pour nettoyer (clean) et exécuter (run) le projet.

main.c : Contient le point d'entrée du programme. Utilise la fonction somme pour additionner deux nombres et affiche les résultats.

utils.c : Contient l'implémentation de la fonction somme, utilisée par le programme principal.

utils.h : Fichier d'en-tête déclarant la fonction somme pour être utilisée par les autres fichiers.


## Étapes attendues pour la compilation

* Tous les fichiers objets (main.o, utils.o) et l'exécutable app sont générés.
* Aucun message d'erreur n'est affiché.
* Le programme peut être exécuté avec la commande ./app.

### Commandes pour exécuter le test

#### 1) Compiler le projet depuis rien :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d 
------------- Mode débogage -------------
Comparaison entre le fichier source : utils.c et le fichier compilé : utils.o
Le fichier n est pas encore compilé : utils.o -> Compilation nécessaire.
gcc -Wall -c utils.c
Comparaison entre le fichier source : main.c et le fichier compilé : main.o
Le fichier n est pas encore compilé : main.o -> Compilation nécessaire.
gcc -Wall -c main.c
gcc -Wall -o app main.o utils.o
--------- Compilation effectuée ! ---------
```

**Que se passe-t-il ?** Lors de cette exécution en mode débogage, le programme Bake a analysé les dépendances spécifiées dans le Bakefile. Il a constaté que les fichiers compilés (utils.o et main.o) n'existaient pas encore, et a donc déclenché leur compilation à partir des fichiers sources (utils.c et main.c). Ensuite, il a lié les fichiers objets pour générer l'exécutable final app. Chaque étape a été affichée en détail pour permettre de suivre le processus.

#### 2) Exécuter le programme après compilation :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d run
```

Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake run
------------- Exécution en mode normal -------------
./app
Les nombres sont 5 et 10.
La somme est  : 15
```

### Nettoyer le projet pour revenir à l'état initial (aucun fichier compilé) :

Pour revenir a l'état initial : 

```bash
java -cp ../../build fr.iutfbleau.projet.Bake clean
```

Résultat : 

```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake clean
------------- Exécution en mode normal -------------
rm -rf app utils.o main.o
```
