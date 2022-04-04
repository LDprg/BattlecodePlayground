package LDbot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public strictfp class MinerBot extends RobotBot{
    @Override
    public void run(RobotController rc) throws GameActionException {
        // Try to mine on squares around us.
        MapLocation me = rc.getLocation();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                MapLocation mineLocation = new MapLocation(me.x + dx, me.y + dy);
                // Notice that the Miner's action cooldown is very low.
                // You can mine multiple times per turn!
                while (rc.canMineGold(mineLocation)) {
                    rc.mineGold(mineLocation);
                }
                while (rc.canMineLead(mineLocation)) {
                    rc.mineLead(mineLocation);
                }
            }
        }

        if(rc.isMovementReady()) {
            MapLocation[] leadloc = rc.senseNearbyLocationsWithLead(-1);
            Direction dir = directions[rng.nextInt(directions.length)];

            if (leadloc.length > 0) {
                rc.setIndicatorString("Sensing Lead");

                int id = 0;
                int maxLead = 0;
                for (int i = 0; i < leadloc.length; i++) {
                    if(rc.senseLead(leadloc[i]) > maxLead){
                        id = i;
                        maxLead = rc.senseLead(leadloc[i]);
                    }
                }

                dir = PathFinder.findPath(rc, leadloc[rng.nextInt(leadloc.length)]);
            } else
                rc.setIndicatorString("Searching Lead");

            if (rc.canMove(dir))
                rc.move(dir);
            else {
                dir = directions[rng.nextInt(directions.length)];

                if (rc.canMove(dir))
                    rc.move(dir);
            }
        }
    }
}
