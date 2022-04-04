package sprintbot.battlecode2022.util.navigation;

import battlecode.common.*;
import static battlecode.common.Direction.*;
import sprintbot.battlecode2022.util.*;

public class DPR53Navigator extends Navigator
{
	private static final int INF_MARBLE = 1000000;
	private RobotController rc;
	private final int UPPER_BOUND_TURN_PER_GRID;
	private final int MOVE_COOLDOWN;
	private final int X_BOUND;
	private final int Y_BOUND;
	private final int[] MARBLE_COST_LOOKUP;
	
	public DPR53Navigator(RobotController controller)
	{
		super(controller);
		rc = controller;
		X_BOUND = rc.getMapWidth() - 1;    // [0, X_BOUND]
		Y_BOUND = rc.getMapHeight() - 1;
		MOVE_COOLDOWN = rc.getType().movementCooldown;
		UPPER_BOUND_TURN_PER_GRID = MOVE_COOLDOWN * 60;
		MARBLE_COST_LOOKUP = new int[]{(int) (1.0 * MOVE_COOLDOWN) * 10,
				(int) (1.1 * MOVE_COOLDOWN) * 10,
				(int) (1.2 * MOVE_COOLDOWN) * 10,
				(int) (1.3 * MOVE_COOLDOWN) * 10,
				(int) (1.4 * MOVE_COOLDOWN) * 10,
				(int) (1.5 * MOVE_COOLDOWN) * 10,
				(int) (1.6 * MOVE_COOLDOWN) * 10,
				(int) (1.7 * MOVE_COOLDOWN) * 10,
				(int) (1.8 * MOVE_COOLDOWN) * 10,
				(int) (1.9 * MOVE_COOLDOWN) * 10,
				(int) (2.0 * MOVE_COOLDOWN) * 10,
				(int) (2.1 * MOVE_COOLDOWN) * 10,
				(int) (2.2 * MOVE_COOLDOWN) * 10,
				(int) (2.3 * MOVE_COOLDOWN) * 10,
				(int) (2.4 * MOVE_COOLDOWN) * 10,
				(int) (2.5 * MOVE_COOLDOWN) * 10,
				(int) (2.6 * MOVE_COOLDOWN) * 10,
				(int) (2.7 * MOVE_COOLDOWN) * 10,
				(int) (2.8 * MOVE_COOLDOWN) * 10,
				(int) (2.9 * MOVE_COOLDOWN) * 10,
				(int) (3.0 * MOVE_COOLDOWN) * 10,
				(int) (3.1 * MOVE_COOLDOWN) * 10,
				(int) (3.2 * MOVE_COOLDOWN) * 10,
				(int) (3.3 * MOVE_COOLDOWN) * 10,
				(int) (3.4 * MOVE_COOLDOWN) * 10,
				(int) (3.5 * MOVE_COOLDOWN) * 10,
				(int) (3.6 * MOVE_COOLDOWN) * 10,
				(int) (3.7 * MOVE_COOLDOWN) * 10,
				(int) (3.8 * MOVE_COOLDOWN) * 10,
				(int) (3.9 * MOVE_COOLDOWN) * 10,
				(int) (4.0 * MOVE_COOLDOWN) * 10,
				(int) (4.1 * MOVE_COOLDOWN) * 10,
				(int) (4.2 * MOVE_COOLDOWN) * 10,
				(int) (4.3 * MOVE_COOLDOWN) * 10,
				(int) (4.4 * MOVE_COOLDOWN) * 10,
				(int) (4.5 * MOVE_COOLDOWN) * 10,
				(int) (4.6 * MOVE_COOLDOWN) * 10,
				(int) (4.7 * MOVE_COOLDOWN) * 10,
				(int) (4.8 * MOVE_COOLDOWN) * 10,
				(int) (4.9 * MOVE_COOLDOWN) * 10,
				(int) (5.0 * MOVE_COOLDOWN) * 10,
				(int) (5.1 * MOVE_COOLDOWN) * 10,
				(int) (5.2 * MOVE_COOLDOWN) * 10,
				(int) (5.3 * MOVE_COOLDOWN) * 10,
				(int) (5.4 * MOVE_COOLDOWN) * 10,
				(int) (5.5 * MOVE_COOLDOWN) * 10,
				(int) (5.6 * MOVE_COOLDOWN) * 10,
				(int) (5.7 * MOVE_COOLDOWN) * 10,
				(int) (5.8 * MOVE_COOLDOWN) * 10,
				(int) (5.9 * MOVE_COOLDOWN) * 10,
				(int) (6.0 * MOVE_COOLDOWN) * 10,
				(int) (6.1 * MOVE_COOLDOWN) * 10,
				(int) (6.2 * MOVE_COOLDOWN) * 10,
				(int) (6.3 * MOVE_COOLDOWN) * 10,
				(int) (6.4 * MOVE_COOLDOWN) * 10,
				(int) (6.5 * MOVE_COOLDOWN) * 10,
				(int) (6.6 * MOVE_COOLDOWN) * 10,
				(int) (6.7 * MOVE_COOLDOWN) * 10,
				(int) (6.8 * MOVE_COOLDOWN) * 10,
				(int) (6.9 * MOVE_COOLDOWN) * 10,
				(int) (7.0 * MOVE_COOLDOWN) * 10,
				(int) (7.1 * MOVE_COOLDOWN) * 10,
				(int) (7.2 * MOVE_COOLDOWN) * 10,
				(int) (7.3 * MOVE_COOLDOWN) * 10,
				(int) (7.4 * MOVE_COOLDOWN) * 10,
				(int) (7.5 * MOVE_COOLDOWN) * 10,
				(int) (7.6 * MOVE_COOLDOWN) * 10,
				(int) (7.7 * MOVE_COOLDOWN) * 10,
				(int) (7.8 * MOVE_COOLDOWN) * 10,
				(int) (7.9 * MOVE_COOLDOWN) * 10,
				(int) (8.0 * MOVE_COOLDOWN) * 10,
				(int) (8.1 * MOVE_COOLDOWN) * 10,
				(int) (8.2 * MOVE_COOLDOWN) * 10,
				(int) (8.3 * MOVE_COOLDOWN) * 10,
				(int) (8.4 * MOVE_COOLDOWN) * 10,
				(int) (8.5 * MOVE_COOLDOWN) * 10,
				(int) (8.6 * MOVE_COOLDOWN) * 10,
				(int) (8.7 * MOVE_COOLDOWN) * 10,
				(int) (8.8 * MOVE_COOLDOWN) * 10,
				(int) (8.9 * MOVE_COOLDOWN) * 10,
				(int) (9.0 * MOVE_COOLDOWN) * 10,
				(int) (9.1 * MOVE_COOLDOWN) * 10,
				(int) (9.2 * MOVE_COOLDOWN) * 10,
				(int) (9.3 * MOVE_COOLDOWN) * 10,
				(int) (9.4 * MOVE_COOLDOWN) * 10,
				(int) (9.5 * MOVE_COOLDOWN) * 10,
				(int) (9.6 * MOVE_COOLDOWN) * 10,
				(int) (9.7 * MOVE_COOLDOWN) * 10,
				(int) (9.8 * MOVE_COOLDOWN) * 10,
				(int) (9.9 * MOVE_COOLDOWN) * 10,
				(int) (10.0 * MOVE_COOLDOWN) * 10,
				(int) (10.1 * MOVE_COOLDOWN) * 10,
				(int) (10.2 * MOVE_COOLDOWN) * 10,
				(int) (10.3 * MOVE_COOLDOWN) * 10,
				(int) (10.4 * MOVE_COOLDOWN) * 10,
				(int) (10.5 * MOVE_COOLDOWN) * 10,
				(int) (10.6 * MOVE_COOLDOWN) * 10,
				(int) (10.7 * MOVE_COOLDOWN) * 10,
				(int) (10.8 * MOVE_COOLDOWN) * 10,
				(int) (10.9 * MOVE_COOLDOWN) * 10,
				(int) (11.0 * MOVE_COOLDOWN) * 10};
	}
	
	public MoveResult move(MapLocation target_loc) throws GameActionException
	{
		if (!rc.isMovementReady())
			return MoveResult.FAIL;
		if (target_loc == null)
			return MoveResult.IMPOSSIBLE;
		int tar_loc_x = target_loc.x;
		int tar_loc_y = target_loc.y;
		if (tar_loc_x < 0 || tar_loc_y < 0
				|| tar_loc_x > X_BOUND || tar_loc_y > Y_BOUND)
			return MoveResult.IMPOSSIBLE;
		MapLocation current_loc = rc.getLocation();
		if (current_loc.distanceSquaredTo(target_loc) <= 2
				&& rc.canSenseLocation(target_loc)) {
			RobotInfo robot = rc.senseRobotAtLocation(target_loc);
			if (robot != null
					&& robot.getTeam() == Cache.OUR_TEAM
					&& robot.getType() == RobotType.MINER) {
				return MoveResult.IMPOSSIBLE;
			}
			if (robot != null
					&& robot.getType().isBuilding()
					&& robot.getMode().canMove == false) {
				return MoveResult.IMPOSSIBLE;
			}
		}
		int cur_loc_x = current_loc.x;
		int cur_loc_y = current_loc.y;
		if (current_loc.equals(target_loc))
			return MoveResult.REACHED;
		MapLocation tmp_loc;
		int dctx = cur_loc_x - tar_loc_x;
		int dcty = cur_loc_y - tar_loc_y;
		int dctd = dctx * dctx + dcty * dcty;
		if (dctd <= 2)
		{
			Direction dir = current_loc.directionTo(target_loc);
			if (rc.canMove(dir))
			{
				rc.move(dir);
				return MoveResult.SUCCESS;
			}
			else
				return MoveResult.FAIL;				
		}
		//System.out.printf("Check Complete, left = 53\n", Clock.getBytecodesLeft());
		tmp_loc = current_loc.translate(0,0);
		int r_0_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_0 = r_0_0;
		tmp_loc = current_loc.translate(-1,1);
		int r_n1_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_1 = rc.canMove(NORTHWEST) ? (r_n1_1 + 1) : INF_MARBLE;
		tmp_loc = current_loc.translate(0,1);
		int r_0_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_1 = Math.min(rc.canMove(NORTH) ? (r_0_1 + 2) : INF_MARBLE,d_n1_1+r_0_1);
		tmp_loc = current_loc.translate(1,1);
		int r_1_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_1 = Math.min(rc.canMove(NORTHEAST) ? (r_1_1 + 3) : INF_MARBLE,d_0_1+r_1_1);
		tmp_loc = current_loc.translate(1,0);
		int r_1_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_0 = Math.min(rc.canMove(EAST) ? (r_1_0 + 4) : INF_MARBLE,Math.min(d_1_1,d_0_1)+r_1_0);
		tmp_loc = current_loc.translate(1,-1);
		int r_1_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n1 = Math.min(rc.canMove(SOUTHEAST) ? (r_1_n1 + 5) : INF_MARBLE,d_1_0+r_1_n1);
		tmp_loc = current_loc.translate(0,-1);
		int r_0_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n1 = Math.min(rc.canMove(SOUTH) ? (r_0_n1 + 6) : INF_MARBLE,Math.min(d_1_0,d_1_n1)+r_0_n1);
		tmp_loc = current_loc.translate(-1,-1);
		int r_n1_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n1 = Math.min(rc.canMove(SOUTHWEST) ? (r_n1_n1 + 7) : INF_MARBLE,d_0_n1+r_n1_n1);
		tmp_loc = current_loc.translate(-1,0);
		int r_n1_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_0 = Math.min(rc.canMove(WEST) ? (r_n1_0 + 8) : INF_MARBLE,Math.min(Math.min(Math.min(d_0_1,d_0_n1),d_n1_1),d_n1_n1)+r_n1_0);
		tmp_loc = current_loc.translate(-2,2);
		int r_n2_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_2 = d_n1_1+r_n2_2;
		tmp_loc = current_loc.translate(-1,2);
		int r_n1_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_2 = Math.min(Math.min(d_0_1,d_n1_1),d_n2_2)+r_n1_2;
		tmp_loc = current_loc.translate(0,2);
		int r_0_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_2 = Math.min(Math.min(Math.min(d_1_1,d_0_1),d_n1_2),d_n1_1)+r_0_2;
		tmp_loc = current_loc.translate(1,2);
		int r_1_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_2 = Math.min(Math.min(d_1_1,d_0_2),d_0_1)+r_1_2;
		tmp_loc = current_loc.translate(2,2);
		int r_2_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_2 = Math.min(d_1_2,d_1_1)+r_2_2;
		tmp_loc = current_loc.translate(2,1);
		int r_2_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_1 = Math.min(Math.min(Math.min(d_2_2,d_1_2),d_1_1),d_1_0)+r_2_1;
		tmp_loc = current_loc.translate(2,0);
		int r_2_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_0 = Math.min(Math.min(Math.min(d_2_1,d_1_1),d_1_0),d_1_n1)+r_2_0;
		tmp_loc = current_loc.translate(2,-1);
		int r_2_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n1 = Math.min(Math.min(d_2_0,d_1_0),d_1_n1)+r_2_n1;
		tmp_loc = current_loc.translate(2,-2);
		int r_2_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n2 = Math.min(d_2_n1,d_1_n1)+r_2_n2;
		tmp_loc = current_loc.translate(1,-2);
		int r_1_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n2 = Math.min(Math.min(Math.min(d_2_n1,d_2_n2),d_1_n1),d_0_n1)+r_1_n2;
		tmp_loc = current_loc.translate(0,-2);
		int r_0_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n2 = Math.min(Math.min(Math.min(d_1_n1,d_1_n2),d_0_n1),d_n1_n1)+r_0_n2;
		tmp_loc = current_loc.translate(-1,-2);
		int r_n1_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n2 = Math.min(Math.min(d_0_n1,d_0_n2),d_n1_n1)+r_n1_n2;
		tmp_loc = current_loc.translate(-2,-2);
		int r_n2_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n2 = Math.min(d_n1_n1,d_n1_n2)+r_n2_n2;
		tmp_loc = current_loc.translate(-2,-1);
		int r_n2_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n1 = Math.min(Math.min(Math.min(d_n1_0,d_n1_n1),d_n1_n2),d_n2_n2)+r_n2_n1;
		tmp_loc = current_loc.translate(-2,0);
		int r_n2_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_0 = Math.min(Math.min(Math.min(d_n1_1,d_n1_0),d_n1_n1),d_n2_n1)+r_n2_0;
		tmp_loc = current_loc.translate(-2,1);
		int r_n2_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_1 = Math.min(Math.min(Math.min(Math.min(d_n1_2,d_n1_1),d_n1_0),d_n2_2),d_n2_0)+r_n2_1;
		tmp_loc = current_loc.translate(-3,3);
		int r_n3_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_3 = d_n2_2+r_n3_3;
		tmp_loc = current_loc.translate(-2,3);
		int r_n2_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_3 = Math.min(Math.min(d_n1_2,d_n2_2),d_n3_3)+r_n2_3;
		tmp_loc = current_loc.translate(-1,3);
		int r_n1_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_3 = Math.min(Math.min(Math.min(d_0_2,d_n1_2),d_n2_3),d_n2_2)+r_n1_3;
		tmp_loc = current_loc.translate(0,3);
		int r_0_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_3 = Math.min(Math.min(Math.min(d_1_2,d_0_2),d_n1_3),d_n1_2)+r_0_3;
		tmp_loc = current_loc.translate(1,3);
		int r_1_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_3 = Math.min(Math.min(Math.min(d_2_2,d_1_2),d_0_3),d_0_2)+r_1_3;
		tmp_loc = current_loc.translate(2,3);
		int r_2_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_3 = Math.min(Math.min(d_2_2,d_1_3),d_1_2)+r_2_3;
		tmp_loc = current_loc.translate(3,3);
		int r_3_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_3 = Math.min(d_2_3,d_2_2)+r_3_3;
		tmp_loc = current_loc.translate(3,2);
		int r_3_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_2 = Math.min(Math.min(Math.min(d_3_3,d_2_3),d_2_2),d_2_1)+r_3_2;
		tmp_loc = current_loc.translate(3,1);
		int r_3_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_1 = Math.min(Math.min(Math.min(d_3_2,d_2_2),d_2_1),d_2_0)+r_3_1;
		tmp_loc = current_loc.translate(3,0);
		int r_3_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_0 = Math.min(Math.min(Math.min(d_3_1,d_2_1),d_2_0),d_2_n1)+r_3_0;
		tmp_loc = current_loc.translate(3,-1);
		int r_3_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_n1 = Math.min(Math.min(Math.min(d_3_0,d_2_0),d_2_n1),d_2_n2)+r_3_n1;
		tmp_loc = current_loc.translate(3,-2);
		int r_3_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_n2 = Math.min(Math.min(d_3_n1,d_2_n1),d_2_n2)+r_3_n2;
		tmp_loc = current_loc.translate(3,-3);
		int r_3_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_n3 = Math.min(d_3_n2,d_2_n2)+r_3_n3;
		tmp_loc = current_loc.translate(2,-3);
		int r_2_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n3 = Math.min(Math.min(Math.min(d_3_n2,d_3_n3),d_2_n2),d_1_n2)+r_2_n3;
		tmp_loc = current_loc.translate(1,-3);
		int r_1_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n3 = Math.min(Math.min(Math.min(d_2_n2,d_2_n3),d_1_n2),d_0_n2)+r_1_n3;
		tmp_loc = current_loc.translate(0,-3);
		int r_0_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n3 = Math.min(Math.min(Math.min(d_1_n2,d_1_n3),d_0_n2),d_n1_n2)+r_0_n3;
		tmp_loc = current_loc.translate(-1,-3);
		int r_n1_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n3 = Math.min(Math.min(Math.min(d_0_n2,d_0_n3),d_n1_n2),d_n2_n2)+r_n1_n3;
		tmp_loc = current_loc.translate(-2,-3);
		int r_n2_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n3 = Math.min(Math.min(d_n1_n2,d_n1_n3),d_n2_n2)+r_n2_n3;
		tmp_loc = current_loc.translate(-3,-3);
		int r_n3_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_n3 = Math.min(d_n2_n2,d_n2_n3)+r_n3_n3;
		tmp_loc = current_loc.translate(-3,-2);
		int r_n3_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_n2 = Math.min(Math.min(Math.min(d_n2_n1,d_n2_n2),d_n2_n3),d_n3_n3)+r_n3_n2;
		tmp_loc = current_loc.translate(-3,-1);
		int r_n3_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_n1 = Math.min(Math.min(Math.min(d_n2_0,d_n2_n1),d_n2_n2),d_n3_n2)+r_n3_n1;
		tmp_loc = current_loc.translate(-3,0);
		int r_n3_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_0 = Math.min(Math.min(Math.min(d_n2_1,d_n2_0),d_n2_n1),d_n3_n1)+r_n3_0;
		tmp_loc = current_loc.translate(-3,1);
		int r_n3_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_1 = Math.min(Math.min(Math.min(d_n2_2,d_n2_1),d_n2_0),d_n3_0)+r_n3_1;
		tmp_loc = current_loc.translate(-3,2);
		int r_n3_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_2 = Math.min(Math.min(Math.min(Math.min(d_n2_3,d_n2_2),d_n2_1),d_n3_3),d_n3_1)+r_n3_2;
		tmp_loc = current_loc.translate(-4,4);
		int r_n4_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_4 = d_n3_3+r_n4_4;
		tmp_loc = current_loc.translate(-3,4);
		int r_n3_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_4 = Math.min(Math.min(d_n2_3,d_n3_3),d_n4_4)+r_n3_4;
		tmp_loc = current_loc.translate(-2,4);
		int r_n2_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_4 = Math.min(Math.min(Math.min(d_n1_3,d_n2_3),d_n3_4),d_n3_3)+r_n2_4;
		tmp_loc = current_loc.translate(-1,4);
		int r_n1_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_4 = Math.min(Math.min(Math.min(d_0_3,d_n1_3),d_n2_4),d_n2_3)+r_n1_4;
		tmp_loc = current_loc.translate(0,4);
		int r_0_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_4 = Math.min(Math.min(Math.min(d_1_3,d_0_3),d_n1_4),d_n1_3)+r_0_4;
		tmp_loc = current_loc.translate(1,4);
		int r_1_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_4 = Math.min(Math.min(Math.min(d_2_3,d_1_3),d_0_4),d_0_3)+r_1_4;
		tmp_loc = current_loc.translate(2,4);
		int r_2_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_4 = Math.min(Math.min(Math.min(d_3_3,d_2_3),d_1_4),d_1_3)+r_2_4;
		tmp_loc = current_loc.translate(3,4);
		int r_3_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_4 = Math.min(Math.min(d_3_3,d_2_4),d_2_3)+r_3_4;
		tmp_loc = current_loc.translate(4,4);
		int r_4_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_4 = Math.min(d_3_4,d_3_3)+r_4_4;
		tmp_loc = current_loc.translate(4,3);
		int r_4_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_3 = Math.min(Math.min(Math.min(d_4_4,d_3_4),d_3_3),d_3_2)+r_4_3;
		tmp_loc = current_loc.translate(4,2);
		int r_4_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_2 = Math.min(Math.min(Math.min(d_4_3,d_3_3),d_3_2),d_3_1)+r_4_2;
		tmp_loc = current_loc.translate(4,1);
		int r_4_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_1 = Math.min(Math.min(Math.min(d_4_2,d_3_2),d_3_1),d_3_0)+r_4_1;
		tmp_loc = current_loc.translate(4,0);
		int r_4_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_0 = Math.min(Math.min(Math.min(d_4_1,d_3_1),d_3_0),d_3_n1)+r_4_0;
		tmp_loc = current_loc.translate(4,-1);
		int r_4_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_n1 = Math.min(Math.min(Math.min(d_4_0,d_3_0),d_3_n1),d_3_n2)+r_4_n1;
		tmp_loc = current_loc.translate(4,-2);
		int r_4_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_n2 = Math.min(Math.min(Math.min(d_4_n1,d_3_n1),d_3_n2),d_3_n3)+r_4_n2;
		tmp_loc = current_loc.translate(4,-3);
		int r_4_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_n3 = Math.min(Math.min(d_4_n2,d_3_n2),d_3_n3)+r_4_n3;
		tmp_loc = current_loc.translate(4,-4);
		int r_4_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_n4 = Math.min(d_4_n3,d_3_n3)+r_4_n4;
		tmp_loc = current_loc.translate(3,-4);
		int r_3_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_n4 = Math.min(Math.min(Math.min(d_4_n3,d_4_n4),d_3_n3),d_2_n3)+r_3_n4;
		tmp_loc = current_loc.translate(2,-4);
		int r_2_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n4 = Math.min(Math.min(Math.min(d_3_n3,d_3_n4),d_2_n3),d_1_n3)+r_2_n4;
		tmp_loc = current_loc.translate(1,-4);
		int r_1_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n4 = Math.min(Math.min(Math.min(d_2_n3,d_2_n4),d_1_n3),d_0_n3)+r_1_n4;
		tmp_loc = current_loc.translate(0,-4);
		int r_0_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n4 = Math.min(Math.min(Math.min(d_1_n3,d_1_n4),d_0_n3),d_n1_n3)+r_0_n4;
		tmp_loc = current_loc.translate(-1,-4);
		int r_n1_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n4 = Math.min(Math.min(Math.min(d_0_n3,d_0_n4),d_n1_n3),d_n2_n3)+r_n1_n4;
		tmp_loc = current_loc.translate(-2,-4);
		int r_n2_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n4 = Math.min(Math.min(Math.min(d_n1_n3,d_n1_n4),d_n2_n3),d_n3_n3)+r_n2_n4;
		tmp_loc = current_loc.translate(-3,-4);
		int r_n3_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_n4 = Math.min(Math.min(d_n2_n3,d_n2_n4),d_n3_n3)+r_n3_n4;
		tmp_loc = current_loc.translate(-4,-4);
		int r_n4_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_n4 = Math.min(d_n3_n3,d_n3_n4)+r_n4_n4;
		tmp_loc = current_loc.translate(-4,-3);
		int r_n4_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_n3 = Math.min(Math.min(Math.min(d_n3_n2,d_n3_n3),d_n3_n4),d_n4_n4)+r_n4_n3;
		tmp_loc = current_loc.translate(-4,-2);
		int r_n4_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_n2 = Math.min(Math.min(Math.min(d_n3_n1,d_n3_n2),d_n3_n3),d_n4_n3)+r_n4_n2;
		tmp_loc = current_loc.translate(-4,-1);
		int r_n4_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_n1 = Math.min(Math.min(Math.min(d_n3_0,d_n3_n1),d_n3_n2),d_n4_n2)+r_n4_n1;
		tmp_loc = current_loc.translate(-4,0);
		int r_n4_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_0 = Math.min(Math.min(Math.min(d_n3_1,d_n3_0),d_n3_n1),d_n4_n1)+r_n4_0;
		tmp_loc = current_loc.translate(-4,1);
		int r_n4_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_1 = Math.min(Math.min(Math.min(d_n3_2,d_n3_1),d_n3_0),d_n4_0)+r_n4_1;
		tmp_loc = current_loc.translate(-4,2);
		int r_n4_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_2 = Math.min(Math.min(Math.min(d_n3_3,d_n3_2),d_n3_1),d_n4_1)+r_n4_2;
		tmp_loc = current_loc.translate(-4,3);
		int r_n4_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_3 = Math.min(Math.min(Math.min(Math.min(d_n3_4,d_n3_3),d_n3_2),d_n4_4),d_n4_2)+r_n4_3;
		tmp_loc = current_loc.translate(-5,5);
		int r_n5_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_5 = d_n4_4+r_n5_5;
		tmp_loc = current_loc.translate(-4,5);
		int r_n4_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_5 = Math.min(Math.min(d_n3_4,d_n4_4),d_n5_5)+r_n4_5;
		tmp_loc = current_loc.translate(-3,5);
		int r_n3_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_5 = Math.min(Math.min(Math.min(d_n2_4,d_n3_4),d_n4_5),d_n4_4)+r_n3_5;
		tmp_loc = current_loc.translate(-2,5);
		int r_n2_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_5 = Math.min(Math.min(Math.min(d_n1_4,d_n2_4),d_n3_5),d_n3_4)+r_n2_5;
		tmp_loc = current_loc.translate(-1,5);
		int r_n1_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_5 = Math.min(Math.min(Math.min(d_0_4,d_n1_4),d_n2_5),d_n2_4)+r_n1_5;
		tmp_loc = current_loc.translate(0,5);
		int r_0_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_5 = Math.min(Math.min(Math.min(d_1_4,d_0_4),d_n1_5),d_n1_4)+r_0_5;
		tmp_loc = current_loc.translate(1,5);
		int r_1_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_5 = Math.min(Math.min(Math.min(d_2_4,d_1_4),d_0_5),d_0_4)+r_1_5;
		tmp_loc = current_loc.translate(2,5);
		int r_2_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_5 = Math.min(Math.min(Math.min(d_3_4,d_2_4),d_1_5),d_1_4)+r_2_5;
		tmp_loc = current_loc.translate(3,5);
		int r_3_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_5 = Math.min(Math.min(Math.min(d_4_4,d_3_4),d_2_5),d_2_4)+r_3_5;
		tmp_loc = current_loc.translate(4,5);
		int r_4_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_5 = Math.min(Math.min(d_4_4,d_3_5),d_3_4)+r_4_5;
		tmp_loc = current_loc.translate(5,5);
		int r_5_5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_5 = Math.min(d_4_5,d_4_4)+r_5_5;
		tmp_loc = current_loc.translate(5,4);
		int r_5_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_4 = Math.min(Math.min(Math.min(d_5_5,d_4_5),d_4_4),d_4_3)+r_5_4;
		tmp_loc = current_loc.translate(5,3);
		int r_5_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_3 = Math.min(Math.min(Math.min(d_5_4,d_4_4),d_4_3),d_4_2)+r_5_3;
		tmp_loc = current_loc.translate(5,2);
		int r_5_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_2 = Math.min(Math.min(Math.min(d_5_3,d_4_3),d_4_2),d_4_1)+r_5_2;
		tmp_loc = current_loc.translate(5,1);
		int r_5_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_1 = Math.min(Math.min(Math.min(d_5_2,d_4_2),d_4_1),d_4_0)+r_5_1;
		tmp_loc = current_loc.translate(5,0);
		int r_5_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_0 = Math.min(Math.min(Math.min(d_5_1,d_4_1),d_4_0),d_4_n1)+r_5_0;
		tmp_loc = current_loc.translate(5,-1);
		int r_5_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_n1 = Math.min(Math.min(Math.min(d_5_0,d_4_0),d_4_n1),d_4_n2)+r_5_n1;
		tmp_loc = current_loc.translate(5,-2);
		int r_5_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_n2 = Math.min(Math.min(Math.min(d_5_n1,d_4_n1),d_4_n2),d_4_n3)+r_5_n2;
		tmp_loc = current_loc.translate(5,-3);
		int r_5_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_n3 = Math.min(Math.min(Math.min(d_5_n2,d_4_n2),d_4_n3),d_4_n4)+r_5_n3;
		tmp_loc = current_loc.translate(5,-4);
		int r_5_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_n4 = Math.min(Math.min(d_5_n3,d_4_n3),d_4_n4)+r_5_n4;
		tmp_loc = current_loc.translate(5,-5);
		int r_5_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_5_n5 = Math.min(d_5_n4,d_4_n4)+r_5_n5;
		tmp_loc = current_loc.translate(4,-5);
		int r_4_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_n5 = Math.min(Math.min(Math.min(d_5_n4,d_5_n5),d_4_n4),d_3_n4)+r_4_n5;
		tmp_loc = current_loc.translate(3,-5);
		int r_3_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_n5 = Math.min(Math.min(Math.min(d_4_n4,d_4_n5),d_3_n4),d_2_n4)+r_3_n5;
		tmp_loc = current_loc.translate(2,-5);
		int r_2_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n5 = Math.min(Math.min(Math.min(d_3_n4,d_3_n5),d_2_n4),d_1_n4)+r_2_n5;
		tmp_loc = current_loc.translate(1,-5);
		int r_1_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n5 = Math.min(Math.min(Math.min(d_2_n4,d_2_n5),d_1_n4),d_0_n4)+r_1_n5;
		tmp_loc = current_loc.translate(0,-5);
		int r_0_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n5 = Math.min(Math.min(Math.min(d_1_n4,d_1_n5),d_0_n4),d_n1_n4)+r_0_n5;
		tmp_loc = current_loc.translate(-1,-5);
		int r_n1_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n5 = Math.min(Math.min(Math.min(d_0_n4,d_0_n5),d_n1_n4),d_n2_n4)+r_n1_n5;
		tmp_loc = current_loc.translate(-2,-5);
		int r_n2_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n5 = Math.min(Math.min(Math.min(d_n1_n4,d_n1_n5),d_n2_n4),d_n3_n4)+r_n2_n5;
		tmp_loc = current_loc.translate(-3,-5);
		int r_n3_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_n5 = Math.min(Math.min(Math.min(d_n2_n4,d_n2_n5),d_n3_n4),d_n4_n4)+r_n3_n5;
		tmp_loc = current_loc.translate(-4,-5);
		int r_n4_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_n5 = Math.min(Math.min(d_n3_n4,d_n3_n5),d_n4_n4)+r_n4_n5;
		tmp_loc = current_loc.translate(-5,-5);
		int r_n5_n5 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_n5 = Math.min(d_n4_n4,d_n4_n5)+r_n5_n5;
		tmp_loc = current_loc.translate(-5,-4);
		int r_n5_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_n4 = Math.min(Math.min(Math.min(d_n4_n3,d_n4_n4),d_n4_n5),d_n5_n5)+r_n5_n4;
		tmp_loc = current_loc.translate(-5,-3);
		int r_n5_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_n3 = Math.min(Math.min(Math.min(d_n4_n2,d_n4_n3),d_n4_n4),d_n5_n4)+r_n5_n3;
		tmp_loc = current_loc.translate(-5,-2);
		int r_n5_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_n2 = Math.min(Math.min(Math.min(d_n4_n1,d_n4_n2),d_n4_n3),d_n5_n3)+r_n5_n2;
		tmp_loc = current_loc.translate(-5,-1);
		int r_n5_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_n1 = Math.min(Math.min(Math.min(d_n4_0,d_n4_n1),d_n4_n2),d_n5_n2)+r_n5_n1;
		tmp_loc = current_loc.translate(-5,0);
		int r_n5_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_0 = Math.min(Math.min(Math.min(d_n4_1,d_n4_0),d_n4_n1),d_n5_n1)+r_n5_0;
		tmp_loc = current_loc.translate(-5,1);
		int r_n5_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_1 = Math.min(Math.min(Math.min(d_n4_2,d_n4_1),d_n4_0),d_n5_0)+r_n5_1;
		tmp_loc = current_loc.translate(-5,2);
		int r_n5_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_2 = Math.min(Math.min(Math.min(d_n4_3,d_n4_2),d_n4_1),d_n5_1)+r_n5_2;
		tmp_loc = current_loc.translate(-5,3);
		int r_n5_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_3 = Math.min(Math.min(Math.min(d_n4_4,d_n4_3),d_n4_2),d_n5_2)+r_n5_3;
		tmp_loc = current_loc.translate(-5,4);
		int r_n5_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n5_4 = Math.min(Math.min(Math.min(Math.min(d_n4_5,d_n4_4),d_n4_3),d_n5_5),d_n5_3)+r_n5_4;
		tmp_loc = current_loc.translate(-4,6);
		int r_n4_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_6 = Math.min(Math.min(d_n3_5,d_n4_5),d_n5_5)+r_n4_6;
		tmp_loc = current_loc.translate(-3,6);
		int r_n3_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_6 = Math.min(Math.min(Math.min(d_n2_5,d_n3_5),d_n4_6),d_n4_5)+r_n3_6;
		tmp_loc = current_loc.translate(-2,6);
		int r_n2_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_6 = Math.min(Math.min(Math.min(d_n1_5,d_n2_5),d_n3_6),d_n3_5)+r_n2_6;
		tmp_loc = current_loc.translate(-1,6);
		int r_n1_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_6 = Math.min(Math.min(Math.min(d_0_5,d_n1_5),d_n2_6),d_n2_5)+r_n1_6;
		tmp_loc = current_loc.translate(0,6);
		int r_0_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_6 = Math.min(Math.min(Math.min(d_1_5,d_0_5),d_n1_6),d_n1_5)+r_0_6;
		tmp_loc = current_loc.translate(1,6);
		int r_1_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_6 = Math.min(Math.min(Math.min(d_2_5,d_1_5),d_0_6),d_0_5)+r_1_6;
		tmp_loc = current_loc.translate(2,6);
		int r_2_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_6 = Math.min(Math.min(Math.min(d_3_5,d_2_5),d_1_6),d_1_5)+r_2_6;
		tmp_loc = current_loc.translate(3,6);
		int r_3_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_6 = Math.min(Math.min(Math.min(d_4_5,d_3_5),d_2_6),d_2_5)+r_3_6;
		tmp_loc = current_loc.translate(4,6);
		int r_4_6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_6 = Math.min(Math.min(Math.min(d_5_5,d_4_5),d_3_6),d_3_5)+r_4_6;
		tmp_loc = current_loc.translate(6,4);
		int r_6_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_4 = Math.min(Math.min(d_5_5,d_5_4),d_5_3)+r_6_4;
		tmp_loc = current_loc.translate(6,3);
		int r_6_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_3 = Math.min(Math.min(Math.min(d_6_4,d_5_4),d_5_3),d_5_2)+r_6_3;
		tmp_loc = current_loc.translate(6,2);
		int r_6_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_2 = Math.min(Math.min(Math.min(d_6_3,d_5_3),d_5_2),d_5_1)+r_6_2;
		tmp_loc = current_loc.translate(6,1);
		int r_6_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_1 = Math.min(Math.min(Math.min(d_6_2,d_5_2),d_5_1),d_5_0)+r_6_1;
		tmp_loc = current_loc.translate(6,0);
		int r_6_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_0 = Math.min(Math.min(Math.min(d_6_1,d_5_1),d_5_0),d_5_n1)+r_6_0;
		tmp_loc = current_loc.translate(6,-1);
		int r_6_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_n1 = Math.min(Math.min(Math.min(d_6_0,d_5_0),d_5_n1),d_5_n2)+r_6_n1;
		tmp_loc = current_loc.translate(6,-2);
		int r_6_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_n2 = Math.min(Math.min(Math.min(d_6_n1,d_5_n1),d_5_n2),d_5_n3)+r_6_n2;
		tmp_loc = current_loc.translate(6,-3);
		int r_6_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_n3 = Math.min(Math.min(Math.min(d_6_n2,d_5_n2),d_5_n3),d_5_n4)+r_6_n3;
		tmp_loc = current_loc.translate(6,-4);
		int r_6_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_6_n4 = Math.min(Math.min(Math.min(d_6_n3,d_5_n3),d_5_n4),d_5_n5)+r_6_n4;
		tmp_loc = current_loc.translate(4,-6);
		int r_4_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_4_n6 = Math.min(Math.min(d_5_n5,d_4_n5),d_3_n5)+r_4_n6;
		tmp_loc = current_loc.translate(3,-6);
		int r_3_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_3_n6 = Math.min(Math.min(Math.min(d_4_n5,d_4_n6),d_3_n5),d_2_n5)+r_3_n6;
		tmp_loc = current_loc.translate(2,-6);
		int r_2_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n6 = Math.min(Math.min(Math.min(d_3_n5,d_3_n6),d_2_n5),d_1_n5)+r_2_n6;
		tmp_loc = current_loc.translate(1,-6);
		int r_1_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n6 = Math.min(Math.min(Math.min(d_2_n5,d_2_n6),d_1_n5),d_0_n5)+r_1_n6;
		tmp_loc = current_loc.translate(0,-6);
		int r_0_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n6 = Math.min(Math.min(Math.min(d_1_n5,d_1_n6),d_0_n5),d_n1_n5)+r_0_n6;
		tmp_loc = current_loc.translate(-1,-6);
		int r_n1_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n6 = Math.min(Math.min(Math.min(d_0_n5,d_0_n6),d_n1_n5),d_n2_n5)+r_n1_n6;
		tmp_loc = current_loc.translate(-2,-6);
		int r_n2_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n6 = Math.min(Math.min(Math.min(d_n1_n5,d_n1_n6),d_n2_n5),d_n3_n5)+r_n2_n6;
		tmp_loc = current_loc.translate(-3,-6);
		int r_n3_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n3_n6 = Math.min(Math.min(Math.min(d_n2_n5,d_n2_n6),d_n3_n5),d_n4_n5)+r_n3_n6;
		tmp_loc = current_loc.translate(-4,-6);
		int r_n4_n6 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n4_n6 = Math.min(Math.min(Math.min(d_n3_n5,d_n3_n6),d_n4_n5),d_n5_n5)+r_n4_n6;
		tmp_loc = current_loc.translate(-6,-4);
		int r_n6_n4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_n4 = Math.min(Math.min(d_n5_n3,d_n5_n4),d_n5_n5)+r_n6_n4;
		tmp_loc = current_loc.translate(-6,-3);
		int r_n6_n3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_n3 = Math.min(Math.min(Math.min(d_n5_n2,d_n5_n3),d_n5_n4),d_n6_n4)+r_n6_n3;
		tmp_loc = current_loc.translate(-6,-2);
		int r_n6_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_n2 = Math.min(Math.min(Math.min(d_n5_n1,d_n5_n2),d_n5_n3),d_n6_n3)+r_n6_n2;
		tmp_loc = current_loc.translate(-6,-1);
		int r_n6_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_n1 = Math.min(Math.min(Math.min(d_n5_0,d_n5_n1),d_n5_n2),d_n6_n2)+r_n6_n1;
		tmp_loc = current_loc.translate(-6,0);
		int r_n6_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_0 = Math.min(Math.min(Math.min(d_n5_1,d_n5_0),d_n5_n1),d_n6_n1)+r_n6_0;
		tmp_loc = current_loc.translate(-6,1);
		int r_n6_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_1 = Math.min(Math.min(Math.min(d_n5_2,d_n5_1),d_n5_0),d_n6_0)+r_n6_1;
		tmp_loc = current_loc.translate(-6,2);
		int r_n6_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_2 = Math.min(Math.min(Math.min(d_n5_3,d_n5_2),d_n5_1),d_n6_1)+r_n6_2;
		tmp_loc = current_loc.translate(-6,3);
		int r_n6_3 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_3 = Math.min(Math.min(Math.min(d_n5_4,d_n5_3),d_n5_2),d_n6_2)+r_n6_3;
		tmp_loc = current_loc.translate(-6,4);
		int r_n6_4 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n6_4 = Math.min(Math.min(Math.min(d_n5_5,d_n5_4),d_n5_3),d_n6_3)+r_n6_4;
		tmp_loc = current_loc.translate(-2,7);
		int r_n2_7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_7 = Math.min(Math.min(d_n1_6,d_n2_6),d_n3_6)+r_n2_7;
		tmp_loc = current_loc.translate(-1,7);
		int r_n1_7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_7 = Math.min(Math.min(Math.min(d_0_6,d_n1_6),d_n2_7),d_n2_6)+r_n1_7;
		tmp_loc = current_loc.translate(0,7);
		int r_0_7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_7 = Math.min(Math.min(Math.min(d_1_6,d_0_6),d_n1_7),d_n1_6)+r_0_7;
		tmp_loc = current_loc.translate(1,7);
		int r_1_7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_7 = Math.min(Math.min(Math.min(d_2_6,d_1_6),d_0_7),d_0_6)+r_1_7;
		tmp_loc = current_loc.translate(2,7);
		int r_2_7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_7 = Math.min(Math.min(Math.min(d_3_6,d_2_6),d_1_7),d_1_6)+r_2_7;
		tmp_loc = current_loc.translate(7,2);
		int r_7_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_7_2 = Math.min(Math.min(d_6_3,d_6_2),d_6_1)+r_7_2;
		tmp_loc = current_loc.translate(7,1);
		int r_7_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_7_1 = Math.min(Math.min(Math.min(d_7_2,d_6_2),d_6_1),d_6_0)+r_7_1;
		tmp_loc = current_loc.translate(7,0);
		int r_7_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_7_0 = Math.min(Math.min(Math.min(d_7_1,d_6_1),d_6_0),d_6_n1)+r_7_0;
		tmp_loc = current_loc.translate(7,-1);
		int r_7_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_7_n1 = Math.min(Math.min(Math.min(d_7_0,d_6_0),d_6_n1),d_6_n2)+r_7_n1;
		tmp_loc = current_loc.translate(7,-2);
		int r_7_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_7_n2 = Math.min(Math.min(Math.min(d_7_n1,d_6_n1),d_6_n2),d_6_n3)+r_7_n2;
		tmp_loc = current_loc.translate(2,-7);
		int r_2_n7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_2_n7 = Math.min(Math.min(d_3_n6,d_2_n6),d_1_n6)+r_2_n7;
		tmp_loc = current_loc.translate(1,-7);
		int r_1_n7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_1_n7 = Math.min(Math.min(Math.min(d_2_n6,d_2_n7),d_1_n6),d_0_n6)+r_1_n7;
		tmp_loc = current_loc.translate(0,-7);
		int r_0_n7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_0_n7 = Math.min(Math.min(Math.min(d_1_n6,d_1_n7),d_0_n6),d_n1_n6)+r_0_n7;
		tmp_loc = current_loc.translate(-1,-7);
		int r_n1_n7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n1_n7 = Math.min(Math.min(Math.min(d_0_n6,d_0_n7),d_n1_n6),d_n2_n6)+r_n1_n7;
		tmp_loc = current_loc.translate(-2,-7);
		int r_n2_n7 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n2_n7 = Math.min(Math.min(Math.min(d_n1_n6,d_n1_n7),d_n2_n6),d_n3_n6)+r_n2_n7;
		tmp_loc = current_loc.translate(-7,-2);
		int r_n7_n2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n7_n2 = Math.min(Math.min(d_n6_n1,d_n6_n2),d_n6_n3)+r_n7_n2;
		tmp_loc = current_loc.translate(-7,-1);
		int r_n7_n1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n7_n1 = Math.min(Math.min(Math.min(d_n6_0,d_n6_n1),d_n6_n2),d_n7_n2)+r_n7_n1;
		tmp_loc = current_loc.translate(-7,0);
		int r_n7_0 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n7_0 = Math.min(Math.min(Math.min(d_n6_1,d_n6_0),d_n6_n1),d_n7_n1)+r_n7_0;
		tmp_loc = current_loc.translate(-7,1);
		int r_n7_1 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n7_1 = Math.min(Math.min(Math.min(d_n6_2,d_n6_1),d_n6_0),d_n7_0)+r_n7_1;
		tmp_loc = current_loc.translate(-7,2);
		int r_n7_2 = rc.canSenseLocation(tmp_loc) ? MARBLE_COST_LOOKUP[rc.senseRubble(tmp_loc)] : INF_MARBLE;
		int d_n7_2 = Math.min(Math.min(Math.min(d_n6_3,d_n6_2),d_n6_1),d_n7_1)+r_n7_2;
		d_n1_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_1,d_0_n1),d_n1_1),d_n1_n1),d_n2_1),d_n2_0),d_n2_n1)+r_n1_0,d_n1_0);
		d_n1_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n1,d_0_n2),d_n1_0),d_n1_n2),d_n2_0),d_n2_n1),d_n2_n2)+r_n1_n1,d_n1_n1);
		d_0_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_0,d_1_n1),d_1_n2),d_0_n2),d_n1_0),d_n1_n1),d_n1_n2)+r_0_n1,d_0_n1);
		d_1_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_0,d_2_n1),d_2_n2),d_1_0),d_1_n2),d_0_n1),d_0_n2)+r_1_n1,d_1_n1);
		d_1_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_1,d_2_0),d_2_n1),d_1_1),d_1_n1),d_0_1),d_0_n1)+r_1_0,d_1_0);
		d_1_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_2,d_2_1),d_2_0),d_1_2),d_1_0),d_0_2),d_0_1)+r_1_1,d_1_1);
		d_0_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_2,d_1_1),d_1_0),d_0_2),d_n1_2),d_n1_1),d_n1_0)+r_0_1,d_0_1);
		d_n1_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_2,d_0_1),d_n1_2),d_n1_0),d_n2_2),d_n2_1),d_n2_0)+r_n1_1,d_n1_1);
		d_n2_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_2,d_n1_1),d_n1_0),d_n2_2),d_n2_0),d_n3_2),d_n3_1),d_n3_0)+r_n2_1,d_n2_1);
		d_n2_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_1,d_n1_0),d_n1_n1),d_n2_1),d_n2_n1),d_n3_1),d_n3_0),d_n3_n1)+r_n2_0,d_n2_0);
		d_n2_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_0,d_n1_n1),d_n1_n2),d_n2_0),d_n2_n2),d_n3_0),d_n3_n1),d_n3_n2)+r_n2_n1,d_n2_n1);
		d_n2_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_n1,d_n1_n2),d_n1_n3),d_n2_n1),d_n2_n3),d_n3_n1),d_n3_n2),d_n3_n3)+r_n2_n2,d_n2_n2);
		d_n1_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n1,d_0_n2),d_0_n3),d_n1_n1),d_n1_n3),d_n2_n1),d_n2_n2),d_n2_n3)+r_n1_n2,d_n1_n2);
		d_0_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_n1,d_1_n2),d_1_n3),d_0_n1),d_0_n3),d_n1_n1),d_n1_n2),d_n1_n3)+r_0_n2,d_0_n2);
		d_1_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_n1,d_2_n2),d_2_n3),d_1_n1),d_1_n3),d_0_n1),d_0_n2),d_0_n3)+r_1_n2,d_1_n2);
		d_2_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_n1,d_3_n2),d_3_n3),d_2_n1),d_2_n3),d_1_n1),d_1_n2),d_1_n3)+r_2_n2,d_2_n2);
		d_2_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_0,d_3_n1),d_3_n2),d_2_0),d_2_n2),d_1_0),d_1_n1),d_1_n2)+r_2_n1,d_2_n1);
		d_2_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_1,d_3_0),d_3_n1),d_2_1),d_2_n1),d_1_1),d_1_0),d_1_n1)+r_2_0,d_2_0);
		d_2_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_2,d_3_1),d_3_0),d_2_2),d_2_0),d_1_2),d_1_1),d_1_0)+r_2_1,d_2_1);
		d_2_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_3,d_3_2),d_3_1),d_2_3),d_2_1),d_1_3),d_1_2),d_1_1)+r_2_2,d_2_2);
		d_1_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_3,d_2_2),d_2_1),d_1_3),d_1_1),d_0_3),d_0_2),d_0_1)+r_1_2,d_1_2);
		d_0_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_3,d_1_2),d_1_1),d_0_3),d_0_1),d_n1_3),d_n1_2),d_n1_1)+r_0_2,d_0_2);
		d_n1_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_3,d_0_2),d_0_1),d_n1_3),d_n1_1),d_n2_3),d_n2_2),d_n2_1)+r_n1_2,d_n1_2);
		d_n2_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_3,d_n1_2),d_n1_1),d_n2_3),d_n2_1),d_n3_3),d_n3_2),d_n3_1)+r_n2_2,d_n2_2);
		d_n3_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_3,d_n2_2),d_n2_1),d_n3_3),d_n3_1),d_n4_3),d_n4_2),d_n4_1)+r_n3_2,d_n3_2);
		d_n3_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_2,d_n2_1),d_n2_0),d_n3_2),d_n3_0),d_n4_2),d_n4_1),d_n4_0)+r_n3_1,d_n3_1);
		d_n3_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_1,d_n2_0),d_n2_n1),d_n3_1),d_n3_n1),d_n4_1),d_n4_0),d_n4_n1)+r_n3_0,d_n3_0);
		d_n3_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_0,d_n2_n1),d_n2_n2),d_n3_0),d_n3_n2),d_n4_0),d_n4_n1),d_n4_n2)+r_n3_n1,d_n3_n1);
		d_n3_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_n1,d_n2_n2),d_n2_n3),d_n3_n1),d_n3_n3),d_n4_n1),d_n4_n2),d_n4_n3)+r_n3_n2,d_n3_n2);
		d_n3_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_n2,d_n2_n3),d_n2_n4),d_n3_n2),d_n3_n4),d_n4_n2),d_n4_n3),d_n4_n4)+r_n3_n3,d_n3_n3);
		d_n2_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_n2,d_n1_n3),d_n1_n4),d_n2_n2),d_n2_n4),d_n3_n2),d_n3_n3),d_n3_n4)+r_n2_n3,d_n2_n3);
		d_n1_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n2,d_0_n3),d_0_n4),d_n1_n2),d_n1_n4),d_n2_n2),d_n2_n3),d_n2_n4)+r_n1_n3,d_n1_n3);
		d_0_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_n2,d_1_n3),d_1_n4),d_0_n2),d_0_n4),d_n1_n2),d_n1_n3),d_n1_n4)+r_0_n3,d_0_n3);
		d_1_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_n2,d_2_n3),d_2_n4),d_1_n2),d_1_n4),d_0_n2),d_0_n3),d_0_n4)+r_1_n3,d_1_n3);
		d_2_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_n2,d_3_n3),d_3_n4),d_2_n2),d_2_n4),d_1_n2),d_1_n3),d_1_n4)+r_2_n3,d_2_n3);
		d_3_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_n2,d_4_n3),d_4_n4),d_3_n2),d_3_n4),d_2_n2),d_2_n3),d_2_n4)+r_3_n3,d_3_n3);
		d_3_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_n1,d_4_n2),d_4_n3),d_3_n1),d_3_n3),d_2_n1),d_2_n2),d_2_n3)+r_3_n2,d_3_n2);
		d_3_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_0,d_4_n1),d_4_n2),d_3_0),d_3_n2),d_2_0),d_2_n1),d_2_n2)+r_3_n1,d_3_n1);
		d_3_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_1,d_4_0),d_4_n1),d_3_1),d_3_n1),d_2_1),d_2_0),d_2_n1)+r_3_0,d_3_0);
		d_3_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_2,d_4_1),d_4_0),d_3_2),d_3_0),d_2_2),d_2_1),d_2_0)+r_3_1,d_3_1);
		d_3_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_3,d_4_2),d_4_1),d_3_3),d_3_1),d_2_3),d_2_2),d_2_1)+r_3_2,d_3_2);
		d_3_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_4,d_4_3),d_4_2),d_3_4),d_3_2),d_2_4),d_2_3),d_2_2)+r_3_3,d_3_3);
		d_2_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_4,d_3_3),d_3_2),d_2_4),d_2_2),d_1_4),d_1_3),d_1_2)+r_2_3,d_2_3);
		d_1_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_4,d_2_3),d_2_2),d_1_4),d_1_2),d_0_4),d_0_3),d_0_2)+r_1_3,d_1_3);
		d_0_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_4,d_1_3),d_1_2),d_0_4),d_0_2),d_n1_4),d_n1_3),d_n1_2)+r_0_3,d_0_3);
		d_n1_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_4,d_0_3),d_0_2),d_n1_4),d_n1_2),d_n2_4),d_n2_3),d_n2_2)+r_n1_3,d_n1_3);
		d_n2_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_4,d_n1_3),d_n1_2),d_n2_4),d_n2_2),d_n3_4),d_n3_3),d_n3_2)+r_n2_3,d_n2_3);
		d_n3_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_4,d_n2_3),d_n2_2),d_n3_4),d_n3_2),d_n4_4),d_n4_3),d_n4_2)+r_n3_3,d_n3_3);
		d_n4_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_4,d_n3_3),d_n3_2),d_n4_4),d_n4_2),d_n5_4),d_n5_3),d_n5_2)+r_n4_3,d_n4_3);
		d_n4_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_3,d_n3_2),d_n3_1),d_n4_3),d_n4_1),d_n5_3),d_n5_2),d_n5_1)+r_n4_2,d_n4_2);
		d_n4_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_2,d_n3_1),d_n3_0),d_n4_2),d_n4_0),d_n5_2),d_n5_1),d_n5_0)+r_n4_1,d_n4_1);
		d_n4_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_1,d_n3_0),d_n3_n1),d_n4_1),d_n4_n1),d_n5_1),d_n5_0),d_n5_n1)+r_n4_0,d_n4_0);
		d_n4_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_0,d_n3_n1),d_n3_n2),d_n4_0),d_n4_n2),d_n5_0),d_n5_n1),d_n5_n2)+r_n4_n1,d_n4_n1);
		d_n4_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_n1,d_n3_n2),d_n3_n3),d_n4_n1),d_n4_n3),d_n5_n1),d_n5_n2),d_n5_n3)+r_n4_n2,d_n4_n2);
		d_n4_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_n2,d_n3_n3),d_n3_n4),d_n4_n2),d_n4_n4),d_n5_n2),d_n5_n3),d_n5_n4)+r_n4_n3,d_n4_n3);
		d_n4_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_n3,d_n3_n4),d_n3_n5),d_n4_n3),d_n4_n5),d_n5_n3),d_n5_n4),d_n5_n5)+r_n4_n4,d_n4_n4);
		d_n3_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_n3,d_n2_n4),d_n2_n5),d_n3_n3),d_n3_n5),d_n4_n3),d_n4_n4),d_n4_n5)+r_n3_n4,d_n3_n4);
		d_n2_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_n3,d_n1_n4),d_n1_n5),d_n2_n3),d_n2_n5),d_n3_n3),d_n3_n4),d_n3_n5)+r_n2_n4,d_n2_n4);
		d_n1_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n3,d_0_n4),d_0_n5),d_n1_n3),d_n1_n5),d_n2_n3),d_n2_n4),d_n2_n5)+r_n1_n4,d_n1_n4);
		d_0_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_n3,d_1_n4),d_1_n5),d_0_n3),d_0_n5),d_n1_n3),d_n1_n4),d_n1_n5)+r_0_n4,d_0_n4);
		d_1_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_n3,d_2_n4),d_2_n5),d_1_n3),d_1_n5),d_0_n3),d_0_n4),d_0_n5)+r_1_n4,d_1_n4);
		d_2_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_n3,d_3_n4),d_3_n5),d_2_n3),d_2_n5),d_1_n3),d_1_n4),d_1_n5)+r_2_n4,d_2_n4);
		d_3_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_n3,d_4_n4),d_4_n5),d_3_n3),d_3_n5),d_2_n3),d_2_n4),d_2_n5)+r_3_n4,d_3_n4);
		d_4_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_n3,d_5_n4),d_5_n5),d_4_n3),d_4_n5),d_3_n3),d_3_n4),d_3_n5)+r_4_n4,d_4_n4);
		d_4_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_n2,d_5_n3),d_5_n4),d_4_n2),d_4_n4),d_3_n2),d_3_n3),d_3_n4)+r_4_n3,d_4_n3);
		d_4_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_n1,d_5_n2),d_5_n3),d_4_n1),d_4_n3),d_3_n1),d_3_n2),d_3_n3)+r_4_n2,d_4_n2);
		d_4_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_0,d_5_n1),d_5_n2),d_4_0),d_4_n2),d_3_0),d_3_n1),d_3_n2)+r_4_n1,d_4_n1);
		d_4_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_1,d_5_0),d_5_n1),d_4_1),d_4_n1),d_3_1),d_3_0),d_3_n1)+r_4_0,d_4_0);
		d_4_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_2,d_5_1),d_5_0),d_4_2),d_4_0),d_3_2),d_3_1),d_3_0)+r_4_1,d_4_1);
		d_4_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_3,d_5_2),d_5_1),d_4_3),d_4_1),d_3_3),d_3_2),d_3_1)+r_4_2,d_4_2);
		d_4_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_4,d_5_3),d_5_2),d_4_4),d_4_2),d_3_4),d_3_3),d_3_2)+r_4_3,d_4_3);
		d_4_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_5,d_5_4),d_5_3),d_4_5),d_4_3),d_3_5),d_3_4),d_3_3)+r_4_4,d_4_4);
		d_3_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_5,d_4_4),d_4_3),d_3_5),d_3_3),d_2_5),d_2_4),d_2_3)+r_3_4,d_3_4);
		d_2_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_5,d_3_4),d_3_3),d_2_5),d_2_3),d_1_5),d_1_4),d_1_3)+r_2_4,d_2_4);
		d_1_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_5,d_2_4),d_2_3),d_1_5),d_1_3),d_0_5),d_0_4),d_0_3)+r_1_4,d_1_4);
		d_0_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_5,d_1_4),d_1_3),d_0_5),d_0_3),d_n1_5),d_n1_4),d_n1_3)+r_0_4,d_0_4);
		d_n1_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_5,d_0_4),d_0_3),d_n1_5),d_n1_3),d_n2_5),d_n2_4),d_n2_3)+r_n1_4,d_n1_4);
		d_n2_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_5,d_n1_4),d_n1_3),d_n2_5),d_n2_3),d_n3_5),d_n3_4),d_n3_3)+r_n2_4,d_n2_4);
		d_n3_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_5,d_n2_4),d_n2_3),d_n3_5),d_n3_3),d_n4_5),d_n4_4),d_n4_3)+r_n3_4,d_n3_4);
		d_n4_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_5,d_n3_4),d_n3_3),d_n4_5),d_n4_3),d_n5_5),d_n5_4),d_n5_3)+r_n4_4,d_n4_4);
		d_n5_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_5,d_n4_4),d_n4_3),d_n5_5),d_n5_3),d_n6_4),d_n6_3)+r_n5_4,d_n5_4);
		d_n5_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_4,d_n4_3),d_n4_2),d_n5_4),d_n5_2),d_n6_4),d_n6_3),d_n6_2)+r_n5_3,d_n5_3);
		d_n5_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_3,d_n4_2),d_n4_1),d_n5_3),d_n5_1),d_n6_3),d_n6_2),d_n6_1)+r_n5_2,d_n5_2);
		d_n5_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_2,d_n4_1),d_n4_0),d_n5_2),d_n5_0),d_n6_2),d_n6_1),d_n6_0)+r_n5_1,d_n5_1);
		d_n5_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_1,d_n4_0),d_n4_n1),d_n5_1),d_n5_n1),d_n6_1),d_n6_0),d_n6_n1)+r_n5_0,d_n5_0);
		d_n5_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_0,d_n4_n1),d_n4_n2),d_n5_0),d_n5_n2),d_n6_0),d_n6_n1),d_n6_n2)+r_n5_n1,d_n5_n1);
		d_n5_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_n1,d_n4_n2),d_n4_n3),d_n5_n1),d_n5_n3),d_n6_n1),d_n6_n2),d_n6_n3)+r_n5_n2,d_n5_n2);
		d_n5_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_n2,d_n4_n3),d_n4_n4),d_n5_n2),d_n5_n4),d_n6_n2),d_n6_n3),d_n6_n4)+r_n5_n3,d_n5_n3);
		d_n5_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_n3,d_n4_n4),d_n4_n5),d_n5_n3),d_n5_n5),d_n6_n3),d_n6_n4)+r_n5_n4,d_n5_n4);
		d_n5_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_n4,d_n4_n5),d_n4_n6),d_n5_n4),d_n6_n4)+r_n5_n5,d_n5_n5);
		d_n4_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_n4,d_n3_n5),d_n3_n6),d_n4_n4),d_n4_n6),d_n5_n4),d_n5_n5)+r_n4_n5,d_n4_n5);
		d_n3_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_n4,d_n2_n5),d_n2_n6),d_n3_n4),d_n3_n6),d_n4_n4),d_n4_n5),d_n4_n6)+r_n3_n5,d_n3_n5);
		d_n2_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_n4,d_n1_n5),d_n1_n6),d_n2_n4),d_n2_n6),d_n3_n4),d_n3_n5),d_n3_n6)+r_n2_n5,d_n2_n5);
		d_n1_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n4,d_0_n5),d_0_n6),d_n1_n4),d_n1_n6),d_n2_n4),d_n2_n5),d_n2_n6)+r_n1_n5,d_n1_n5);
		d_0_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_n4,d_1_n5),d_1_n6),d_0_n4),d_0_n6),d_n1_n4),d_n1_n5),d_n1_n6)+r_0_n5,d_0_n5);
		d_1_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_n4,d_2_n5),d_2_n6),d_1_n4),d_1_n6),d_0_n4),d_0_n5),d_0_n6)+r_1_n5,d_1_n5);
		d_2_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_n4,d_3_n5),d_3_n6),d_2_n4),d_2_n6),d_1_n4),d_1_n5),d_1_n6)+r_2_n5,d_2_n5);
		d_3_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_n4,d_4_n5),d_4_n6),d_3_n4),d_3_n6),d_2_n4),d_2_n5),d_2_n6)+r_3_n5,d_3_n5);
		d_4_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_n4,d_5_n5),d_4_n4),d_4_n6),d_3_n4),d_3_n5),d_3_n6)+r_4_n5,d_4_n5);
		d_5_n5 = Math.min(Math.min(Math.min(Math.min(Math.min(d_6_n4,d_5_n4),d_4_n4),d_4_n5),d_4_n6)+r_5_n5,d_5_n5);
		d_5_n4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_n3,d_6_n4),d_5_n3),d_5_n5),d_4_n3),d_4_n4),d_4_n5)+r_5_n4,d_5_n4);
		d_5_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_n2,d_6_n3),d_6_n4),d_5_n2),d_5_n4),d_4_n2),d_4_n3),d_4_n4)+r_5_n3,d_5_n3);
		d_5_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_n1,d_6_n2),d_6_n3),d_5_n1),d_5_n3),d_4_n1),d_4_n2),d_4_n3)+r_5_n2,d_5_n2);
		d_5_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_0,d_6_n1),d_6_n2),d_5_0),d_5_n2),d_4_0),d_4_n1),d_4_n2)+r_5_n1,d_5_n1);
		d_5_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_1,d_6_0),d_6_n1),d_5_1),d_5_n1),d_4_1),d_4_0),d_4_n1)+r_5_0,d_5_0);
		d_5_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_2,d_6_1),d_6_0),d_5_2),d_5_0),d_4_2),d_4_1),d_4_0)+r_5_1,d_5_1);
		d_5_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_3,d_6_2),d_6_1),d_5_3),d_5_1),d_4_3),d_4_2),d_4_1)+r_5_2,d_5_2);
		d_5_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_4,d_6_3),d_6_2),d_5_4),d_5_2),d_4_4),d_4_3),d_4_2)+r_5_3,d_5_3);
		d_5_4 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_6_4,d_6_3),d_5_5),d_5_3),d_4_5),d_4_4),d_4_3)+r_5_4,d_5_4);
		d_5_5 = Math.min(Math.min(Math.min(Math.min(Math.min(d_6_4,d_5_4),d_4_6),d_4_5),d_4_4)+r_5_5,d_5_5);
		d_4_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_5_5,d_5_4),d_4_6),d_4_4),d_3_6),d_3_5),d_3_4)+r_4_5,d_4_5);
		d_3_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_6,d_4_5),d_4_4),d_3_6),d_3_4),d_2_6),d_2_5),d_2_4)+r_3_5,d_3_5);
		d_2_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_6,d_3_5),d_3_4),d_2_6),d_2_4),d_1_6),d_1_5),d_1_4)+r_2_5,d_2_5);
		d_1_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_6,d_2_5),d_2_4),d_1_6),d_1_4),d_0_6),d_0_5),d_0_4)+r_1_5,d_1_5);
		d_0_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_6,d_1_5),d_1_4),d_0_6),d_0_4),d_n1_6),d_n1_5),d_n1_4)+r_0_5,d_0_5);
		d_n1_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_6,d_0_5),d_0_4),d_n1_6),d_n1_4),d_n2_6),d_n2_5),d_n2_4)+r_n1_5,d_n1_5);
		d_n2_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_6,d_n1_5),d_n1_4),d_n2_6),d_n2_4),d_n3_6),d_n3_5),d_n3_4)+r_n2_5,d_n2_5);
		d_n3_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_6,d_n2_5),d_n2_4),d_n3_6),d_n3_4),d_n4_6),d_n4_5),d_n4_4)+r_n3_5,d_n3_5);
		d_n4_5 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n3_6,d_n3_5),d_n3_4),d_n4_6),d_n4_4),d_n5_5),d_n5_4)+r_n4_5,d_n4_5);
		d_n5_5 = Math.min(Math.min(Math.min(Math.min(Math.min(d_n4_6,d_n4_5),d_n4_4),d_n5_4),d_n6_4)+r_n5_5,d_n5_5);
		d_n6_4 = Math.min(Math.min(Math.min(Math.min(d_n5_5,d_n5_4),d_n5_3),d_n6_3)+r_n6_4,d_n6_4);
		d_n6_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_4,d_n5_3),d_n5_2),d_n6_4),d_n6_2),d_n7_2)+r_n6_3,d_n6_3);
		d_n6_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_3,d_n5_2),d_n5_1),d_n6_3),d_n6_1),d_n7_2),d_n7_1)+r_n6_2,d_n6_2);
		d_n6_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_2,d_n5_1),d_n5_0),d_n6_2),d_n6_0),d_n7_2),d_n7_1),d_n7_0)+r_n6_1,d_n6_1);
		d_n6_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_1,d_n5_0),d_n5_n1),d_n6_1),d_n6_n1),d_n7_1),d_n7_0),d_n7_n1)+r_n6_0,d_n6_0);
		d_n6_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_0,d_n5_n1),d_n5_n2),d_n6_0),d_n6_n2),d_n7_0),d_n7_n1),d_n7_n2)+r_n6_n1,d_n6_n1);
		d_n6_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_n1,d_n5_n2),d_n5_n3),d_n6_n1),d_n6_n3),d_n7_n1),d_n7_n2)+r_n6_n2,d_n6_n2);
		d_n6_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n5_n2,d_n5_n3),d_n5_n4),d_n6_n2),d_n6_n4),d_n7_n2)+r_n6_n3,d_n6_n3);
		d_n6_n4 = Math.min(Math.min(Math.min(Math.min(d_n5_n3,d_n5_n4),d_n5_n5),d_n6_n3)+r_n6_n4,d_n6_n4);
		d_n4_n6 = Math.min(Math.min(Math.min(Math.min(d_n3_n5,d_n3_n6),d_n4_n5),d_n5_n5)+r_n4_n6,d_n4_n6);
		d_n3_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_n5,d_n2_n6),d_n2_n7),d_n3_n5),d_n4_n5),d_n4_n6)+r_n3_n6,d_n3_n6);
		d_n2_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_n5,d_n1_n6),d_n1_n7),d_n2_n5),d_n2_n7),d_n3_n5),d_n3_n6)+r_n2_n6,d_n2_n6);
		d_n1_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n5,d_0_n6),d_0_n7),d_n1_n5),d_n1_n7),d_n2_n5),d_n2_n6),d_n2_n7)+r_n1_n6,d_n1_n6);
		d_0_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_n5,d_1_n6),d_1_n7),d_0_n5),d_0_n7),d_n1_n5),d_n1_n6),d_n1_n7)+r_0_n6,d_0_n6);
		d_1_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_n5,d_2_n6),d_2_n7),d_1_n5),d_1_n7),d_0_n5),d_0_n6),d_0_n7)+r_1_n6,d_1_n6);
		d_2_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_n5,d_3_n6),d_2_n5),d_2_n7),d_1_n5),d_1_n6),d_1_n7)+r_2_n6,d_2_n6);
		d_3_n6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_n5,d_4_n6),d_3_n5),d_2_n5),d_2_n6),d_2_n7)+r_3_n6,d_3_n6);
		d_4_n6 = Math.min(Math.min(Math.min(Math.min(d_5_n5,d_4_n5),d_3_n5),d_3_n6)+r_4_n6,d_4_n6);
		d_6_n4 = Math.min(Math.min(Math.min(Math.min(d_6_n3,d_5_n3),d_5_n4),d_5_n5)+r_6_n4,d_6_n4);
		d_6_n3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_n2,d_6_n2),d_6_n4),d_5_n2),d_5_n3),d_5_n4)+r_6_n3,d_6_n3);
		d_6_n2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_n1,d_7_n2),d_6_n1),d_6_n3),d_5_n1),d_5_n2),d_5_n3)+r_6_n2,d_6_n2);
		d_6_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_0,d_7_n1),d_7_n2),d_6_0),d_6_n2),d_5_0),d_5_n1),d_5_n2)+r_6_n1,d_6_n1);
		d_6_0 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_1,d_7_0),d_7_n1),d_6_1),d_6_n1),d_5_1),d_5_0),d_5_n1)+r_6_0,d_6_0);
		d_6_1 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_2,d_7_1),d_7_0),d_6_2),d_6_0),d_5_2),d_5_1),d_5_0)+r_6_1,d_6_1);
		d_6_2 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_2,d_7_1),d_6_3),d_6_1),d_5_3),d_5_2),d_5_1)+r_6_2,d_6_2);
		d_6_3 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_7_2,d_6_4),d_6_2),d_5_4),d_5_3),d_5_2)+r_6_3,d_6_3);
		d_6_4 = Math.min(Math.min(Math.min(Math.min(d_6_3,d_5_5),d_5_4),d_5_3)+r_6_4,d_6_4);
		d_4_6 = Math.min(Math.min(Math.min(Math.min(d_5_5,d_4_5),d_3_6),d_3_5)+r_4_6,d_4_6);
		d_3_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_4_6,d_4_5),d_3_5),d_2_7),d_2_6),d_2_5)+r_3_6,d_3_6);
		d_2_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_3_6,d_3_5),d_2_7),d_2_5),d_1_7),d_1_6),d_1_5)+r_2_6,d_2_6);
		d_1_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_2_7,d_2_6),d_2_5),d_1_7),d_1_5),d_0_7),d_0_6),d_0_5)+r_1_6,d_1_6);
		d_0_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_1_7,d_1_6),d_1_5),d_0_7),d_0_5),d_n1_7),d_n1_6),d_n1_5)+r_0_6,d_0_6);
		d_n1_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_0_7,d_0_6),d_0_5),d_n1_7),d_n1_5),d_n2_7),d_n2_6),d_n2_5)+r_n1_6,d_n1_6);
		d_n2_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n1_7,d_n1_6),d_n1_5),d_n2_7),d_n2_5),d_n3_6),d_n3_5)+r_n2_6,d_n2_6);
		d_n3_6 = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(d_n2_7,d_n2_6),d_n2_5),d_n3_5),d_n4_6),d_n4_5)+r_n3_6,d_n3_6);
		d_n4_6 = Math.min(Math.min(Math.min(Math.min(d_n3_6,d_n3_5),d_n4_5),d_n5_5)+r_n4_6,d_n4_6);
		d_n7_2 = Math.min(Math.min(Math.min(Math.min(d_n6_3,d_n6_2),d_n6_1),d_n7_1)+r_n7_2,d_n7_2);
		d_n7_1 = Math.min(Math.min(Math.min(Math.min(Math.min(d_n6_2,d_n6_1),d_n6_0),d_n7_2),d_n7_0)+r_n7_1,d_n7_1);
		d_n7_0 = Math.min(Math.min(Math.min(Math.min(Math.min(d_n6_1,d_n6_0),d_n6_n1),d_n7_1),d_n7_n1)+r_n7_0,d_n7_0);
		d_n7_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(d_n6_0,d_n6_n1),d_n6_n2),d_n7_0),d_n7_n2)+r_n7_n1,d_n7_n1);
		d_n7_n2 = Math.min(Math.min(Math.min(Math.min(d_n6_n1,d_n6_n2),d_n6_n3),d_n7_n1)+r_n7_n2,d_n7_n2);
		d_n2_n7 = Math.min(Math.min(Math.min(Math.min(d_n1_n6,d_n1_n7),d_n2_n6),d_n3_n6)+r_n2_n7,d_n2_n7);
		d_n1_n7 = Math.min(Math.min(Math.min(Math.min(Math.min(d_0_n6,d_0_n7),d_n1_n6),d_n2_n6),d_n2_n7)+r_n1_n7,d_n1_n7);
		d_0_n7 = Math.min(Math.min(Math.min(Math.min(Math.min(d_1_n6,d_1_n7),d_0_n6),d_n1_n6),d_n1_n7)+r_0_n7,d_0_n7);
		d_1_n7 = Math.min(Math.min(Math.min(Math.min(Math.min(d_2_n6,d_2_n7),d_1_n6),d_0_n6),d_0_n7)+r_1_n7,d_1_n7);
		d_2_n7 = Math.min(Math.min(Math.min(Math.min(d_3_n6,d_2_n6),d_1_n6),d_1_n7)+r_2_n7,d_2_n7);
		d_7_n2 = Math.min(Math.min(Math.min(Math.min(d_7_n1,d_6_n1),d_6_n2),d_6_n3)+r_7_n2,d_7_n2);
		d_7_n1 = Math.min(Math.min(Math.min(Math.min(Math.min(d_7_0,d_7_n2),d_6_0),d_6_n1),d_6_n2)+r_7_n1,d_7_n1);
		d_7_0 = Math.min(Math.min(Math.min(Math.min(Math.min(d_7_1,d_7_n1),d_6_1),d_6_0),d_6_n1)+r_7_0,d_7_0);
		d_7_1 = Math.min(Math.min(Math.min(Math.min(Math.min(d_7_2,d_7_0),d_6_2),d_6_1),d_6_0)+r_7_1,d_7_1);
		d_7_2 = Math.min(Math.min(Math.min(Math.min(d_7_1,d_6_3),d_6_2),d_6_1)+r_7_2,d_7_2);
		d_2_7 = Math.min(Math.min(Math.min(Math.min(d_3_6,d_2_6),d_1_7),d_1_6)+r_2_7,d_2_7);
		d_1_7 = Math.min(Math.min(Math.min(Math.min(Math.min(d_2_7,d_2_6),d_1_6),d_0_7),d_0_6)+r_1_7,d_1_7);
		d_0_7 = Math.min(Math.min(Math.min(Math.min(Math.min(d_1_7,d_1_6),d_0_6),d_n1_7),d_n1_6)+r_0_7,d_0_7);
		d_n1_7 = Math.min(Math.min(Math.min(Math.min(Math.min(d_0_7,d_0_6),d_n1_6),d_n2_7),d_n2_6)+r_n1_7,d_n1_7);
		d_n2_7 = Math.min(Math.min(Math.min(Math.min(d_n1_7,d_n1_6),d_n2_6),d_n3_6)+r_n2_7,d_n2_7);
		int minDistance = 1000000000;
		if (dctd <= 53)
		{
			switch ((7-dctx)*15+7-dcty)
			{
				case 5:minDistance=d_n7_n2;break;
				case 6:minDistance=d_n7_n1;break;
				case 7:minDistance=d_n7_0;break;
				case 8:minDistance=d_n7_1;break;
				case 9:minDistance=d_n7_2;break;
				case 18:minDistance=d_n6_n4;break;
				case 19:minDistance=d_n6_n3;break;
				case 20:minDistance=d_n6_n2;break;
				case 21:minDistance=d_n6_n1;break;
				case 22:minDistance=d_n6_0;break;
				case 23:minDistance=d_n6_1;break;
				case 24:minDistance=d_n6_2;break;
				case 25:minDistance=d_n6_3;break;
				case 26:minDistance=d_n6_4;break;
				case 32:minDistance=d_n5_n5;break;
				case 33:minDistance=d_n5_n4;break;
				case 34:minDistance=d_n5_n3;break;
				case 35:minDistance=d_n5_n2;break;
				case 36:minDistance=d_n5_n1;break;
				case 37:minDistance=d_n5_0;break;
				case 38:minDistance=d_n5_1;break;
				case 39:minDistance=d_n5_2;break;
				case 40:minDistance=d_n5_3;break;
				case 41:minDistance=d_n5_4;break;
				case 42:minDistance=d_n5_5;break;
				case 46:minDistance=d_n4_n6;break;
				case 47:minDistance=d_n4_n5;break;
				case 48:minDistance=d_n4_n4;break;
				case 49:minDistance=d_n4_n3;break;
				case 50:minDistance=d_n4_n2;break;
				case 51:minDistance=d_n4_n1;break;
				case 52:minDistance=d_n4_0;break;
				case 53:minDistance=d_n4_1;break;
				case 54:minDistance=d_n4_2;break;
				case 55:minDistance=d_n4_3;break;
				case 56:minDistance=d_n4_4;break;
				case 57:minDistance=d_n4_5;break;
				case 58:minDistance=d_n4_6;break;
				case 61:minDistance=d_n3_n6;break;
				case 62:minDistance=d_n3_n5;break;
				case 63:minDistance=d_n3_n4;break;
				case 64:minDistance=d_n3_n3;break;
				case 65:minDistance=d_n3_n2;break;
				case 66:minDistance=d_n3_n1;break;
				case 67:minDistance=d_n3_0;break;
				case 68:minDistance=d_n3_1;break;
				case 69:minDistance=d_n3_2;break;
				case 70:minDistance=d_n3_3;break;
				case 71:minDistance=d_n3_4;break;
				case 72:minDistance=d_n3_5;break;
				case 73:minDistance=d_n3_6;break;
				case 75:minDistance=d_n2_n7;break;
				case 76:minDistance=d_n2_n6;break;
				case 77:minDistance=d_n2_n5;break;
				case 78:minDistance=d_n2_n4;break;
				case 79:minDistance=d_n2_n3;break;
				case 80:minDistance=d_n2_n2;break;
				case 81:minDistance=d_n2_n1;break;
				case 82:minDistance=d_n2_0;break;
				case 83:minDistance=d_n2_1;break;
				case 84:minDistance=d_n2_2;break;
				case 85:minDistance=d_n2_3;break;
				case 86:minDistance=d_n2_4;break;
				case 87:minDistance=d_n2_5;break;
				case 88:minDistance=d_n2_6;break;
				case 89:minDistance=d_n2_7;break;
				case 90:minDistance=d_n1_n7;break;
				case 91:minDistance=d_n1_n6;break;
				case 92:minDistance=d_n1_n5;break;
				case 93:minDistance=d_n1_n4;break;
				case 94:minDistance=d_n1_n3;break;
				case 95:minDistance=d_n1_n2;break;
				case 96:minDistance=d_n1_n1;break;
				case 97:minDistance=d_n1_0;break;
				case 98:minDistance=d_n1_1;break;
				case 99:minDistance=d_n1_2;break;
				case 100:minDistance=d_n1_3;break;
				case 101:minDistance=d_n1_4;break;
				case 102:minDistance=d_n1_5;break;
				case 103:minDistance=d_n1_6;break;
				case 104:minDistance=d_n1_7;break;
				case 105:minDistance=d_0_n7;break;
				case 106:minDistance=d_0_n6;break;
				case 107:minDistance=d_0_n5;break;
				case 108:minDistance=d_0_n4;break;
				case 109:minDistance=d_0_n3;break;
				case 110:minDistance=d_0_n2;break;
				case 111:minDistance=d_0_n1;break;
				case 112:minDistance=d_0_0;break;
				case 113:minDistance=d_0_1;break;
				case 114:minDistance=d_0_2;break;
				case 115:minDistance=d_0_3;break;
				case 116:minDistance=d_0_4;break;
				case 117:minDistance=d_0_5;break;
				case 118:minDistance=d_0_6;break;
				case 119:minDistance=d_0_7;break;
				case 120:minDistance=d_1_n7;break;
				case 121:minDistance=d_1_n6;break;
				case 122:minDistance=d_1_n5;break;
				case 123:minDistance=d_1_n4;break;
				case 124:minDistance=d_1_n3;break;
				case 125:minDistance=d_1_n2;break;
				case 126:minDistance=d_1_n1;break;
				case 127:minDistance=d_1_0;break;
				case 128:minDistance=d_1_1;break;
				case 129:minDistance=d_1_2;break;
				case 130:minDistance=d_1_3;break;
				case 131:minDistance=d_1_4;break;
				case 132:minDistance=d_1_5;break;
				case 133:minDistance=d_1_6;break;
				case 134:minDistance=d_1_7;break;
				case 135:minDistance=d_2_n7;break;
				case 136:minDistance=d_2_n6;break;
				case 137:minDistance=d_2_n5;break;
				case 138:minDistance=d_2_n4;break;
				case 139:minDistance=d_2_n3;break;
				case 140:minDistance=d_2_n2;break;
				case 141:minDistance=d_2_n1;break;
				case 142:minDistance=d_2_0;break;
				case 143:minDistance=d_2_1;break;
				case 144:minDistance=d_2_2;break;
				case 145:minDistance=d_2_3;break;
				case 146:minDistance=d_2_4;break;
				case 147:minDistance=d_2_5;break;
				case 148:minDistance=d_2_6;break;
				case 149:minDistance=d_2_7;break;
				case 151:minDistance=d_3_n6;break;
				case 152:minDistance=d_3_n5;break;
				case 153:minDistance=d_3_n4;break;
				case 154:minDistance=d_3_n3;break;
				case 155:minDistance=d_3_n2;break;
				case 156:minDistance=d_3_n1;break;
				case 157:minDistance=d_3_0;break;
				case 158:minDistance=d_3_1;break;
				case 159:minDistance=d_3_2;break;
				case 160:minDistance=d_3_3;break;
				case 161:minDistance=d_3_4;break;
				case 162:minDistance=d_3_5;break;
				case 163:minDistance=d_3_6;break;
				case 166:minDistance=d_4_n6;break;
				case 167:minDistance=d_4_n5;break;
				case 168:minDistance=d_4_n4;break;
				case 169:minDistance=d_4_n3;break;
				case 170:minDistance=d_4_n2;break;
				case 171:minDistance=d_4_n1;break;
				case 172:minDistance=d_4_0;break;
				case 173:minDistance=d_4_1;break;
				case 174:minDistance=d_4_2;break;
				case 175:minDistance=d_4_3;break;
				case 176:minDistance=d_4_4;break;
				case 177:minDistance=d_4_5;break;
				case 178:minDistance=d_4_6;break;
				case 182:minDistance=d_5_n5;break;
				case 183:minDistance=d_5_n4;break;
				case 184:minDistance=d_5_n3;break;
				case 185:minDistance=d_5_n2;break;
				case 186:minDistance=d_5_n1;break;
				case 187:minDistance=d_5_0;break;
				case 188:minDistance=d_5_1;break;
				case 189:minDistance=d_5_2;break;
				case 190:minDistance=d_5_3;break;
				case 191:minDistance=d_5_4;break;
				case 192:minDistance=d_5_5;break;
				case 198:minDistance=d_6_n4;break;
				case 199:minDistance=d_6_n3;break;
				case 200:minDistance=d_6_n2;break;
				case 201:minDistance=d_6_n1;break;
				case 202:minDistance=d_6_0;break;
				case 203:minDistance=d_6_1;break;
				case 204:minDistance=d_6_2;break;
				case 205:minDistance=d_6_3;break;
				case 206:minDistance=d_6_4;break;
				case 215:minDistance=d_7_n2;break;
				case 216:minDistance=d_7_n1;break;
				case 217:minDistance=d_7_0;break;
				case 218:minDistance=d_7_1;break;
				case 219:minDistance=d_7_2;break;
				default:System.out.printf("Error in DPNavigator: unexpected encoded value 219",(7-dctx)*15+7-dcty);break;
			}
		}
		else
		{
			minDistance = Math.min(minDistance, d_n7_n2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-7 + dctx), Math.abs(-2 + dcty)));
			minDistance = Math.min(minDistance, d_n7_n1 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-7 + dctx), Math.abs(-1 + dcty)));
			minDistance = Math.min(minDistance, d_n7_0 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-7 + dctx), Math.abs(0 + dcty)));
			minDistance = Math.min(minDistance, d_n7_1 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-7 + dctx), Math.abs(1 + dcty)));
			minDistance = Math.min(minDistance, d_n7_2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-7 + dctx), Math.abs(2 + dcty)));
			minDistance = Math.min(minDistance, d_n6_n4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-6 + dctx), Math.abs(-4 + dcty)));
			minDistance = Math.min(minDistance, d_n6_n3 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-6 + dctx), Math.abs(-3 + dcty)));
			minDistance = Math.min(minDistance, d_n6_n2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-6 + dctx), Math.abs(-2 + dcty)));
			minDistance = Math.min(minDistance, d_n6_2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-6 + dctx), Math.abs(2 + dcty)));
			minDistance = Math.min(minDistance, d_n6_3 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-6 + dctx), Math.abs(3 + dcty)));
			minDistance = Math.min(minDistance, d_n6_4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-6 + dctx), Math.abs(4 + dcty)));
			minDistance = Math.min(minDistance, d_n5_n5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-5 + dctx), Math.abs(-5 + dcty)));
			minDistance = Math.min(minDistance, d_n5_n4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-5 + dctx), Math.abs(-4 + dcty)));
			minDistance = Math.min(minDistance, d_n5_4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-5 + dctx), Math.abs(4 + dcty)));
			minDistance = Math.min(minDistance, d_n5_5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-5 + dctx), Math.abs(5 + dcty)));
			minDistance = Math.min(minDistance, d_n4_n6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-4 + dctx), Math.abs(-6 + dcty)));
			minDistance = Math.min(minDistance, d_n4_n5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-4 + dctx), Math.abs(-5 + dcty)));
			minDistance = Math.min(minDistance, d_n4_5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-4 + dctx), Math.abs(5 + dcty)));
			minDistance = Math.min(minDistance, d_n4_6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-4 + dctx), Math.abs(6 + dcty)));
			minDistance = Math.min(minDistance, d_n3_n6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-3 + dctx), Math.abs(-6 + dcty)));
			minDistance = Math.min(minDistance, d_n3_6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-3 + dctx), Math.abs(6 + dcty)));
			minDistance = Math.min(minDistance, d_n2_n7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-2 + dctx), Math.abs(-7 + dcty)));
			minDistance = Math.min(minDistance, d_n2_n6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-2 + dctx), Math.abs(-6 + dcty)));
			minDistance = Math.min(minDistance, d_n2_6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-2 + dctx), Math.abs(6 + dcty)));
			minDistance = Math.min(minDistance, d_n2_7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-2 + dctx), Math.abs(7 + dcty)));
			minDistance = Math.min(minDistance, d_n1_n7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-1 + dctx), Math.abs(-7 + dcty)));
			minDistance = Math.min(minDistance, d_n1_7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(-1 + dctx), Math.abs(7 + dcty)));
			minDistance = Math.min(minDistance, d_0_n7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(0 + dctx), Math.abs(-7 + dcty)));
			minDistance = Math.min(minDistance, d_0_7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(0 + dctx), Math.abs(7 + dcty)));
			minDistance = Math.min(minDistance, d_1_n7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(1 + dctx), Math.abs(-7 + dcty)));
			minDistance = Math.min(minDistance, d_1_7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(1 + dctx), Math.abs(7 + dcty)));
			minDistance = Math.min(minDistance, d_2_n7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(2 + dctx), Math.abs(-7 + dcty)));
			minDistance = Math.min(minDistance, d_2_n6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(2 + dctx), Math.abs(-6 + dcty)));
			minDistance = Math.min(minDistance, d_2_6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(2 + dctx), Math.abs(6 + dcty)));
			minDistance = Math.min(minDistance, d_2_7 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(2 + dctx), Math.abs(7 + dcty)));
			minDistance = Math.min(minDistance, d_3_n6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(3 + dctx), Math.abs(-6 + dcty)));
			minDistance = Math.min(minDistance, d_3_6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(3 + dctx), Math.abs(6 + dcty)));
			minDistance = Math.min(minDistance, d_4_n6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(4 + dctx), Math.abs(-6 + dcty)));
			minDistance = Math.min(minDistance, d_4_n5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(4 + dctx), Math.abs(-5 + dcty)));
			minDistance = Math.min(minDistance, d_4_5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(4 + dctx), Math.abs(5 + dcty)));
			minDistance = Math.min(minDistance, d_4_6 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(4 + dctx), Math.abs(6 + dcty)));
			minDistance = Math.min(minDistance, d_5_n5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(5 + dctx), Math.abs(-5 + dcty)));
			minDistance = Math.min(minDistance, d_5_n4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(5 + dctx), Math.abs(-4 + dcty)));
			minDistance = Math.min(minDistance, d_5_4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(5 + dctx), Math.abs(4 + dcty)));
			minDistance = Math.min(minDistance, d_5_5 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(5 + dctx), Math.abs(5 + dcty)));
			minDistance = Math.min(minDistance, d_6_n4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(6 + dctx), Math.abs(-4 + dcty)));
			minDistance = Math.min(minDistance, d_6_n3 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(6 + dctx), Math.abs(-3 + dcty)));
			minDistance = Math.min(minDistance, d_6_n2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(6 + dctx), Math.abs(-2 + dcty)));
			minDistance = Math.min(minDistance, d_6_2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(6 + dctx), Math.abs(2 + dcty)));
			minDistance = Math.min(minDistance, d_6_3 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(6 + dctx), Math.abs(3 + dcty)));
			minDistance = Math.min(minDistance, d_6_4 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(6 + dctx), Math.abs(4 + dcty)));
			minDistance = Math.min(minDistance, d_7_n2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(7 + dctx), Math.abs(-2 + dcty)));
			minDistance = Math.min(minDistance, d_7_n1 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(7 + dctx), Math.abs(-1 + dcty)));
			minDistance = Math.min(minDistance, d_7_0 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(7 + dctx), Math.abs(0 + dcty)));
			minDistance = Math.min(minDistance, d_7_1 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(7 + dctx), Math.abs(1 + dcty)));
			minDistance = Math.min(minDistance, d_7_2 + UPPER_BOUND_TURN_PER_GRID * Math.max(Math.abs(7 + dctx), Math.abs(2 + dcty)));
		}
		if (minDistance >= INF_MARBLE)
			return MoveResult.FAIL;
		switch (minDistance % 10)
		{
			case 1:
				if (rc.canMove(NORTHWEST))
				{
					rc.move(NORTHWEST);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 2:
				if (rc.canMove(NORTH))
				{
					rc.move(NORTH);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 3:
				if (rc.canMove(NORTHEAST))
				{
					rc.move(NORTHEAST);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 4:
				if (rc.canMove(EAST))
				{
					rc.move(EAST);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 5:
				if (rc.canMove(SOUTHEAST))
				{
					rc.move(SOUTHEAST);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 6:
				if (rc.canMove(SOUTH))
				{
					rc.move(SOUTH);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 7:
				if (rc.canMove(SOUTHWEST))
				{
					rc.move(SOUTHWEST);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			case 8:
				if (rc.canMove(WEST))
				{
					rc.move(WEST);
					return MoveResult.SUCCESS;
				}
				else
					return MoveResult.FAIL;
			default:
				return MoveResult.FAIL;
		}
	}
}