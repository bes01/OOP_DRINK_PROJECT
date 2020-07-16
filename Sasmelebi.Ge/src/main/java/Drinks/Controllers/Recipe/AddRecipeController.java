package Drinks.Controllers.Recipe;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.IngredientDao.IngredientData;
import Drinks.Model.DataBase.RecipeDao.RecipeAddition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class AddRecipeController {
    private RecipeAddition recipeAddition;
    private IngredientData ingredientData;
    public AddRecipeController(){
        recipeAddition = new RecipeAddition();
        ingredientData = new IngredientData();
    }
    @GetMapping(value = "/user/add_recipe")
    public ModelAndView getMainJsp(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView=new ModelAndView("AddRecipe");
        return modelAndView;
    }

    @PostMapping(value = "/user/add_recipe")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile part, HttpSession session) throws IOException {
        ModelAndView modelAndView = new ModelAndView("AddRecipe");
        File f= new File("AddRecipeController.java");
        String path=f.getCanonicalPath();
        String finalPath=path.substring(0,path.indexOf("AddRecipeController.java"));
        finalPath+="src\\main\\webapp\\resources\\photos\\"+part.getOriginalFilename();
        Files.write(Paths.get(finalPath),part.getBytes());
        session.setAttribute("sessionPath","/resources/photos/"+part.getOriginalFilename());
        modelAndView.addObject("path","/resources/photos/"+part.getOriginalFilename());
        return modelAndView;
    }
    @PostMapping(value = "/user/add_recipe/submit")
    public void handleSubmit(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String[] enumeration= request.getParameterValues("DynamicTextBox");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i=0; i<enumeration.length; i++){
            if (!enumeration[i].equals("")){
                Ingredient curIngredient = ingredientData.getIngredient(enumeration[i]);
                if (curIngredient==null){
                    ingredients.add(new Ingredient(-1,enumeration[i]));
                    ingredientData.addNewIngredient(enumeration[i]);
                }else{
                    ingredients.add(curIngredient);
                }
            }
        }
        String name = request.getParameter("name");
        String path = (String) request.getSession().getAttribute("sessionPath");
        if (path==null){
            path="/resources/no_photo.png";
        }
        request.getSession().removeAttribute("sessionPath");
        String instruction = request.getParameter("instruction");
//      recipeAddition.addDrink(new Drink(-1,name,path,instruction,-1,(Integer) request.getSession().getAttribute("user_id"), ingredients ));
        recipeAddition.addDrink(new Drink(-1,name,path,instruction,-1, 1, ingredients ));
        httpServletResponse.sendRedirect("/homePage");
    }
}
