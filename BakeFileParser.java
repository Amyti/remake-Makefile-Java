import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BakeFileParser{
    public static void parseBakeFile(String filePath, Map<String, String> variables, Map<String, Rule> rules) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Rule ruleActuel = null;

        while((line = reader.readLine()) != null){
            line = line.trim();

            /*ignorer les commentaires */
            if(line.isEmpty() || line.startsWith("#")){
                continue;
            }


            /*Gestion des variables */
            if (line.contains("=")){
                String[] parts = line.split("=",2);
                String key = parts[0].trim();
                String value = parts[1].trim();
                variables.put(key, value);
                continue;
            }

            /*Gestion des regles */
            if(line.contains(":")){
                String[] parts = line.split(":",2);
                String target = parts[0].trim();
                List<String> dependances = new ArrayList<>();
                if(parts[1] != null && !parts[1].isEmpty()) {
                    dependances = Arrays.asList(parts[1].split("\\s+"));
                    ruleActuel = new Rule();
                    ruleActuel.target = target;
                    ruleActuel.dependances.addAll(dependances);
                    rules.put(target, ruleActuel);
                    continue;
                }
            }

            /*Gestion des commandes associées à une regle */
            if(ruleActuel != null && line.startsWith("\t")){
                ruleActuel.commandes.add(line.trim());
            } else {
                throw new IOException("Erreur de format dans le Bakefile à la ligne :" +line);
            }
        }
        reader.close();
    }

    public static void main(String[] args){
        String bakefilePath = "Bakefile";
        try{
            Map<String, String> variables =  new HashMap<>();
            Map<String, Rule> rules = new LinkedHashMap<>();
            parseBakeFile(bakefilePath, variables, rules);

            System.out.println("variables : "+variables);
            System.out.println("Régles :");
            for(Rule rule : rules.values()){
                System.out.println("Target:"+rule.target);
                System.out.println("Dependances:"+rule.dependances);
                System.out.println("commandes:" + rule.commandes);
            }
        } catch(IOException e){
            System.err.println("Erreur lors de la lecture du Bakefile: " + e.getMessage());
        }
    }
}