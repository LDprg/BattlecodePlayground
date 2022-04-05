package LDbot.util;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static LDbot.RobotBot.getRndDir;
import static LDbot.util.Cache.*;

public class Navigator {
    public static Direction findPath(MapLocation stop) throws GameActionException {
        Direction dir = robotLocation.directionTo(stop);
        if (rc.isLocationOccupied(robotLocation.add(dir)))
            return getRndDir();
        else
            return dir;
    }

    public static MapLocation getRandomDestination() {
        return new MapLocation(rng.nextInt(rc.getMapWidth()), rng.nextInt(rc.getMapHeight()));
    }
}
