/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tyler
 */
public class Course
{
    private final List<PlacedBoard> boards;
    private final List<Position> flags;
    private final List<Position> startingPositions;

    public Course(List<PlacedBoard> boards, List<Position> flags, List<Position> startingPositions)
    {
        this.boards = boards;
        this.flags = flags;
        this.startingPositions = startingPositions;
    }

    public static Course fromCourseMap(Map<String, Object> courseMap)
    {
        return null;
    }

    public static class PlacedBoard
    {
        private final BoardDefinition boardDefinition;
        private final Position boardStartOffset;

        public PlacedBoard(BoardDefinition boardDefinition, Position boardStartOffset)
        {
            this.boardDefinition = boardDefinition;
            this.boardStartOffset = boardStartOffset;
        }

    }

}
