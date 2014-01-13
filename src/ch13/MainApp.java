package ch13;

public class MainApp {

	public static void main(String[] args){
		/* searches in graph
	    Graph theGraph = new Graph();
	    theGraph.addVertex('A');    // 0  (start for bfs)
	    theGraph.addVertex('B');    // 1
	    theGraph.addVertex('C');    // 2
	    theGraph.addVertex('D');    // 3
	    theGraph.addVertex('E');    // 4
	
	    theGraph.addEdge(0, 1);     // AB
	    theGraph.addEdge(1, 2);     // BC
	    theGraph.addEdge(0, 3);     // AD
	    theGraph.addEdge(3, 4);     // DE
	
	    System.out.print("depth-first visits: ");
	    theGraph.dfs();             // depth-first search
	    System.out.println();
	    
	      System.out.print("breadth-first visits: ");
	      theGraph.bfs();             // breadth-first search
	      System.out.println();
	      */
		

	      Graph theGraph = new Graph();
	      theGraph.addVertex('A');    // 0  (start for mst)
	      theGraph.addVertex('B');    // 1
	      theGraph.addVertex('C');    // 2
	      theGraph.addVertex('D');    // 3
	      theGraph.addVertex('E');    // 4

	      theGraph.addEdge(0, 1);     // AB
	      theGraph.addEdge(0, 2);     // AC
	      theGraph.addEdge(0, 3);     // AD
	      theGraph.addEdge(0, 4);     // AE
	      theGraph.addEdge(1, 2);     // BC
	      theGraph.addEdge(1, 3);     // BD
	      theGraph.addEdge(1, 4);     // BE
	      theGraph.addEdge(2, 3);     // CD
	      theGraph.addEdge(2, 4);     // CE
	      theGraph.addEdge(3, 4);     // DE

	      System.out.print("Minimum spanning tree: ");
	      theGraph.mst();             // minimum spanning tree
	      System.out.println();
    }  // end main()

}
