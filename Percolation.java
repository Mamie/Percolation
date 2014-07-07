public class Percolation{
 private int N;
 private boolean[] grid;
 private WeightedQuickUnionUF uf;
 private WeightedQuickUnionUF uf1;
 private int sitenum;
 
public Percolation (int N) {
    
    if (N <= 0) throw new IllegalArgumentException("Argument out of bound");
    
    this.N = N;
    sitenum = N*N;
    uf = new WeightedQuickUnionUF(sitenum + 2);  //reserve places for virtual top and bottom
    uf1 = new WeightedQuickUnionUF(sitenum + 1); //a reference for comparison with only virtual top
    grid = new boolean[sitenum];                 //initialize all the sites to be blocked
    
    for (int k = 0; k < N; k++) {                  
        uf.union(sitenum, k);                     //connect first row to the virtual top
        uf1.union(sitenum, k); 
        uf.union(sitenum + 1, sitenum - N + k);   //connect bottom row to the virtual bottom
    }
 }

 private int xyTo1D(int i, int j)
 {
     indexValid(i, j);
     return (i-1) * N + j - 1;                         //convert the coordinate to 1D index
  }
 
private void indexValid(int i, int j)
 {
 if (i <= 0 || i > N) throw new IndexOutOfBoundsException("row i out of bound.");
 if (j <= 0 || j > N) throw new IndexOutOfBoundsException("column j out of bound.");
 }

public void open (int i, int j)
{
 int index = xyTo1D(i, j);
 if (!grid[index]) 
     grid[index] = true;          //open the grid if it is not already open
 
 if (i != 1 && isOpen(i-1, j)) {  //union the open left grid if any
     uf.union(xyTo1D(i-1, j), index);
     uf1.union(xyTo1D(i-1, j), index);
 }
 if (i != N && isOpen(i+1, j)) {  //union the open right grid if any
     uf.union(xyTo1D(i+1,j), index);
     uf1.union(xyTo1D(i+1, j), index);
 }
 if (j != 1 && isOpen(i, j-1)) {  //union the open upper grid if any
     uf.union(xyTo1D(i, j-1), index);
     uf1.union(xyTo1D(i, j-1), index);
 }
 if (j != N && isOpen(i, j+1)) {  //union the open lower grid if any
     uf.union(xyTo1D(i, j+1), index); 
     uf1.union(xyTo1D(i, j+1), index); 
 }
 }

public boolean isOpen (int i, int j) {  //check if a grid is open
    return grid[xyTo1D(i,j)];
 }

public boolean isFull(int i, int j) {  //check if a grid is full
    int index = xyTo1D(i,j);
    return (isOpen(i, j) && uf.connected(sitenum, index) && uf1.connected(sitenum, index));
//isFull is only true when the site is open and connected to virtual top both 
//with and without the existence of virtual bottom.
}

public boolean percolates() {  //check if the system percolates.
    if (N == 1 && isOpen(1, 1) == false) return false;
    return uf.connected(sitenum, sitenum + 1);
}
}