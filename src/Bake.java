import java.util.*;
import java.lang.Thread;


public class Bake {
    private String bakefile = "Bakefile";
    private GrapheDep graphe;
    private List<String> ordre;
    private BakeReader read;
    private BakeExecute exec;


    public Bake(){
        try {
            read = new BakeReader(bakefile);

            graphe = new GrapheDep(read.getCibles());

            if (graphe.detecterCycle()) {
                System.out.println("Dependances ciculaire détecté !");
            } else {
                System.out.println("Aucun cycle détecté !");
                Thread.sleep(2000);
            }

            ordre = graphe.obtenirOrdreDeConstruction();

            System.out.println("Ordre de construction : " + ordre);

            exec = new BakeExecute(ordre, read.getCommandes());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("-d")) {
            System.out.println("Mode débogage activé.");

        } else {
            
            try{
                System.out.println("------------- Exécution en mode normale -------------");
                Thread.sleep(1000);
                new Bake();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }


        
    }
}