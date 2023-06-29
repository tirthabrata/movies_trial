package in.movies.servise;

import in.movies.dto.GenresMoviesWithSubtotals;
import in.movies.dto.MovieDto;
import in.movies.dto.TopRatedMovieDto;
import in.movies.entity.Movie;

import java.util.List;

public interface MoviesServise {

    String saveNewMovie(Movie movies);
    List<Movie> fetchMovieList();
    void deleteById(Integer mid);

    List<MovieDto> allLongestDurationMovies();

    List<TopRatedMovieDto> getAllTopRatedMovies();

    void incrementRuntimeMinutesMovies();

    GenresMoviesWithSubtotals getGaneresWiseNumVotesList();
}
