package LDbot.util;

import battlecode.common.Direction;
import battlecode.common.RobotController;
import battlecode.common.Team;

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
    public static int turnCount = 0;
}
