
package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Game;

public class MoveLogic {


	public static boolean isValidMove(Game game,
			int startIndex, int endIndex) {
		return game == null? false : isValidMove(game.getBoard(),
				game.isP1Turn(), startIndex, endIndex, game.getSkipIndex());
	}

	public static boolean isValidMove(Board board, boolean isP1Turn,
			int startIndex, int endIndex, int skipIndex) {
		
		// Basic checks
		if (board == null || !Board.isValidIndex(startIndex) ||
				!Board.isValidIndex(endIndex)) {
			return false;
		} else if (startIndex == endIndex) {
			return false;
		} else if (Board.isValidIndex(skipIndex) && skipIndex != startIndex) {
			return false;
		}
		
		// Perform the tests to validate the move
		if (!validateIDs(board, isP1Turn, startIndex, endIndex)) {
			return false;
		} else if (!validateDistance(board, isP1Turn, startIndex, endIndex)) {
			return false;
		}
		
		// Passed all tests
		return true;
	}
	

	private static boolean validateIDs(Board board, boolean isP1Turn,
			int startIndex, int endIndex) {
		
		// Check if end is clear
		if (board.get(endIndex) != Board.EMPTY) {
			return false;
		}
		
		// Check if proper ID
		int id = board.get(startIndex);
		if ((isP1Turn && !Board.isBlackChecker(id))
				|| (!isP1Turn && !Board.isWhiteChecker(id))) {
			return false;
		}
		
		// Check the middle
		Point middle = Board.middle(startIndex, endIndex);
		int midID = board.get(Board.toIndex(middle));
		if (midID != Board.INVALID && ((!isP1Turn &&
				!Board.isBlackChecker(midID)) ||
				(isP1Turn && !Board.isWhiteChecker(midID)))) {
			return false;
		}
		
		// Passed all tests
		return true;
	}
	

	private static boolean validateDistance(Board board, boolean isP1Turn,
			int startIndex, int endIndex) {
		
		// Check that it was a diagonal move
		Point start = Board.toPoint(startIndex);
		Point end = Board.toPoint(endIndex);
		int dx = end.x - start.x;
		int dy = end.y - start.y;
		if (Math.abs(dx) != Math.abs(dy) || Math.abs(dx) > 2 || dx == 0) {
			return false;
		}
		
		// Check that it was in the right direction
		int id = board.get(startIndex);
		if ((id == Board.WHITE_CHECKER && dy > 0) ||
				(id == Board.BLACK_CHECKER && dy < 0)) {
			return false;
		}
		
		// Check that if this is not a skip, there are none available
		Point middle = Board.middle(startIndex, endIndex);
		int midID = board.get(Board.toIndex(middle));
		if (midID < 0) {
			
			// Get the correct checkers
			List<Point> checkers;
			if (isP1Turn) {
				checkers = board.find(Board.BLACK_CHECKER);
				checkers.addAll(board.find(Board.BLACK_KING));
			} else {
				checkers = board.find(Board.WHITE_CHECKER);
				checkers.addAll(board.find(Board.WHITE_KING));
			}
			
			// Check if any of them have a skip available
			for (Point p : checkers) {
				int index = Board.toIndex(p);
				if (!MoveGenerator.getSkips(board, index).isEmpty()) {
					return false;
				}
			}
		}
		
		// Passed all tests
		return true;
	}
	

	public static boolean isSafe(Board board, Point checker) {
		
		// Trivial cases
		if (board == null || checker == null) {
			return true;
		}
		int index = Board.toIndex(checker);
		if (index < 0) {
			return true;
		}
		int id = board.get(index);
		if (id == Board.EMPTY) {
			return true;
		}
		
		// Determine if it can be skipped
		boolean isBlack = Board.isBlackChecker(id);
		List<Point> check = new ArrayList<>();
		MoveGenerator.addPoints(check, checker, Board.BLACK_KING, 1);
		for (Point p : check) {
			int start = Board.toIndex(p);
			int tid = board.get(start);
			
			// Nothing here
			if (tid == Board.EMPTY || tid == Board.INVALID) {
				continue;
			}
			
			// Check ID
			boolean isWhite = Board.isWhiteChecker(tid);
			if (isBlack && !isWhite) {
				continue;
			}
			
			// Determine if valid skip direction
			int dx = (checker.x - p.x) * 2;
			int dy = (checker.y - p.y) * 2;
			if (!Board.isKingChecker(tid) && (isWhite ^ (dy < 0))) {
				continue;
			}
			int endIndex = Board.toIndex(new Point(p.x + dx, p.y + dy));
			if (MoveGenerator.isValidSkip(board, start, endIndex)) {
				return false;
			}
		}
		
		return true;
	}
}
