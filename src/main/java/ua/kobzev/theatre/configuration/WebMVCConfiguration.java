package ua.kobzev.theatre.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import ua.kobzev.theatre.converters.PdfMessageConverter;
import ua.kobzev.theatre.viewresolvers.PdfViewResolver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kkobziev on 3/20/16.
 */
@Configuration
@EnableWebMvc
public class WebMVCConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.add(new PdfMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        super.configureMessageConverters(messageConverters);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("mediaType").
                ignoreAcceptHeader(true).
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON).
                mediaType("pdf", MediaType.valueOf("application/pdf"));
        super.configureContentNegotiation(configurer);
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
        ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setOrder(2);
        contentNegotiatingViewResolver.setDefaultViews(Arrays.asList(new View[]{new MappingJackson2JsonView()}));
        contentNegotiatingViewResolver.setViewResolvers(Arrays.asList(new ViewResolver[]{new PdfViewResolver()} ));

//        <property name="mediaTypes">
//        <map>
//        <entry key="atom" value="application/atom+xml"/>
//        <entry key="html" value="text/html"/>
//        <entry key="json" value="application/json"/>
//        </map>
//        </property>
//        <property name="defaultViews">
//        <list>
//        <bean class="o.s.web.servlet.view.json.MappingJacksonJsonView"/>
//        </list>
//        </property>

        return contentNegotiatingViewResolver;
    }

	@Bean(name = "beanNameViewResolver")
	public ViewResolver viewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();
		viewResolver.setOrder(0);

        return viewResolver;
	}

    //start Thymeleaf specific configuration
    @Bean(name ="templateResolver")
    public ServletContextTemplateResolver getTemplateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("XHTML");
        return templateResolver;
    }
    @Bean(name ="templateEngine")
    public SpringTemplateEngine getTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }
    @Bean(name="viewResolver")
    public ViewResolver getViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(getTemplateEngine());
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver resolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean(name = "pdfReport")
    public JasperReportsPdfView getJasperReportsPdfView(){
        JasperReportsPdfView pdfView = new JasperReportsPdfView();
        pdfView.setUrl("classpath:tickets-template.jrxml");
        pdfView.setReportDataKey("datasource");
        return pdfView;
    }
}
