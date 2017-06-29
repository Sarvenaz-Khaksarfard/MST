package com.sarvenaz;

import java.io.*;
import java.util.*;
import java.lang.*;

public class Output {
private Formatter output;
	
	public void createFile(int nodesNumber){
		try{
			output = new Formatter("Result_of_Graph_G_N_" + nodesNumber + ".txt");			
		}
		catch(Exception e){
			System.out.println("An error happened while creating the output file");
		}
	}
	
	public void writeFile(int nodesNumber, int a[][], int b[] ){
		
		int total = 0;
		output.format("%s%d\r\n", "Total number of nodes = ", nodesNumber);
		output.format("%s%d\r\n", "Total number of edges in the minimum spanning three = ", nodesNumber-1);
		output.format("%s\r\n", "List of edges & their costs:");
		
		for(int i = 0; i < nodesNumber - 1; i++){
			output.format("%s%d%s%d%s%d\r\n", "(", a[i][0],",",a[i][1], ")   edge cost: ", b[i] );
		}
		
		output.format("%s", "Total cost of minimum spanning three is= Sum of (");
		
		for(int j = 0; j < nodesNumber - 1; j++){
			output.format("%d%s", b[j], "+");
			total+= b[j];
		}
		output.format("%s%d\r\n", ")=", total);

	}
	
	public void closeFile(){
		output.close();
	}
}
