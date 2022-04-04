package LDbot.bots;

import LDbot.PathFinder;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public strictfp class MinerBot extends RobotBot {
    @Override
    public void run(RobotController rc) throws GameActionException {
        MapLocation me = rc.getLocation();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                // You can mine multiple times per turn!
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation)) {
                    rc.mineLead(mineLocation);
                }
            }
        }

        MapLocation[] leadloc = rc.senseNearbyLocationsWithLead(-1);
        if (rc.isMovementReady()) {
            Direction dir = directions[rng.nextInt(directions.length)];

            if (leadloc.length > 0)
                dir = PathFinder.findPath(rc, getMaxLead(rc, leadloc));

            if (rc.canMove(dir))
                rc.move(dir);
        }
    }
}
