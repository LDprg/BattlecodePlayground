package LDbot.util;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import static LDbot.RobotBot.directions;
import static LDbot.RobotBot.rng;

public class Navigator {
    public static Direction findPath(RobotController rc, MapLocation stop) throws GameActionException {
        Direction dir = rc.getLocation().directionTo(stop);
        if (rc.isLocationOccupied(rc.getLocation().add(dir)))
            dir = directions[rng.nextInt(directions.length)];
        return dir;
    }

    public static MapLocation getRandomDestination(RobotController rc) {
        return new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
    }
}
