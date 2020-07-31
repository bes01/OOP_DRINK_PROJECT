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
    public ModelAndView openRegisterPage(HttpServletRequest request,
                                         HttpServletResponse response,
                                         HttpSession session) throws IOException {
        ModelAndView mav = new ModelAndView("/Authentication/RegisterPage");
        if (request.getSession().getAttribute("user_id") != null) {
            response.sendRedirect("/HomePage");
            return null;
        }
        return mav;
    }


    @PostMapping("/Register")
    public ModelAndView post(HttpServletRequest request,
                             HttpServletResponse response,
                             HttpSession session) throws SQLException, IOException {
        ModelAndView mav = new ModelAndView("/Authentication/RegisterPage");
        String name = request.getParameter("name");
        String last_name = request.getParameter("last_name");
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String ageString = request.getParameter("age");
        int age = 0;
        if (!ageString.equals("")) {
            age = Integer.parseInt(ageString);
        }
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String repeat_password = request.getParameter("repeat_password");
        if (name.equals("")) {
            mav.addObject("error", "Registration failed: First name should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (last_name.equals("")) {
            mav.addObject("error", "Registration failed: Last name should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (username.equals("")) {
            mav.addObject("error", "Registration failed: Username should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (sex == null) {
            mav.addObject("error", "Registration failed: Sex should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (age == 0) {
            mav.addObject("error", "Registration failed: Age should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (mail.equals("")) {
            mav.addObject("error", "Registration failed: Mail should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (password.equals("")) {
            mav.addObject("error", "Registration failed: Password should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        if (repeat_password.equals("")) {
            mav.addObject("error", "Registration failed: Repeat password should not be empty");
            fillAgain(name, last_name, username, age, mail, mav);
            return mav;
        }
        UserData userdt = new UserData();
        User user = userdt.searchUserByNickname(username);
        User userByMail = userdt.searchUserByMail(mail);
        if (!password.equals(repeat_password)) {
            mav.addObject("error", "Registration failed: Passwords don't match");
            mav.addObject("name", name);
            mav.addObject("last_name", last_name);
            mav.addObject("username", username);
            mav.addObject("age", age);
            mav.addObject("mail", mail);
            return mav;
        }
        if (user != null) {
            mav.addObject("error", "Registration failed: Username " + username + " already taken");
            mav.addObject("name", name);
            mav.addObject("last_name", last_name);
            mav.addObject("age", age);
            mav.addObject("mail", mail);
            return mav;
        }
        if (userByMail != null) {
            mav.addObject("error", "Registration failed: This mail " + mail + " already registered");
            mav.addObject("name", name);
            mav.addObject("last_name", last_name);
            mav.addObject("age", age);
            mav.addObject("mail", mail);
            return mav;
        }
        userdt.addUser(name, last_name, username, sex, age, mail, password);
        response.sendRedirect("/");
        return mav;
    }

    private void fillAgain(String name, String last_name, String username, int age, String mail, ModelAndView mav) {
        if (!name.equals("")) {
            mav.addObject("name", name);
        }
        if (!last_name.equals("")) {
            mav.addObject("last_name", last_name);
        }
        if (!username.equals("")) {
            mav.addObject("username", username);
        }
        if (age != 0) {
            mav.addObject("age", age);
        }
        if (!mail.equals("")) {
            mav.addObject("mail", mail);
        }
    }
}
