package LDbot.bots;

import LDbot.RobotBot;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public strictfp class WatchtowerBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;

        RobotInfo[] enemies = rc.senseNearbyRobots(actionRadius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = getMinHealth(enemies).location;

            rc.setIndicatorString("Trying to attack");

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }
    }
}
