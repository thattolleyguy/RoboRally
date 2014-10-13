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
public class BoardConfiguration
{

    private final String name;
    private final List<List<Tile>> board;
    private final int width;
    private final int height;

    public BoardConfiguration(@JsonProperty("name") String name, @JsonProperty("board") List<List<Tile>> board, @JsonProperty("width") int width, @JsonProperty("height") int height)
    {
        this.name = name;
        this.board = board;
        this.width = width;
        this.height = height;
    }

    public static BoardConfiguration randomConfiguration(String name, int height, int width)
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
                column.add(y, new Tile(values[valIdx], x, y, dirValues[dirIdx]));
            }
        }
        return new BoardConfiguration(name, board, width, height);
    }

    public static BoardConfiguration fromPredefinedBoard(PredefinedBoard boardType) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        Map readValue = mapper.readValue(new File(boardType.fileLocation), Map.class);
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
                final Direction direction = tileMap.containsKey("rotation") ? Direction.valueOf((String) tileMap.get("rotation")) : Direction.EAST;
                column.add(y, new Tile(Tile.TileType.valueOf((String) tileMap.get("tileType")), x, y, direction));
            }
        }
        return new BoardConfiguration((String) readValue.get("name"), board, width, height);
    }

    public static enum PredefinedBoard
    {

        SPINZONE("src/main/resources/assets/boards/spinzone.json");
        private final String fileLocation;

        PredefinedBoard(String fileLocation)
        {
            this.fileLocation = fileLocation;
        }

    }

    public List<List<Tile>> getBoard()
    {
        return board;
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

    public static void main(String[] args) throws IOException
    {
        BoardConfiguration fromPredefinedBoard = BoardConfiguration.fromPredefinedBoard(BoardConfiguration.PredefinedBoard.SPINZONE);
        System.out.println(fromPredefinedBoard.board);
    }

}
