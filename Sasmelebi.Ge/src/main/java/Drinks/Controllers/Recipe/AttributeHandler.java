package Drinks.Controllers.Recipe;

import Drinks.Model.Containers.Ingredient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AttributeHandler {
    public ModelAndView determineExistence(HttpServletRequest request, ModelAndView modelAndView) {
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



    public String[] getIngredientArray(ArrayList<Ingredient> myIngredients) {
        String[] ingredientNames = new String[myIngredients.size()];
        for (int i=0; i<myIngredients.size(); i++){
            String s=myIngredients.get(i).getIngredientName();
            ingredientNames[i]=s;
        }
        return ingredientNames;
    }

    public ModelAndView fillViewData(ModelAndView modelAndView, String drinkName, String imagePath,
                                     String instruction, String [] ingredientNames) {

        modelAndView.addObject("name",drinkName);
        modelAndView.addObject("instruction",instruction);
        modelAndView.addObject("Values",ingredientNames);
        if (!imagePath.equals(""))modelAndView.addObject("path",imagePath);
        return modelAndView;
    }
    public HttpServletRequest fillViewData(HttpServletRequest request, String drinkName, String imagePath,
                                           String instruction, String [] ingredientNames) {

        request.setAttribute("name",drinkName);
        request.setAttribute("instruction",instruction);
        request.setAttribute("Values",ingredientNames);
        if (!imagePath.equals(""))request.setAttribute("path",imagePath);
        return request;
    }
    public String handlePhotoUploadInProject(MultipartFile part) throws IOException {
        if(!part.getOriginalFilename().equals("")) {
            File f = new File("AddRecipeController.java");
            String path = f.getCanonicalPath();
            System.out.println(part.getOriginalFilename().equals(""));
            String finalPath = path.substring(0, path.indexOf("AddRecipeController.java"));
            finalPath += "src\\main\\webapp\\resources\\photos\\" + part.getOriginalFilename();
            Files.write(Paths.get(finalPath), part.getBytes());
            return   part.getOriginalFilename();
        }
        return "";
    }
}
