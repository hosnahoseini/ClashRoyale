package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class Archer extends Soldier{

    public Archer(int level) {
        hitSpeed = 1.2;
        target = TargetType.AIR_GROUND;
        range = 5;
        areaSplash = false;

        switch (level){
            case 1 :
                hp = 125;
                damage = 33;
                break;
            case 2 :
                hp = 127;
                damage = 44;
                break;
            case 3 :
                hp = 151;
                damage = 48;
                break;
            case 4 :
                hp = 166;
                damage = 53;
                break;
            case 5 :
                hp = 182;
                damage = 58;
                break;
        }


    }
}