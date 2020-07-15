package Drinks.Controllers.Drink;

import Drinks.Model.Containers.Drink;
import Drinks.Model.DataBase.DrinkDao.TheDrinkData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShowDrinkController {

    @GetMapping(value = "/Drink")
    public ModelAndView getDrinkPage(HttpServletRequest request){
        String drinkId = request.getParameter("drinkId");
        ModelAndView mw = new ModelAndView("/Drink");
        TheDrinkData dt = new TheDrinkData();
        Drink dr = dt.getDrink(drinkId);
        mw.addObject("drink");
        return mw;
    }

    @PostMapping(value = "/Drink")
    public ModelAndView postDrinkPage(HttpServletRequest request){
        return null;
    }
}
