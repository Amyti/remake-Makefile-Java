# Test 5 :  Dépendance circulaire.

Ce code illustre un cas de dépendance circulaire entre deux classes (A et B), où chacune dépend de l'autre pour fonctionner. Cela provoque une boucle logique lors de la compilation. Ce test vérifie que le programme Bake détecte correctement ces dépendances circulaires et empêche la compilation.

## Objectif

Ce test a pour but de s’assurer que le programme Bake détecte une dépendance circulaire présente dans les définitions des classes et affiche un message d'erreur clair sans tenter de continuer la compilation.

## Description des fichiers

Bakefile : Contient les règles de compilation pour les fichiers .java. Les dépendances circulaires entre A.java et B.java sont implicitement présentes dans les définitions des classes.

* Compile les fichiers A.java, B.java, et C.java en fichiers .class.
* Inclut les règles nécessaires pour exécuter (run) ou nettoyer (clean) le projet.

A.java : La classe A dépend de la classe B pour effectuer une action via sa méthode doSomething.

B.java : La classe B dépend de la classe A pour son fonctionnement via le constructeur.

C.java : Une classe indépendante qui effectue une autre action sans dépendance circulaire.

Main.java : Le point d’entrée du programme. Crée des instances des classes A et C, puis appelle leurs méthodes.

## Étapes attendues pour la compilation

* Lire les dépendances définies dans le Bakefile.
* Analyser les fichiers sources pour identifier les dépendances.
* Détecter la boucle entre A.java et B.java.
* Afficher un message d'erreur pour indiquer la dépendance circulaire, et interrompre la compilation.

### Commandes pour exécuter le test

#### 1) Compilation avec une dépendance circulaires présente :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake -d 
```
Résultat : 
```bash
$ java -cp ../../build fr.iutfbleau.projet.Bake -d 
------------- Mode débogage -------------
Erreur : Dépendances circulaires détectées !
```

**Que se passe-t-il ?** Lors de cette exécution en mode débogage, le programme Bake a analysé les dépendances spécifiées dans le Bakefile et a détecté une dépendance circulaire entre les fichiers source A.java et B.java :

   * La classe A dépend de la classe B via son constructeur, où elle crée une instance de B.

   * La classe B dépend de la classe A via son constructeur, où elle crée une instance de A.

Cette situation crée une boucle logique où aucune des deux classes ne peut être compilée en premier, car elles dépendent l'une de l'autre. Bake a correctement détecté cette boucle et a affiché un message d'erreur.