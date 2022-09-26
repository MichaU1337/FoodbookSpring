package mmierzwa.repository;

import mmierzwa.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    void deleteRecipeById(Long id);
    Optional<Recipe> findRecipeById(Long id);
}
