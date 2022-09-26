package mmierzwa.service;

import lombok.AllArgsConstructor;
import mmierzwa.model.RecipeList;
import mmierzwa.repository.RecipeListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeListService {
    private final RecipeListRepository repository;

    public List<RecipeList> listOfRecipes(String username){
        return repository.findByUser_Email(username);
    }

    public void removeFromFavourites(String email, Long recipeId){
        repository.deleteRecipeListByUser_EmailAndRecipe_Id(email, recipeId);
    }
//    public void addRecipeToFavourites(String username, Long recipeId) {
//
//        repository.addRecipeToFavourites(username, recipeId);
//    }
}
