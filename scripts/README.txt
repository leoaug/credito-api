
1 - Criar a pasta no servidor e colocar os seguintes arquivos:

 - env.properties
 - guia-atividades.jar (gerado dentro da pasta target)
 - start.bat (windows) ou start.sh (linux)
 
2 - Dentro do arquivo start.bat ou start.sh, mudar o caminho do JDK caso queira:
 
 - start.bat (comentar a linha usando a palavra 'REM')
 
 	REM set JAVA_HOME=C:\Users\c1260311\jdk-21.0.6
 	set "JAVA_HOME=%JAVA_HOME%"
 	
 - start.sh (comentar a linha usando o caracter '#')
  
  	# JAVA_HOME="/home/seuusuario/jdk-21.0.6"
 	export JAVA_HOME
 	