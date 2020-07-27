package Drinks.Listeners;

import Drinks.Model.DataBase.Connector;
import Drinks.Model.DataBase.DataRetrieve.DrinkData;
import Drinks.Model.DataBase.DataRetrieve.IngredientData;
import Drinks.Model.DataBase.DataRetrieve.UserData;
import Drinks.Model.DataBase.RecipeDao.IngredientPrefix;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextCreationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute("connector", Connector.getInstance());
        servletContextEvent.getServletContext().setAttribute("userData", new UserData());
        servletContextEvent.getServletContext().setAttribute("ingredientData", new IngredientData());
        servletContextEvent.getServletContext().setAttribute("drinkData",new DrinkData());
        servletContextEvent.getServletContext().setAttribute("IngredientPrefix",new IngredientPrefix());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
