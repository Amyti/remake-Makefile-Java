### VARIABLES ###
JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none -d build

JVM = java
JVMFLAGS = -cp build

SRC = src
BUILD = build

### REGLES ESSENTIELLES ###
all: $(BUILD)/Bake.class

$(BUILD)/Bake.class: $(SRC)/Bake.java $(BUILD)/BakeExecute.class $(BUILD)/BakeReader.class $(BUILD)/GrapheDep.class
	$(JC) $(JCFLAGS) $(SRC)/Bake.java

$(BUILD)/BakeExecute.class: $(SRC)/BakeExecute.java $(BUILD)/BakeComparator.class
	$(JC) $(JCFLAGS) $(SRC)/BakeExecute.java

$(BUILD)/BakeComparator.class: $(SRC)/BakeComparator.java
	$(JC) $(JCFLAGS) $(SRC)/BakeComparator.java

$(BUILD)/BakeReader.class: $(SRC)/BakeReader.java
	$(JC) $(JCFLAGS) $(SRC)/BakeReader.java

$(BUILD)/GrapheDep.class: $(SRC)/GrapheDep.java
	$(JC) $(JCFLAGS) $(SRC)/GrapheDep.java

### REGLES OPTIONNELLES ###
clean:
	-rm -rf $(BUILD)/*.class

### BUTS FACTICES ###
.PHONY: all clean
