/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

/**
 * The type Movie.
 *
 * @author cristian
 */
public class Movie {
    private String name;
    private String genre;

    /**
     * Instantiates a new Movie.
     */
    public Movie(){}

    /**
     * Instantiates a new Movie.
     *
     * @param name  the name
     * @param genre the genre
     */
    public Movie(String name, String genre){
        this.name=name;
        this.genre=genre;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
