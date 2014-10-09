/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author tyler
 */
public class GameManager
{
	private Map<UUID, Game> gameMap = Maps.newHashMap();

	public Game getGame(UUID id)
	{
		return gameMap.get(id);
	}

	public Collection<Game> getGames()
	{
		return gameMap.values();
	}

	public UUID createGame(String name)
	{
		UUID gameId = UUID.randomUUID();
		gameMap.put(gameId, new Game(name, gameId, 12, 16));
		return gameId;
	}
}
