package com.tjtolley.roborally.game;

public class Player
{
    private final String name;
    private final Position startingPosition;
    private final int startingPositionNumber;
    Position robotPosition;
    Position archivePosition;
    int damage;
    int lives;

    // TODO: Add option cards
    public Player(String name, Position startingPosition, int startingPositionNumber)
    {
        this.name = name;
        this.startingPosition = startingPosition;
        this.startingPositionNumber = startingPositionNumber;
        this.archivePosition = startingPosition;
        this.robotPosition = startingPosition;
        lives = 3;
        damage = 0;
    }

}
