package mmierzwa.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String name;
    private String author;
    private String descriptionShort;
    private String descriptionLong;
    private String category;
    private String pictureMain;
    private String pictureMini;

    @Min(0L)
    @Max(5L)
    private int difficulty;

    @Min(0L)
    private int servings;
}
