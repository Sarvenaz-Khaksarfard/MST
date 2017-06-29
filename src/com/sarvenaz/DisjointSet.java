package com.sarvenaz;

public class DisjointSet {

	int[] parent;
	int[] depth;
			
	public DisjointSet(int n) {
		
		parent = new int[n];
		depth = new int[n];
		
		for(int i=0; i<n; i++){
			parent[i]=i;
			depth[i] = 0;
		}
	}

	public int find(int n){
		if(parent[n] == n)
			return n;
		else
			return find(parent[n]);
		
	}
	
	public void union(int n1, int n2){
            if (depth[find(n1)] < depth[find(n2)])
                parent[find(n1)] = find(n2);
                else if (depth[find(n1)] > depth[find(n2)])
                parent[find(n2)] = find(n1);
            else {
                parent[find(n2)] = find(n1);
                depth[find(n1)]++;
          
        }
	}
}
