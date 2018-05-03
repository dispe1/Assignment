/**
 * Homework Assignment #4: Percolation
 *
 *  - Percolation data structure
 *
 * @ Student ID : 20145720
 * @ Name       : Im Gi-chan
 **/

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Percolation {

    private static final boolean SITE_BLOCKED = false;
    private static final boolean SITE_OPEN = true;
    private boolean[] sites;          // sites[i] = state of site i
    private int mN;                   // remember the input N
    private int topIdx;               // idx of the special top
    private int bottomIdx;            // idx of the speical bottom
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF draw;	//Avoid backflow when visualizer is working.

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("N must be >0");
        
        mN = N;
        topIdx = mN * mN;
        bottomIdx = (mN * mN) + 1;
        sites = new boolean[mN * mN];	//Create mN * mN boolean arrays.
        uf = new WeightedQuickUnionUF(mN * mN + 2);		//Create mN * mN + 2(top,bottom) WQUFs.
        draw = new WeightedQuickUnionUF(mN * mN + 2);	//Create mN * mN + 2(top,bottom) WQUFs.
        
        Arrays.fill(sites, SITE_BLOCKED);		//Initialize with all sites blocked
    }

    private void checkIndex(int i, int j) {
        if (i < 1 || i > mN)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j < 1 || j > mN)
            throw new IndexOutOfBoundsException("column index j out of bounds");
    }

    // open site(row i, column j) if it is not open already
    public void open(int i, int j) {
    	checkIndex(i, j);
    	int index = (i - 1) * mN + (j - 1);
    	
    	if(sites[index] == SITE_BLOCKED ) {		//if site is not open already
    		sites[index] = SITE_OPEN;			//change site to open. 
    		if (i == 1) {						//union to topIdx site when it is the first row.
    			uf.union(index,topIdx);
    			draw.union(index,topIdx);
    		}
    		else if (i == mN) {					//union to bottomIdx site when it is the last row.
    			uf.union(index, bottomIdx);
    		}									
    		
    		if(i > 1 && isOpen(i - 1, j)) {		//union to top site when it isn't first row.
    			uf.union(index, index - mN);
    			draw.union(index, index - mN);
    		}
    		if(i < mN && isOpen(i + 1, j)) {	//union to bottom site when it isn't last row.
    			uf.union(index, index + mN);
    			draw.union(index, index + mN);
    		}
    		if(j > 1 && isOpen(i, j - 1)) {		//union to left site when it isn't first column.
    			uf.union(index, index - 1);
    			draw.union(index, index - 1);
    		}
    		if(j < mN && isOpen(i, j + 1)) {	//union to right site when it isn't last column.
    			uf.union(index, index + 1);
    			draw.union(index, index + 1);
    		}
    	}

    }

    // is site(row i, column j) open?
    public boolean isOpen(int i, int j) {
		checkIndex(i, j);
        return sites[(i - 1) * mN + (j - 1)];
    }

    // is site(row i, column j) full?
    public boolean isFull(int i, int j) {
		checkIndex(i, j);
        return draw.connected(topIdx, (i - 1) * mN + (j - 1));
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topIdx, bottomIdx);   	//Check that the top and bottom are connected.
    }

    // test client(optional)
    public static void main(String[] args)
    {
        Scanner in;
        int N = 0;
        long start = System.currentTimeMillis();

        try {
            // get input file from argument
            in = new Scanner(new File(args[0]), "UTF-8");
        } catch (Exception e) {
            System.out.println("invalid or no input file ");
            return;
        }

        N = in.nextInt();         // N-by-N percolation system
        System.out.printf("N = %d\n", N);

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(N);

        while (in.hasNext()) {
            int i = in.nextInt();   // get i for open site (i,j)
            int j = in.nextInt();   // get j for open site (i,j)
            perc.open(i, j);        // open site (i,j)
            System.out.printf("open(%d, %d)\n", i, j);
        }
        if (perc.percolates()) {
            System.out.println("This system percolates");
        } else {
            System.out.println("This system does NOT percolate");
        }

        double time = (System.currentTimeMillis() - start) / 1000.0;
        System.out.println("running time = "+ time + " sec");
        in.close();
    }
}

