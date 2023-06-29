package in.movies.controller;

import in.movies.entity.Ratings;
import in.movies.servise.RatingsServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rating")
public class RatingsController {

    @Autowired
    private RatingsServise ratingsServise;

    @PostMapping("/crate")
    public Ratings saveRatings(@RequestBody Ratings ratings){
        return ratingsServise.saveAll(ratings);
    }

    @GetMapping("/fetch")
    public List<Ratings> fetchAll(){
        return ratingsServise.fetchRatingList();
    }

    public String deleteRatingById(@PathVariable Integer ratid){
        ratingsServise.deleteById(ratid);
        return "Deleted Successfully !!!";
    }
}
