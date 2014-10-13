/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author tyler
 */
public class Game
{

	private final String name;
	private final UUID id;
	private final BoardConfiguration configuration;

	public Game(@JsonProperty("name") String name, @JsonProperty("id") UUID id, int width, int height) throws IOException
	{
		this.name = name;
		this.id = id;
//        configuration = BoardConfiguration.randomConfiguration(name, width, height);
		BoardConfiguration tempBoard = null;
		try
		{
			tempBoard = BoardConfiguration.fromSavedBoard(name);
		}
		catch (Exception ex)
		{
			tempBoard = BoardConfiguration.randomConfiguration(name, width, height);
		}
		configuration = tempBoard;
	}

	public String getName()
	{
		return name;
	}

	public UUID getId()
	{
		return id;
	}

	public List<List<Tile>> getBoard()
	{
		return configuration.getBoard();
	}

}
