package Drinks.Controllers.Drink;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DrinkController {

    @GetMapping(value = "/Drink")
    public ModelAndView getDrinkPage(HttpServletRequest request){
        return null;
    }

    @PostMapping(value = "/Drink")
    public ModelAndView postDrinkPage(HttpServletRequest request){
        return null;
    }
}
