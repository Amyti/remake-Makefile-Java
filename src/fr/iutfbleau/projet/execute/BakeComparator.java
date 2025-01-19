package fr.iutfbleau.projet.execute;

import java.nio.file.*;
import java.io.IOException;
import java.util.HashMap;
import java.lang.InterruptedException;
import fr.iutfbleau.projet.execute.BakeComparator;
public class BakeComparator {

    private boolean modeDebug;

    public BakeComparator(boolean modeDebug) {
        this.modeDebug = modeDebug;
    }

   
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

   
    public String transfoCible(String cible) {
        if (cible.endsWith(".class")) {
            return cible.replace(".class", ".java");
        } else if (cible.endsWith(".o")) {
            return cible.replace(".o", ".c");
        }
        return cible; 
    }
}
