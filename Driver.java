/**
 *Name: Stephen Flynn
 *PID: A14984805
 *User: cs12sp21bbl
 *File Name: Driver.java
 *Description: This is the driver file for Heap.java and related files
 *in the service of an array based heap data structure.
 *It implements a main method and switch statements to facilitate use.
 *
 */
import java.io.*;
/**
 * Class:            UCSDStudent
 * Description:      Implements a wrapper class for use in various functions
 *                   and as a vehicle primarily for the Heap.java functions.
 *
 * Fields:           name  - the name of the student
 *                   studentnum  - the ID number of the student
 *                   tracker - the data tracker used across classes
 *
 * Public functions: jettison - gets rid of the student in memory
 *                   getName - return the name field of the student
 *                   equals - checks if the student is alphabetically equal
 *                   isLessThan - checks if the student is alphabetically
 *                                less than a given name
 *                   toString - converts base to String for output
 *
 */
class UCSDStudent extends Base {

	public String name;
	public long studentnum;
	private Tracker tracker;

	//TODO: YOUR CODE GOES HERE
    public UCSDStudent (String nm, long val, String caller) {
        tracker = new Tracker("UCSDStudent", Size.of(name)
        + Size.of(tracker)
        + Size.of(studentnum),
        caller + " calling UCSDStudent ctor");
        name = nm;
        studentnum = val;
    }
/*---------------------------------------------------------------------------
Function Name:      jettison          
Purpose:            to get rid of memory space for student         
Description:        utilizes Tracker method to remove from memory tracker

                              
Input:              none          
                              
Output:             none          
Result:             removes student from tracker and memory          
Side Effects:       none          
---------------------------------------------------------------------------*/
    public void jettison() {
        tracker.jettison();
    }
/*---------------------------------------------------------------------------
Function Name:      getName          
Purpose:            to return the name of a specified UCSDStudent         
Description:        simply returns name field

                              
Input:              none          
                              
Output:             String name field          
Result:             returns a String          
Side Effects:       none          
---------------------------------------------------------------------------*/
    public String getName() {
        return name;
    }
/*---------------------------------------------------------------------------
Function Name:      equals         
Purpose:            to check if a name is alphabetically equal to another
Description:        uses casting, recursion, and polymorphic calls to check
for equality

                              
Input:              any Object, most likely a UCSDStudent          
                              
Output:             true if equals, false if not          
Result:             checks two names for equality          
Side Effects:       none          
---------------------------------------------------------------------------*/
    public boolean equals (Object object) {
        if (this == object)
            return true;
        if (!(object instanceof UCSDStudent))
            return false;
        UCSDStudent otherStudent = (UCSDStudent) object;
        return name.equals (otherStudent.getName());
    }
/*---------------------------------------------------------------------------
Function Name:      isLessThan         
Purpose:            to check if a name is alphabetically less than another
Description:        uses compareTo method to check for inequality

                              
Input:              any Base, most likely a UCSDStudent          
                              
Output:             true if less than, false if not          
Result:             checks two names for inequality          
Side Effects:       none          
---------------------------------------------------------------------------*/
    public boolean isLessThan (Base base){
        return (name.compareTo (base.getName()) < 0 ) ? true : false;
    }
/*---------------------------------------------------------------------------
Function Name:      toString         
Purpose:            converts UCSDStudent for console presentation         
Description:        converts......to a String. who could have guessed.

                              
Input:              none          
                              
Output:             String representation of the name and studentnum fields
Result:             outputs a useful usable string of information          
Side Effects:       none          
---------------------------------------------------------------------------*/
	public String toString () {
		return "name:  " + name + "  studentNum:  " + studentnum + ".";
	}
}


/**
*Class: Driver
*
*Description: Holds the main method for usage with the Heap class.
*
*Fields: NULL - constant 0
*        ON - constant string 
*        OFF - constant string
*
*Public Functions: main - the main method which, when run, allows use of an
*                         array based heap data structure with associated
*                         functionality.
*
*
*/
public class Driver {
	private static final short NULL = 0;
  private static final String ON = "on ";
  private static final String OFF = "off ";
  
/*------------------------------------------------------------------------
Function Name: main
Purpose: allows use of an array based heap data structure
Description: uses a large switch statement in a while loop to gets commands
Input: args, input arguments from command line
Output: none
Result: use of a Heap
Side Effects: none
------------------------------------------------------------------------*/
	public static void main (String [] args) {

	// initialize debug states
	Heap.debugOff ();




	// The real start of the code
   	Heap symtab =
		new Heap<UCSDStudent> ("UCSDStudentHeap", "main");
		String buffer = null;
		char command;
		long number = 0;
    //check for options and debug messages
		UCSDStudent stu = new UCSDStudent (buffer, 0, "main"); 
    //prompt user
    System.out.print("\nPlease enter the number"
    +" of objects to be able to store:  "); 
    //store answer
    try{
        number = MyLib.decin();
        //update symtab maxSize field
        symtab.setMaxSize(number);
        symtab.setStudentArray(number);
        buffer = MyLib.getline();
    }
    catch(EOFException eof){
        
    }

		System.out.println ("Initial Heap:\n" + symtab);

		while (true) {
        buffer = null;
			  command = NULL; // reset command each time in loop
				System.out.print ("Please enter a command:  " + "(c)heck memory, (d)ebug ");
        if(symtab.getDebug()){
            System.out.print(OFF);
        }
        else{
            System.out.print(ON);
        }
        if(!symtab.isFull()){
            System.out.print("(i)nsert, ");
        }
        if(!symtab.isEmpty()){
            System.out.print("(r)emove, ");
        }
        System.out.print("(w)rite:  ");
			
      
      
        try {
			  command = MyLib.getchar ();
			  MyLib.clrbuf (command); // get rid of return

			  switch (command) {
			
        case 'd':
            if(symtab.getDebug()){
                symtab.debugOff();
            }
            else{
                symtab.debugOn();
            }
         
            break;
       
        case 'c':
            Tracker.checkMemoryLeaks();
        
            break;
      
	  		case 'i':
      
            if(!symtab.isFull()){
                System.out.print ("Please enter UCSD student"
			    	    + " name to insert:  ");

		    	    	buffer = MyLib.getline (); // formatted input

		        		System.out.print ("Please enter UCSD student"
	    		    	+ " number:  ");

	    			    number = MyLib.decin ();
	    			    MyLib.clrbuf (command); // get rid of return

		        		// create student and place in symbol table
		        		symtab.insert (new UCSDStudent (buffer, number,
		    	   		"main"));
            }
				

			    	break;

			  case 'r':
				
            if(!symtab.isEmpty()){
                UCSDStudent removed; // data to be removed

			        	removed = symtab.remove ();

	      	    	if (removed != null) {
			      	    	System.out.println ("Student removed!!!"); 
			   		        System.out.println (removed);
		    	    	}
            }
        		
				    break;

			  case 'w':
    	      System.out.println (symtab);
	    	}
			  }
	  		catch (EOFException eof) {
			    	break;
	  		}
		}

		System.out.println ("\nFinal Heap:\n" + symtab);
		stu.jettison ();
		symtab.jettison ();
		Tracker.checkMemoryLeaks ();
	}
}
