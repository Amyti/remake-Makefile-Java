#include <stdio.h>
#include "operation.h"

int main() {
    int a, b, choix;
    printf("Bienvenue dans la calculatrice\n");
    printf("1. Addition\n2. Soustraction\n3. Multiplication\n4. Division\n");
    printf("Entrez votre choix: ");
    scanf("%d", &choix);
    printf("Entrez deux nombres: ");
    scanf("%d %d", &a, &b);

    switch (choix) {
        case 1:
            printf("Résultat: %d\n", addition(a, b));
            break;
        case 2:
            printf("Résultat: %d\n", soustraction(a, b));
            break;
        case 3:
            printf("Résultat: %d\n", multiplication(a, b));
            break;
        case 4:
            if (b != 0) {
                printf("Résultat: %.2f\n", division(a, b));
            } else {
                printf("Erreur: Division par zéro!\n");
            }
            break;
        default:
            printf("Choix invalide!\n");
            break;
    }

    return 0;
}
