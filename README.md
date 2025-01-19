# Bake

Ce Bake a été développé par Amir Kabbouri, Bamba Top et Athiran Nagathurai dans le cadre de la  [SAE32_2024](https://iut-fbleau.fr/sitebp/sae3/32_2024/M83NX3P299YXJJ2R.php) lors de notre deuxième année (2024-2025) de BUT Informatique à l'IUT de Fontainebleau.

## Principes généraux :

Nous allons produire Bake, notre propre version basique de cette commande. Celle-ci utilisera un fichier de configuration Bakefile, qui respecte le même format mais sans les éléments les plus complexes.

## Lancement du programme :

### Compilation :
Pour compiler  : 

```bash
make
```

### Compilation d'un programme avec bake

Pour compiler un programme il faut utiliser la commande : 

```bash
java -cp ../../build fr.iutfbleau.projet.Bake 
```
dans les projets.

### Lancement du programme avec bake

Utiliser la commande suivante lancer le programme :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake run
```

### Pour effacer les fichiers `.class`

Utiliser la commande pour supprimer les fichiers class :

```bash
java -cp ../../build fr.iutfbleau.projet.Bake clean
```

### Mode Debug

Un mode debug est également disponible, offrant un affichage avancé. Pour l'activer, il suffit d'ajouter l'argument ```-d``` lors de l'exécution.

## Fonctionnalités :

### Fonctionnalités exigées

 Le programme acceptera en argument une ou plusieurs cibles à mettre à jour. Si aucune cible n'est fournie, la première cible du fichier de configuration sera mise à jour.

De plus, on pourra placer avant les arguments l'option -d, qui encourage le programme à afficher des informations de déboguage durant son exécution :

    quel fichier est considéré pour une mise à jour,
    quelle comparaison de dates est faite et quel résultat est obtenu,
    quel fichier va être régénéré.

Le fichier de configuration Bakefile contiendra les règles de dépendance pour chaque cible, avec la recette associée. Il pourra également contenir des commentaires (commençant par #) et des affectations de variables (uniquement avec le symbole =).

La seule cible spéciale permise sera .PHONY.

Pour limiter le travail à accomplir, on ne gèrera pas les variables d'environnement, les variables automatiques, les fonctions, les règles implicites, les motifs de règles et les caractères spéciaux préfixes pour les recettes (voir le manuel de GNU Make).

Le programme devra avant tout lire le fichier de configuration en une seule passe. Pour simplifier on substituera la valeur des variables au fur et à mesure de la lecture (ce n'est pas le comportement normal de make).

Une fois la configuration chargée, le programme décidera des mises à jour nécessaires et exécutera les recettes correspondantes (via un ProcessBuilder). Lors de l'échec d'une recette, tout le programme doit s'arrêter.

Attention En cas de dépendance circulaire, le programme doit réagir pour éviter une boucle infinie. Toute dépendance envers une cible qui est déjà en cours d'évaluation sera donc ignorée. 
  
  ## Rapport d'avancement :
  
  Pour une analyse détaillée du projet, veuillez consulter le rapport d'avancement [rapport.pdf](https://grond.iut-fbleau.fr/kabbouri/SAE32_2024/src/branch/main/rapport.pdf), disponible dans ce dépôt. Ce document inclut des descriptions de fonctionnalités, des diagrammes de structure, et des réflexions personnelles des auteurs sur le développement du programme.

## API officielle de Java :

- Documentation : [API Officielle Java](https://iut-fbleau.fr/docs/java/api/index.html)
- Auteur : Oracle

### Crédits :

- Code :
  
  - Amir Kabbouri  (@kabbouri)

  - Bamba Top (@topb)
  
  - Athiran Nagathurai (@nagathur)