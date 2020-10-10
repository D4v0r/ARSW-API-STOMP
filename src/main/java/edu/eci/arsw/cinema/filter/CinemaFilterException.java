package edu.eci.arsw.cinema.filter;

/**
 * The type Cinema filter exception.
 */
public class CinemaFilterException extends Exception{
    /**
     * The constant FILTER_NUMERIC_EXPECTED.
     */
    public static final String FILTER_NUMERIC_EXPECTED = "Property Invalid";

    /**
     * Instantiates a new Cinema filter exception.
     *
     * @param message the message
     */
    public CinemaFilterException(String message){
        super(message);
    }
}
