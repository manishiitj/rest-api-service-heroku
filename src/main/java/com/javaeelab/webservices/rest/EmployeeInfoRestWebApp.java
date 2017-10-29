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

    private final static String SERVER_LISTENING_PORT_KEY = "server.listening.port";

    private final static String APPLICATION_NAME = "application.name";

    private final static PropertyHelper propertyHelper = new PropertyHelper();

    public static void main( String[] args ) {

        BasicConfigurator.configure();
        logger.info(propertyHelper.getPropertyValue(APPLICATION_NAME));
        logger.info("Starting server.." );

        ResourceConfig resourceConfig = new ResourceConfig();

        resourceConfig.packages(EmployeeInfoApi.class.getPackage().getName());
        resourceConfig.register(JacksonFeature.class);

        ServletHolder servlet = new ServletHolder(new ServletContainer(resourceConfig));


        String PORT = System.getenv("PORT");

        logger.info("Environment PORT: " + PORT);

        int Port = Integer.parseInt(PORT);



        // Read server listening port value from property file
        String propertyValue = propertyHelper.getPropertyValue(SERVER_LISTENING_PORT_KEY);

        if (!StringUtils.isEmpty(propertyValue)) {

            int SERVER_LISTENING_PORT = Integer.parseInt(propertyValue);
            //Server server = new Server(SERVER_LISTENING_PORT);

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
}
