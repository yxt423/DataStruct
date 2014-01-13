package ch13;

public class TopoGraph {
   private final int MAX_VERTS = 20;
   private Vertex vertexList[]; // list of vertices
   private int adjMat[][];      // adjacency matrix
   private int nVerts;          // current number of vertices
   private char sortedArray[];
   
   public TopoGraph(){
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++)      // set adjacency
		for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
		adjMat[j][k] = 0;
		sortedArray = new char[MAX_VERTS];  // sorted vert labels
   }
   
   public void addVertex(char lab)
   {
   vertexList[nVerts++] = new Vertex(lab);
   }
   public void addEdge(int start, int end)
   {
   adjMat[start][end] = 1;
   }
   public void displayVertex(int v)
   {
   System.out.print(vertexList[v].label);
   }
   
   public void topo(){  // toplogical sort
	   int orig_nVerts = nVerts;
	   
	   while(nVerts > 0){
		   int currentVertex = noSuccessor();
		   if(currentVertex == -1){
			   System.out.println("ERROR: Graph has cycles");
			   return;
		   }
		   else{
			   sortedArray[nVerts-1] = vertexList[currentVertex].label;
			   deleteVertex(currentVertex);
		   }
	   }
	   
	   System.out.print("Topologically sorted order: ");
       for(int j=0; j<orig_nVerts; j++)
          System.out.print( sortedArray[j] );
       System.out.println("");
   }
   
   public int noSuccessor(){
	   int col = 0, row = 0;
	   for(col=0; col<nVerts; col++){
		   for(row=0; row<nVerts; row++){
			   if(adjMat[row][col] == 1)
				   break;
		   }
		   if(row == nVerts)
			   return col;
	   }
	   return -1;
   }
   
   // ??
   public int noSuccessors()  // returns vert with no successors
   {                       // (or -1 if no such verts)
   boolean isEdge;  // edge from row to column in adjMat

   for(int row=0; row<nVerts; row++)  // for each vertex,
      {
      isEdge = false;                 // check edges
      for(int col=0; col<nVerts; col++)
         {
         if( adjMat[row][col] > 0 )   // if edge to
            {                         // another,
            isEdge = true;
            break;                    // this vertex
            }                         //    has a successor
         }                            //    try another
      if( !isEdge )                   // if no edges,
         return row;                  //    has no successors
      }
   return -1;                         // no such vertex
   }  // end noSuccessors()
   
   public void deleteVertex(int delVert){
	   if(delVert != nVerts-1){
         for(int j=delVert; j<nVerts-1; j++)
             vertexList[j] = vertexList[j+1];
                                    // delete row from adjMat
          for(int row=delVert; row<nVerts-1; row++)
             moveRowUp(row, nVerts);
                                    // delete col from adjMat
          for(int col=delVert; col<nVerts-1; col++)
             moveColLeft(col, nVerts-1);
	   }
	   nVerts--;
   }
   
   private void moveRowUp(int row, int length){
	   for(int col=0; col<length; col++)
	         adjMat[row][col] = adjMat[row+1][col];
   }
   
   private void moveColLeft(int col, int length){
      for(int row=0; row<length; row++)
          adjMat[row][col] = adjMat[row][col+1];
   }

}
