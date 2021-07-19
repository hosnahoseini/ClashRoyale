package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.Path;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.PathFinder;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;

public abstract class Soldier extends GameObject {

    /**
     * Speed of player (related to movement)
     */
    protected int speed;
    /**
     * Area splash ability (attack point or effect)
     */
    protected boolean areaSplash;

    /**
     * Setting default values for soldier object
     */
    protected Soldier(Side side) {
        super(side);
        myType = TargetType.GROUND; // except for baby dragon!
    }

    /**
     * Thread of object (applies algorithm of game object)
     */
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            headTile = new Tile(headTile.getX() + 1, headTile.getY() + 1);
            Console.getConsole().printTracingMessage(headTile.getX() + 1+","+ headTile.getY() + 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = GameObjectState.ATTACK;
        angle = Angle.EAST;
        System.out.println("changed");
    }

    /**
     * this algorithm finds closest target tile
     *
     * @return tile of closest target
     */
    protected Tile findClosestTargetTile() {
        ArrayList<GameObject> aliveEnemies = battleField.getOneSideObjects(Side.getOppositeSide(teamSide));
        aliveEnemies = new ArrayList<GameObject>(aliveEnemies);
        Tile nextTile = null;
        if (aliveEnemies != null) {
            double distance = 1000;
            for (GameObject enemy : aliveEnemies) {
                // Giant
                if (attackTargetType == TargetType.BUILDING) {
                    if (enemy.getMyType() == TargetType.BUILDING) {
                        if (distance > battleField.calculateDistance(headTile, enemy.getHeadPixel())) {
                            distance = battleField.calculateDistance(headTile, enemy.getHeadPixel());
                            nextTile = enemy.getHeadPixel();
                        }
                    }
                } else if (attackTargetType == TargetType.GROUND) {
                    if (enemy.getMyType() == TargetType.BUILDING || enemy.getMyType() == TargetType.GROUND) {
                        if (distance > battleField.calculateDistance(headTile, enemy.getHeadPixel())) {
                            distance = battleField.calculateDistance(headTile, enemy.getHeadPixel());
                            nextTile = enemy.getHeadPixel();
                        }
                    }
                } else {
                    if (distance > battleField.calculateDistance(headTile, enemy.getHeadPixel())) {
                        distance = battleField.calculateDistance(headTile, enemy.getHeadPixel());
                        nextTile = enemy.getHeadPixel();
                    }
                }
            }
        }
        return null;
    }

    /**
     * move me to next tile
     *
     * @param nextTile next tile
     * @return true if i have moved successfully
     */
    protected boolean move(Tile nextTile) {
        if (nextTile != null) {
            Angle nextTileAngel = headTile.getSurroundingTileAngel(nextTile);
            if (nextTileAngel != null) {
                if (headTile.carry(angle, z)) {
                    headTile = nextTile;
                    try {
                        Thread.sleep((int) (speed * 1000));
                    } catch (InterruptedException ignored) {
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates object to next place
     */
    protected void mover() {
        Thread moverThread = (new Thread(() -> {
            PathFinder pathFinder = new PathFinder(battleField);
            Path path = pathFinder.getPath();
            Tile closestTargetTile = findClosestTargetTile();
            while (hp > 0) {
                if (closestTargetTile != findClosestTargetTile()) {
                    closestTargetTile = findClosestTargetTile();
                    pathFinder.findPath(headTile, closestTargetTile, z);
                }
                if (state == GameObjectState.MOVING) {
                    if (!move(path.forward())) {
                        pathFinder.findPath(headTile, closestTargetTile, z);
                    }
                }
            }
        }));
        moverThread.setDaemon(true);
        moverThread.start();
    }

    /**
     * Finds shortest path to the nearest target
     */
    protected void findTarget() {

    }


}