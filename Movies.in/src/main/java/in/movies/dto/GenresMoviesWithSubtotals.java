package in.movies.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class GenresMoviesWithSubtotals {
    List<GeneresWiseMovie> generesWiseMovies;
    Map<String, Long> genresWiseTotalNumVotesMap;
}
