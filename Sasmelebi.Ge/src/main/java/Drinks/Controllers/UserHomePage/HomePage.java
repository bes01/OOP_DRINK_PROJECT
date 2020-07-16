package Drinks.Controllers.UserHomePage;

import Drinks.Model.Containers.User;
import Drinks.Model.DataBase.DataRemove.DrinkRemoval;
import Drinks.Model.DataBase.DataRetrieve.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class HomePage {

    @GetMapping("/HomePage")
    public ModelAndView renderHomePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        //sturuas skriptebs tu gaushvebt bazaze komenti moxsenit amas da wamoighebs sturuas users
        request.getSession().setAttribute("user_id", 3);
        if (request.getSession().getAttribute("user_id") == null)
            response.sendRedirect("/Login");
        UserData data = new UserData();
        User user = data.searchUserById((int) request.getSession().getAttribute("user_id"));
        ModelAndView mav = new ModelAndView("/UserHomePage/homePage");
        mav.addObject("user", user);
        mav.addObject("favourites",user.getFavourites());
        return mav;
    }

    @GetMapping("/removeFavourite")
    public void removeFavouriteDrink(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int drink_id = Integer.parseInt(request.getParameter("drink_id"));
        int user_id = (int) request.getSession().getAttribute("user_id");
        DrinkRemoval drinkRemoval = new DrinkRemoval();
        drinkRemoval.removeFromFavourites(drink_id,user_id);
        response.sendRedirect("/HomePage");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user_id") != null)
            request.getSession().removeAttribute("user_id");
        response.sendRedirect("/Login");
    }
}
