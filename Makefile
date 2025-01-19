### VARIABLES ###
JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none -d build
JVM = java
JVMFLAGS = -cp build
PRO = src/fr/iutfbleau/projet
EXE = src/fr/iutfbleau/projet/execute
UTILS = src/fr/iutfbleau/projet/utils
BUILD = build/fr/iutfbleau/projet
BUILDE = build/fr/iutfbleau/projet/execute
BUILDU = build/fr/iutfbleau/projet/utils
CLASSPATH = -cp build
JARNAME = Bake.jar

### REGLES ESSENTIELLES ###

$(BUILD)/Bake.class: $(PRO)/Bake.java $(BUILDE)/BakeExecute.class $(BUILDE)/BakeComparator.class $(BUILDU)/BakeReader.class $(BUILDU)/GrapheDep.class
	$(JC) $(JCFLAGS) $(CLASSPATH) $(PRO)/Bake.java

$(BUILDE)/BakeExecute.class: $(EXE)/BakeExecute.java $(BUILDE)/BakeComparator.class
	$(JC) $(JCFLAGS) $(CLASSPATH) $(EXE)/BakeExecute.java

$(BUILDE)/BakeComparator.class: $(EXE)/BakeComparator.java
	$(JC) $(JCFLAGS) $(CLASSPATH) $(EXE)/BakeComparator.java

$(BUILDU)/BakeReader.class: $(UTILS)/BakeReader.java
	$(JC) $(JCFLAGS) $(CLASSPATH) $(UTILS)/BakeReader.java

$(BUILDU)/GrapheDep.class: $(UTILS)/GrapheDep.java
	$(JC) $(JCFLAGS) $(CLASSPATH) $(UTILS)/GrapheDep.java

all: $(BUILD)/Bake.class
	@echo "compilation terminée."

### REGLES OPTIONNELLES ###
clean:
	@echo "Suppression des fichiers compilés ..."
	-rm -rf $(BUILD)/*.class
	-rm -rf $(BUILDE)/*.class
	-rm -rf $(BUILDU)/*.class
	@echo "Nettoyage terminé !"

# Aide
help:
	@echo "Commandes disponibles :"
	@echo " - make          : Compiler le projet (cible 'all')"
	@echo " - make jar      : Générer l'archive JAR (${JARNAME})"
	@echo " - make javadoc  : Générer la documentation Javadoc"
	@echo " - make clean    : Nettoyer les fichiers compilés"

### BUTS FACTICES ###
.PHONY: all clean