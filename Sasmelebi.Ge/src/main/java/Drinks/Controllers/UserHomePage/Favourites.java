package Drinks.Controllers.UserHomePage;

import Drinks.Model.DataBase.DataRemove.DrinkRemoval;
import Drinks.Model.DataBase.DataRetrieve.DrinkData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class Favourites {

    @GetMapping("/Favourites")
    public ModelAndView renderFavourites(HttpServletRequest request) throws SQLException {
        ModelAndView mav = new ModelAndView("/UserHomePage/favourites");
        int user_id = (int) request.getSession().getAttribute("user_id");
        DrinkData drinkData = new DrinkData();
        mav.addObject("favourites", drinkData.favourites(user_id));
        return mav;
    }

    @PostMapping("/Favourites")
    public void removeFavouriteDrink(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int drink_id = Integer.parseInt(request.getParameter("drink_id"));
        int user_id = (int) request.getSession().getAttribute("user_id");
        DrinkRemoval drinkRemoval = new DrinkRemoval();
        drinkRemoval.removeFromFavourites(drink_id,user_id);
        response.sendRedirect("/Favourites");
    }
}
