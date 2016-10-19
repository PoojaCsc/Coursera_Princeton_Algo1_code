package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean[][] grid; // grid of size n * n , true for open and false
								// for closed sites
	private int n;
	private WeightedQuickUnionUF uf; // for percolates method,will be
										// initialized with 2 virtual nodes, to
										// p and bottom
	private WeightedQuickUnionUF uf_b; // for isFull method to avoid backwash ,
										// initialized with only top virtual
										// node

	/*
	 * By backwash problem we mean that the nodes at the bottom though not full
	 * i.e not connected to a top node by a chain of nodes appear full because
	 * they are connected to the bottom virtual node which when gets connected
	 * to upper virtual node indicates that it percolates. So we create two WQUF
	 * objects , one which we initialize with both top and bottom virtual nodes
	 * This we use in the percolates(). The other WQUF object has only the top
	 * virtual node along with other nodes.This we use only in isFull() to avoid
	 * backwash problem. So, whenever we union in open() , we have to do so for
	 * both objects.
	 */

	public Percolation(int n) { // create n-by-n grid, with all sites blocked
		if (n <= 0) 
			throw new IllegalArgumentException();
		grid = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			{
				{
					for (int j = 0; j < n; j++)
						grid[i][j] = false;
				}
			}
		}
		this.n = n;

		/*
		 * We use virtual nodes at top and bottom to avoid multiple calls to
		 * isConnected(). Instead we connect all first row nodes to top virtual
		 * node and all bottom row nodes to bottom virtual node.If top and
		 * bottom virtual nodes are connected, then the system percolates
		 */

		uf = new WeightedQuickUnionUF((n * n) + 2); // initialize this WQUF with
													// both top and bottom
													// virtual nodes
		uf_b = new WeightedQuickUnionUF(n * n + 1);// initialize this one just
													// top virtual node plus
													// other nodes
	}

	private void validArgs(int i, int j) { // method to check if arguments are
											// valid
		if (i < 1 || i > n || j < 1 || j > n) {
			throw new IndexOutOfBoundsException();
		}

	}

	private int xyTo1d(int i, int j) { // method to map a 2d array to a 1d array
										// for union method
		return ((i - 1) * n)
				+ j; /*
						 * used row major format with the formula : (row *
						 * numcol) +col) . i-1 because the user enters node
						 * starting from 1,1 not 0,0
						 */
	}

	public void open(int i, int j) { //

		validArgs(i, j);
		grid[i - 1][j - 1] = true; // i-1 and j-1 coz for user the grid starts
									// from 1,1 not 0,0
		int p = xyTo1d(i, j);

		if (i - 1 == 0) { // for virtual top site
			uf.union(p, 0);
			uf_b.union(p, 0);
		}

		if (i == n) {
			uf.union(p, n * n + 1);
		}

		// connect the open site to adjacent open sites
		if (i > 1 && isOpen(i - 1, j)) {
			uf.union(p, xyTo1d(i - 1, j));
			uf_b.union(p, xyTo1d(i - 1, j));
		}

		if (i < n && isOpen(i + 1, j)) {
			uf.union(p, xyTo1d(i + 1, j));
			uf_b.union(p, xyTo1d(i + 1, j));
		}

		if (j > 1 && isOpen(i, j - 1)) {
			uf.union(p, xyTo1d(i, j - 1));
			uf_b.union(p, xyTo1d(i, j - 1));
		}

		if (j < n && isOpen(i, j + 1)) {
			uf.union(p, xyTo1d(i, j + 1));
			uf_b.union(p, xyTo1d(i, j + 1));
		}
	}

	public boolean isOpen(int i, int j) {
		validArgs(i, j);
		return grid[i - 1][j - 1];
	}

	public boolean isFull(int i, int j) { // A site isFull when it is connected
											// to any of the top row nodes.
		validArgs(i, j);
		int p = xyTo1d(i, j);
		return (uf_b.connected(p, 0));
	}

	public boolean percolates() { // it percolates if top virtual site is
									// connected to bottom virtual site
		return uf.connected(0, n * n + 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Percolation perc = new Percolation(3);
		perc.open(1, 2);
		perc.open(2, 2);
		perc.open(2, 3);
		perc.open(3, 3);
		System.out.println(perc.isFull(3, 3));
		System.out.println(perc.percolates());

	}

}
