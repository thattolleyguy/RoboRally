/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tjtolley.roborally.game;

import com.google.common.collect.Lists;
import java.util.List;

/**
 *
 * @author tyler
 */
public class Card
{
    public static enum CardType
    {
        U_TURN(Lists.newArrayList(10, 20, 30, 40, 50, 60)),
        ROTATE_LEFT(Lists.newArrayList(70, 90, 110, 130, 150, 170, 190, 210, 230,
                                       250, 270, 290, 310, 330, 350, 370, 390, 410)),
        ROTATE_RIGHT(Lists.newArrayList(80, 100, 120, 140, 160, 180, 200, 220, 240,
                                        260, 280, 300, 320, 340, 360, 380, 400, 420)),
        BACK_UP(Lists.newArrayList(430, 440, 450, 460, 470, 480)),
        FORWARD_1(Lists.newArrayList(490, 500, 510, 520, 530, 540, 550, 560, 570,
                                     580, 590, 600, 610, 620, 630, 640, 650, 660)),
        FORWARD_2(Lists.newArrayList(670, 680, 690, 700, 710, 720, 730, 740, 750,
                                     760, 770, 780)),
        FORWARD_3(Lists.newArrayList(790, 800, 810, 820, 830, 840));

        public final List<Integer> priorities;

        private CardType(List<Integer> priorities)
        {
            this.priorities = priorities;
        }

    }
    public final CardType cardType;
    public final int priority;
    
    public static final int NUMBER_OF_CARDS=84;

    public Card(CardType cardType, int priority)
    {
        this.cardType = cardType;
        this.priority = priority;
    }

}
