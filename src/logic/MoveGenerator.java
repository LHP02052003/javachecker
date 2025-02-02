package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Board;

public class MoveGenerator {

	public static List<Point> getMoves(Board board, Point start) {
		return getMoves(board, Board.toIndex(start));
	}

	public static List<Point> getMoves(Board board, int startIndex) {
		
		// Trivial cases
		List<Point> endPoints = new ArrayList<>();
		if (board == null || !Board.isValidIndex(startIndex)) {
			return endPoints;
		}
		
		// Determine possible points
		int id = board.get(startIndex);
		Point p = Board.toPoint(startIndex);
		addPoints(endPoints, p, id, 1);
		
		// Remove invalid points
		for (int i = 0; i < endPoints.size(); i ++) {
			Point end = endPoints.get(i);
			if (board.get(end.x, end.y) != Board.EMPTY) {
				endPoints.remove(i --);
			}
		}
		
		return endPoints;
	}
	

	public static List<Point> getSkips(Board board, Point start) {
		return getSkips(board, Board.toIndex(start));
	}
	

	public static List<Point> getSkips(Board board, int startIndex) {
		
		// Trivial cases
		List<Point> endPoints = new ArrayList<>();
		if (board == null || !Board.isValidIndex(startIndex)) {
			return endPoints;
		}
		
		// Determine possible points
		int id = board.get(startIndex);
		Point p = Board.toPoint(startIndex);
		addPoints(endPoints, p, id, 2);
		
		// Remove invalid points
		for (int i = 0; i < endPoints.size(); i ++) {
			
			// Check that the skip is valid
			Point end = endPoints.get(i);
			if (!isValidSkip(board, startIndex, Board.toIndex(end))) {
				endPoints.remove(i --);
			}
		}

		return endPoints;
	}
	

	public static boolean isValidSkip(Board board,
			int startIndex, int endIndex) {
		
		if (board == null) {
			return false;
		}

		// Check that end is empty
		if (board.get(endIndex) != Board.EMPTY) {
			return false;
		}
		
		// Check that middle is enemy
		int id = board.get(startIndex);
		int midID = board.get(Board.toIndex(Board.middle(startIndex, endIndex)));
		if (id == Board.INVALID || id == Board.EMPTY) {
			return false;
		} else if (midID == Board.INVALID || midID == Board.EMPTY) {
			return false;
		} else if (Board.isBlackChecker(midID) ^ Board.isWhiteChecker(id)) {
			return false;
		}
		
		return true;
	}
	

	public static void addPoints(List<Point> points, Point p, int id, int delta) {
		
		// Add points moving down
		boolean isKing = Board.isKingChecker(id);
		if (isKing || id == Board.BLACK_CHECKER) {
			points.add(new Point(p.x + delta, p.y + delta));
			points.add(new Point(p.x - delta, p.y + delta));
		}
		
		// Add points moving up
		if (isKing || id == Board.WHITE_CHECKER) {
			points.add(new Point(p.x + delta, p.y - delta));
			points.add(new Point(p.x - delta, p.y - delta));
		}
	}
}
