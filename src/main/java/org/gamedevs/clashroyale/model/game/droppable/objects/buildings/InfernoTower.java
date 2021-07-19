package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class InfernoTower extends Building {

    int maxDamage;

    public InfernoTower(int level, Side side){
        super(side);
        hitSpeed = 0.4;
        attackTargetType = TargetType.AIR_GROUND;
        range = 6;
        lifeTime = 40;
        effectiveLifeTime = true;

        switch (level) {
            case 1:
                hp = 800;
                damage = 20;
                maxDamage = 400;
                break;
            case 2:
                hp = 880;
                damage = 22;
                maxDamage = 440;
                break;
            case 3:
                hp = 968;
                damage = 24;
                maxDamage = 484;
                break;
            case 4:
                hp = 1064;
                damage = 26;
                maxDamage = 532;
                break;
            case 5:
                hp = 1168;
                damage = 29;
                maxDamage = 584;
                break;
        }
    }
}