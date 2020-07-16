package Drinks.Controllers.Recipe;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.DataBase.IngredientDao.IngredientData;
import Drinks.Model.DataBase.RecipeDao.ExistenceChecker;
import Drinks.Model.DataBase.RecipeDao.RecipeAddition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    private ExistenceChecker checkExistence;
    public AddRecipeController(){
        recipeAddition = new RecipeAddition();
        ingredientData = new IngredientData();
        checkExistence=new ExistenceChecker();
    }
    @GetMapping(value = "/user/add_recipe")
    public ModelAndView getMainJsp(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView=new ModelAndView("/AddRecipe/AddRecipe");
        if (request.getSession().getAttribute("exists")==null){
            modelAndView.addObject("exists",false);

        }else {
            boolean exists = (boolean) request.getSession().getAttribute("exists");
            if (exists) {
                modelAndView.addObject("exists", true);
            } else {
                modelAndView.addObject("exists", false);

            }
            request.getSession().removeAttribute("exists");
        }
        return modelAndView;
    }

    @PostMapping(value = "/user/add_recipe")
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile part, HttpSession session) throws IOException {
        ModelAndView modelAndView = new ModelAndView("/AddRecipe/AddRecipe");
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
    public void  handleSubmit(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        String[] enumeration = request.getParameterValues("DynamicTextBox");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String name = request.getParameter("name");
        String path = (String) request.getSession().getAttribute("sessionPath");
        String instruction = request.getParameter("instruction");
        int authorId =1;
//        int authorId= (int) request.getSession().getAttribute("user");
        int parentId=0;
        if (request.getAttribute("drink")==null){
            parentId=-1;
        }else{
            Drink d =(Drink)request.getAttribute("drink");
            parentId=d.getDrinkId();
        }
        if (path == null) {
            path = "/resources/no_photo.png";
        }
        if (checkExistence.checkExistance(enumeration,name,path,instruction,parentId,authorId)) {
            request.getSession().removeAttribute("sessionPath");
            request.getSession().setAttribute("exists",true);
            httpServletResponse.sendRedirect("/user/add_recipe");
        } else {
            request.setAttribute("exists",false);
            handleRecipeAddition(request, httpServletResponse, enumeration, ingredients, name, path, instruction);
            httpServletResponse.sendRedirect("/homePage");
        }
    }

    private void handleRecipeAddition(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                      String[] enumeration, ArrayList<Ingredient> ingredients,
                                      String name, String path, String instruction) throws IOException {
        for (int i = 0; i < enumeration.length; i++) {
            if (!enumeration[i].equals("")) {
                Ingredient curIngredient = ingredientData.getIngredient(enumeration[i]);
                if (curIngredient == null) {
                    ingredients.add(new Ingredient(-1, enumeration[i]));
                    ingredientData.addNewIngredient(enumeration[i]);
                } else {
                    ingredients.add(curIngredient);
                }
            }
        }
        request.getSession().removeAttribute("sessionPath");
//      recipeAddition.addDrink(new Drink(-1,name,path,instruction,-1,(Integer) request.getSession().getAttribute("user_id"), ingredients ));
        recipeAddition.addDrink(new Drink(-1, name, path, instruction, -1, 1, ingredients));
    }
}
