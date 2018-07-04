package com.mxt.configuration;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.charset.Charset;

@Configuration
public class TomcatConfig {
    @Value("${server.ssl.port}")
    private int sslPort;
    @Value("${server.https.keyStore}")
    private String keyStorePath;
    @Value("${server.https.keyAlias}")
    private String keyAlias;
    @Value("${server.https.keyPassword}")
    private String keyPassword;


    @Bean
    public EmbeddedServletContainerFactory containerFactory() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setUriEncoding(Charset.forName("utf-8"));
        //tomcat.addAdditionalTomcatConnectors(createSSLConnector());//添加tomcat connector 监听ssl
        return tomcat;
    }

    private Connector createSSLConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File truststore = new File(keyStorePath);
            connector.setScheme("https");
            protocol.setSSLEnabled(true);
            connector.setSecure(true);
            connector.setPort(sslPort);
            protocol.setKeystoreFile(truststore.getAbsolutePath());
            protocol.setKeystorePass(keyPassword);
            protocol.setKeyAlias(keyAlias);
            return connector;
        } catch (Exception ex) {
            throw new IllegalStateException("cant access keystore: [" + "keystore" + "]  ", ex);
        }
    }
}
