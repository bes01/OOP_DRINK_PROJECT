package Drinks.Controllers.Search;

import Drinks.Constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.text.spi.JavaTimeDateTimePatternProvider;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SearchController {

    @RequestMapping(value="/Search" ,  method = RequestMethod.GET)
    public ModelAndView getSearchPage(HttpServletRequest request){
        ModelAndView ret = new ModelAndView("/Search/Search");
        String drinkName = request.getParameter("drink_name");
        String[] ingredientNames = request.getParameterValues("ingredient");
        ret.addObject("last_search_name", drinkName);
        ret.addObject("last_ingredients",ingredientNames);
        return ret;
    }
}
