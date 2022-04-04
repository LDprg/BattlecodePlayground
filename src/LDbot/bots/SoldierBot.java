package LDbot.bots;

import LDbot.RobotBot;
import LDbot.util.Navigator;
import battlecode.common.*;

import static LDbot.util.Navigator.getRandomDestination;

public strictfp class SoldierBot extends RobotBot {
    private enum Mode {
        SEARCH,
        ENGAGE
    }

    private Mode mode = Mode.SEARCH;

    private MapLocation searchDestination = getRandomDestination(rc);

    @Override
    public void run() throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;
        int visionRadius = rc.getType().visionRadiusSquared;

        rc.setIndicatorString("Mode: " + mode.toString());

        RobotInfo[] enemies = rc.senseNearbyRobots(actionRadius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = getMinHealth(enemies).location;

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }

        if (EnemyArchon != null || rc.getLocation().isWithinDistanceSquared(searchDestination,10))
            mode = Mode.ENGAGE;

        enemies = rc.senseNearbyRobots(visionRadius, opponent);
        if (rc.isMovementReady() && mode == Mode.ENGAGE) {
            Direction dir = directions[rng.nextInt(directions.length)];

            if (enemies.length > 0)
                dir = Navigator.findPath(rc, getMinHealth(enemies).getLocation());

            if (EnemyArchon != null)
                dir = Navigator.findPath(rc, EnemyArchon);

            if (rc.canMove(dir))
                rc.move(dir);
        } else if (rc.isMovementReady() && mode == Mode.SEARCH){
            Direction dir = Navigator.findPath(rc, searchDestination);
            if (rc.canMove(dir))
                rc.move(dir);
        }
    }
}
