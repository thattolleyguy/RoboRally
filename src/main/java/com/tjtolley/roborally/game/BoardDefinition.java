/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.tjtolley.roborally.game.Tile.Direction;
import com.tjtolley.roborally.game.Tile.EdgeType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author tyler
 */
public class BoardDefinition
{

    private final String name;
    private final List<List<Tile>> tiles;
    private final int width;
    private final int height;

    public BoardDefinition(@JsonProperty("name") String name, @JsonProperty("tiles") List<List<Tile>> tiles, @JsonProperty("width") int width, @JsonProperty("height") int height)
    {
        this.name = name;
        this.tiles = tiles;
        this.width = width;
        this.height = height;
    }

    public static BoardDefinition randomConfiguration(String name, int height, int width)
    {
        Random r = new Random();
        List<List<Tile>> board = Lists.newArrayList();
        Tile.TileType[] values = Tile.TileType.values();
        final Direction[] dirValues = Direction.values();
        for (int x = 0; x < width; x++) {
            final ArrayList<Tile> column = Lists.<Tile>newArrayList();
            board.add(x, column);
            for (int y = 0; y < height; y++) {
                int valIdx = r.nextInt(values.length);
                int dirIdx = r.nextInt(dirValues.length);
                column.add(y, new Tile(values[valIdx], x, y, dirValues[dirIdx], EdgeType.EMPTY, EdgeType.EMPTY, EdgeType.EMPTY, EdgeType.EMPTY));
            }
        }
        return new BoardDefinition(name, board, width, height);
    }

    public static BoardDefinition fromSavedBoard(String boardName)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/assets/boards/" + boardName.toLowerCase() + ".json");
            if (!file.exists()) {
                throw new IllegalArgumentException("File not found");
            }
            Map readValue = mapper.readValue(file, Map.class);
            int width = (int) readValue.get("width");
            int height = (int) readValue.get("height");
            List<List<Map<String, Object>>> tiles = (List<List<Map<String, Object>>>) readValue.get("board");
            List<List<Tile>> board = Lists.newArrayList();
            for (int row = 0; row < width; row++) {
                board.add(row, Lists.<Tile>newArrayList());
            }
            for (List<Map<String, Object>> list : tiles) {
                for (Map<String, Object> tileMap : list) {
                    final int x = (int) tileMap.get("x");
                    List<Tile> column = board.get(x);
                    final int y = (int) tileMap.get("y");
                    final Direction direction = tileMap.containsKey("rotation") ? Direction.valueOf((String) tileMap.get("rotation")) : Direction.NORTH;
                    final Tile.EdgeType northEdge = tileMap.containsKey("northEdge") ? EdgeType.valueOf((String) tileMap.get("northEdge")) : Tile.EdgeType.EMPTY;
                    final Tile.EdgeType southEdge = tileMap.containsKey("southEdge") ? EdgeType.valueOf((String) tileMap.get("southEdge")) : Tile.EdgeType.EMPTY;
                    final Tile.EdgeType eastEdge = tileMap.containsKey("eastEdge") ? EdgeType.valueOf((String) tileMap.get("eastEdge")) : Tile.EdgeType.EMPTY;
                    final Tile.EdgeType westEdge = tileMap.containsKey("westEdge") ? EdgeType.valueOf((String) tileMap.get("westEdge")) : Tile.EdgeType.EMPTY;
                    column.add(y, new Tile(Tile.TileType.valueOf((String) tileMap.get("tileType")), x, y, direction, northEdge, southEdge, eastEdge, westEdge));
                }
            }
            return new BoardDefinition((String) readValue.get("name"), board, width, height);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Unable to parse board", ex);
        }
    }

    public static BoardDefinition fromBoardMap(Map<String, Object> boardMap)
    {
        String boardType = (String) boardMap.get("boardType");
        switch (boardType) {
            case "composite":
                return null;
            case "custom":
                return null;
            case "predefined":
                return null;
            case "random":
            default:
                return BoardDefinition.randomConfiguration("custom", (Integer) boardMap.get("height"), (Integer) boardMap.get("width"));
        }
    }

    public List<List<Tile>> getTiles()
    {
        return tiles;
    }

    public String getName()
    {
        return name;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

}
