package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Archer;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * a class which handle buildings
 *
 * @author Pouya Mohammadi -Hosna Hoseini
 * 9826039 -CE@AUT     9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Building extends GameObject {

    /**
     * Life time of building.
     * Set a std  value for infinite life time (used for king and princesses towers)
     */
    protected int lifeTime;

    /**
     * If life time matchers for building,
     */
    protected boolean effectiveLifeTime;

    /**
     * Setting default values for building object
     */
    protected Building(Side side){
        super(side);
        myType = TargetType.BUILDING;
    }

    /**
     * Start attacking to the target (gives damage to target object)
     */
    @Override
    protected void attackOrMove(GameObject target) {
        if (target != null) {
            new Bullet(this).throwBullet(headTile, target.getHeadTile());
            state = GameObjectState.ATTACK;
            target.reduceHP(damage);
        } else {
            state = GameObjectState.IDLE;
        }
    }

    /**
     * Updates hp with life time or vise versa
     */
    protected void updateLifeTime(){

    }

    public int getLifeTime() {
        return lifeTime;
    }
}
