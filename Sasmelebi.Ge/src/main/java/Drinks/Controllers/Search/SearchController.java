package Drinks.Controllers;

import Drinks.Constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

    @RequestMapping(value="/Search" ,  method = RequestMethod.GET)
    public String getSearchPage(){

        return "/Search/Search";
    }
}
