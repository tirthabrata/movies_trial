package in.movies.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopRatedMovieDto {
    private String tconst;
    private String primaryTitle;
    private double avarageRating;
    private String genres;
}
