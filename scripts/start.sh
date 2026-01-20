#!/bin/bash

# Caminho do JDK - edite se quiser forçar uma versão
# JAVA_HOME="/home/seuusuario/jdk-21.0.6"
export JAVA_HOME

if [ -z "$JAVA_HOME" ]; then
    echo "JAVA_HOME não definido. Usando java do sistema."
    if ! command -v java &> /dev/null; then
        echo "ERRO: Java não encontrado no PATH."
        exit 1
    fi
else
    export PATH="$JAVA_HOME/bin:$PATH"
fi

# Obtém o diretório onde está o script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Caminhos dinâmicos
JAR_PATH="$SCRIPT_DIR/credito-api.jar"
ENV_PATH="file:$SCRIPT_DIR/env.properties"

echo "Iniciando credito-api..."
java -jar "$JAR_PATH" --spring.config.additional-location="$ENV_PATH"

