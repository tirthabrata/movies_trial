package in.movies.repository;

import in.movies.entity.Movie;
import in.movies.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Integer> {
    List<Ratings> findByAvarageRatingGreaterThanOrderByAvarageRatingDesc(double avarageRating);


}
