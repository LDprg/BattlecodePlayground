package LDbot;

import battlecode.common.*;

public strictfp class SoldierBot extends RobotBot{
    @Override
    public void run(RobotController rc) throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;
        int visionRadius = rc.getType().visionRadiusSquared;
        Team opponent = rc.getTeam().opponent();


        RobotInfo[] enemies = rc.senseNearbyRobots(actionRadius, opponent);
        if (enemies.length > 0) {
            MapLocation toAttack = getMinHealth(enemies).location;
            if (rc.canAttack(toAttack)) {
                rc.attack(toAttack);
            }
        }

        if(rc.isMovementReady()) {
            RobotInfo enemyloc[] = rc.senseNearbyRobots(visionRadius, opponent);
            Direction dir = directions[rng.nextInt(directions.length)];

            if (enemyloc.length > 0)
                dir = PathFinder.findPath(rc,getMinHealth(enemyloc).getLocation());

            if (rc.canMove(dir))
                rc.move(dir);
        }
    }
}
