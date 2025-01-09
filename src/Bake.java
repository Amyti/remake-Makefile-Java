public class Bake {








    
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-d")) {
            System.out.println("Mode débogage activé.");
        } else {
            System.out.println("Exécution en mode normal.");
        }
        // Point d'entrée pour le programme
    }
}