package ua.kobzev.theatre.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by kkobziev on 3/19/16.
 */
public class WebMVCInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MainConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("spring.profiles.active", "JDBCTEMPLATE");
        //MYBATIS HIBERNATE JDBCTEMPLATE

//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(MainConfiguration.class);
//        ctx.setServletContext(servletContext);
//        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
//        servlet.setApplicationContext(ctx);
//        servlet.setTransformWsdlLocations(true);
//        Dynamic dynamic = servletContext.addServlet("dispatcher",servlet);
//        dynamic.addMapping("/soapws/*");
//        dynamic.setLoadOnStartup(1);
    }

    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }

    private static final String LOCATION = null;//"/Users/kkobziev/Documents/Projects/MovieTheatre/managing-a-movie-theater/"; // Temporary location where files will be stored

    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
}
