@echo off
echo Definindo variáveis de ambiente...

setx SPRING_CLOUD_EUREKA_URI_SERVER "http://localhost:8761/eureka" /M

echo Variaveis definidas com sucesso!
pause
