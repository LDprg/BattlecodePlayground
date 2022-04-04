package LDbot.util;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Communication {
    public static MapLocation getEnemyArchon(RobotController rc) throws GameActionException {
        int x = rc.readSharedArray(Channels.EnemyArchonX.id) - 1;
        int y = rc.readSharedArray(Channels.EnemyArchonY.id) - 1;
        if (x > 0 && y > 0)
            return new MapLocation(x, y);
        else
            return null;
    }

    public static void setEnemyArchon(RobotController rc, MapLocation location) throws GameActionException {
        rc.writeSharedArray(Channels.EnemyArchonX.id, location.x + 1);
        rc.writeSharedArray(Channels.EnemyArchonY.id, location.y + 1);
    }

    public enum Channels {
        EnemyArchonX(0),
        EnemyArchonY(1);

        private final int id;

        Channels(int value) {
            this.id = value;
        }
    }
}
