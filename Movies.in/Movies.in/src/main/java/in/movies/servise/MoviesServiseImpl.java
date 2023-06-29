package in.movies.servise;

import in.movies.dto.*;
import in.movies.entity.Movie;
import in.movies.entity.Ratings;
import in.movies.repository.MoviesRepository;
import in.movies.repository.RatingsRepository;
import in.movies.utility.GeneresWiseMovieRowmapper;
import in.movies.utility.GeneresWiseTotalNumVotesRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class MoviesServiseImpl implements MoviesServise {

    @Autowired
    private MoviesRepository movisRepository;
    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String saveNewMovie(Movie movie) {
        String status=null;
        try {
            movisRepository.save(movie);
            status= "success";
        }catch (Exception e){
            status= "failure";
        }
        return status;
    }

    @Override
    public List<Movie> fetchMovieList() {
        return movisRepository.findAll();
    }

    @Override
    public void deleteById(Integer mid) {
        movisRepository.deleteById(mid);
    }

    @Override
    public List<MovieDto> allLongestDurationMovies() {
        List<MovieDto> moviesDtos = null;
        List<Movie> top10ByOrderByRuntimeMinutesDesc = movisRepository.findTop10ByOrderByRuntimeMinutesDesc();
        if (top10ByOrderByRuntimeMinutesDesc.size() > 0) {

            moviesDtos = top10ByOrderByRuntimeMinutesDesc.stream().map(movies ->
                            MovieDto.builder()
                                    .tconst(movies.getTconst())
                                    .primaryTitle(movies.getPrimaryTitle())
                                    .runtimeMinutes(movies.getRuntimeMinutes())
                                    .genres(movies.getGenres()).build())
                    .collect(Collectors.toList());
        }
        return moviesDtos;
    }

    @Override
    public List<TopRatedMovieDto> getAllTopRatedMovies() {
        List<Ratings> list = ratingsRepository.findByAvarageRatingGreaterThanOrderByAvarageRatingDesc(6.0);
        Map<String, Double> ratingMap = list.stream()
                .collect(Collectors.toMap(Ratings::getTconst, Ratings::getAvarageRating));
        List<String> movieTconstList = list.stream().map(ratings -> ratings.getTconst()).collect(Collectors.toList());
        List<Movie> movies=movisRepository.findAllByTconstIn(movieTconstList);

        List<TopRatedMovieDto> moviesDtos = movies.stream().map(movie ->
                        TopRatedMovieDto.builder()
                                .tconst(movie.getTconst())
                                .primaryTitle(movie.getPrimaryTitle())
                                .avarageRating(ratingMap.get(movie.getTconst()))
                                .genres(movie.getGenres()).build())
                .collect(Collectors.toList());
        moviesDtos.sort(comparing(TopRatedMovieDto::getAvarageRating));
        return moviesDtos;
    }

    @Override
    public void incrementRuntimeMinutesMovies() {
        movisRepository.updateDocumentaryGeneres();
        movisRepository.updateAnimationGeneres();
        movisRepository.updateOtherGeneres();
    }

    @Override
    public GenresMoviesWithSubtotals getGaneresWiseNumVotesList(){
        String SQL_GET_ALL_GENRES_WISE_NUMVOTES_LIST="select genres,primary_title, num_votes from movies INNER JOIN ratings on movies.tconst=ratings.tconst order by genres desc ";
        String NUMVOTES_SUM_GENRESWISE="select genres, SUM(num_votes ) as total FROM ratings inner join  movies on movies.tconst=ratings.tconst GROUP BY genres";
        List<GeneresWiseMovie> GeneresWiseMovieList = jdbcTemplate.query(SQL_GET_ALL_GENRES_WISE_NUMVOTES_LIST, new GeneresWiseMovieRowmapper());
        List<GenresWiseTotalNumVotes> genresWiseTotalNumVotes = jdbcTemplate.query(NUMVOTES_SUM_GENRESWISE, new GeneresWiseTotalNumVotesRowmapper());
        Map<String, Long> map = genresWiseTotalNumVotes.stream()
                .collect(Collectors.toMap(GenresWiseTotalNumVotes::getGenres, GenresWiseTotalNumVotes::getTotal));

        return GenresMoviesWithSubtotals.builder().generesWiseMovies(GeneresWiseMovieList).genresWiseTotalNumVotesMap(map).build();
    }
}
