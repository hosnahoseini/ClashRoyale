package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class Wizard extends Soldier{
    public Wizard(int level) {
        hitSpeed = 1.7;
        target = TargetType.AIR_GROUND;
        range = 5;
        areaSplash = true;

        switch (level){
            case 1 :
                hp = 340;
                damage = 130;
                break;
            case 2 :
                hp = 374;
                damage = 143;
            case 3:
                hp = 411;
                damage = 157;
                break;
            case 4 :
                hp = 452;
                damage = 172;
                break;
            case 5 :
                hp = 496;
                damage = 182;
                break;
        }

    }
}