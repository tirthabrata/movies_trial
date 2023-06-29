package in.movies.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "movies")
public class Movie {

    @Id
    private Integer mId;
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private double runtimeMinutes;
    private String genres;

}
