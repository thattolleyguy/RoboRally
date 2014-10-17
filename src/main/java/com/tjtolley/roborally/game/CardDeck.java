package com.tjtolley.roborally.game;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.LinkedList;

public class CardDeck
{
    private LinkedList<Card> deckOfCards;

    public CardDeck()
    {
        deckOfCards = Lists.newLinkedList();
        int i = 0;
        for (Card.CardType cardType : Card.CardType.values()) {
            for (Integer priority : cardType.priorities) {
                deckOfCards.add(new Card(cardType, priority));
            }
        }
    }

    public Card deal()
    {
        return deckOfCards.pop();
    }

    public void shuffle()
    {
        Collections.shuffle(deckOfCards);
    }
}
