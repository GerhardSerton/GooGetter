package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Enemy extends Entity {

    GooGetter.Direction direction;
    Tile[][] map;
    Tile[][] colourMap;
    int xblock;
    int yblock;

    public Enemy(GooGetter game, int x, int y, int speed, Texture texture, int xblock, int yblock) {
        super(game, x, y, speed, texture);
        this.xblock = xblock;
        this.yblock = yblock;
    }

    public void nearestTile(){
        int cx = x;
        int cy = y;

        cx = cx / 20;
        cy = cy / 20;

        xblock = cx;
        yblock = cy;
    }


    public void update(float delta, int playerx, float playery){
        dx = 0;
        dy = 0;

        if(playerx > xblock)
        {
            dx = (int)(speed * delta);
            direction = GooGetter.Direction.R;
        }
        else if (playerx < xblock)
        {
            dx = (int)(-speed * delta);
            direction = GooGetter.Direction.L;
        }

        if (playery > yblock)
        {
            dy = (int)(speed * delta);
            direction = GooGetter.Direction.U;
        }
        else if (playery < yblock)
        {
            dy = (int)(-speed * delta);
            direction = GooGetter.Direction.D;
        }
    }

    public void initialiseMap(int[][] level){
        Tile[][] map2 = new Tile[level.length][level[0].length];
        Tile[][] map3 = new Tile[level.length][level[0].length];
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[0].length; x++)
            {
                boolean obs = false;
                if (level[y][x] == 1 || level[y][x] == 2 || level[y][x] == 6)
                {obs = true;}
                map2[y][x] = new Tile(x, y, obs);
                map3[y][x] = new Tile(x, y, obs);
            }
        }
        this.map = map2;
        this.colourMap = map3;
    }

    public void setBlocks(int xb, int yb){
        xblock = xb;
        yblock = yb;
    }
    public Tile pathfind(int xd, int yd){

        int startingX = xblock;
        int startingY = yblock;

        Comparator<Tile> compare = Comparator.comparingInt(Tile::getTotal);

        PriorityQueue<Tile> open = new PriorityQueue<Tile>(compare);
        PriorityQueue<Tile> closed = new PriorityQueue<Tile>(compare);

        map[startingY][startingX].setInList();
        open.add(map[startingY][startingX]);
        open.peek().setSource(open.peek());
        open.peek().cost = 0;


        while (!open.isEmpty())
        {
            Tile current = open.poll();
            if (current.cost == 1)
            {
                current.setSource(current);
            }
            if (current.compareTo(current, map[yd][xd]))
            {

                this.colourPath(current);
                return current.getSource();
            }
            else
            {
                current.setClosed();
                closed.add(current);

                int cy = current.ypos;
                int cx = current.xpos;

                if (cx - 1 > 0)
                {
                    if (!map[cy][cx-1].inList && !map[cy][cx-1].obstacle && !map[cy][cx-1].closed)
                    {
                        map[cy][cx-1].setPrevious(current);
                        map[cy][cx-1].setInList();
                        map[cy][cx-1].setSource(current.getSource());
                        map[cy][cx-1].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                        map[cy][cx-1].setCost();
                        map[cy][cx-1].setTotal();

                        open.add(map[cy][cx-1]);
                    }
                }
                if (cx + 1 < 25)
                {
                    if (!map[cy][cx+1].inList && !map[cy][cx+1].obstacle && !map[cy][cx+1].closed)
                    {
                        map[cy][cx+1].setPrevious(current);
                        map[cy][cx+1].setInList();
                        map[cy][cx+1].setSource(current.getSource());
                        map[cy][cx+1].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                        map[cy][cx+1].setCost();
                        map[cy][cx+1].setTotal();

                        open.add(map[cy][cx+1]);
                    }
                }
                if (cy - 1 > 0)
                {
                    if (!map[cy-1][cx].inList && !map[cy-1][cx].obstacle && !map[cy-1][cx].closed)
                    {
                        map[cy-1][cx].setPrevious(current);
                        map[cy-1][cx].setInList();
                        map[cy-1][cx].setSource(current.getSource());
                        map[cy-1][cx].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                        map[cy-1][cx].setCost();
                        map[cy-1][cx].setTotal();

                        open.add(map[cy-1][cx]);
                    }
                }
                if (cy + 1 < 25)
                {
                    if (!map[cy+1][cx].inList && !map[cy+1][cx].obstacle && !map[cy+1][cx].closed)
                    {
                        map[cy+1][cx].setPrevious(current);
                        map[cy+1][cx].setInList();
                        map[cy+1][cx].setSource(current.getSource());
                        map[cy+1][cx].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                        map[cy+1][cx].setCost();
                        map[cy+1][cx].setTotal();

                        open.add(map[cy+1][cx]);
                    }
                }
                /**
                if (cy + 1 < 25 && cx + 1 < 25)
                {
                    if (!map[cy+1][cx+1].inList && !map[cy+1][cx+1].obstacle && !map[cy+1][cx+1].closed)
                    {
                        map[cy+1][cx+1].setPrevious(current);
                        map[cy+1][cx+1].setInList();
                        map[cy+1][cx+1].setSource(current.getSource());
                        map[cy+1][cx+1].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                        map[cy+1][cx+1].setCost();
                        map[cy+1][cx+1].setTotal();

                        open.add(map[cy+1][cx+1]);
                    }
                }
                if (cy - 1 > 0 && cx + 1 < 25)
            {
                if (!map[cy-1][cx+1].inList && !map[cy-1][cx+1].obstacle && !map[cy-1][cx+1].closed)
                {
                    map[cy-1][cx+1].setPrevious(current);
                    map[cy-1][cx+1].setInList();
                    map[cy-1][cx+1].setSource(current.getSource());
                    map[cy-1][cx+1].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                    map[cy-1][cx+1].setCost();
                    map[cy-1][cx+1].setTotal();

                    open.add(map[cy-1][cx+1]);
                }
            }
                if (cy - 1 > 0 && cx - 1 > 0)
            {
                if (!map[cy-1][cx-1].inList && !map[cy-1][cx-1].obstacle && !map[cy-1][cx-1].closed)
                {
                    map[cy-1][cx-1].setPrevious(current);
                    map[cy-1][cx-1].setInList();
                    map[cy-1][cx-1].setSource(current.getSource());
                    map[cy-1][cx-1].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                    map[cy-1][cx-1].setCost();
                    map[cy-1][cx-1].setTotal();

                    open.add(map[cy-1][cx-1]);
                }
            }
                if (cy + 1 < 25 && cx - 1 > 0)
                {
                    if (!map[cy+1][cx-1].inList && !map[cy+1][cx-1].obstacle && !map[cy+1][cx-1].closed)
                    {
                        map[cy+1][cx-1].setPrevious(current);
                        map[cy+1][cx-1].setInList();
                        map[cy+1][cx-1].setSource(current.getSource());
                        map[cy+1][cx-1].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                        map[cy+1][cx-1].setCost();
                        map[cy+1][cx-1].setTotal();

                        open.add(map[cy+1][cx-1]);
                    }
                }
                 */

                /**for (int cy = current.ypos - 1; cy <= current.ypos + 1; cy++) {
                    for (int cx = current.xpos - 1; cx <= current.xpos + 1; cx++) {
                        if(cy >= 0 && cx >= 0 && cy < map.length && cx < map.length)
                        {
                            if (!map[cy][cx].inList && !map[cy][cx].obstacle && !map[cy][cx].closed)
                            {
                                map[cy][cx].setPrevious(current);
                                map[cy][cx].setInList();
                                map[cy][cx].setSource(current.getSource());
                                map[cy][cx].setDeltaMax(this.deltaMax(cx, xd, cy, yd));
                                map[cy][cx].setCost();
                                map[cy][cx].setTotal();

                                open.add(map[cy][cx]);
                            }
                        }
                    }
                }*/
            }

        }
        System.out.println("Error: No path found");
        return new Tile(x, y, false);



    }

    public void colourPath(Tile t){
        Tile test = t;
        while (test.sourceTile != test)
        {
            colourMap[test.ypos][test.xpos].setColour();
            test = test.previous;
        }
        colourMap[test.ypos][test.xpos].setColour();
    }

    public Tile[][] getColourPath(){
        return colourMap;
    }


    public int deltaMax(int xa, int xb, int ya, int yb)
    {
        int deltaX = Math.abs(xa - xb);
        int deltaY = Math.abs(ya - yb);

        if (deltaX >= deltaY)
            return deltaX;
        else
            return deltaY;
    }
}
