package in.movies.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ratings")
public class Ratings {
    @Id
    private Integer rId;
    private String tconst;
    private Double avarageRating;
    private Integer numVotes;
}
