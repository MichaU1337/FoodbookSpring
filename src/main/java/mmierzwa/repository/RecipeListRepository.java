package mmierzwa.repository;

import mmierzwa.model.RecipeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecipeListRepository extends JpaRepository<RecipeList, Long> {
    List<RecipeList> findByUser_Email(String userEmail);
    void deleteRecipeListByUser_EmailAndRecipe_Id(String email, Long recipeId);
}
