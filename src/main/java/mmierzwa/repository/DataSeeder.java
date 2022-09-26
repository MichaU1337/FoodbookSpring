package mmierzwa.repository;

import mmierzwa.model.Recipe;
import mmierzwa.model.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public DataSeeder(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();

        Recipe createdR1 = recipeRepository.save(recipe1);
        Recipe createdR2 = recipeRepository.save(recipe2);

        User user = new User();
        user.setEmail(Instant.now() + "@email.com");

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(createdR1);

        user.setRecipes(recipes);
//        User appUser = userRepository.save(user);
    }
}
