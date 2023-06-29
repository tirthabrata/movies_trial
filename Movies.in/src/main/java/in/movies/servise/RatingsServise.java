package in.movies.servise;

import in.movies.entity.Ratings;

import java.util.List;

public interface RatingsServise {

    Ratings saveAll(Ratings ratings);
    List<Ratings> fetchRatingList();

    void deleteById(Integer ratId);
}
