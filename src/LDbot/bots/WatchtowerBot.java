package LDbot.bots;

import battlecode.common.*;

public strictfp class WatchtowerBot extends RobotBot {
    @Override
    public void run(RobotController rc) throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();

        RobotInfo[] enemies = rc.senseNearbyRobots(actionRadius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = getMinHealth(enemies).location;

            rc.setIndicatorString("Trying to attack");

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }
    }
}
