/**
 * Homework Assignment #6: "8-Puzzle"
 *
 *  - Board class for solving "8-Puzzle" Programming Assignment
 *
 *  Compilation:  javac Board.java Queue.java
 *
 * @ Student ID : 20145720
 * @ Name       : Lim Gi-chan
 **/

import java.io.File;
import java.util.Scanner;

public class Board {

	private int[][] tiles;
	private int N;
	private int manhattan;
	private int hamming;

	// construct a board from an N-by-N array of blocks
	// (where blocks[i][j] = block in row i, column j)
	public Board(int[][] blocks) {
		if (blocks == null)
			throw new java.lang.NullPointerException();

		N = blocks.length;
		if (N < 2 || N > 128)
			throw new IllegalArgumentException("N must be <= 128");

		tiles = new int[N][N];
		for (int i = 0; i < N; i++)
			System.arraycopy(blocks[i], 0, tiles[i], 0, blocks[i].length);

		setDistance();
	}

	// board dimension N
	public int dimension() {
		return N;
	}

	public void setDistance() {
		int manhattan = 0;
		int hamming = 0;

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(tiles[i][j] != 0) {
					if(tiles[i][j] != (i * N + j + 1)) {
						hamming++;
					}
					int x = (tiles[i][j] - 1) / N - i;
					int y = (tiles[i][j] - 1) % N - j;
					manhattan += (Math.abs(x) + Math.abs(y));
				}
			}
		}
		this.manhattan = manhattan;
		this.hamming = hamming;
	}

	// sum of Manhattan distances between blocks and goal
	public int getManhattan() {
		return manhattan;
	}
	
	public int getHamming() {
		return hamming;
	}

	// is this board the goal board?
	public boolean isGoal() {
		if(manhattan == 0)
			return true;
		else
			return false;
	}

	private void swap(int r1, int c1, int r2, int c2) {
		if (r1 < 0 || c1 < 0 || r2 < 0 || c2 < 0)
			throw new IndexOutOfBoundsException("row/col index < 0");
		if (r1 >= N || c1 >= N || r2 >= N || c2 >= N)
			throw new IndexOutOfBoundsException("row/col index >= N");

		// swap blocks
		int temp = tiles[r1][c1];
		tiles[r1][c1] = tiles[r2][c2];
		tiles[r2][c2] = temp;
		setDistance();

	}

	// a board that is obtained by exchanging two adjacent blocks in the same row
	public Board twin() {
		Board blocks = new Board(tiles);

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N - 1; j++) {
				if(blocks.tiles[i][j] != 0 && blocks.tiles[i][j + 1] != 0) {
					blocks.swap(i, j, i, j + 1);
					return blocks;
				}
			}
		}
		return blocks;
	}

	// does this board equal y?
	public boolean equals(Board y) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(tiles[i][j] != y.tiles[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {

		Queue<Board> nbrs = new Queue<Board>();

		// put all neighbor boards into the queue
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(tiles[i][j] == 0) {
					if(i > 0) {
						Board temp = new Board(tiles);
						temp.swap(i, j, i -1, j);
						nbrs.enqueue(temp);
					}
					if(i < N - 1) {
						Board temp = new Board(tiles);
						temp.swap(i, j, i + 1, j);
						nbrs.enqueue(temp);
					}
					if(j > 0) {
						Board temp = new Board(tiles);
						temp.swap(i, j, i, j - 1);
						nbrs.enqueue(temp);
					}
					if(j < N - 1) {
						Board temp = new Board(tiles);
						temp.swap(i, j, i, j + 1);
						nbrs.enqueue(temp);
					}
					return nbrs;
				}
			}
		}
		return nbrs;
	}

	// string representation of this board (in the output format specified below)
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	// unit tests (DO NOT MODIFY)
	public static void main(String[] args) {
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
				blocks[i][j] = (int)in.nextInt();
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
		Board initial = new Board(blocks);

		System.out.println("\n=== Initial board ===");
		System.out.println(" - manhattan = " + initial.getManhattan());
		System.out.println(initial.toString());

		Board twin = initial.twin();

		System.out.println("\n=== Twin board ===");
		System.out.println(" - manhattan = " + twin.getManhattan());
		System.out.println(" - equals = " + initial.equals(twin));
		System.out.println(twin.toString());

		System.out.println("\n=== Neighbors ===");
		for (Board board : initial.neighbors())
			System.out.println(board);

		in.close();
	}
}

