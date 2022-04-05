package LDbot;

import LDbot.bots.*;
import battlecode.common.*;

import static LDbot.util.Cache.opponent;
import static LDbot.util.Cache.rc;
import static LDbot.util.Communication.getEnemyArchon;
import static LDbot.util.Communication.setEnemyArchon;

public abstract strictfp class RobotBot {
    protected MapLocation EnemyArchon = null;

    public static RobotBot init(RobotController nrc) throws GameActionException {
        rc = nrc;
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

    protected static RobotInfo getMinHealth(RobotInfo[] list) throws GameActionException {
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

    protected static MapLocation getMaxLead(MapLocation[] list) throws GameActionException {
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

    public void runALL() throws GameActionException {
        opponent = rc.getTeam().opponent();
        RobotInfo[] ri = rc.senseNearbyRobots(-1, opponent);
        for (RobotInfo robot :
                ri) {
            if (robot.getType() == RobotType.ARCHON)
                setEnemyArchon(rc, robot.getLocation());
        }

        EnemyArchon = getEnemyArchon(rc);

        run();
    }

    protected abstract void run() throws GameActionException;
}