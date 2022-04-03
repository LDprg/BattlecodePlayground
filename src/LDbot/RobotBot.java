package LDbot;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

import java.util.Random;

public abstract strictfp class RobotBot {
    /** Random generator **/
    static final Random rng = new Random(6147);

    /** Array containing all the possible movement directions. */
    static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

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