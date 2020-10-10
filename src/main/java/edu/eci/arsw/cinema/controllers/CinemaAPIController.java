/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author cristian
 */
@RestController
@Service
public class CinemaAPIController {

    @Autowired
    CinemaServices cinemaServices;

    @RequestMapping( value = "/cinemas", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemas(){
        try {
            return new ResponseEntity<>(cinemaServices.getAllCinemas(), HttpStatus.OK);
        } catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cinemas/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinema(@PathVariable String name){
        try{
            return new ResponseEntity<>(cinemaServices.getCinemaByName(name), HttpStatus.OK);
        } catch (CinemaException e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cinemas/{name}/{date}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaFunctionsByDate(@PathVariable String name, @PathVariable String date){
        try{
            return new ResponseEntity<>(cinemaServices.getFunctionsbyCinemaAndDate(name, date), HttpStatus.OK);
        }catch (CinemaException e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cinemas/{name}/{date}/{moviename}", method = RequestMethod.GET)
    public ResponseEntity<?> getCinemaFunction(@PathVariable String name, @PathVariable String date, @PathVariable String moviename) {
        try {
            return new ResponseEntity<>(cinemaServices.getFunction(name, date, moviename), HttpStatus.OK);
        } catch (CinemaException e) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cinemas/{name}", method = RequestMethod.POST)
    public ResponseEntity<?> addCinemaFunction(@PathVariable String name, @RequestBody CinemaFunction function){
        try{
            cinemaServices.addCinemaFunction(name, function);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (CinemaException e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cinemas/{name}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCinemaFunction(@PathVariable String name, @RequestBody CinemaFunction function){
        try{
            cinemaServices.updateCinemaFunction(name, function);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (CinemaException e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cinemas/{name}/{date}/{movie}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCinemaFunction(@PathVariable String name, @PathVariable String date, @PathVariable String movie){
        try{
            cinemaServices.deleteFunction(name, date, movie);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CinemaException e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
