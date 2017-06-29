package com.sarvenaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GUI extends JFrame{
	
	JLabel nodelabel;
	JLabel filelabel;
	JLabel algolabel;
	
	JRadioButton kruskal;
	JRadioButton primes;
	
	JComboBox nodecombo;
	JComboBox filecombo;
	
	JButton startbtn;
	JButton stepbtn;
	JButton resetbtn;
	
	JTextArea outputtext;
	
	JScrollPane scroll;
	
	static int n ;
	String temp;
	String filename;
	
    static int EdgeArray[][];
	static int minEdges[];
	int i;
	int c;
	int total;
	
	public GUI() {
		super("Minimum Spanning Tree");
		setLayout(new BorderLayout());
		
		JPanel leftp = new JPanel();
		leftp.setLayout(new BorderLayout());
		add(leftp, BorderLayout.WEST);
		
		JPanel rightp = new JPanel();
		add(rightp,BorderLayout.EAST);
		
		JPanel inputpanel = new JPanel();
		inputpanel.setPreferredSize(new Dimension(540,100));
		leftp.add(inputpanel, BorderLayout.NORTH);
		inputpanel.setLayout(null);
		
		algolabel = new JLabel("Minimum Spanning Tree Algorithm:");
		algolabel.setSize(200, 15);
		algolabel.setLocation(30, 15);
		inputpanel.add(algolabel);
		
		kruskal = new JRadioButton("Kruskal",true);
		kruskal.setSize(100, 20);
		kruskal.setLocation(270, 15);
		inputpanel.add(kruskal);
		
		primes = new JRadioButton("Prime's");
		primes.setSize(100, 20);
		primes.setLocation(400, 15);
		inputpanel.add(primes);
		
		nodelabel = new JLabel("Number of Nodes:");
		nodelabel.setSize(120,20);
		nodelabel.setLocation(30, 40);
		inputpanel.add(nodelabel);
		
		nodecombo = new JComboBox();
		nodecombo.setSize(150,25);
		nodecombo.setLocation(200, 40);
		nodecombo.addItem("10");
		nodecombo.addItem("20");
		nodecombo.addItem("50");
		nodecombo.addItem("100");
		nodecombo.addItem("1000");
		inputpanel.add(nodecombo);
		
		filelabel = new JLabel("File Name");
		filelabel.setSize(120,20);
		filelabel.setLocation(30, 70);
		inputpanel.add(filelabel);
		
		filecombo = new JComboBox();
		filecombo.setSize(300,25);
		filecombo.setLocation(200, 70);
		filecombo.addItem("AdjacencyMatrix_of_Graph_G_N_10.txt");
		filecombo.addItem("AdjacencyMatrix_of_Graph_G_N_20.txt");
		filecombo.addItem("AdjacencyMatrix_of_Graph_G_N_50.txt");
		filecombo.addItem("AdjacencyMatrix_of_Graph_G_N_100.txt");
		filecombo.addItem("AdjacencyMatrix_of_Graph_G_N_1000.txt");
		inputpanel.add(filecombo);
		
		JPanel buttonpanel = new JPanel();
		rightp.add(buttonpanel);
		buttonpanel.setPreferredSize(new Dimension(150,500));
		add(buttonpanel,BorderLayout.EAST);
		
		startbtn = new JButton("Start");
		startbtn.setPreferredSize(new Dimension(100,50));
		buttonpanel.add(startbtn);
		
		stepbtn = new JButton("Step");
		stepbtn.setPreferredSize(new Dimension(100,50));
		buttonpanel.add(stepbtn);
		
		resetbtn = new JButton("Reset");
		resetbtn.setPreferredSize(new Dimension(100,50));
		buttonpanel.add(resetbtn);
		
		JPanel outputpanel = new JPanel();
		outputpanel.setPreferredSize(new Dimension(540,350));
		outputpanel.setLayout(null);
		leftp.add(outputpanel, BorderLayout.SOUTH);
		
		outputtext = new JTextArea();
		scroll = new JScrollPane(outputtext);
		scroll.setSize(520,320);
		scroll.setLocation(10,10);
		outputpanel.add(scroll);

		JOptionPane.showMessageDialog(null, "Please select number of nodes, file's name, algorithm and press Start");
		
		nodecombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				temp = nodecombo.getSelectedItem().toString();
				n = Integer.parseInt(temp);
			}
			
		});
				
		filecombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filename = filecombo.getSelectedItem().toString();
			}
			
		});
		Handler handler = new Handler();
		startbtn.addActionListener(handler);
		
		eventHandler ehandler = new eventHandler();
		stepbtn.addActionListener(ehandler);
		
		resethandler rhandler = new resethandler();
		resetbtn.addActionListener(rhandler);
	}
	
	private class resethandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			outputtext.setText(null);
			
		}
		
	}
	private class eventHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
				Handler h = new Handler();
				h.dataset();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
				outputtext.append("(" + EdgeArray[c][0] +"," + EdgeArray[c][1] + ")   edge cost: " + minEdges[c] + "\r\n" );
				c++;	
				if(c==n-1){
					outputtext.append("Total cost of minimum spanning tree is= Sum of (");
					 for(int i=0; i<n-1;i++){
                         outputtext.append(minEdges[i] + "+");
                         total += minEdges[i];
                     };
                     outputtext.append(") = " + total);
                     JOptionPane.showMessageDialog(null, "Minimum Spanning Tree creation is Completed and output file is created");
				}
				Output o = new Output();
				o.createFile(n);
				o.writeFile(n, EdgeArray, minEdges);
				o.closeFile();
		}
		
	}

	private class Handler implements ActionListener{

		public void actionPerformed(ActionEvent e)  {
			
			outputtext.setText(null);
			outputtext.append("Total number of nodes = " + n + "\n");
			outputtext.append("Total number of edges in the minimum spanning tree = " + (n-1) + "\r\n");
			outputtext.append("List of edges & their costs:\r\n");
			
			JOptionPane.showMessageDialog(null, "Press Step to observe the spanning tree creation process of the graph");
			
			c=0;

		}
		
			public void dataset() throws FileNotFoundException{
			List<Edge> edges = new LinkedList<Edge>();
			int[][] matrix = new int[n][n];
			
			EdgeArray = new int[n - 1][2];
			minEdges = new int[n - 1];
			
			String path = "src/com/sarvenaz/" + filename;
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
			DisjointSet ds = new DisjointSet(n);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i != j && matrix[i][j] != 0) {
						Edge ed = new Edge();
						ed.node1 = i;
						ed.node2 = j;
						ed.cost = matrix[i][j];
						matrix[j][i] = 0;
						edges.add(ed);
					}
				}
			}
			Collections.sort(edges, new EdgeComparator());

			for (Edge ed : edges) {
				if (ds.find(ed.node1) != ds.find(ed.node2)) {
					EdgeArray[i][0] = ed.node1;
					EdgeArray[i][1] = ed.node2;
					minEdges[i] = ed.cost;
					i++;
					ds.union(ed.node1, ed.node2);
				}
		}

			}
		
		
	}
}




