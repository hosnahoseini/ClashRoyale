package org.gamedevs.clashroyale.model.game.battle.tools;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;

/**
 * a to generate card for game deck
 *
 * @author Hosna Hoseini
 * 9823010 -CE@AUT
 * @version 1.0
 */
public class CardGenerator {

    /**
     * players all cards which he can use them in this game
     */
    private DeckContainer completeDeck = new DeckContainer();
    /**
     * elixir of current player
     */
    private Elixir elixir;
    /**
     * next card in the game deck
     */
    private Card nextCard;

    /**
     * constructor
     *
     * @param deckContainer players all cards
     * @param elixir        elixir
     */
    public CardGenerator(DeckContainer deckContainer, Elixir elixir) {
        completeDeck.getDeck().addAll(deckContainer.getDeck());
        this.elixir = elixir;
    }

    /**
     * get 4 cards as firsts card in deck
     *
     * @return cards
     */
    public ArrayList<Card> getInitialCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Card card = completeDeck.getRandomCard();
            putCardAsElixirListener(card);
            cards.add(card);
            completeDeck.removeCard(card);
//            Console.getConsole().printTracingMessage(card.getCardName().toString());
        }

        setNextCard();
        return cards;

    }

    /**
     * get a new card to add into deck,
     * this is the main method that you have to use
     * whenever you need a new card
     *
     * @return new card
     */
    public Card getANewCard() {
        Card out = nextCard;
        do {
            nextCard = completeDeck.getRandomCard();
        }while (nextCard == null);
        completeDeck.removeCard(nextCard);
        putCardAsElixirListener(nextCard);
        return out;
    }

    /**
     * choose a card as next card in the deck and save it
     * so you can use this card later
     */
    private void setNextCard() {
        nextCard = completeDeck.getRandomCard();
        putCardAsElixirListener(nextCard);
        completeDeck.removeCard(nextCard);
    }

    /**
     * add card to elixir listener so whenever the elixir become
     * less than cost the card will be locked
     *
     * @param card card
     */
    private void putCardAsElixirListener(Card card) {
        if (card != null) {
            elixir.elixirValueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    if (elixir.elixirValueProperty().get() < card.getCost() && !card.isLock()) {
                        card.setLock(true);
                    } else if (elixir.elixirValueProperty().get() >= card.getCost() && card.isLock()) {
                        card.setLock(false);
                    }
                }
            });
        }
    }

    //Getter
    public Card getNextCard() {
        return nextCard;
    }
    public DeckContainer getCompleteDeck() {
        return completeDeck;
    }
    public Elixir getElixir() {
        return elixir;
    }

    //Setter
    public void setElixir(Elixir elixir) {
        this.elixir = elixir;
    }


}
