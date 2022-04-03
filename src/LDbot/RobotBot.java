package LDbot;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public abstract strictfp class RobotBot {
    static RobotBot init(RobotController rc) throws GameActionException{
        switch (rc.getType()) {
            case ARCHON:        return new ArchonBot();
            case MINER:         return new MinerBot();
            case SOLDIER:       return new SoldierBot();
            case LABORATORY:    return new LaboratoryBot();
            case WATCHTOWER:    return new Watchtowerbot();
            case BUILDER:       return new BuilderBot();
            case SAGE:          return new Sagebot();
        }
        return null;
    }

    abstract void run(RobotController rc) throws GameActionException;
}