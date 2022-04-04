package LDbot.bots;

import battlecode.common.*;

import java.util.Random;

import static LDbot.RobotPlayer.*;

public abstract strictfp class RobotBot {
    /**
     * Random generator
     **/
    static final Random rng = new Random(6147);

    /**
     * Array containing all the possible movement directions.
     */
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

    public static RobotBot init(RobotController rc) throws GameActionException {
        switch (rc.getType()) {
            case ARCHON:
                return new ArchonBot();
            case MINER:
                return new MinerBot();
            case SOLDIER:
                return new SoldierBot();
            case LABORATORY:
                return new LaboratoryBot();
            case WATCHTOWER:
                return new WatchtowerBot();
            case BUILDER:
                return new BuilderBot();
            case SAGE:
                return new SageBot();
        }
        return null;
    }

    static RobotInfo getMinHealth(RobotInfo[] list) throws GameActionException {
        int id = 0;
        int lowestHealth = Integer.MAX_VALUE;

        for (int i = 0; i < list.length; i++) {
            if (list[i].getHealth() < lowestHealth) {
                id = i;
                lowestHealth = list[i].getHealth();
            }
        }

        return list[id];
    }

    static MapLocation getMaxLead(RobotController rc, MapLocation[] list) throws GameActionException {
        int id = 0;
        int maxLead = 0;
        for (int i = 0; i < list.length; i++) {
            if (rc.senseLead(list[i]) > maxLead) {
                id = i;
                maxLead = rc.senseLead(list[i]);
            }
        }

        return list[id];
    }

    public void runALL(RobotController rc) throws GameActionException{
        //if(turnCount > 100 && !rc.getType().isBuilding())
        //    rc.disintegrate();

        run(rc);
    }

    abstract void run(RobotController rc) throws GameActionException;
}