package LDbot.util;

import battlecode.common.*;

import java.util.Random;

public class Cache {
    public static final Random rng = new Random();//(6147);
    public static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };
    public static RobotController rc;
    public static Team me;
    public static Team opponent;
    public static RobotType robotType;

    public static int turnCount = 0;

    public static RobotInfo[] friendlyRobotsVisible;
    public static RobotInfo[] enemyRobotsVisible;
    public static RobotInfo[] friendlyRobots;
    public static RobotInfo[] enemyRobots;

    public static MapLocation robotLocation;
}
