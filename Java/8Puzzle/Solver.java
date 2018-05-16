/**
 * Homework Assignment #6: "8-Puzzle"
 *
 *  - Solver class for solving "8-Puzzle" Programming Assignment
 *
 *  Compilation:  javac Solver.java Board.java
 *  Execution:    java Solver inputfile.txt
 *  Dependencies: MinPQ
 *
 * @ Student ID : 20145720
 * @ Name       : Lim Gi-chan
 **/

import java.io.File;
import java.util.Scanner;

public class Solver {

	private boolean solved;
	private Node solution;
	/*********************************
	 * YOU CAN ADD MORE HERE
	 *********************************/

	// search node
	private class Node implements Comparable<Node> {
		private Board board;
		private int moves;
		private Node prev;

		public Node(Board board, int moves, Node prev) {
			if (board == null)
				throw new java.lang.NullPointerException();
			this.board = board;
			this.moves = moves;
			this.prev = prev;
		}

		// calculate distance of this search node
		public int distance() {
			return board.getManhattan();
		}

		// calculate priority of this search node
		public int priority() {
			return moves + distance() + board.getHamming();
		}

		// compare node by priority (implements Comparable<Node>)
		public int compareTo(Node that) {
			return this.priority()- that.priority();
		}
	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null)
			throw new java.lang.NullPointerException();

		solved = false;
		solution = null;

		// create initial search node (and it's twin)
		Node initialNode = new Node(initial, 0, null);

		// priority queue
		MinPQ<Node> MinPQ = new MinPQ<Node>();

		// insert the initial search node into a priority queue
		MinPQ.insert(initialNode);
		int distance = initialNode.distance();

		// solve the puzzle
		while(!MinPQ.min().board.isGoal() && !MinPQ.min().board.twin().isGoal()) {
			
			Node node = MinPQ.delMin();

			for (Board neighbor: node.board.neighbors()) {
				if (node.moves == 0) {
					MinPQ.insert(new Node(neighbor, node.moves+1, node));
				}
				else if ((!neighbor.equals(node.prev.board))
						&& ((neighbor.getManhattan() + node.moves+1) <= distance) ) {
					MinPQ.insert(new Node(neighbor, node.moves+1, node));
				}
			}

			if(MinPQ.isEmpty()) {
				MinPQ.insert(initialNode);
				distance +=2;
			}
		}
		if(MinPQ.min().board.isGoal()) {
			solved = true;
			solution = MinPQ.min();
		}

	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return solved;
	}
	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {

		if(!isSolvable())
			return -1;
		return solution.moves;
	}

	// sequence of boards in a intest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (solution == null)
			return null;
		Stack<Board> sol = new Stack<Board>();
		Node searchNode = solution;
		while (searchNode != null) {
			sol.push(searchNode.board);
			searchNode = searchNode.prev;
		}
		return sol;
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {

		// to calculate running time
		long start = System.currentTimeMillis();
		double time;

		// read the input file
		Scanner in;
		try {
			in = new Scanner(new File(args[0]), "UTF-8");
		} catch (Exception e) {
			System.out.println("invalid or no input file ");
			return;
		}

		// create initial board from file
		int N = in.nextInt();
		int[][] blocks = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				blocks[i][j] = in.nextInt();
				if (blocks[i][j] >= N*N) {
					in.close();
					throw new IllegalArgumentException("value must be < N^2");
				}
				if (blocks[i][j] < 0) {
					in.close();
					throw new IllegalArgumentException("value must be >= 0");
				}
			}
		}

		// initial board
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			System.out.println("No solution possible");
		else {
			System.out.println("Minimum number of moves = " + solver.moves() + "\n");
			for (Board board : solver.solution())
				System.out.println(board);
		}

		// calculate running time
		time = (System.currentTimeMillis() - start) / 1000.0;
		System.out.println("time = "+ time + "sec");
		System.out.println("Minimum number of moves = " + solver.moves() + "\n");

		in.close();
	}
}

