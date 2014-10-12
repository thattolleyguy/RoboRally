package com.tjtolley.roborally.game;

import com.google.common.collect.Maps;
import java.util.EnumMap;

public class Tile
{
    private final TileType tileType;
    private final EnumMap<Direction, EdgeType> edges;
    private final Direction rotation;
    private final int x;
    private final int y;

    public Tile(TileType tileType, int x, int y)
    {
        this.edges = Maps.newEnumMap(Direction.class);
        this.tileType = tileType;
        this.x = x;
        this.y = y;
        this.rotation = Direction.NORTH;
    }

    public TileType getTileType()
    {
        return tileType;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public static enum TileType
    {
        EMPTY,
        CONVEYOR_SINGLE_STRAIGHT,
        CONVEYOR_SINGLE_CW_CURVE,
        CONVEYOR_SINGLE_CCW_CURVE,
        CONVEYOR_SINGLE_CW_TEE,
        CONVEYOR_SINGLE_CCW_TEE,
        CONVEYOR_SINGLE_TEE,
        CONVEYOR_DOUBLE_STRAIGHT,
        CONVEYOR_DOUBLE_CW_CURVE,
        CONVEYOR_DOUBLE_CCW_CURVE,
        CONVEYOR_DOUBLE_CW_TEE,
        CONVEYOR_DOUBLE_CCW_TEE,
        CONVEYOR_DOUBLE_TEE,
        PUSHER_ODD,
        PUSHER_EVEN,
        PIT,
        GEAR_CW,
        GEAR_CCW,
        WRENCH,
        WRENCH_AND_HAMMER;
    }

    public static enum EdgeType
    {
        EMPTY,
        WALL,
        SINGLE_LASER_WALL,
        DOUBLE_LASER_WALL,
        TRIPLE_LASER_WALL
    }

    public static enum Direction
    {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

}
