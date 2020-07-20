package Drinks.Controllers.Drink;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.DrinkFull;
import Drinks.Model.Containers.User;
import Drinks.Model.DataBase.DataRetrieve.UserData;
import Drinks.Model.DataBase.DrinkDao.FavouritesData;
import Drinks.Model.DataBase.DrinkDao.RankingData;
import Drinks.Model.DataBase.DrinkDao.TheDrinkData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class DrinkController {

    @GetMapping(value = "/Drink")
    public ModelAndView getDrinkPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        if(request.getSession().getAttribute("user_id") == null) {
            response.sendRedirect("/");
            return null;
        }
        User user = new UserData().searchUserById
                ((Integer)request.getSession().getAttribute("user_id"));
        String drinkId = request.getParameter("drink_id");
        ModelAndView mw = new ModelAndView("/Drink/showDrink");
        TheDrinkData drinkDt = new TheDrinkData();
        Drink dr = drinkDt.getDrink(Integer.valueOf(drinkId));
        if(dr == null){
            response.sendRedirect("/Drink/DrinkNotFound");
            return null;
        }
        mw.addObject("drink", dr);
        RankingData rankingDt = new RankingData();
        DrinkFull drFull = new DrinkFull(rankingDt.sumRated(dr.getDrinkId()),
                rankingDt.userRated(dr.getDrinkId(), user.getUserId()),
                new UserData().searchUserById(dr.getAuthorId()),
                drinkDt.getParentDrink(dr.getDrinkId()));
        mw.addObject("wrappedDrinkInfo", drFull);
        return mw;
    }

    @GetMapping(value = "/Drink/DrinkNotFound")
    public ModelAndView getNotFoundPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user_id") == null) {
            response.sendRedirect("/");
            return null;
        }
        ModelAndView mw = new ModelAndView("/Drink/DrinkNotFound");
        return mw;
    }

    @PostMapping(value = "/Drink/Ranking")
    public void postDrinkRankingPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String drinkId = request.getParameter("drink_id");
        User user = new UserData().searchUserById
                ((Integer)request.getSession().getAttribute("user_id"));
        Drink dr = new TheDrinkData().getDrink(Integer.valueOf(drinkId));
        if(dr == null){
            response.sendRedirect("/Drink/DrinkNotFound");
        }
        String ranking = request.getParameter("ranking_sc");
        new RankingData().justRated(dr.getDrinkId(), user.getUserId(), Integer.valueOf(ranking));
        response.sendRedirect("/Drink?drink_id=" + drinkId);
    }

    @PostMapping(value = "/Drink/extend")
    public void postDrinkExtendPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String drinkId = request.getParameter("drink_id");
        response.sendRedirect("/addDrink/extend?drink_id=" + drinkId);
    }

    @PostMapping(value = "/Drink/favourite")
    public void postDrinkFavouritePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String drinkId = request.getParameter("drink_id");
        User user = new UserData().searchUserById
                ((Integer)request.getSession().getAttribute("user_id"));
        new FavouritesData().addInFavourites(Integer.valueOf(drinkId), user.getUserId());
        response.sendRedirect("/HomePage");
    }

    @GetMapping(value = "/Drink/Image")
    public ModelAndView getDrinkImagePage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if(request.getSession().getAttribute("user_id") == null) {
            response.sendRedirect("/");
            return null;
        }
        ModelAndView mw = new ModelAndView("/Drink/DrinkImage");
        return mw;
    }

    @PostMapping(value = "/Drink/Image")
    public void postDrinkImagePage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("here i am");
        response.sendRedirect("/Drink/Image");
    }
}
