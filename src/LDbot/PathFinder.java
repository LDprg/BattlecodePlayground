package LDbot;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public strictfp class PathFinder {
    public static Direction findPath(RobotController rc, MapLocation stop){
        MapLocation start = rc.getLocation();
        return start.directionTo(stop);
    }
}
