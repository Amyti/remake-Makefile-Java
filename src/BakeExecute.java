import java.io.*;
import java.util.*;
import java.lang.Thread;
import java.io.IOException;
import java.lang.InterruptedException;

public class BakeExecute {
    private BakeComparator comparator;
    private List<String> ordre = new ArrayList<>();
    private HashMap<String, String> commandes = new HashMap<>();
    private boolean modeDebug;
    private Set<String> phonyCibles = new HashSet<>();;

    public BakeExecute(List<String> ordre, HashMap<String, String> commandes, Set<String> phonyCibles, boolean modeDebug) {
        try {
            this.ordre = ordre;
            this.commandes = commandes;
            this.phonyCibles = phonyCibles;
            this.modeDebug = modeDebug;
            comparator = new BakeComparator(modeDebug);

            Thread.sleep(1500);
            System.out.println("--------- Application compilé avec succees ! ---------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void executerCommandesCible(List<String> cibles) {
    try {
        for (String cible : cibles) {

            String commande = commandes.get(cible);
            if (commande == null) {
                System.out.println("PAs de commande pour la cible " + cible);
                continue; 
            }

            String ficSource = comparator.transfoCible(cible);

            if (comparator.verifRecompilation(ficSource, cible)) {
                System.out.println(commande);

                if (!executerCommande(commande)) {
                    System.out.println("Erreur lors de l'exécution de la commande pour la cible : " + cible);
                    return; 
                }
            } else {
                System.out.println("Pas de recompilation nécessaire pour la cible : " + cible);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void executerCommandes() {
        try {
            for (String cible : ordre) {

                if (phonyCibles.contains(cible)) {
                    continue; 
                }
                String commande = commandes.get(cible);
                String FicSource = comparator.transfoCible(cible);
                if (commande != null && comparator.verifRecompilation(FicSource, cible)) {
                    System.out.println(commande);
                    if (!executerCommande(commande)) {
                        System.err.println("Erreur lors de l'exécution de la commande.");
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean executerCommande(String commande) {
        String[] command = commande.split(" ");

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            try {
                Process process = processBuilder.start();

                int exitCode = process.waitFor();
                return exitCode == 0;
            } catch (IOException | InterruptedException e) {
                return false; 
            }
        
        
    }

    
}
