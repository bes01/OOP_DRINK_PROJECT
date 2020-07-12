package Drinks.Listeners;

import Drinks.Model.DataBase.Connector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextCreationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("connector", new Connector());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
