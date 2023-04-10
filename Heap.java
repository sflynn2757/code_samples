/**
 *Name: Stephen Flynn
 *PID: A14984805
 *User: cs12sp21bbl
 *File Name: Heap.java
 *Description: This program effectively implements an array based heap
 *data structure with a Driver and UCSDStudent classes, as well as having
 *functionality to insert and remove new Students in a priority queue.
 */
 
 /**
 * Class:            Heap
 * Description:      Implements an array based heap structure with the
 *                   the ability to take in any class extended from Base,
 *                   most usually UCSDStudents. It also contains various
 *                   functionality to insert, remove, and write out the heap.
 *
 * Fields:           root -  the assigned root student of the Heap
 *                   occupancy  - the number of students in the Heap
 *                   representation - for use in other classes and Driver
 *                   tracker - data Tracker object common across assignments
 *                   heapName - the assigned name of the Heap
 *                   finalLeaf - the index of the final leaf of the array
 *                   studentArray - the actual container holding Bases
 *                   maxSize - a constant for maintaining order and max items
 *                   
 *
 * Public functions: Heap - constructor method
 *                   jettison - jettisons all students and the Heap itself 
 *                   getDebug - returns current debug value
 *                   debugOff - turns off debug bool
 *                   debugOn - turns on debug bool
 *                   insert - insert a new Student into the Heap
 *                   isEmpty - check if Heap has Students in it
 *                   ifFull - checks if Heap has max Students in it
 *                   remove - remove and return the root value,
 *                            then restructure
 *                   toString - return a representation of the Heap
 *                              to the console
 */
 public class Heap<Whatever extends Base> {

	// data fields
	private int root;
	private long occupancy; 
	private String heapName;
	private String representation;
	private Tracker tracker;
  private int maxSize;
  private UCSDStudent[] studentArray;
  private int finalLeaf;
	// debug flag
	private static boolean debug;

	// debug messages

	private static final String AND = " and ";
	private static final String CLOSE = "]\n";
	private static final String COMPARE = " - Comparing ";
	private static final String INSERT = " - Inserting ";
	private static final String TREE = "[Heap ";
 
	public Heap (String name, String caller) {

		tracker = new Tracker ("Heap", Size.of (root) 
			+ Size.of (occupancy) 
			+ Size.of (heapName) 
			+ Size.of (representation) 
			+ Size.of (tracker)
      + Size.of (maxSize)
      + Size.of (finalLeaf)
      + Size.of (studentArray),
			caller + " calling Heap Ctor");
		// --------- DO NOT CHANGE ABOVE ---------
		
		//TODO: YOUR CODE GOES HERE
      //max number of storable elements
      maxSize = 0;
      occupancy = 0;
      heapName = name;
      root = 0;
      studentArray = null;
      finalLeaf = 0;
      
	}
/*---------------------------------------------------------------------------
Function Name:       jettison         
Purpose:             to get rid of all students present and then to delete the
Heap object as well.
Description:        Iterates through studentArray to jettison students, 
then jettisons the Heap tracker.
                              
Input:               None 
                              
Output:              None          
Result:              fully jettisons all students and Heap from memory     
Side Effects:        None
---------------------------------------------------------------------------*/
	public void jettison () { 
		  //TODO: YOUR CODE GOES HERE
      if (debug){
          System.err.print("[ Jettisoning " + heapName + "]\n");
      }
      if(!isEmpty()){
          for(int iterator = 0; iterator <= finalLeaf; iterator++){
          studentArray[iterator].jettison();
          studentArray[iterator] = null;
          }
      }
      
      tracker.jettison();
	}
/*------------------------------------------------------------------------
Function Name:      setMaxSize
Purpose:            sets the maxSize data field
Description:        one line method sets maxSize data field
Input:              a long, the max objects user wants the Heap to hold
Output:             none
Result:             a set data field
Side Effects:       none
------------------------------------------------------------------------*/
  public void setMaxSize(long size){
      maxSize = (int)size;
  }
/*------------------------------------------------------------------------
Function Name:      setStudentArray
Purpose:            sets the studentArray data field
Description:        one line method sets studentArray data field
Input:              a long, the numbers of items in the new array
Output:             none
Result:             a set data field
Side Effects:       none
------------------------------------------------------------------------*/ 
  public void setStudentArray(long size){
      studentArray = new UCSDStudent[(int)size];
  }
/*------------------------------------------------------------------------
Function Name:      getParent
Purpose:            gets the parent of a given index
Description:        gets the parent in the studentArray
Input:              index of the studentArray Heap
Output:             the parent of the given index
Result:             returns a parent
Side Effects:       none
------------------------------------------------------------------------*/  
  private UCSDStudent getParent(int index){
      return studentArray[(index - 1) / 2];
  }
/*------------------------------------------------------------------------
Function Name:      getLChild
Purpose:            gets the left child of a given index
Description:        gets the left child in the studentArray
Input:              index of the studentArray Heap
Output:             the left child of the given index
Result:             returns a left child
Side Effects:       none
------------------------------------------------------------------------*/  
  private UCSDStudent getLChild(int index){
      
      return studentArray[(index * 2) + 1];
  }
/*------------------------------------------------------------------------
Function Name:      getRChild
Purpose:            gets the right child of a given index
Description:        gets the right child in the studentArray
Input:              index of the studentArray Heap
Output:             the right child of the given index
Result:             returns a right child
Side Effects:       none
------------------------------------------------------------------------*/  
  private UCSDStudent getRChild(int index){
    
      return studentArray[(index * 2) + 2];
  }
/*------------------------------------------------------------------------
Function Name:      getParentIndex
Purpose:            gets the parent index of a given index
Description:        gets the parent index in the studentArray
Input:              index of the studentArray Heap
Output:             the parent index of the given index
Result:             returns a parent index
Side Effects:       none
------------------------------------------------------------------------*/  
  private int getParentIndex(int index){
      return (index - 1) / 2;
  }
/*------------------------------------------------------------------------
Function Name:      getLChildIndex
Purpose:            gets the left child index of a given index
Description:        gets the left child index in the studentArray
Input:              index of the studentArray Heap
Output:             the left child index of the given index
Result:             returns a left child index
Side Effects:       none
------------------------------------------------------------------------*/  
  private int getLChildIndex(int index){
     
      return (index * 2) + 1;
     
  }
/*------------------------------------------------------------------------
Function Name:      getRChildIndex
Purpose:            gets the right child index of a given index
Description:        gets the right child index in the studentArray
Input:              index of the studentArray Heap
Output:             the right child index of the given index
Result:             returns a right child index
Side Effects:       none
------------------------------------------------------------------------*/  
  private int getRChildIndex(int index){
  
      return (index * 2) + 2;

  }
/*------------------------------------------------------------------------
Function Name:      hasARightChild
Purpose:            determines if an index has a right child
Description:        checks if the right child index has a student
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index has a right child
Side Effects:       none
------------------------------------------------------------------------*/  
  private boolean hasARightChild(int index){
      if(getRChild(index) != null){
          return true;
      }
      
      return false;
  }
/*------------------------------------------------------------------------
Function Name:      hasALeftChild
Purpose:            determines if an index has a left child
Description:        checks if the left child index has a student
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index has a left child
Side Effects:       none
------------------------------------------------------------------------*/  
  private boolean hasALeftChild(int index){
      if(getLChild(index) != null){
          return true;
      }
      
      return false;
  }
/*------------------------------------------------------------------------
Function Name:      hasAParent
Purpose:            determines if an index has a parent
Description:        checks if the parent index has a student
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index has a parent
Side Effects:       none
------------------------------------------------------------------------*/  
  private boolean hasAParent(int index){
      if(getParent(index) != null){
          return true;
      }
      
      return false;
  }
/*------------------------------------------------------------------------
Function Name:      has2Children
Purpose:            checks for both left and right child
Description:        checks both hasLeft and hasRightChild bools
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index has 2 children
Side Effects:       none
------------------------------------------------------------------------*/
  private boolean has2Children(int index){
      return (hasALeftChild(index) && hasARightChild(index));
  }
/*------------------------------------------------------------------------
Function Name:      isAChild
Purpose:            determines if an index is a child
Description:        checks if the index has a parent
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index is a child
Side Effects:       none
------------------------------------------------------------------------*/ 
  private boolean isAChild(int index){
      return hasAParent(index);
  }
/*------------------------------------------------------------------------
Function Name:      isAParent
Purpose:            determines if an index is a parent
Description:        checks if the index has at least 1 child
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index is a parent
Side Effects:       none
------------------------------------------------------------------------*/
  private boolean isAParent(int index){
      return (hasALeftChild(index) || hasARightChild(index));
  }
/*------------------------------------------------------------------------
Function Name:      isALeftChild
Purpose:            determines if an index is a left child
Description:        checks if the index has a parent, and that parent has
a left child, namely the given index
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index is a left child
Side Effects:       none
------------------------------------------------------------------------*/
  private boolean isALeftChild(int index){
      if(hasAParent(index)){
          return hasALeftChild(getParentIndex(index));
      }
      return false;
  }
/*------------------------------------------------------------------------
Function Name:      isARightChild
Purpose:            determines if an index is a right child
Description:        checks if the index has a parent, and that parent has
a right child, namely the given index
Input:              index of the studentArray Heap
Output:             true if yes false if no
Result:             boolean of whether index is a right child
Side Effects:       none
------------------------------------------------------------------------*/
  private boolean isARightChild(int index){
      if(hasAParent(index)){
          return hasARightChild(getParentIndex(index));
      }
      return false;
  }

/*------------------------------------------------------------------------
Function Name:      swap
Purpose:            swaps the data in any two indices of studentArray
Description:        uses a temporary variable to swap the students in
any two indices of the Heap, but maintains index and other notables.
Input:              any two indices 
Output:             none
Result:             up to two swapped student indices
Side Effects:       none
------------------------------------------------------------------------*/  
  private void swap(int ind1, int ind2){
      //temporary variable for holding data
      if (debug){
          System.err.print("[ Swapping " + studentArray[ind1].getName()
          + AND + studentArray[ind2].getName()+ " ]\n");
      }
      UCSDStudent temp = studentArray[ind1];
      studentArray[ind1] = studentArray[ind2];
      studentArray[ind2] = temp;
  }
/*------------------------------------------------------------------------
Function Name:      getFinalLeaf
Purpose:            finds the final leaf in any case where the finalLeaf
field happens to become incorrect.
Description:        loops through Student array to find that last index
containing a Student.
Input:              none
Output:             the new correct Final leaf
Result:             finds the final leaf independent of the class field
Side Effects:       none
------------------------------------------------------------------------*/
  private UCSDStudent getFinalLeaf(){
      for(int iterator = 0; iterator < maxSize; iterator++){
          if(studentArray[iterator] == null){
              return studentArray[iterator - 1];
          }
      }
      return null;
  }
/*------------------------------------------------------------------------
Function Name:      reheapUp
Purpose:            restructures the Heap after a new Student is inserted
Description:        after the student is added at the final leaf, checks
if it is more important than its parent and swaps if so. Repeats until it
is more important than its child and less important than its parent.
Input:              starting index, usually final leaf's index
Output:             none
Result:             a restructured Heap
Side Effects:       none
------------------------------------------------------------------------*/  
  private void reheapUp(int index){
      if(debug){
          System.err.print("[ reheaping Up starting at index "
          + index + " ]\n");
      }
      //temporary varaible to preserve current working index
      int working = index;
      //swap current student and its parent IF the parent is less important
      while (true){
          if(isAChild(working)){
              if(studentArray[working].isLessThan(getParent(working))){
                  swap(working, getParentIndex(working));
                  //now check the parent index
                  working = getParentIndex(working);
              }
              else{
                  break;
              }
              //repeat
          }  
      }
  }
/*------------------------------------------------------------------------
Function Name:      reheapDown
Purpose:            restructures the Heap after a student is removed.
Description:        after the root and final are swapped, checks the most
important child of the current student and compares and swaps if necessary
Input:              index, usually root
Output:             none
Result:             a restructured Heap
Side Effects:       none
------------------------------------------------------------------------*/  
  private void reheapDown(int index){
      if(debug){
          System.err.print("[ reheaping Down starting at index "
          + index + " ]\n");
      }
      //temporary variable to maintain position
      int working = index;
      //check for children
      while (true){
          
          //check if child indices are beyond the scope of the Heap
          if (((working * 2) + 2) > (maxSize - 1)){
              break;
          }
          else if (((working * 2) + 1) > (maxSize - 1)){
              break;
          }
           
          //any kids?
          if(hasARightChild(working) || hasALeftChild(working)){
             
              //2 child case
              if(has2Children(working)){
                  //if left child is more important than right child
                  if(studentArray[getLChildIndex(working)].isLessThan(
                  studentArray[getRChildIndex(working)])){
                      //if left child is more important than parent, swap
                      if(studentArray[getLChildIndex(working)].isLessThan(
                      studentArray[working])){
                          swap(working, getLChildIndex(working));
                          working = getLChildIndex(working);
                      }
                      else{
                          break;
                      }
                  }
                  //if right child is more important than right child
                  else if(!(studentArray[getLChildIndex(working)].isLessThan(
                  studentArray[getRChildIndex(working)]))){
                      //if right child is more important than parent, swap
                      if(studentArray[getRChildIndex(working)].isLessThan(
                      studentArray[working])){
                          swap(working, getRChildIndex(working));
                          working = getRChildIndex(working);
                      }
                      else{
                          break;
                      }
                  }
              }    
              //1 child case same as above
              else if(hasALeftChild(working)){
                  if(studentArray[getLChildIndex(working)].isLessThan(
                  studentArray[working])){
                      swap(working, getLChildIndex(working));
                      working = getLChildIndex(working);
                  }
                  else{
                      break;
                      }
              }
              else if(hasARightChild(working)){
                  if(studentArray[getRChildIndex(working)].isLessThan(
                  studentArray[working])){
                      swap(working, getRChildIndex(working));
                      working = getRChildIndex(working);
                  }
                  else{
                      break;
                  }
              }
          }
          else{
              break;
          }
      }
  } 
  
  
/*---------------------------------------------------------------------------
Function Name:      debugOff      
Purpose:            to turn off debug boolean  
Description:        single line, sets boolean to false

                              
Input:              none
                              
Output:             none      
Result:             turns off debug capabilities      
Side Effects:       none
---------------------------------------------------------------------------*/
	public static void debugOff () {
  		//TODO: YOUR CODE GOES HERE
      debug = false;
	}
/*---------------------------------------------------------------------------
Function Name:      debugOn      
Purpose:            to turn on debug boolean  
Description:        single line, sets boolean to true

                              
Input:              none
                              
Output:             none      
Result:             turns on debug capabilities      
Side Effects:       none
---------------------------------------------------------------------------*/
	public static void debugOn () {
	  	//TODO: YOUR CODE GOES HERE
      debug = true;
	}
/*------------------------------------------------------------------------
Function Name:      getDebug
Purpose:            returns the current debug value for checking
Description:        single line method, returns debug
Input:              none
Output:             the status of debug
Result:             returns the current debug value
Side Effects:       none
------------------------------------------------------------------------*/ 
  public static boolean getDebug(){
      if(debug){
          return true;
      }
      else{
          return false;
      }
  }
/*---------------------------------------------------------------------------
Function Name:        insert        
Purpose:              places a student in a Heap object at the final leaf,
then restructures appropriately.
Description:          moves through the Heap to find the correct index, then
inserts the new student into studentArray. Afterwards, reheaps up to the
correct position in the Heap.
                             
Input:                the student wished to insert      
                              
Output:               true if successful, false if failure        
Result:               an inserted student into the Heap        
Side Effects:         none        
---------------------------------------------------------------------------*/
	public boolean insert (UCSDStudent student) {
      //user input check just to make sure this is ok
      if(maxSize == 0){
          return false;
      }
	
      if (debug){
          System.err.print(TREE + heapName + INSERT
          + student.getName() + CLOSE);
      }   
      //if root place at the beginning
      if (isEmpty()){
          studentArray[0] = student;
      }
      else if(!isFull()){
          /* loop through studentArray, first TNode with fewer than
          2 children must be the place for the final leaf */
          for(int iterator = 0; iterator < maxSize; iterator++){
              if(!has2Children(iterator)){
                  if(!hasALeftChild(iterator)){
                      studentArray[getLChildIndex(iterator)] = student;
                      finalLeaf = getLChildIndex(iterator);
                      break;
                  }
                  else if(!hasARightChild(iterator)){
                      studentArray[getRChildIndex(iterator)] = student;
                      finalLeaf = getRChildIndex(iterator);
                      break;
                  }
              } 
          }
      }
      //catastrophic error message (should never print unless bug)
      else{
          System.err.println("TRIED TO INSERT INTO A FULL HEAP!");
          student.jettison();
          return false;
          
      }
  
      
      //update for new student
      occupancy++;
      //restructure correctly
      reheapUp(finalLeaf);
     
      return true;
	}
/*---------------------------------------------------------------------------
Function Name:        isEmpty        
Purpose:              to check for students present in the Heap 
Description:          one line, checks occupancy field for 0 status        

                              
Input:                none        
                              
Output:               true if true, false if false        
Result:               checks if empty        
Side Effects:         none       
---------------------------------------------------------------------------*/
	public boolean isEmpty () {
	  	
      if (occupancy == 0){
          return true;
      }
      
      return false;
	}
/*---------------------------------------------------------------------------
Function Name:        isFull       
Purpose:              to check for students present in the Heap 
Description:          one line, checks occupancy field for max status        

                              
Input:                none        
                              
Output:               true if true, false if false        
Result:               checks if full        
Side Effects:         none       
---------------------------------------------------------------------------*/
  public boolean isFull(){
      
      if(occupancy == maxSize){
          return true;
      }
      
      return false;
  }
/*---------------------------------------------------------------------------
Function Name:       remove         
Purpose:             removes a student from the heap and from memory, then
returns that data to the user for their use.        
Description:         removes at the root by swapping root and leaf, then
reheaping Down, until the new root is in its correct place.  

                              
Input:               none     
                              
Output:              the student you remove and its data        
Result:              removes a student and returns the data        
Side Effects:        none 
---------------------------------------------------------------------------*/
	public UCSDStudent remove () {
      //first, save data in root for return
      UCSDStudent result = studentArray[root];
      //swap final leaf and root data
      swap(finalLeaf, root);
      //delete the final leaf containing the old root
      if(debug){
          System.err.print("[ Removing " + result.getName() + " ]\n");
      }
      studentArray[finalLeaf].jettison();
      studentArray[finalLeaf] = null;
      occupancy--;
      if(finalLeaf != 0){
          finalLeaf--; 
      }
       
      //reheapDown from root to fix Heap structure
      reheapDown(root);

      //return removed data
		  return result;
  }
  

	/**
	* Creates a string representation of this heap. This method first
	* adds the general information of this heap, then calls the
	* recursive TNode function to add all students to the return string 
	*
	* @return  String representation of this heap 
	*/
/*---------------------------------------------------------------------------
Function Name:      toString         
Purpose:            converts Heap for console presentation         
Description:        appends to representation field to form a valid
string to present the contents of the Heap to the user.

                              
Input:              none          
                              
Output:             String representation of the Heap
Result:             outputs a useful usable string of information          
Side Effects:       none          
---------------------------------------------------------------------------*/
	public String toString () {
      //reset representation
		  representation = "The Heap has ";
      //ensure proper grammar
      if (occupancy == 1){
          representation += occupancy + " item:";
      }
		  else if (occupancy != 1){
          representation += occupancy + " items:";
      }
      //iterate through and add the students to the list
		  if (!isEmpty()){
          for(int iterator = 0; iterator <= finalLeaf; iterator++){
              representation += "\nAt index " + iterator +":  "
              + studentArray[iterator].toString();
          }
      }
		  //if debug print the tracked data too	
		  if (debug)
			  System.err.println (tracker);
      
	  	return representation;
  }


}  

	
