package com.mygdx.game;

public class Tile {

    int xpos;
    int ypos;
    boolean closed = false;
    boolean inList = false;
    int deltaMax;
    int cost;
    boolean obstacle;
    int total;
    Tile sourceTile;
    Tile previous;
    boolean colour = false;

    public Tile (int x, int y, boolean o)
    {
        xpos = x;
        ypos = y;
        obstacle = o;
    }

    public void setClosed(){
        closed = true;
    }

    public void setInList(){ inList = true;}

    public  void setColour(){colour = true;}

    public void setDeltaMax(int dm){
        deltaMax = dm;
    }

    public void setPrevious(Tile p){previous = p;}
    public void setCost(){
        cost = previous.cost + 1;
    }

    public void setTotal(){
        total = deltaMax + cost;
    }

    public void setSource(Tile source){sourceTile = source;}

    public Tile getSource(){return sourceTile;}

    public int getTotal(){return total;}

    public boolean getColour(){ return colour;}

    public boolean compareTo(Tile a, Tile b){
        if (a.xpos == b.xpos && a.ypos == b.ypos)
        {
            return true;
        }
        else
            return false;
    }



}
