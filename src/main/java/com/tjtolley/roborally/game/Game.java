/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 *
 * @author tyler
 */
public class Game
{
	private final String name;
	private final UUID id;
	private final Tile[][] board;

	public Game(@JsonProperty("name") String name, @JsonProperty("id") UUID id, int width, int height)
	{
		this.name = name;
		this.id = id;
		board = new Tile[height][width];
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				board[y][x] = new Tile(Tile.TileType.PLAIN, x, y);
			}
		}
	}

	public String getName()
	{
		return name;
	}

	public UUID getId()
	{
		return id;
	}

	public Tile[][] getBoard()
	{
		return board;
	}
	
	

}
