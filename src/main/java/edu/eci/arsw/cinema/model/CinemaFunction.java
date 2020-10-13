/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import edu.eci.arsw.cinema.services.CinemaException;

import java.util.*;

/**
 * The type Cinema function.
 *
 * @author cristian
 */
public class CinemaFunction {
    
    private Movie movie;
    private List<List<Boolean>> seats=new ArrayList<>();
    private String date;

    /**
     * Instantiates a new Cinema function.
     */
    public CinemaFunction(){}

    /**
     * Instantiates a new Cinema function.
     *
     * @param movie the movie
     * @param date  the date
     */
    public CinemaFunction(Movie movie, String date){
        this.movie=movie;
        this.date=date;
        for (int i=0;i<7;i++){
            List<Boolean> row= new ArrayList<>(Arrays.asList(new Boolean[12]));
            Collections.fill(row, Boolean.TRUE);
            this.seats.add(row);
        }
    }

    /**
     * Buy ticket.
     *
     * @param row the row
     * @param col the col
     * @throws CinemaException the cinema exception
     */
    public void buyTicket(int row,int col) throws CinemaException{
        if (seats.get(row).get(col).equals(true)){
            seats.get(row).set(col,Boolean.FALSE);
        }
        else{
            throw new CinemaException("Seat booked");
        }
    }

    /**
     * Gets seats.
     *
     * @return the seats
     */
    public List<List<Boolean>> getSeats() {
        return this.seats;
    }

    /**
     * Gets movie.
     *
     * @return the movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets movie.
     *
     * @param movie the movie
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CinemaFunction{" +
                "movie=" + movie.getName() +
                ", genre=" + movie.getGenre() +
                ", date='" + date + '\'' +
                '}';
    }
}
