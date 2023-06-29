package in.movies.utility;

import in.movies.dto.GeneresWiseMovie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneresWiseMovieRowmapper implements RowMapper<GeneresWiseMovie> {
    @Override
    public GeneresWiseMovie mapRow(ResultSet rs, int rowNum) throws SQLException {
        GeneresWiseMovie generesWiseMovie = new GeneresWiseMovie();

        generesWiseMovie.setGenres(rs.getString("genres"));
        generesWiseMovie.setPrimaryTitle(rs.getString("primary_title"));
        generesWiseMovie.setNumVotes(rs.getInt("num_votes"));
        return generesWiseMovie;
    }
}