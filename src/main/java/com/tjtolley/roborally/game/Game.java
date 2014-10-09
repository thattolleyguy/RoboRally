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

	public Game(@JsonProperty("name") String name, @JsonProperty("id") UUID id)
	{
		this.name = name;
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public UUID getId()
	{
		return id;
	}

}
