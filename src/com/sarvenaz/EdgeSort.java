package com.sarvenaz;

import java.util.*;

public class EdgeSort {

}
class EdgeComparator implements Comparator<Edge>
{

	@Override
	public int compare(Edge o1, Edge o2) {
		// TODO Auto-generated method stub
		
		return o1.cost < o2.cost ? -1 : o1.cost > o2.cost ? 1 : 0;
	}
   
}
