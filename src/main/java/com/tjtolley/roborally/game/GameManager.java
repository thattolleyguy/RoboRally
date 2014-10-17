/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public UUID createGame(Map<String, Object> gameSettings)
    {
        UUID gameId = UUID.randomUUID();
        try {
            //        configuration = BoardConfiguration.randomConfiguration(name, width, height);
            Map<String,Object> boardMap = (Map<String,Object>) gameSettings.get("board");

            BoardConfiguration board = BoardConfiguration.fromBoardMap(boardMap);
            gameMap.put(gameId, new Game((String) gameSettings.get("name"), gameId, board));
        }
        catch (IOException ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gameId;
    }
}
