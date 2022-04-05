package LDbot.bots;

import LDbot.RobotBot;
import battlecode.common.GameActionException;

import static LDbot.util.Cache.rc;

public strictfp class LaboratoryBot extends RobotBot {
    @Override
    public void run() throws GameActionException {
        if (rc.canTransmute())
            rc.transmute();
    }
}
