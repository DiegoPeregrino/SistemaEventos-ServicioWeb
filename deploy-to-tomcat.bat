@echo off
REM Script de despliegue - ajustar rutas antes de usar
set PROJECT_DIR=C:\Users\trigo\Documents\GitHub\PROYECTO_FINAL_DSWII
set TOMCAT_DIR=C:\apache-tomcat-9.0.XX

cd /d "%PROJECT_DIR%"

REM Copiar clases compiladas si existen
xcopy /E /I build\classes src\main\webapp\WEB-INF\classes

REM Crear WAR
if defined JAVA_HOME (
  "%JAVA_HOME%\bin\jar.exe" cvf "%PROJECT_DIR%\SistemaEventos.war" -C src\main\webapp .
) else (
  jar cvf "%PROJECT_DIR%\SistemaEventos.war" -C src\main\webapp .
)

REM Copiar WAR a Tomcat
copy "%PROJECT_DIR%\SistemaEventos.war" "%TOMCAT_DIR%\webapps\"

echo Deploy completo. Inicia Tomcat si no está corriendo:
echo %TOMCAT_DIR%\bin\startup.bat

pause
