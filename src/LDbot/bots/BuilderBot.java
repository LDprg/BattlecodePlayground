package LDbot.bots;

import LDbot.RobotBot;
import LDbot.util.Navigator;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import static LDbot.util.Cache.*;

public strictfp class BuilderBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        RobotInfo[] friendly = rc.senseNearbyRobots(-1, rc.getTeam());
        Direction dir = directions[rng.nextInt(directions.length)];

        int buildingCount = 0;
        for (RobotInfo ri :
                friendly) {
            if (ri.getType().isBuilding())
                ++buildingCount;
        }

        RobotInfo repair = null;

        for (RobotInfo ri :
                friendly) {
            if (ri.getType().isBuilding() && ri.getHealth() < ri.getType().getMaxHealth(ri.getLevel())) {
                if (rc.getLocation().isAdjacentTo(ri.getLocation()))
                    repair = ri;
                else {
                    if (rc.isMovementReady() && rc.canMove(Navigator.findPath(rc, ri.getLocation())))
                        rc.move(Navigator.findPath(rc, ri.getLocation()));
                }
            }
        }

        if (buildingCount <= 0 && rc.canBuildRobot(RobotType.WATCHTOWER, dir))
            rc.buildRobot(RobotType.WATCHTOWER, dir);
        else if (repair != null && rc.canRepair(repair.getLocation()))
            rc.repair(repair.getLocation());
        else if (rc.isMovementReady() && rc.canMove(dir))
            rc.move(dir);
    }
}
