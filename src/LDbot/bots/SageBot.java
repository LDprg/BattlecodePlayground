package LDbot.bots;

import LDbot.RobotBot;
import LDbot.util.Navigator;
import battlecode.common.*;

public strictfp class SageBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;
        int visionRadius = rc.getType().visionRadiusSquared;

        RobotInfo[] enemies = rc.senseNearbyRobots(actionRadius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = getMinHealth(enemies).location;

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }

        enemies = rc.senseNearbyRobots(visionRadius, opponent);
        if (rc.isMovementReady()) {
            Direction dir = directions[rng.nextInt(directions.length)];

            if (enemies.length > 0)
                dir = Navigator.findPath(rc, getMinHealth(enemies).getLocation());

            if (rc.canMove(dir))
                rc.move(dir);
        }
    }
}
