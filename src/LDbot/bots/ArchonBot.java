package LDbot.bots;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public strictfp class ArchonBot extends RobotBot {
    private final int[] Types = {
            0, 0, 1, 1, 1, 2, 6
    };

    private int count = 0;
    private int wait = 0;

    @Override
    public void run(RobotController rc) throws GameActionException {
        // Pick a direction to build in.
        Direction dir = directions[rng.nextInt(directions.length)];

        switch (Types[count]) {
            case 0:
                rc.setIndicatorString("Trying to build a miner");
                if (rc.canBuildRobot(RobotType.MINER, dir)) {
                    rc.buildRobot(RobotType.MINER, dir);
                    count++;
                }
                break;
            case 1:
                rc.setIndicatorString("Trying to build a soldier");
                if (rc.canBuildRobot(RobotType.SOLDIER, dir)) {
                    rc.buildRobot(RobotType.SOLDIER, dir);
                    count++;
                }
                break;
            case 2:
                rc.setIndicatorString("Trying to build a Builder");
                if (rc.canBuildRobot(RobotType.BUILDER, dir)) {
                    rc.buildRobot(RobotType.BUILDER, dir);
                    count++;
                }
                break;
            case 3:
                rc.setIndicatorString("Trying to build a Sage");
                if (rc.canBuildRobot(RobotType.SAGE, dir)) {
                    rc.buildRobot(RobotType.SAGE, dir);
                    count++;
                }
                break;
            default:
                wait++;
                if(wait == 100) {
                    count++;
                    wait = 0;
                }
                break;
        }

        if (count >= Types.length)
            count = 0;
    }
}
