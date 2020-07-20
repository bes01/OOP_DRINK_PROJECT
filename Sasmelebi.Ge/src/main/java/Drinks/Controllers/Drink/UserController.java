package Drinks.Controllers.Drink;

import Drinks.Model.Containers.User;
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
public class UserController {
    @GetMapping(value = "/User")
    public ModelAndView getUserPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        if(request.getSession().getAttribute("user_id") == null) {
            response.sendRedirect("/");
            return null;
        }
        String userId = request.getParameter("user_id");
        ModelAndView mw = new ModelAndView("/Drink/UserPage");
        User otherUser = null;
        try {
            otherUser = new UserData().searchUserById(Integer.valueOf(userId));
        } catch (SQLException e){
            response.sendRedirect("/Drink/UserNotFound");
            return null;
        }
        mw.addObject("user", otherUser);
        return mw;
    }

    @GetMapping(value = "/Drink/UserNotFound")
    public ModelAndView getUserNotFoundPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("user_id") == null) {
            response.sendRedirect("/");
            return null;
        }
        ModelAndView mw = new ModelAndView("/Drink/UserNotFound");
        return mw;
    }

    @PostMapping(value = "/User")
    public void postUserPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

    }
}
