@echo off
setlocal

REM Caminho do JDK - edite conforme sua instalação
REM set JAVA_HOME=C:\Users\c1260311\jdk-21.0.6
set "JAVA_HOME=%JAVA_HOME%"

REM Adiciona o JDK ao PATH se definido
if not defined JAVA_HOME (
    echo JAVA_HOME não definido. Usando java do sistema.
    java -version >nul 2>&1
    if errorlevel 1 (
        echo ERRO: Java não encontrado no PATH.
        pause
        exit /b 1
    )
) else (
    set "PATH=%JAVA_HOME%\bin;%PATH%"
)

REM Obtém o diretório onde o script está localizado
set "SCRIPT_DIR=%~dp0"
set "SCRIPT_DIR=%SCRIPT_DIR:~0,-1%"

REM Caminhos automáticos com base na pasta do script
set "JAR_PATH=%SCRIPT_DIR%\credito-api.jar"
set "ENV_PATH=file:%SCRIPT_DIR%\env.properties"

echo Iniciando credito-api...
java -jar "%JAR_PATH%" --spring.config.additional-location=%ENV_PATH%

pause

