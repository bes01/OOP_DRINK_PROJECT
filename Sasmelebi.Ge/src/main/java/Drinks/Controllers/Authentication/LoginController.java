package Drinks.Controllers.Authentication;

import Drinks.Model.Containers.User;
import Drinks.Model.DataBase.DataRetrieve.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class LoginController {
    @GetMapping("")
    public ModelAndView openLoginPage(HttpServletRequest request,
                                      HttpServletResponse response,
                                      HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView("/Authentication/LoginPage");
        if (request.getSession().getAttribute("user_id") != null) {
            response.sendRedirect("/HomePage");
            return null;
        }
        return mav;
    }

    @PostMapping("")
    public ModelAndView login(HttpServletRequest request,
                              HttpServletResponse response,
                              HttpSession session, @RequestParam String username, @RequestParam String password)
            throws SQLException, IOException {
        ModelAndView mav = new ModelAndView("/Authentication/LoginPage");
        if (username.equals("")) {
            mav.addObject("error", "Authentication failed: Username should not be empty");
            return mav;
        }
        if (password.equals("")) {
            mav.addObject("error", "Authentication failed: Password should not be empty");
            mav.addObject("username", username);
            return mav;
        }
        UserData userdt = new UserData();
        User user = userdt.searchUserByNickname(username);
        if (user == null) {
            mav.addObject("error", "Authentication failed: User not found");
            mav.addObject("username", username);
            return mav;
        }
        if (!user.getPassword().equals(password)) {
            mav.addObject("error", "Authentication failed: Incorrect password provided");
            mav.addObject("username", username);
            return mav;
        }
        session.setAttribute("user_id", user.getUserId());
        response.sendRedirect("/HomePage");
        return mav;
    }
}
