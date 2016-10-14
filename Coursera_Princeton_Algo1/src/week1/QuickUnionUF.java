package week1;

public class QuickUnionUF {    // as trees
	
	private int[] id;
	
	public QuickUnionUF(int N){
		id = new int[N];
		for(int i = 0 ; i < N; i++){
			id[i] = i;
		}
	}
	
	private int root(int i){   // function to find root. id[i] is root of i. When both are equal it means i is only the root
		while(id[i] != i)
			i = id[i];
		return i;
	}
	
	public boolean connected(int p, int q){ // p and q are connected if their root is equal then it means they are in the same component
		return root(p) == root(q);          // Complexity : O(N)
	}
	
	public void union(int p , int q){ // to connect pa and q , change root of q to that of p 
		int proot = root(p);			// Complexity : O(N)
		int qroot = root(q);
		id[q] = proot;
		
	}

}
