public class Utils {
    public static void resultat(int age) {
    if (age < 0) {
        System.out.println("nan!");
    } 
    else if (age <= 15) {
        System.out.println("T'es jeune!");
    } 
    else if (age <= 50) {
        System.out.println("T'es normal!");
    } 
    else if (age <= 101) {
        System.out.println("BOF BOF !");
    } 
    else {
        System.out.println("Longue vie à toi!");
    }
    
    System.out.println("affiché depuis la classe Utils.");
}

}
