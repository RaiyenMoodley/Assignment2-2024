SRC_DIR := src
BIN_DIR := bin

JC := javac
JFLAGS := -g
CLASSPATH := $(SRC_DIR)

JAVA_FILE := $(wildcard $(SRC_DIR)/*.java)

CLASS_FILES := $(patsubst $(SRC_DIR)/%.java, $(BIN_DIR)/%.class, $(JAVA_FILE))

all: $(CLASS_FILES)

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java | $(BIN_DIR)
	$(JC) $(JFLAGS) -cp $(CLASSPATH) -d $(BIN_DIR) $<

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

clean:
	rm -rf $(BIN_DIR))

.PHONY: all clean