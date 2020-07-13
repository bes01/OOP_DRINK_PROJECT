package Drinks.Listeners;

import Drinks.Model.DataBase.Connector;
import Drinks.Model.DataBase.DataRetrieve.UserData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextCreationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("connector", Connector.getInstance());
        servletContextEvent.getServletContext().setAttribute("userData", new UserData());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
