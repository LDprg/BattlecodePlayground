package LDbot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import java.util.ArrayList;
import java.util.List;

public strictfp class PathFinder {
    public static Direction findPath(RobotController rc, MapLocation stop) throws GameActionException {
        return rc.getLocation().directionTo(stop);
    }
}
