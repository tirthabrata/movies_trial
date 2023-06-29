package in.movies.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieDto {
    private String tconst;
    private String primaryTitle;
    private double runtimeMinutes;
    private String genres;
}
