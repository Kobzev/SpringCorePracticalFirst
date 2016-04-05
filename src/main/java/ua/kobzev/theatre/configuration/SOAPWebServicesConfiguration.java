package ua.kobzev.theatre.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

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

    @Bean(name = "users")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UsersPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("");
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("./schema/user.xsd"));
    }
}
