package LDbot.bots;

import LDbot.RobotBot;
import LDbot.util.Navigator;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;

import static LDbot.util.Cache.*;

public strictfp class MinerBot extends RobotBot {
    public int MiningThreshold = 1;

    @Override
    public void run() throws GameActionException {
        for (Direction dir : directions) {
            MapLocation mineLocation = robotLocation.add(dir);
            while (rc.canMineGold(mineLocation)) {
                rc.mineGold(mineLocation);
            }
            while (rc.canMineLead(mineLocation) && rc.senseLead(mineLocation) > MiningThreshold) {
                rc.mineLead(mineLocation);
            }
        }

        MapLocation[] leadloc = rc.senseNearbyLocationsWithLead(-1);
        if (rc.isMovementReady()) {
            Direction dir = getRndDir();

            if (leadloc.length > 0 && rc.senseLead(leadloc[rng.nextInt(leadloc.length)]) > 10)
                dir = Navigator.findPath(leadloc[rng.nextInt(leadloc.length)]);

            if (rc.canMove(dir))
                rc.move(dir);
        }
    }
}
