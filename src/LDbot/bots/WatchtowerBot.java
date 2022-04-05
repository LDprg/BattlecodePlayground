package LDbot.bots;

import LDbot.RobotBot;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static LDbot.util.Cache.enemyRobots;
import static LDbot.util.Cache.rc;

public strictfp class WatchtowerBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        if (enemyRobots.length > 0) {
            MapLocation toAttack = getMinHealth(enemyRobots).location;

            rc.setIndicatorString("Trying to attack");

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }
    }
}
