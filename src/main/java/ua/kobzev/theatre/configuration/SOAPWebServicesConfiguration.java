package ua.kobzev.theatre.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * Created by kkobziev on 4/4/16.
 */
@Configuration
@EnableWs
public class SOAPWebServicesConfiguration extends WsConfigurerAdapter {
//    @Bean
//    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setApplicationContext(applicationContext);
//        servlet.setTransformWsdlLocations(true);
//        return new ServletRegistrationBean(servlet, "/ws/*");
//    }

//    @Bean(name = "countries")
//    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("CountriesPort");
//        wsdl11Definition.setLocationUri("/ws");
//        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
//        wsdl11Definition.setSchema(countriesSchema);
//        return wsdl11Definition;
//    }
//
//    @Bean
//    public XsdSchema countriesSchema() {
//        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
//    }

    @Bean
    public MessageDispatcherServlet wsDispatcher(){
        MessageDispatcherServlet dispatcherServlet = new MessageDispatcherServlet();
        return dispatcherServlet;
    }
}
