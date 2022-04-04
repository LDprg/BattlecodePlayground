package LDbot.bots;

import LDbot.RobotBot;
import battlecode.common.*;

public strictfp class ArchonBot extends RobotBot {
    private final RobotType[] Types = {
            RobotType.MINER,
            RobotType.MINER,
            RobotType.SOLDIER,
            RobotType.SOLDIER,
            RobotType.SOLDIER,
    };

    private int count = 0;

    @Override
    public void run() throws GameActionException {
        // Pick a direction to build in.
        Direction dir = directions[rng.nextInt(directions.length)];

        rc.setIndicatorString("Trying to build a " + Types[count].toString());
        if (rc.canBuildRobot(Types[count], dir)) {
            rc.buildRobot(Types[count], dir);
            count++;
        }

        if (count >= Types.length)
            count = 0;

        RobotInfo[] ri = rc.senseNearbyRobots(-1, rc.getTeam());

        for (RobotInfo robot :
                ri) {
            if (robot.getHealth() < robot.getType().getMaxHealth(1) && rc.canRepair(robot.getLocation()))
                rc.repair(robot.getLocation());
        }
    }
}
