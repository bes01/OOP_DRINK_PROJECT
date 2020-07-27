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
public class RegisterController {
    @GetMapping("/Register")
    public ModelAndView openRegisterPage(){
        ModelAndView mav = new ModelAndView("/Authentication/RegisterPage");
        return mav;
    }


    @PostMapping("/Register")
    public ModelAndView post(HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session,
                             @RequestParam String name,
                             @RequestParam String last_name,
                             @RequestParam String username,
                             @RequestParam String sex,
                             @RequestParam int age,
                             @RequestParam String mail,
                             @RequestParam String password,
                             @RequestParam String repeat_password) throws SQLException, IOException {
        ModelAndView mav = new ModelAndView("/Authentication/RegisterPage");
        UserData userdt = new UserData();
        User user = userdt.searchUserByNickname(username);
        if (!password.equals(repeat_password)) {
            mav.addObject("error", "Passwords don't match");
            mav.addObject("name", name);
            mav.addObject("last_name", last_name);
            mav.addObject("username", username);
            mav.addObject("sex", sex);
            mav.addObject("age", age);
            mav.addObject("mail", mail);
            return mav;
        }
        if (user != null) {
            mav.addObject("error", "Username " + username + " already taken");
            mav.addObject("name", name);
            mav.addObject("last_name", last_name);
            mav.addObject("sex", sex);
            mav.addObject("age", age);
            mav.addObject("mail", mail);
            return mav;
        }
        userdt.addUser(name, last_name, username, sex, age, mail, password);
        response.sendRedirect("/");
        return mav;
    }
}
