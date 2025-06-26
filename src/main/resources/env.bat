@echo off
echo Definindo variáveis de ambiente...

setx SPRING_CLOUD_EUREKA_HOST "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT "8761" /M
setx SPRING_CLOUD_EUREKA_PATH "/eureka" /M

setx KEYCLOAK_REALM "realm-empresa" /M
setx KEYCLOAK_API_GATEWAY_CLIENT_ID "api-gateway" /M
setx KEYCLOAK_API_GATEWAY_CLIENT_SECRET "YuqVdLA9Kr9LpLKcsU4Cyo9HzvHEW9f5" /M
setx KEYCLOAK_URI_SERVER "http://localhost.com.br/auth/realms/realm-empresa" /M

echo Variaveis definidas com sucesso!
pause
