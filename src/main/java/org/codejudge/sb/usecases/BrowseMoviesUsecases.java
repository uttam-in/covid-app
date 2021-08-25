package org.codejudge.sb.usecases;

import org.codejudge.sb.models.Cinema;
import org.codejudge.sb.models.Movie;
import org.codejudge.sb.models.Show;
import org.codejudge.sb.models.ShowSeat;
import org.codejudge.sb.reposotories.CinemaRepository;
import org.codejudge.sb.reposotories.MovieRepository;
import org.codejudge.sb.reposotories.ShowRepository;
import org.codejudge.sb.reposotories.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrowseMoviesUsecases {
    private final CinemaRepository cinemaRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;

    public BrowseMoviesUsecases(CinemaRepository cinemaRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository, MovieRepository movieRepository) {
        this.cinemaRepository = cinemaRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
    }

    public List<Cinema> listCinemas() {
        return cinemaRepository.findAll();
    }

    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }

    public List<Show> listShows() {
        return showRepository.findAll();
    }

    public List<ShowSeat> listShowSeats() {
        return showSeatRepository.findAll();
    }
}
