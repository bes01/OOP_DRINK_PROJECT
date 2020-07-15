package Drinks.Controllers.Search;

import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.DataRetrieve.IngredientData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class SearchController {

    @RequestMapping(value = "/Search", method = RequestMethod.GET)
    public ModelAndView getSearchPage(HttpServletRequest request) {

        ModelAndView ret = new ModelAndView("/Search/Search");

        String drinkName = request.getParameter("drink_name");
        String[] ingredientIdsTemp = request.getParameterValues("ingredient");
        int[] ingredientIds = getIngredientIds(ingredientIdsTemp);

        IngredientData db = (IngredientData) request.getServletContext().getAttribute("ingredientData");
        ArrayList<Ingredient> ingredients = db.getAllIngredients();

        ret.addObject("last_search_name", drinkName);
        ret.addObject("last_ingredients", ingredientIds);
        ret.addObject("all_ingredients", ingredients);

        return ret;
    }
    private int[] getIngredientIds(String[] parameters ){
        int[] ingredientIds = null;
        if (parameters != null) {
            ingredientIds = new int[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                ingredientIds[i] = Integer.parseInt(parameters[i]);
            }
        }
        return ingredientIds;

    }
}

