package in.movies.utility;

import in.movies.dto.GeneresWiseMovie;
import in.movies.dto.GenresWiseTotalNumVotes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneresWiseTotalNumVotesRowmapper implements RowMapper<GenresWiseTotalNumVotes> {
    @Override
    public GenresWiseTotalNumVotes mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenresWiseTotalNumVotes genresWiseTotalNumVotes = new GenresWiseTotalNumVotes();

        genresWiseTotalNumVotes.setGenres(rs.getString("genres"));
        genresWiseTotalNumVotes.setTotal(rs.getLong("total"));
        return genresWiseTotalNumVotes;
    }
}