package in.movies.controller;

import in.movies.dto.GenresMoviesWithSubtotals;
import in.movies.dto.MovieDto;
import in.movies.dto.TopRatedMovieDto;
import in.movies.entity.Movie;
import in.movies.servise.MoviesServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MoviesController {

    @Autowired
    private MoviesServise moviesServise;

    @GetMapping("/longest-duration-movies")
    public List<MovieDto> allLongestDurationMovies(){
        return moviesServise.allLongestDurationMovies();
    }

    @GetMapping("/top-rated-movies")
    public List<TopRatedMovieDto> allTopRatedMovies(){
        return moviesServise.getAllTopRatedMovies();
    }
    @PostMapping("/new-movie")
    public String saveNewMovie(@RequestBody Movie movies){
        return moviesServise.saveNewMovie(movies);
    }
    @PostMapping("/update-runtime-minutes")
    public void incrementRuntimeMinutesMovies(){
       moviesServise.incrementRuntimeMinutesMovies();
    }

    @GetMapping("/genre-movies-with-subtotals")
    public GenresMoviesWithSubtotals getGaneresWiseNumVotesList(){
        return moviesServise.getGaneresWiseNumVotesList();
    }


}
