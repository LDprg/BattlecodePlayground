package LDbot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public strictfp class PathFinder {
    public static class Field{
        MapLocation location;
        Field parent = null;
        int cost = 0;
        boolean calc = false;
    }

    public static Direction findPath(RobotController rc, MapLocation stop) throws GameActionException {
        MapLocation start = rc.getLocation();
        Field next = new Field();
        next.location = start;

        List<Field> fieldArray = new ArrayList<>();

        while(!next.location.equals(stop)) {

            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if(dx==0 && dy==0)
                        continue;

                    Field field = new Field();
                    field.location = new MapLocation(next.location.x + dx, next.location.y + dy);
                    field.parent = next;

                    boolean alreadyExisting = false;

                    for (Field f :
                            fieldArray) {
                        if(f.location.equals(field.location)) {
                            alreadyExisting = true;
                            break;
                        }
                    }

                    if (alreadyExisting)
                        continue;

                    if(rc.canSenseLocation(field.location)){
                        field.cost = 10 + rc.senseRubble(field.location) + 10 * field.location.distanceSquaredTo(stop);
                        if (rc.isLocationOccupied(field.location)) {
                            field.cost = Integer.MAX_VALUE;
                        }
                    } else {
                        field.cost = 10 + 10 * field.location.distanceSquaredTo(stop);
                    }

                    fieldArray.add(field);
                }
            }

            if(!fieldArray.isEmpty()) {
                int mincost = Integer.MAX_VALUE;
                for (Field field :
                        fieldArray) {
                    if(field.location.equals(stop)){
                        next = field;
                        field.calc = true;
                        mincost = field.cost;
                        break;
                    }

                    if(field.cost < mincost && !field.calc) {
                        next = field;
                        field.calc = true;
                        mincost = field.cost;
                    }
                }
            }
        }

        if (next.parent != null)
            while(next.parent.parent != null)
                next = next.parent;

        return start.directionTo(next.location);
    }
}
