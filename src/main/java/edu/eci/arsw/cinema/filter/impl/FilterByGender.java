package edu.eci.arsw.cinema.filter.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.CinemaFunction;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Filter by gender.
 */
@Component("FilterByGender")
public class FilterByGender  implements CinemaFilter{

    @Override
    public List<CinemaFunction> filterBy(List<CinemaFunction> functions, String property) {

        return functions.stream()
                        .filter(cinemaFunction -> cinemaFunction.getMovie().getGenre().equals(property))
                        .collect(Collectors.toList());

    }
}
