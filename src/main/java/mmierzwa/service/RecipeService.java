package mmierzwa.service;

import mmierzwa.exception.ResourceNotFoundException;
import mmierzwa.model.Recipe;
import mmierzwa.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe addRecipe(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public List<Recipe> findAllRecipes(){
        return recipeRepository.findAll();
    }

    public Recipe updateRecipe(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public Recipe findRecipeById(Long id){
        return recipeRepository.findRecipeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id " + id + " was not found"));
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteRecipeById(id);
    }
}
