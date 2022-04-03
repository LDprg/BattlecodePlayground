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

        MapLocation[] leadloc = rc.senseNearbyLocationsWithLead(-1);
        Direction dir = directions[rng.nextInt(directions.length)];

        if(leadloc.length > 0){
            dir = PathFinder.findPath(rc,leadloc[rng.nextInt(leadloc.length)]);
        }


        if(rc.canMove(dir))
            rc.move(dir);
        else{
            dir = directions[rng.nextInt(directions.length)];

            if(rc.canMove(dir))
                rc.move(dir);
        }
    }
}
