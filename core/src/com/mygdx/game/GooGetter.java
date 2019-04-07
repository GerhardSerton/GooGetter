package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GooGetter implements Screen {
	GooGame game;
	SpriteBatch batch;
	Texture img;

	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	ArrayList<Pit> pits = new ArrayList<Pit>();
	EndPoint end;

    enum Axis { X, Y };

	enum Direction { U, D, L, R };

	int levelNumber = 1;
	Player player;
	int[][] chosenWorld;

	int[][] level0 = new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};

	int[][] level1 = new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};

	int[][] level2 = new int[][]{
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,0,0,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,3,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,3,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	

	public GooGetter(boolean save, GooGame game){
		this.game = game;
		this.create();

	}


	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		try {
			Scanner level = new Scanner(new File("SaveFile.txt"));
			String n = level.next();
			levelNumber = Integer.parseInt(n);
		}
		catch (FileNotFoundException e){
			levelNumber = 0;
			System.out.println("FILE NOT FOUND");
		}
		if (levelNumber == 0)
		{
			chosenWorld = level0;
		}
		else if (levelNumber == 1)
		{
			chosenWorld = level1;
		}
		else
		{
			chosenWorld = level2;
		}
		scanWorld(chosenWorld);
	}

	public void scanWorld(int[][] world){
		for (int i = 0; i < 25; i++){
			for (int j = 0; j < 25; j++){
				if (world[i][j] == 4)
				{
					player = new Player(this, j * 20, i * 20, 200, new Texture("player1.png"));
				}
				else if (world[i][j] == 3)
				{
					Enemy newE = new Enemy(this, j * 20, i * 20, 100, new Texture("robot.png"), j, i);
					newE.initialiseMap(chosenWorld);
					enemies.add(newE);
				}
				else if (world[i][j] == 2)
				{
					pits.add(new Pit(this, j*20, i*20,100, new Texture("spikes.png")));
				}
				else if(world[i][j] == 5)
				{
					end = new EndPoint(this, j*20, i*20, 0, new Texture("roofspikes.png"));
				}


			}
		}
	}

	public void movePlayer(Player p, /**Axis axis,*/ int newX, int newY){
        /**
	    Direction direction;
	    if(axis == Axis.Y) {
            if(newY -p.y < 0) direction = Direction.U;
            else direction = Direction.D;
        }
        else {
            if(newX - p.x < 0) direction = Direction.L;
            else direction = Direction.R;
        }
         */

        if(!tileCollision(p, p.direction, newX, newY)) {
            // full move with no collision
            p.move(newX, newY);
        }
    }

    public void moveEnemy(Enemy e, int newX, int newY)
	{
		if(!tileCollision(e, e.direction, newX, newY))
		{
			e.move(newX, newY);
			e.nearestTile();
		}
	}

    public boolean tileCollision(Entity e, Direction d, int newX, int newY){

	    boolean collision = false;
        int x1 = (int) Math.floor(Math.min(e.x, newX) / 20.0);
        int y1 = (int) Math.floor(Math.min(e.y, newY) / 20.0);
        int x2 = (int) Math.floor((Math.max(e.x, newX) + 20.0) / 20.0);
        int y2 = (int) Math.floor((Math.max(e.y, newY) + 20.0) / 20.0);

        for(int x = x1; x <= x2; x++) {
            for(int y = y1; y <= y2; y++) {
                if(chosenWorld[x][y] == 1) {
                    collision = true;
                }
            }
        }
	    return collision;}

    public boolean endpointCollision(int newX, int newY){
		boolean collision = false;
		EndPoint ep = end;

		if (newX < ep.x + 20 && ep.x < newX + 20 && newY < ep.y + 20 && ep.y < newY + 20)
			collision = true;

		return collision;

	}

	@Override
	public void render (float d) {

		float delta = Gdx.graphics.getDeltaTime();

		player.update(delta);
		movePlayer(player,player.x + player.dx, player.y + player.dy);
		Tile playerPos = player.nearestTile();
		int[][] pathMap = new int[][]{
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		};

		if (chosenWorld == level1) {
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).initialiseMap(chosenWorld);
				Tile enemyDest = enemies.get(i).pathfind(playerPos.xpos, playerPos.ypos);
				Tile[][] path = enemies.get(i).getColourPath();
				for (int y = 0; y < path.length; y++) {
					for (int x = 0; x < path[0].length; x++) {
						if (path[y][x].colour) {
							pathMap[y][x] = 1;
						}
					}
				}
				enemies.get(i).update(delta, enemyDest.xpos, enemyDest.ypos);
				moveEnemy(enemies.get(i), enemies.get(i).x + enemies.get(i).dx, enemies.get(i).y + enemies.get(i).dy);
			}
		}
		else if (chosenWorld == level2)
		{
			boolean swarm = true;
			int swarmx = 12;
			int swarmy = 5;

			enemies.get(0).initialiseMap(chosenWorld);
			Tile Dest = enemies.get(0).pathfind(playerPos.xpos, playerPos.ypos);
			Tile[][] path1 = enemies.get(0).getColourPath();
			for (int y = 0; y < path1.length; y++) {
				for (int x = 0; x < path1[0].length; x++) {
					if (path1[y][x].colour) {
						pathMap[y][x] = 1;
					}
				}
			}
			enemies.get(0).update(delta, Dest.xpos, Dest.ypos);
			moveEnemy(enemies.get(0), enemies.get(0).x + enemies.get(0).dx, enemies.get(0).y + enemies.get(0).dy);

			for (int i = 1; i < enemies.size(); i++) {
				enemies.get(i).initialiseMap(chosenWorld);
				Tile enemyDest = enemies.get(i).pathfind(enemies.get(0).xblock, enemies.get(0).yblock);
				Tile[][] path = enemies.get(i).getColourPath();
				for (int y = 0; y < path.length; y++) {
					for (int x = 0; x < path[0].length; x++) {
						if (path[y][x].colour) {
							pathMap[y][x] = 1;
						}
					}
				}
				enemies.get(i).update(delta, enemyDest.xpos, enemyDest.ypos);
				moveEnemy(enemies.get(i), enemies.get(i).x + enemies.get(i).dx, enemies.get(i).y + enemies.get(i).dy);
			}
		}

		if (endpointCollision(player.x, player.y))
		{
			try {
				BufferedWriter saver = new BufferedWriter(new FileWriter("SaveFile.txt"));

				if (chosenWorld == level0) {
					saver.write("1");
					saver.close();
					game.setScreen(new GooGetter(true, this.game));

				} else if (chosenWorld == level1) {
					saver.write("2");
					saver.close();
					game.setScreen(new GooGetter(true, this.game));

				} else if (chosenWorld == level2) {
					saver.write("2");
					System.out.println("You've completed all of the levels!");
					saver.close();
				}
			}
			catch (IOException e)
			{
				System.out.println("ERROR: Save file not found");
			}
		}




		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.sBatch.begin();

		Texture tile = new Texture("wall.png");
		Texture pit = new Texture("spikes.png");
		Texture background = new Texture("background.png");
		Texture path = new Texture("path.png");

		for (int i = 0; i < 25; i++)
		{
			for (int j = 0; j < 25; j++)
			{
				if (chosenWorld[i][j] == 1)
				{
					game.sBatch.draw(tile, j*20, i*20);
				}
				else if (chosenWorld[i][j] == 2)
                {
                    game.sBatch.draw(pit, j*20, i*20);
                }
                else if (pathMap[i][j] == 1)
				{
					game.sBatch.draw(path, j*20, i*20);
				}
                else
				{
					game.sBatch.draw(background,j*20, i*20);
				}
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
            game.sBatch.draw(enemies.get(i).texture, enemies.get(i).x, enemies.get(i).y);
        }
		game.sBatch.draw(player.texture, player.x, player.y);
		game.sBatch.draw(end.texture, end.x, end.y);
		game.sBatch.end();
	}

	@Override
	public void show() {

	}


	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
