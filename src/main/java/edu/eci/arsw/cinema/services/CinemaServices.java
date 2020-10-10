/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.filter.CinemaFilterException;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * The type Cinema services.
 */
@Service
public class CinemaServices {

    /**
     * The Cps.
     */
    @Autowired
    @Qualifier("InMemoryCinema")
    CinemaPersitence cps;

    /**
     * The Filter.
     */
    @Autowired
     @Qualifier("FilterByGender")
    CinemaFilter filter;

    /**
     * Add new cinema.
     *
     * @param c the c
     */
    public void addNewCinema(Cinema c) {
        try {
            cps.saveCinema(c);
        } catch (CinemaPersistenceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get all cinemas set.
     *
     * @return the set
     */
    public Set<Cinema> getAllCinemas(){
        try {
            return cps.getAllCinemas();
        } catch (CinemaPersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets cinema by name.
     *
     * @param name the name
     * @return the cinema by name
     */
    public Cinema getCinemaByName(String name) throws CinemaException{
        if(cps.getCinema(name) == null) throw  new CinemaException("Este cinema no existe");
        return  cps.getCinema(name);
    }


    /**
     * Buy ticket.
     *
     * @param row       the row
     * @param col       the col
     * @param cinema    the cinema
     * @param date      the date
     * @param movieName the movie name
     */
    public void buyTicket(int row, int col, String cinema, String date, String movieName){
        try {
            cps.buyTicket(row, col, cinema, date, movieName);
        } catch (CinemaException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets functionsby cinema and date.
     *
     * @param cinema the cinema
     * @param date   the date
     * @return the functionsby cinema and date
     */
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException{
        if(cps.getFunctionsbyCinemaAndDate(cinema, date).isEmpty()) throw new CinemaException("Este cinema no tiene funciones programadas para la fecha indicada.");
        return cps.getFunctionsbyCinemaAndDate(cinema,date);
    }

    /**
     * Get functions by filter list.
     *
     * @param cinema   the cinema
     * @param date     the date
     * @param property the property
     * @return the list
     */
    public List<CinemaFunction> getFunctionsByFilter(String cinema, String date, String property) throws CinemaException {
        List<CinemaFunction> functions = getFunctionsbyCinemaAndDate(cinema, date);

        try{
           functions =  filter.filterBy(functions, property);
        } catch (CinemaFilterException e){
            e.printStackTrace();
        }
        return functions;
    }

    public CinemaFunction getFunction(String cinema, String date, String movie) throws CinemaException{
            if(cps.getFunction(cinema, date, movie) == null) throw new CinemaException(CinemaPersistenceException.NO_FOUND_CINEMA_FUNCTION);
            return cps.getFunction(cinema, date, movie);
    }

    public void addCinemaFunction(String cinema, CinemaFunction function) throws CinemaException{
        try{
            cps.addCinemaFunction(cinema, function);
        }catch (CinemaPersistenceException e){
            throw new CinemaException(e.getMessage()); }
    }

    public void updateCinemaFunction(String cinema, CinemaFunction function) throws  CinemaException{
        try{
            cps.updateCinemaFunction(cinema, function);
        }catch (CinemaPersistenceException e){
            throw new CinemaException(e.getMessage());
        }
    }

    public void deleteFunction(String cinema, String date, String movie) throws  CinemaException{
        try{
            cps.deleteCinemaFunction(cinema, date, movie);
        } catch (CinemaPersistenceException e){
            throw new CinemaException(e.getMessage());
        }
    }

    public void buyTicket(String cinema, String date, String movie, int row, int col) throws  CinemaException{
        try{
            cps.buyTicket(row, col, cinema, date, movie);
        }catch (Exception e){
            throw new CinemaException("Error procesando compra de ticket");
        }
    }
}
