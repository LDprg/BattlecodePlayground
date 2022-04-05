package LDbot.bots;

import LDbot.RobotBot;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import static LDbot.util.Cache.*;

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
        Direction dir = directions[rng.nextInt(directions.length)];

        rc.setIndicatorString("Trying to build a " + Types[count].toString());
        if (rc.canBuildRobot(Types[count], dir)) {
            rc.buildRobot(Types[count], dir);
            count++;
        }

        if (count >= Types.length)
            count = 0;

        for (RobotInfo robot :
                friendlyRobots) {
            if (robot.getHealth() < robot.getType().getMaxHealth(1) && rc.canRepair(robot.getLocation()))
                rc.repair(robot.getLocation());
        }
    }
}
