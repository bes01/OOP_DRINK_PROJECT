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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class AddRecipeController {
    private RecipeAddition recipeAddition;
    private IngredientData ingredientData;
    private ExistenceChecker checkExistence;
    private AttributeHandler attributeHandler;
    public AddRecipeController(){
        recipeAddition = new RecipeAddition();
        ingredientData = new IngredientData();
        checkExistence=new ExistenceChecker();
        attributeHandler = new AttributeHandler();
    }
    @GetMapping(value = "/user/add_recipe")
    public ModelAndView getMainJsp(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView=new ModelAndView("/AddRecipe/AddRecipe");
        return attributeHandler.determineExistence(request, modelAndView);
    }

    // roca foto ar avtvirte da ise davasubmite
    @PostMapping(value = "/user/add_recipe/submit")
    public void  handleSubmit(HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        handleSubmitUrl(request, httpServletResponse, "/resources/photos/no_photo.png");
    }


    @PostMapping(value = "/user/add_recipe")
    public void handleFileUpload(@RequestParam("file") MultipartFile part,
                                 HttpSession session,HttpServletRequest request,
                                 HttpServletResponse httpServletResponse) throws IOException {
        String path=attributeHandler.handlePhotoUploadInProject(part);
        if (!path.equals(""))httpServletResponse.sendRedirect("/user/add_recipe/photo?image="+path);
        else httpServletResponse.sendRedirect("/user/add_recipe");

    }

    @GetMapping (value = "/user/add_recipe/photo{image}")
    public ModelAndView getMainJspWithPhoto(@RequestParam("image") String image, HttpServletRequest request) throws IOException {
        ModelAndView modelAndView=new ModelAndView("/AddRecipe/AddRecipePhoto");
        modelAndView.addObject("path","/resources/photos/"+image);
        return modelAndView;
    }
    @PostMapping(value = "/user/add_recipe/photo/submit{image}")
    public void  handleSubmitWithPhoto(@RequestParam String image, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        System.out.println(image);
        handleSubmitUrl(request, httpServletResponse, image);
    }

    private void handleSubmitUrl(HttpServletRequest request, HttpServletResponse httpServletResponse, String s) throws IOException {
        String[] enumeration = request.getParameterValues("DynamicTextBox");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String name = request.getParameter("name");
        String instruction = request.getParameter("instruction");
        int authorId = 1;
        //aq redirec unda tu daloginebuli araa
//        int authorId= (int) request.getSession().getAttribute("user");
        int parentId = -1;
        String path = s;
        if (checkExistence.checkExistance(enumeration, name, path, instruction, parentId, authorId)) {
            request.getSession().setAttribute("exists", true);
            httpServletResponse.sendRedirect("/user/add_recipe");
        } else {
            request.setAttribute("exists", false);
            handleRecipeAddition(request, httpServletResponse, enumeration, ingredients, name, path, instruction);
            httpServletResponse.sendRedirect("/homePage");
        }
    }

    private void handleRecipeAddition(HttpServletRequest request, HttpServletResponse httpServletResponse, String[] enumeration, ArrayList<Ingredient> ingredients, String name, String path, String instruction) {
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
//      recipeAddition.addDrink(new Drink(-1,name,path,instruction,-1,(Integer) request.getSession().getAttribute("user_id"), ingredients ));
        recipeAddition.addDrink(new Drink(-1, name, path, instruction, -1, 1, ingredients));
    }
}
