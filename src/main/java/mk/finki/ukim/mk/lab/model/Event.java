package mk.finki.ukim.mk.lab.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double popularityScore;

    @ManyToOne
    private Location location;
    @ManyToOne
    private Category category;

    public Event(String name, String description, Double popularityScore, Location location, Category category){
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
        this.category = category;
    }

}
