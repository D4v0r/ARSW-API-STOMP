/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The type In memory cinema persistence.
 */
@Component("InMemoryCinema")
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final ConcurrentHashMap<String,Cinema> cinemas=new ConcurrentHashMap<>();


    /**
     * Instantiates a new In memory cinema persistence.
     */
    public InMemoryCinemaPersistence() {
        //load stub data
        //----------------------------CINEMA I------------------------------------------------------
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);

        //----------------------------CINEMA II-------------------------------------------------------
        String functionDatec2 = "2019-12-18 18:30";
        List<CinemaFunction> functionsc2= new ArrayList<>();
        CinemaFunction funct3 = new CinemaFunction(new Movie("The Movie","Adventure"),functionDatec2);
        CinemaFunction funct4 = new CinemaFunction(new Movie("The Hole","Horror"),functionDatec2);
        functionsc2.add(funct3);
        functionsc2.add(funct4);
        Cinema c2=new Cinema("cinemaY",functionsc2);

        cinemas.put("cinemaX", c);
        cinemas.put("cinemaY", c2);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException{

        List<CinemaFunction> cinemaFunctions =cinemas.get(cinema).getFunctions();
        synchronized (cinemaFunctions) {
            Optional<CinemaFunction> cf = cinemaFunctions.stream().filter(cinemaFunction -> {
                return cinemaFunction.getMovie().getName().equals(movieName) && cinemaFunction.getDate().startsWith(date);
            }).findFirst();
            if (cf.isPresent()) {
                CinemaFunction cinemaFunction = cf.get();
                cinemaFunction.buyTicket(row, col);
                System.out.println("Compra exitosa, para la funcion: " + cinemaFunction.getMovie().getName() + " con horario: " + cinemaFunction.getDate());
            } else {
                throw new CinemaException(CinemaPersistenceException.NO_FOUND_CINEMA_FUNCTION);
            }
        }
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> cinemaFunctions = cinemas.get(cinema).getFunctions();
        List<CinemaFunction> cinemaFunctionList =
                cinemaFunctions.stream().filter(cinemaFunction -> cinemaFunction.getDate().startsWith(date)).collect(Collectors.toList());
        return cinemaFunctionList;
    }

    @Override
    public CinemaFunction getFunction(String cinema, String date, String movie){
        try {
            return cinemas.get(cinema).getFunctions().stream()
                    .filter(cinemaFunction -> cinemaFunction.getDate().equals(date) && cinemaFunction.getMovie().getName().equals(movie))
                    .findAny()
                    .get();
        } catch (NoSuchElementException e){
           return null;
        }
    }

    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) {
        return cinemas.get(name);
    }

    @Override
    public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
        return new HashSet<>(cinemas.values());
    }

    @Override
    public void addCinemaFunction(String cinema, CinemaFunction function) throws CinemaPersistenceException {
        if(getFunction(cinema, function.getDate(), function.getMovie().getName()) != null ) throw new CinemaPersistenceException(CinemaPersistenceException.CINEMA_FUNCTION_ALREADY_EXISTS);
        getCinema(cinema).getFunctions().add(function);
    }

    @Override
    public void updateCinemaFunction(String cinema, CinemaFunction function) throws CinemaPersistenceException {
        try{
            if(getFunction(cinema, function.getDate(), function.getMovie().getName()) == null){
                addCinemaFunction(cinema, function);
            } else{
                getCinema(cinema).getFunctions().replaceAll( x -> {
                    final CinemaFunction y;
                    if(x.toString().equals(function.toString())){
                        y = function;
                    } else {
                        y = x;
                    }
                    return y;
                });
            }
        }catch (Exception e){
            throw new CinemaPersistenceException("Error actulizando función");
        }
    }

    @Override
    public void deleteCinemaFunction(String cinema, String date, String movie) throws CinemaPersistenceException {
        try {
            CinemaFunction function = getFunction(cinema, date, movie);
            System.out.println(function);
            cinemas.get(cinema).getFunctions().remove(function);
        } catch (Exception e){
            e.printStackTrace();
            throw new CinemaPersistenceException("Error eliminando función");

        }
    }

}
