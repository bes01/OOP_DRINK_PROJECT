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
public class ForgotPasswordController {
    @GetMapping("/ForgotPassword")
    public ModelAndView openForgotPasswordPage(){
        ModelAndView mav = new ModelAndView("/Authentication/ForgotPasswordPage");
        return mav;
    }


    @PostMapping("/ForgotPassword")
    public ModelAndView post(HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             @RequestParam String mail) throws SQLException, IOException {
        ModelAndView mav = new ModelAndView("/Authentication/ForgotPasswordPage");
        UserData userdt = new UserData();
        User user = userdt.searchUserByMail(mail);
        if (user == null) {
            mav.addObject("error", "No such user with the mail:  " + mail);
            return mav;
        }
        SendEmail.send(mail, user.getFirstName(), user.getLastName(), user.getNickName(), user.getPassword());
        response.sendRedirect("/");
        return mav;
    }
}
