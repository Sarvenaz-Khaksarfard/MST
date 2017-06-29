package com.sarvenaz;

import java.util.*;
import java.io.*;

public class Logic {

	int n; // number of nodes
    
	int i;

	public void DataSet(int number, String fname, 	int[][] EdgeArray, int[]  minEdges) throws FileNotFoundException{
		List<Edge> edges = new LinkedList<Edge>();
		int[][] matrix = new int[number][number];
		
		EdgeArray = new int[number - 1][2];
		minEdges = new int[number - 1];
		
		String path = "src/com/sarvenaz/" + fname;
		File f = new File(path);
		Scanner matrixfile = new Scanner(f);
		String line;
		
		i=0;
		int x = 0;
		while (matrixfile.hasNext()) {
			line = matrixfile.nextLine();
			StringTokenizer st = new StringTokenizer(line, "    ");
			int[] numbers = new int[st.countTokens()];
			int temp = st.countTokens();

			for (int i = 0; i < temp; i++) {
				matrix[x][i] = Integer.parseInt(st.nextToken());
			}
			x++;
		}
		DisjointSet ds = new DisjointSet(number);

		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				if (i != j && matrix[i][j] != 0) {
					Edge e = new Edge();
					e.node1 = i;
					e.node2 = j;
					e.cost = matrix[i][j];
					matrix[j][i] = 0;
					edges.add(e);
				}
			}
		}
		Collections.sort(edges, new EdgeComparator());

		for (Edge e : edges) {
			if (ds.find(e.node1) != ds.find(e.node2)) {
				EdgeArray[i][0] = e.node1;
				EdgeArray[i][1] = e.node2;
				minEdges[i] = e.cost;
				i++;
				ds.union(e.node1, e.node2);
			}
	}
}
}
