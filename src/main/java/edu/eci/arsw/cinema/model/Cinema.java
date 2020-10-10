/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

import java.util.List;

/**
 * The type Cinema.
 *
 * @author cristian
 */
public class Cinema {
    private String name;
    private List<CinemaFunction> functions;


    /**
     * Instantiates a new Cinema.
     */
    public Cinema(){}

    /**
     * Instantiates a new Cinema.
     *
     * @param name      the name
     * @param functions the functions
     */
    public Cinema(String name,List<CinemaFunction> functions){
        this.name=name;
        this.functions=functions;
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
     * Gets functions.
     *
     * @return the functions
     */
    public List<CinemaFunction> getFunctions() {
        return this.functions;
    }

    /**
     * Sets schedule.
     *
     * @param functions the functions
     */
    public void setSchedule(List<CinemaFunction> functions) {
        this.functions = functions;
    }
}
