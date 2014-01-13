package ch14;

class DistPar{  // items stored in sPath array 
	public int distance;   // distance from start to this vertex
	public int parentVert; // current parent of this vertex
	
	public DistPar(int pv, int d){ 
	   distance = d;
	   parentVert = pv;
	}
} 

class Vertex2{
	public char label;        // label (e.g. 'A')
	public boolean isInTree;
	
	public Vertex2(char lab){
	   label = lab;
	   isInTree = false;
	}
}  

class Graph2{
   private final int MAX_VERTS = 20;
   private final int INFINITY = 1000000;
   private Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private int nTree;           // number of verts in tree
   private DistPar sPath[];     // array for shortest-path data
   private int currentVert;     // current vertex
   private int startToCurrent;  // distance to currentVert
   
   public Graph2(){
	   vertexList = new Vertex[MAX_VERTS];
	                                      // adjacency matrix
	   adjMat = new int[MAX_VERTS][MAX_VERTS];
	   nVerts = 0;
	   nTree = 0;
	   for(int j=0; j<MAX_VERTS; j++)     // set adjacency
	      for(int k=0; k<MAX_VERTS; k++)  //     matrix
	         adjMat[j][k] = INFINITY;     //     to infinity
	   sPath = new DistPar[MAX_VERTS];    // shortest paths
   }
   
   public void addVertex(char lab){
	   vertexList[nVerts++] = new Vertex(lab);
   }
   
   public void addEdge(int start, int end, int weight){
	   adjMat[start][end] = weight;  // (directed)
   }
   
   public void path(){
	   vertexList[0].isInTree = true; // put a vertex in tree
	   nTree++;
	   currentVert = 0;
	   int startTree = 0;
	   
	   for(int i=0; i<nVerts; i++){
		   int tempDist = adjMat[startTree][i];
		   sPath[i] = new DistPar(startTree, tempDist);
	   }
	   
	   while(nTree < nVerts){
		   int minVert = getMin();
		   int minDistance = sPath[minVert].distance;
		   if(minDistance == INFINITY){
			   System.out.println("There are unreachable vertices.");
			   return;
		   }
		   currentVert = minVert;
		   startToCurrent = sPath[minVert].distance;
		   vertexList[minVert].isInTree = true;
		   nTree++;
		   
		   adjust_sPath();
	   }
	   
	   nTree = 0;
	   displayPaths();
	   for(int j=0; j<nVerts; j++)
		   vertexList[j].isInTree = false;
   }
   
   public int getMin(){               // get entry from sPath
	   int minDist = INFINITY;
	   int minVert = 0;
	   for(int i=1; i<nVerts; i++){
		   if( vertexList[i].isInTree == false && sPath[i].distance < minDist){
			   minDist = sPath[i].distance;
			   minVert = i;
		   }
	   }
	   return minVert;
   }
   
   public void adjust_sPath(){
	   for(int i=1; i<nVerts; i++){
		   if(vertexList[i].isInTree == false){
			   int startToFringe = startToCurrent + adjMat[currentVert][i];
			   if(startToFringe < sPath[i].distance){
				   sPath[i].distance = startToFringe;
				   sPath[i].parentVert = currentVert;
			   }
		   }
	   }
   }
   
   public void displayPaths(){
	   for(int j=0; j<nVerts; j++){ // display contents of sPath[]
	      System.out.print(vertexList[j].label + "="); // B=
	      if(sPath[j].distance == INFINITY)
	         System.out.print("inf");                  // inf
	      else
	         System.out.print(sPath[j].distance);      // 50
	      char parent = vertexList[ sPath[j].parentVert ].label;
	      System.out.print("(" + parent + ") ");       // (A)
	   }
	   System.out.println("");
   }
}

public class PathApp {

	public static void main(String[] args){
	    Graph2 theGraph = new Graph2();
	    theGraph.addVertex('A');     // 0  (start)
	    theGraph.addVertex('B');     // 1
	    theGraph.addVertex('C');     // 2
	    theGraph.addVertex('D');     // 3
	    theGraph.addVertex('E');     // 4
	
	    theGraph.addEdge(0, 1, 50);  // AB 50
	    theGraph.addEdge(0, 3, 80);  // AD 80
	    theGraph.addEdge(1, 2, 60);  // BC 60
	    theGraph.addEdge(1, 3, 90);  // BD 90
	    theGraph.addEdge(2, 4, 40);  // CE 40
	    theGraph.addEdge(3, 2, 20);  // DC 20
	    theGraph.addEdge(3, 4, 70);  // DE 70
	    theGraph.addEdge(4, 1, 50);  // EB 50
	
	    System.out.println("Shortest paths");
	    theGraph.path();             // shortest paths
	    System.out.println();
    } 

}
