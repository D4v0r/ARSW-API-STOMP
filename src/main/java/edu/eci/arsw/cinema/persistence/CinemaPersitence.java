/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.services.CinemaException;

import java.util.List;
import java.util.Set;

/**
 * The interface Cinema persitence.
 */
public interface CinemaPersitence {

    /**
     * Buy ticket.
     *
     * @param row       the row
     * @param col       the col
     * @param cinema    the cinema
     * @param date      the date
     * @param movieName the movie name
     * @throws CinemaException the cinema exception
     */
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException;

    /**
     * Gets functionsby cinema and date.
     *
     * @param cinema the cinema
     * @param date   the date
     * @return the functionsby cinema and date
     */
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date);

    public CinemaFunction getFunction(String cinema, String date, String movie);

    /**
     * Save cinema.
     *
     * @param cinema the cinema
     * @throws CinemaPersistenceException the cinema persistence exception
     */
    public void saveCinema(Cinema cinema) throws CinemaPersistenceException;

    /**
     * Gets cinema.
     *
     * @param name the name
     * @return the cinema* @throws CinemaPersistenceException the cinema persistence exception
     */
    public Cinema getCinema(String name);

    /**
     * Gets all cinemas.
     *
     * @return the all cinemas
     * @throws CinemaPersistenceException the cinema persistence exception
     */
    public Set<Cinema> getAllCinemas() throws  CinemaPersistenceException;

    public void addCinemaFunction(String cinema, CinemaFunction function) throws  CinemaPersistenceException;

    public void updateCinemaFunction(String cinema, CinemaFunction function) throws  CinemaPersistenceException;

    public void deleteCinemaFunction(String cinema, String date, String movie) throws CinemaPersistenceException;

}
