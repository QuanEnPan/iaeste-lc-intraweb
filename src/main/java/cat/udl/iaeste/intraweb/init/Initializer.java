package cat.udl.iaeste.intraweb.init;

import javax.servlet.*;

import cat.udl.iaeste.intraweb.config.IaesteIntrawebAppContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.EnumSet;

public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(IaesteIntrawebAppContext.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/api/*");

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

        FilterRegistration.Dynamic security = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
        security.addMappingForUrlPatterns(dispatcherTypes, true, "/api/*");

        FilterRegistration.Dynamic httpMethod = servletContext.addFilter("httpMethodFilter", new HiddenHttpMethodFilter());
        httpMethod.addMappingForUrlPatterns(dispatcherTypes, true, "/api/*");

        servletContext.addListener(new ContextLoaderListener(rootContext));
    }

}
