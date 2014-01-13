package ch14;

class Edge
{
public int srcVert;   // index of a vertex starting edge
public int destVert;  // index of a vertex ending edge
public int distance;  // distance from src to dest

public Edge(int sv, int dv, int d)  // constructor
   {
   srcVert = sv;
   destVert = dv;
   distance = d;
   }
}  // end class Edge
class Vertex
{
public char label;        // label (e.g. 'A')
public boolean isInTree;

public Vertex(char lab)   // constructor
   {
   label = lab;
   isInTree = false;
   }
}

class Graph{

   private final int MAX_VERTS = 20;
   private final int INFINITY = 1000000;
   private Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private int currentVert;
   private PriorityQ thePQ;
   private int nTree;           // number of verts in tree

   public Graph()               // constructor
      {
      vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      for(int j=0; j<MAX_VERTS; j++)      // set adjacency
         for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
            adjMat[j][k] = INFINITY;
      thePQ = new PriorityQ();
      }  // end constructor
   
   public void addVertex(char lab)
   {
   vertexList[nVerts++] = new Vertex(lab);
   }

   public void addEdge(int start, int end, int weight)
   {
   adjMat[start][end] = weight;
   adjMat[end][start] = weight;
   }

   public void displayVertex(int v)
   {
   System.out.print(vertexList[v].label);
   }
   
   public void mstw(){           // minimum spanning tree
	   currentVert = 0;
	   
	   while(nTree < nVerts -1){
		   vertexList[currentVert].isInTree = true;
		   nTree++;
		   
		   for(int j=0; j<nVerts; j++){
			   if(j == currentVert)
				   continue;
			   if(vertexList[j].isInTree == true)
				   continue;
			   int distance = adjMat[currentVert][j];
			   if(distance == INFINITY)
				   continue;
			   
			   putInPQ(j,distance);
		   }
		   if(thePQ.size()==0){
			   System.out.println(" GRAPH NOT CONNECTED");
			   return;
		   }
		   Edge theEdge = thePQ.removeMin();
		   int sourceVert = theEdge.srcVert;
		   currentVert = theEdge.destVert;
		   
		   System.out.print(vertexList[sourceVert].label);
		   System.out.print(vertexList[currentVert].label + " ");
	   }
	   for(int j=0; j<nVerts; j++) 
		   vertexList[j].isInTree = false;
   }
   
   public void putInPQ(int newVert, int newdis){
	   int queueIndex = thePQ.find(newVert);
	   if(queueIndex == -1){
		   Edge newEdge = new Edge(currentVert, newVert, newdis);
		   thePQ.insert(newEdge);
	   }
	   else{
		   Edge tempEdge = thePQ.peekN(queueIndex);
		   int olddis = tempEdge.distance;
		   if(olddis > newdis){
			   thePQ.removeN(queueIndex);
			   Edge newEdge = new Edge(currentVert, newVert, newdis);
			   thePQ.insert(newEdge);
		   }
	   }
   }
}
public class MstwApp {

	public static void main(String[] args){
	    Graph theGraph = new Graph();
	    theGraph.addVertex('A');    // 0  (start for mst)
	    theGraph.addVertex('B');    // 1
	    theGraph.addVertex('C');    // 2
	    theGraph.addVertex('D');    // 3
	    theGraph.addVertex('E');    // 4
	    theGraph.addVertex('F');    // 5
	
	    theGraph.addEdge(0, 1, 6);  // AB  6
	    theGraph.addEdge(0, 3, 4);  // AD  4
	    theGraph.addEdge(1, 2, 10); // BC 10
	    theGraph.addEdge(1, 3, 7);  // BD  7
	    theGraph.addEdge(1, 4, 7);  // BE  7
	    theGraph.addEdge(2, 3, 8);  // CD  8
	    theGraph.addEdge(2, 4, 5);  // CE  5
	    theGraph.addEdge(2, 5, 6);  // CF  6
	    theGraph.addEdge(3, 4, 12); // DE 12
	    theGraph.addEdge(4, 5, 7);  // EF  7
	
	    System.out.print("Minimum spanning tree: ");
	    theGraph.mstw();            // minimum spanning tree
	    System.out.println();
    }  // end main()

}
