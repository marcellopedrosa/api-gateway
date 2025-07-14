@echo off
echo Definindo variaveis de ambiente DEV/local............

setx CONFIG_SERVER_URI_DEV "http://localhost:8888" /M
setx WEB_SERVER_DEV "http://localhost.dev.com.br" /M
setx ZIPKIN_URL_DEV "http://lab.empresa.com.br:9411/api/v2/spans" /M

setx SPI_REALM_DEV "empresa-ad" /M
setx SPI_API_GATEWAY_CLIENT_ID_DEV "api-gateway" /M
setx SPI_API_GATEWAY_CLIENT_SECRET_DEV "YuqVdLA9Kr9LpLKcsU4Cyo9HzvHEW9f5" /M
setx SPI_URI_SERVER_DEV "http://localhost.dev.com.br/auth/realms/empresa-ad" /M

setx SPRING_CLOUD_EUREKA_HOST_DEV "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT_DEV "8761" /M
setx SPRING_CLOUD_EUREKA_PATH_DEV "/eureka" /M

echo Definindo variaveis de ambiente HML/Homologacao............

setx CONFIG_SERVER_URI_HML "http://localhost:8888" /M
setx WEB_SERVER_HML "http://localhost.dev.com.br" /M
setx ZIPKIN_URL_HML "http://lab.empresa.com.br:9411/api/v2/spans" /M

setx SPI_REALM_HML "empresa-ad" /M
setx SPI_API_GATEWAY_CLIENT_ID_HML "api-gateway" /M
setx SPI_API_GATEWAY_CLIENT_SECRET_HML "YuqVdLA9Kr9LpLKcsU4Cyo9HzvHEW9f5" /M
setx SPI_URI_SERVER_HML "http://localhost.dev.com.br/auth/realms/empresa-ad" /M

setx SPRING_CLOUD_EUREKA_HOST_HML "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT_HML "8761" /M
setx SPRING_CLOUD_EUREKA_PATH_HML "/eureka" /M

echo Definindo variaveis de ambiente PRD/Producao............

setx CONFIG_SERVER_URI_PRD "http://localhost:8888" /M
setx WEB_SERVER_PRD "http://localhost.dev.com.br" /M
setx ZIPKIN_URL_PRD "http://lab.empresa.com.br:9411/api/v2/spans" /M

setx SPI_REALM_PRD "empresa-ad" /M
setx SPI_API_GATEWAY_CLIENT_ID_PRD "api-gateway" /M
setx SPI_API_GATEWAY_CLIENT_SECRET_PRD "YuqVdLA9Kr9LpLKcsU4Cyo9HzvHEW9f5" /M
setx SPI_URI_SERVER_PRD "http://localhost.dev.com.br/auth/realms/empresa-ad" /M

setx SPRING_CLOUD_EUREKA_HOST_PRD "http://localhost" /M
setx SPRING_CLOUD_EUREKA_PORT_PRD "8761" /M
setx SPRING_CLOUD_EUREKA_PATH_PRD "/eureka" /M

echo Definindo variaveis SERVER LOG............

setx SERVER_LOG_HOST "logs.empresa.com.br" /M
setx SERVER_LOG_PORT "12201" /M

echo Variaveis definidas com sucesso!
pause
