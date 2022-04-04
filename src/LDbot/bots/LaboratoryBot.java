package LDbot.bots;

import LDbot.RobotBot;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public strictfp class LaboratoryBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        if (rc.canTransmute())
            rc.transmute();
    }
}
