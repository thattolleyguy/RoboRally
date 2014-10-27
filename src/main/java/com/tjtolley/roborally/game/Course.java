/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.google.common.collect.Lists;
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
        List<Map<String, Object>> placedBoards = (List<Map<String, Object>>) courseMap.get("placedBoards");
        List<PlacedBoard> boards = Lists.newArrayList();
        for (Map<String, Object> boardDefinition : placedBoards) {
            final Map<String, Object> positionOffset = (Map<String, Object>) boardDefinition.get("boardStartOffset");
            boards.add(new PlacedBoard(
                    BoardDefinition.fromBoardMap((Map<String, Object>) boardDefinition.get("boardDefinition")),
                    new Position((Integer) positionOffset.get("x"), (Integer) positionOffset.get("y"))));
        }
        return new Course(boards, Lists.<Position>newArrayList(), Lists.<Position>newArrayList());
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

        public BoardDefinition getBoardDefinition()
        {
            return boardDefinition;
        }

        public Position getBoardStartOffset()
        {
            return boardStartOffset;
        }

    }

}
