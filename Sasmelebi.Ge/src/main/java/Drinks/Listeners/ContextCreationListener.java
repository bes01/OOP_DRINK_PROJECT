package Drinks.Listeners;

import Drinks.Model.DataBase.Connector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextCreationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("connector", Connector.getInstance());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
