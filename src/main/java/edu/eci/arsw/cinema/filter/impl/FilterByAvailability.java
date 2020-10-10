package edu.eci.arsw.cinema.filter.impl;

import edu.eci.arsw.cinema.filter.CinemaFilterException;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.CinemaFunction;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * The type Filter by availability.
 */
@Component("FilterByAvailability")
public class FilterByAvailability implements CinemaFilter{

    @Override
    public List<CinemaFunction> filterBy(List<CinemaFunction> functions, String property) throws  CinemaFilterException {
        final int numberOfSeats;
        try{
           numberOfSeats =  Integer.parseInt(property);
        } catch (NumberFormatException e){
            throw new CinemaFilterException(CinemaFilterException.FILTER_NUMERIC_EXPECTED);
        }
        return functions.stream()
                .filter(cinemaFunction -> {
                    AtomicInteger count = new AtomicInteger();
                    cinemaFunction.getSeats().forEach(x -> {
                        x.forEach(y -> {
                            if(y.equals(true)){
                                count.getAndIncrement();
                            }
                        });
                    });
                    return (count.get()>= numberOfSeats);
                })
                .collect(Collectors.toList());
    }
}
