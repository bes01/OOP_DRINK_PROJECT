package Drinks.Controllers.Recipe;

import Drinks.Model.Containers.Drink;
import Drinks.Model.Containers.Ingredient;
import Drinks.Model.Containers.User;
import Drinks.Model.DataBase.DataRetrieve.DrinkData;
import Drinks.Model.DataBase.DrinkDao.TheDrinkData;
import Drinks.Model.DataBase.IngredientDao.IngredientData;
import Drinks.Model.DataBase.RecipeDao.ExistenceChecker;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class ExtendRecipe {
    private AttributeHandler attributeHandler;
    private ExistenceChecker checkExistence;
    private RecipeAddition recipeAddition;
    private IngredientData ingredientData;
    private TheDrinkData drinkData;
    public ExtendRecipe() throws SQLException {
        attributeHandler= new AttributeHandler();
        checkExistence = new ExistenceChecker();
        recipeAddition = new RecipeAddition();
        ingredientData = new IngredientData();
        drinkData= new TheDrinkData();
    }

    @GetMapping("/addDrink/extend{drink_id}")
    public ModelAndView renderDrinkExtension(@RequestParam int drink_id, HttpServletRequest request) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/AddRecipe/ExtendRecipe");
        Drink drink = drinkData.getDrink(drink_id) ;
        modelAndView.addObject("drink_id",drink_id);
        String[] ingredientNames = attributeHandler.getIngredientArray(drink.getMyIngredients());
        modelAndView=attributeHandler.fillViewData(modelAndView,drink.getDrinkName(),drink.getImagePath(),
                drink.getInstruction(),ingredientNames);
        return  attributeHandler.determineExistence(request,modelAndView);
    }


    @PostMapping("/addDrink/extend{drink_id}")
    public void addPhoto(@RequestParam("file") MultipartFile part,
                         @RequestParam int drink_id,
                         HttpServletResponse httpServletResponse) throws IOException, SQLException {
        if (attributeHandler.handlePhotoUploadInProject(part).equals(""))
            httpServletResponse.sendRedirect("/addDrink/extend");
        else httpServletResponse.sendRedirect("/addDrink/extend/photo?drink_id="+Integer.toString(drink_id)+"&image="+part.getOriginalFilename());
    }

    @GetMapping("/addDrink/extend/photo")
    public  ModelAndView renderWithPhoto(@RequestParam int drink_id,@RequestParam String image) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("/AddRecipe/ExtendRecipePhoto");
        Drink drink = drinkData.getDrink(drink_id);
        modelAndView.addObject("drink_id",drink_id);
        modelAndView.addObject("picture",image);

        String [] ingredientNames=attributeHandler.getIngredientArray(drink.getMyIngredients());
        attributeHandler.fillViewData(modelAndView,drink.getDrinkName(),
                "/resources/photos/"+image,drink.getInstruction(),ingredientNames);
        return modelAndView;
    }

    @PostMapping("/addDrink/extend/photo/submit")
    public void submitRecipe(@RequestParam int drink_id,@RequestParam String name,
                             @RequestParam String instruction,@RequestParam String image,
                             HttpServletRequest request,HttpServletResponse httpServletResponse) throws IOException {
        String path= "/resources/photos/"+image;
        handleExtendAddition(drink_id, name, instruction, request, httpServletResponse, path);
    }
    @PostMapping("/addDrink/extend/submit")
    public void submitRecipeWithPhoto(@RequestParam int drink_id,@RequestParam String name,
                                      @RequestParam String instruction,
                                      HttpServletRequest request,HttpServletResponse httpServletResponse) throws IOException, SQLException {
        Drink drink = drinkData.getDrink(drink_id) ;
        String path= drink.getImagePath();
        handleExtendAddition(drink_id, name, instruction, request, httpServletResponse, path);
    }

    private void handleExtendAddition(@RequestParam int drink_id, @RequestParam String name, @RequestParam String instruction, HttpServletRequest request, HttpServletResponse httpServletResponse, String path) throws IOException {
        int author_id=1;
//        User author = (User) request.getSession().getAttribute("user");
//        int author_id=author.getUserId();
        String[] enumeration = request.getParameterValues("DynamicTextBox");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        if (checkExistence.checkExistance(enumeration,name,path,instruction, drink_id,author_id)) {
            request.getSession().setAttribute("exists",true);
            httpServletResponse.sendRedirect("/addDrink/extend?drink_id="+drink_id);
        } else {
            request.setAttribute("exists",false);
            handleRecipeAddition( enumeration, ingredients, name, path, instruction,author_id, drink_id);
            httpServletResponse.sendRedirect("/HomePage");
        }
    }

    private void handleRecipeAddition(String[] enumeration, ArrayList<Ingredient> ingredients,
                                      String name, String path, String instruction, int userId, int parentId) {
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
        recipeAddition.addDrink(new Drink(-1, name, path, instruction, parentId, userId, ingredients));
    }

}
