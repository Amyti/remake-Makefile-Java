/**
 * La classe <code>BakeComparator</code> est utilisée pour comparer les dates de modification
 * des fichiers afin de déterminer si une recompilation est nécessaire.
 * 
 * @version 1.0
 * @author Amir Kabbouri - Bamba Top - Athiran Nagathurai
 */
package fr.iutfbleau.projet.execute;

import java.nio.file.*;
import java.io.IOException;

public class BakeComparator {

    /**
     * Indique si le mode débogage est activé.
     */
    private boolean modeDebug;

    /**
     * Constructeur de la classe <code>BakeComparator</code>.
     * 
     * @param modeDebug Indique si le mode débogage est activé.
     */
    public BakeComparator(boolean modeDebug) {
        this.modeDebug = modeDebug;
    }

    /**
     * Vérifie si une recompilation est nécessaire en comparant les dates
     * de modification des fichiers source et compilé.
     * 
     * @param ficSource  Chemin du fichier source.
     * @param ficCompile Chemin du fichier compilé.
     * @return <code>true</code> si la recompilation est nécessaire, sinon <code>false</code>.
     * @throws IOException En cas d'erreur d'accès aux fichiers.
     */
    public boolean verifRecompilation(String ficSource, String ficCompile) throws IOException {
        if (modeDebug) {
            System.out.println("Comparaison entre le fichier source : " + ficSource + " et le fichier compilé : " + ficCompile);
        }

        if (!Files.exists(Paths.get(ficCompile))) {
            if (modeDebug) {
                System.out.println("Le fichier n'est pas encore compilé : " + ficCompile + " -> Compilation nécessaire.");
            }
            return true;
        }

        long sourceDate = Files.getLastModifiedTime(Paths.get(ficSource)).toMillis();
        long compilerDate = Files.getLastModifiedTime(Paths.get(ficCompile)).toMillis();

        if (modeDebug) {
            System.out.println("Heure de modification du fichier source : " + sourceDate);
            System.out.println("Heure de modification du fichier compilé : " + compilerDate);
        }

        return sourceDate > compilerDate;
    }

    /**
     * Transforme une cible compilée en son fichier source correspondant.
     * 
     * @param cible La cible compilée (ex. .class, .o).
     * @return Le fichier source correspondant.
     */
    public String transfoCible(String cible) {
        if (cible.endsWith(".class")) {
            return cible.replace(".class", ".java");
        } else if (cible.endsWith(".o")) {
            return cible.replace(".o", ".c");
        }
        return cible; 
    }
}
