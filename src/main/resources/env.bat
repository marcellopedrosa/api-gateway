@echo off
echo Definindo variaveis de ambiente PRD/Producao...

setx GATEWAY_PORT "8080" /M
setx CONFIG_SERVER_URI "http://localhost:8888" /M
setx WEB_SERVER "http://localhost.com.br" /M
setx ZIPKIN_URL "http://lab.empresa.com.br:9411/api/v2/spans" /M

echo Definindo Server Log PRD/QA eh centralizado
setx SERVER_LOG_HOST "logs.empresa.com.br" /M
setx SERVER_LOG_PORT "12201" /M

setx SPI_REALM "empresa-ad" /M
setx SPI_API_GATEWAY_CLIENT_ID "admin-service" /M
setx SPI_API_GATEWAY_CLIENT_SECRET "01n2ug8OELqcP77NH0KoLzSCMc06OkwG" /M
setx SPI_URI_SERVER "http://localhost.com.br/auth/realms/empresa-ad" /M

setx SPRING_CLOUD_EUREKA_HOST "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT "8761" /M
setx SPRING_CLOUD_EUREKA_PATH "/eureka" /M

echo Definindo variaveis de ambiente QA/Qualidade/Homologacao...

setx GATEWAY_PORT_QA "8080" /M
setx CONFIG_SERVER_URI_QA "http://localhost:8888" /M
setx WEB_SERVER_QA "http://localhost.com.br" /M
setx ZIPKIN_URL_QA "http://lab.empresa.com.br:9411/api/v2/spans" /M

setx SPI_REALM_QA "empresa-ad" /M
setx SPI_URI_SERVER_QA "http://localhost.com.br/auth/realms/empresa-ad"
setx SPI_API_GATEWAY_CLIENT_SECRET_QA "01n2ug8OELqcP77NH0KoLzSCMc06OkwG" /M

setx SPRING_CLOUD_EUREKA_HOST_QA "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT_QA "8761" /M
setx SPRING_CLOUD_EUREKA_PATH_QA "/eureka" /M

echo Variaveis definidas com sucesso!
pause