package LDbot;

import battlecode.common.*;

public strictfp class SoldierBot extends RobotBot{
    @Override
    public void run(RobotController rc) throws GameActionException {
        // Try to attack someone
        int radius = rc.getType().actionRadiusSquared;
        Team opponent = rc.getTeam().opponent();
        RobotInfo[] enemies = rc.senseNearbyRobots(radius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = enemies[0].location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }

        RobotInfo enemyloc[] = rc.senseNearbyRobots(-1, rc.getTeam().opponent());
        Direction dir = directions[rng.nextInt(directions.length)];

        if(enemyloc.length > 0){
            dir = PathFinder.findPath(rc,enemyloc[rng.nextInt(enemyloc.length)].getLocation());
        }

        if(rc.canMove(dir))
            rc.move(dir);
        else{
            dir = directions[rng.nextInt(directions.length)];

            if(rc.canMove(dir))
                rc.move(dir);
        }
    }
}
