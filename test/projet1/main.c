#include <stdio.h>
#include "utils.h"

int main() {
    int a = 5, b = 10;
    int resultat = 0;
    printf("Les nombres sont %d et %d.\n", a, b);

    resultat = somme(a, b);
    printf("La somme est  : %d\n", resultat);

    return 0;
}
