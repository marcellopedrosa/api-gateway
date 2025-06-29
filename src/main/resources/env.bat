@echo off
echo Definindo variáveis de ambiente...

setx CONFIG_SERVER "http://localhost:8888" /M
setx ZIPKIN_URL "http://lab.fachesf.com.br:9411/api/v2/spans" /M
setx SERVER_LOG_HOST "logs.fachesf.com.br" /M
setx SERVER_LOG_PORT "12201" /M

setx SPRING_CLOUD_EUREKA_HOST "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT "8761" /M
setx SPRING_CLOUD_EUREKA_PATH "/eureka" /M

setx KEYCLOAK_REALM "fachesf-ad" /M
setx KEYCLOAK_API_GATEWAY_CLIENT_ID "api-gateway" /M
setx KEYCLOAK_API_GATEWAY_CLIENT_SECRET "YuqVdLA9Kr9LpLKcsU4Cyo9HzvHEW9f5" /M
setx KEYCLOAK_URI_SERVER "http://localhost.com.br/auth/realms/fachesf-ad" /M

echo Variaveis definidas com sucesso!
pause
