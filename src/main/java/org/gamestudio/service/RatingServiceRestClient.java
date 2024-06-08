package org.gamestudio.service;

import org.gamestudio.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class RatingServiceRestClient implements RatingService{

    private final String url = "http://localhost:8080/api/rating";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating) {
        restTemplate.postForEntity(url,rating,Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        return restTemplate.getForEntity(url + "/" + game, Integer.class)
                .getBody();
    }

    @Override
    public int getRating(String game, String player) {
        return Objects.requireNonNull(restTemplate.getForEntity(url + "/" + game + "/" + player,
                Rating.class).getBody()).getRating();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Reset is not supported on web interface");
    }
}
