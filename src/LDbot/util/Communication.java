package LDbot.util;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class Communication {
    public static MapLocation getEnemyArchon(RobotController rc, int num) throws GameActionException {
        int x = rc.readSharedArray(Channels.EnemyArchon0X.id + num) - 1;
        int y = rc.readSharedArray(Channels.EnemyArchon0Y.id + num) - 1;
        if (x > 0 && y > 0)
            return new MapLocation(x, y);
        else
            return null;
    }

    public static void setEnemyArchon(RobotController rc, MapLocation location, int num) throws GameActionException {
        rc.writeSharedArray(Channels.EnemyArchon0X.id + num, location.x + 1);
        rc.writeSharedArray(Channels.EnemyArchon0Y.id + num, location.y + 1);
    }

    public enum Event {
        EnemyArchonFound,
        EnemyArchonDestroyed
    }

    public enum Channels {
        EnemyArchon0X(0),
        EnemyArchon0Y(1),
        EnemyArchon1X(2),
        EnemyArchon1Y(3),
        EnemyArchon2X(4),
        EnemyArchon2Y(5),
        EnemyArchon3X(6),
        EnemyArchon3Y(7);

        private final int id;

        Channels(int value) {
            this.id = value;
        }
    }
}
