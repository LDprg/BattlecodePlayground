package LDbot.bots;

import LDbot.RobotBot;
import LDbot.util.Navigator;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static LDbot.util.Cache.*;
import static LDbot.util.Navigator.getRandomDestination;

public strictfp class SoldierBot extends RobotBot {
    private final MapLocation searchDestination = getRandomDestination();
    private Mode mode = Mode.SEARCH;

    @Override
    public void run() throws GameActionException {
        rc.setIndicatorString("Mode: " + mode.toString());

        if (enemyRobots.length > 0) {
            MapLocation toAttack = getMinHealth(enemyRobots).location;

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }

        if (robotLocation.isWithinDistanceSquared(searchDestination, 10))
            mode = Mode.ENGAGE;

        if (rc.isMovementReady() && mode == Mode.ENGAGE) {
            Direction dir = getRndDir();

            if (enemyRobotsVisible.length > 0)
                dir = Navigator.findPath(getMinHealth(enemyRobotsVisible).getLocation());

            if (EnemyArchon != null)
                dir = Navigator.findPath(EnemyArchon);

            if (rc.canMove(dir))
                rc.move(dir);
        } else if (rc.isMovementReady() && mode == Mode.SEARCH) {
            Direction dir = Navigator.findPath(searchDestination);
            if (rc.canMove(dir))
                rc.move(dir);
        }
    }

    private enum Mode {
        SEARCH,
        ENGAGE
    }
}
