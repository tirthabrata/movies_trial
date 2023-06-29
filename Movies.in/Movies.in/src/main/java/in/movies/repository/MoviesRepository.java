package in.movies.repository;

import in.movies.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findTop10ByOrderByRuntimeMinutesDesc();

    List<Movie> findAllByTconstIn(List<String> movieTconstList);


    @Transactional
    @Modifying
    @Query(value="UPDATE movies\n" +
            "SET runtime_minutes = runtime_minutes+15\n" +
            "WHERE genres = 'Documentary'",nativeQuery = true)
    void updateDocumentaryGeneres();

    @Modifying
    @Query(value="UPDATE movies\n" +
            "SET runtime_minutes = runtime_minutes+30\n" +
            "WHERE genres = 'Animation'",nativeQuery = true)
    void updateAnimationGeneres();

    @Modifying
    @Query(value = "UPDATE movies\n" +
            "SET runtime_minutes = runtime_minutes+45\n" +
            "WHERE genres not in ('Documentary','Animation')",nativeQuery = true)
    void updateOtherGeneres();
}
