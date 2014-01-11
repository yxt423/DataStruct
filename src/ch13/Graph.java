package ch13;

class Vertex
{
	public char label;        // label (e.g. 'A')
	public boolean wasVisited;
	
	public Vertex(char lab){   // constructor
	   label = lab;
	   wasVisited = false;
   }
}

public class Graph {
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][];      // adjacency matrix
	private int nVerts;          // current number of vertices
	private StackX theStack;     // used in dfs
	private Queue theQueue;      // used in bfs
	
    public Graph(){              // constructor
      vertexList = new Vertex[MAX_VERTS];
                                          // adjacency matrix
      adjMat = new int[MAX_VERTS][MAX_VERTS];
      nVerts = 0;
      for(int j=0; j<MAX_VERTS; j++)      // set adjacency
         for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
            adjMat[j][k] = 0;
      theStack = new StackX();
      theQueue = new Queue();
    }  // end constructor
    
    public void dfs(){  // depth-first search
    	vertexList[0].wasVisited = true;
    	displayVertex(0);
    	theStack.push(0);
    	
    	while(!theStack.isEmpty()){
    		int v = getAdjUnvisitedVertex(theStack.peek());
    		if(v == -1){
    			theStack.pop();
    		}
    		else{
    			vertexList[v].wasVisited = true;
    			theStack.push(v);
    			System.out.print(vertexList[v].label);
    		}
    	}
    	
    	// stack is empty, so we're done
    	for(int j=0; j<nVerts; j++)	// reset flags
    		vertexList[j].wasVisited = false;
    }
    
    public int getAdjUnvisitedVertex(int v){
    	for(int i = 0; i < nVerts; i++){
    		if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false)
    			return i;
    	}
    	return -1;
    }
    
    public void bfs(){                   // breadth-first search
    	vertexList[0].wasVisited = true;
    	displayVertex(0);
    	theQueue.insert(0);
    	int v2;
    	
    	while(!theQueue.isEmpty()){
    		int v1 = theQueue.remove();
    		for(int i=0; i<nVerts; i++){
    			if(adjMat[v1][i] == 1 && vertexList[i].wasVisited == false){
    				vertexList[i].wasVisited = true;
    				displayVertex(i);
    				theQueue.insert(i);
    			}
    		}
    	}
    	
    	for(int j=0; j<nVerts; j++)
    		vertexList[j].wasVisited = false;
    }
    public void addVertex(char lab){
    	vertexList[nVerts++] = new Vertex(lab);
    }
    
    public void addEdge(int start, int end){
    	adjMat[start][end] = 1;
    	adjMat[end][start] = 1;
    }
    
    public void displayVertex(int v){
    	System.out.print(vertexList[v].label);
    }
}
