package com.tjtolley.roborally.game;

public class Tile
{
	private final TileType tileType;
	private final int x;
	private final int y;

	public Tile(TileType tileType, int x, int y)
	{
		this.tileType = tileType;
		this.x = x;
		this.y = y;
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
		PLAIN,
		CONVEYOR_SINGLE,
		CONVEYOR_DOUBLE,
		PIT,
		GEAR_CW,
		GEAR_CCW,
		WRENCH,
		WRENCH_AND_HAMMER;

	}

}
