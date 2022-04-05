package LDbot.bots;

import LDbot.RobotBot;
import LDbot.util.Navigator;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static LDbot.util.Cache.*;

public strictfp class SageBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        if (enemyRobots.length > 0) {
            MapLocation toAttack = getMinHealth(enemyRobots).location;

            if (rc.canAttack(toAttack))
                rc.attack(toAttack);
        }

        if (rc.isMovementReady()) {
            Direction dir = getRndDir();

            if (enemyRobotsVisible.length > 0)
                dir = Navigator.findPath(getMinHealth(enemyRobotsVisible).getLocation());

            if (EnemyArchon != null)
                dir = Navigator.findPath(EnemyArchon);

            if (rc.canMove(dir))
                rc.move(dir);
        }
    }
}
