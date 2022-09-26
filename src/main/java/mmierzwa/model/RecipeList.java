package mmierzwa.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe_user")
@RequiredArgsConstructor
public class RecipeList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne()
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
