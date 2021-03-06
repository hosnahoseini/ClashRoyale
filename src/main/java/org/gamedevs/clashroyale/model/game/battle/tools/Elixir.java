package org.gamedevs.clashroyale.model.game.battle.tools;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * A class to represent and handle Elixir in game
 *
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class Elixir extends Runnable {

    /**
     * time (in seconds) that elixir has to produce slowly
     */
    private static final int SLOW_PRODUCTION_DURATION = 60 * 1;
    /**
     * maximum elixir that can be produce;
     */
    private static final int MAXIMUM_ELIXIR = 10;

    /**
     * elixir value
     */
    private final DoubleProperty elixirValue;
    /**
     * game clock
     */
    private final Clock clock;

    /**
     * constructor
     */
    public Elixir(Clock clock) {
        this.clock = clock;
        elixirValue = new SimpleDoubleProperty(4);
        threadName = "Elixir";
    }

    /**
     * produce elixir considering speed for 3 minute
     * stop when it reaches 3 min
     */
    public void run() {
        do {
            if (elixirValue.get() < MAXIMUM_ELIXIR) {
                if (clock.getTotalSeconds() > SLOW_PRODUCTION_DURATION)
                    Platform.runLater(() -> elixirValue.setValue(elixirValue.add(0.05).getValue()));
                else
                    Platform.runLater(() -> elixirValue.setValue(elixirValue.add(0.1).getValue()));

            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Console.getConsole().printTracingMessage("interruption in elixir thread while sleeping");
            }

        } while (clock.getTotalSeconds() > 0);
    }


    /**
     * stop Producing elixir
     */
    public void stopProducing() {
        super.shutdown();
    }

    /**
     * start elixir
     */
    public void startElixir() {
        super.start();
    }

    /**
     * reduce elixir
     *
     * @param reduction amount of used elixir
     */
    public void reduceElixir(float reduction) {
        Platform.runLater(() -> elixirValue.setValue(elixirValue.subtract(reduction).getValue()));
    }

    //Getter
    public double getElixirValue() {
        return elixirValue.get();
    }
    public DoubleProperty elixirValueProperty() {
        return elixirValue;
    }
}
