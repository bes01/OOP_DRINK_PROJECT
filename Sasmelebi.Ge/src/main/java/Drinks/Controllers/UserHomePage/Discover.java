package Drinks.Controllers.UserHomePage;

import Drinks.Model.Containers.Drink;
import Drinks.Model.DataBase.DataRetrieve.DiscoverData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class Discover {
//    @GetMapping("/Discover")
//    public ModelAndView renderDiscoverPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        if (request.getSession().getAttribute("user") == null) {
//            response.sendRedirect("/logout");
//            return null;
//        }
//        ModelAndView maw = new ModelAndView("/UserHomePage/discover");
//        DiscoverData discoverData = new DiscoverData();
//        maw.addObject("recentlyAdded", discoverData.recentlyAdded());
//        maw.addObject("topDrinks", discoverData.topDrinks());
//        maw.addObject("randomDrinks", discoverData.randomDrinks());
//        return maw;
//    }
}
