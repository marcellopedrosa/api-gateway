package br.com.csc.api_gateway.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ch.qos.logback.classic.Logger;

import de.siegmar.logbackgelf.GelfEncoder;
import de.siegmar.logbackgelf.GelfUdpAppender;
import jakarta.annotation.PostConstruct;

@Configuration
public class LoggingConfig {
    @Autowired
    Environment env;

    @PostConstruct
    public void configureLogback() {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        GelfUdpAppender gelfAppender = new GelfUdpAppender();
        GelfEncoder encoder = new GelfEncoder();

        PatternLayout layout = new PatternLayout();
        layout.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{36} - %msg%n");
        layout.setContext(loggerContext);
        layout.start();

        encoder.setFullPatternLayout(layout);
        encoder.addStaticField("app_name:" + env.getProperty("spring.application.name", "default-app"));
        encoder.setContext(loggerContext);
        encoder.start();

        String graylogHost = System.getenv("SERVER_LOG_HOST");
        if (graylogHost == null || graylogHost.isBlank()) {
            throw new IllegalStateException("Variável de ambiente SERVER_LOG_HOST não definida.");
        }
        gelfAppender.setGraylogHost(graylogHost);

        String graylogPort = System.getenv("SERVER_LOG_PORT");
        if (graylogPort == null || graylogPort.isBlank()) {
            throw new IllegalStateException("Variável de ambiente SERVER_LOG_PORT não definida.");
        }
        gelfAppender.setGraylogPort(Integer.parseInt(graylogPort));
        
        gelfAppender.setContext(loggerContext);
        gelfAppender.setEncoder(encoder);
        gelfAppender.start();

        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(gelfAppender);

    }
}
