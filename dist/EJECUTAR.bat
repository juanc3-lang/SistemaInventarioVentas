@echo off
cd /d %~dp0
java -cp ".;SistemaInventarioVentas.jar;lib/*" sistema.Main
pause