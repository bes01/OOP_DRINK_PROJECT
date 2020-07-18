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
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            response.sendRedirect("/");
            return null;
        }
        String userId = request.getParameter("user_id");
        ModelAndView mw = new ModelAndView("/Drink/UserPage");
        User otherUser = new UserData().searchUserById(Integer.valueOf(userId));
        if(otherUser == null){
            response.sendRedirect("/Drink/UserNotFound");
            return null;
        }
        mw.addObject("user", otherUser);
        return mw;
    }

    @PostMapping(value = "/User")
    public void postUserPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

    }
}
