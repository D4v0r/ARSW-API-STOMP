/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.model;

/**
 * The type Seat.
 *
 * @author cristian
 */
public class Seat {
    
    private int row;
    private int col;

    /**
     * Instantiates a new Seat.
     */
    public Seat(){
    }

    /**
     * Instantiates a new Seat.
     *
     * @param row the row
     * @param col the col
     */
    public Seat(int row, int col){
        this.row=row;
        this.col=col;
    }


    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }


    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }


    /**
     * Gets col.
     *
     * @return the col
     */
    public int getCol() {
        return col;
    }


    /**
     * Sets col.
     *
     * @param col the col
     */
    public void setCol(int col) {
        this.col = col;
    }
    
    @Override
    public String toString(){
        return this.row+" "+this.col;
    }
}
