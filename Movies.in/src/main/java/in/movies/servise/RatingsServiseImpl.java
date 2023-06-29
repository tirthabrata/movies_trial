package in.movies.servise;

import in.movies.entity.Ratings;
import in.movies.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingsServiseImpl implements RatingsServise{

    @Autowired
    private RatingsRepository ratingsRepository;
    @Override
    public Ratings saveAll(Ratings ratings) {
        return ratingsRepository.save(ratings);
    }

    @Override
    public List<Ratings> fetchRatingList() {
        return ratingsRepository.findAll();
    }

    @Override
    public void deleteById(Integer ratId) {
        ratingsRepository.deleteById(ratId);
    }
}
