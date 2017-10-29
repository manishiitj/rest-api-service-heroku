package com.javaeelab.webservices.rest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.apache.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * @author azam.akram
 *
 * Server to receive and Respond API calls
 *
 */

public class EmployeeInfoRestWebApp
{
    private final static Logger logger = Logger.getLogger(EmployeeInfoRestWebApp.class);

    private final static String APPLICATION_NAME = "application.name";

    private static String PORT = System.getenv("PORT");

    private final static PropertyHelper propertyHelper = new PropertyHelper();


    public static void main( String[] args ) {

        logger.info("Starting " + propertyHelper.getPropertyValue(APPLICATION_NAME));

        logger.info("Read port from environment variable PORT: " + PORT);
        if (StringUtils.isEmpty(PORT)) {
            logger.error("Problem in reading port value from environment variable PORT: ");
            return;
        }

        int Port = Integer.parseInt(PORT);

        BasicConfigurator.configure();
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages(EmployeeInfoApi.class.getPackage().getName());
        resourceConfig.register(JacksonFeature.class);

        ServletHolder servlet = new ServletHolder(new ServletContainer(resourceConfig));
        Server server = new Server(Port);

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            logger.info("Server listening on port: " + Port );
            server.start();
            server.join();

        } catch (Exception e) {
            logger.error("Problem in running server: " + e.getMessage());
        } finally {
            server.destroy();
        }
    }
}
