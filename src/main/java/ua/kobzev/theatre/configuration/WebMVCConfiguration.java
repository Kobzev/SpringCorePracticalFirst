package ua.kobzev.theatre.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Created by kkobziev on 3/20/16.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration {

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
        return templateEngine;
    }
    @Bean(name="viewResolver")
    public ViewResolver getViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(getTemplateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean(name = "pdfReport")
    public JasperReportsPdfView getJasperReportsPdfView(){
        JasperReportsPdfView pdfView = new JasperReportsPdfView();
        pdfView.setUrl("classpath:tickets-template.jrxml");
        pdfView.setReportDataKey("datasource");
        return pdfView;
    }
}
