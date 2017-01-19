/* Final Project 112   
   File Name:          KennelManager.java
   Programmer:         Eric Orozco
   Date Last Modified: Dec 10, 2013

   Problem Statement: Create a program that organizes Dog data in kennels
   
   Overall Plan:
   Project Necessities:
   1. Style sheet and comments
   2. 3 classes and tester
   3. constructors
   4. Data structures: Arraylists
   5. Error checking and exceptions
   6. Inheritance(extends JFame), FileI/O (Random Access)
   	  Exception Handeling(try catch), GUI(main menu)
   	  GUI with menu(editing data)
   7. 5-6 minimum of code
   
   Dog:
   1. Create instance varianles for Dog
   2. Each dog will have name,age,breed, tag number, sex, intact
   		, meds, and diet. All will be Strings for easy access and
   		simplicity
   3. Create default and main constructor
   4. Create getters and setter
   5. toString and equals method
   6. hashCodse method?
   
   KennelManager
   1. Extend JFrame and implement ActionListener
   2. Establish main menu window with three button options to
   		choose from
   3. create labels and add pictures to make it look good
   		each button will create a new window 
   4. Button 1: Display all dogs
   5. get all data from a random access file and print all
   		data to the text box
   6. Button 2 is to search
   7. to search let user enter tag or name
   		in text box, search through the RAF and show results
   		in large text box
   8. add a menu bar in a flowLayout to allow user to edit
   		dog information
   9. This option will create a new window edit information
   10. Option 3: Add dog
   11. Create a friendly box layout to allow user to oragnizibly
   		save a new dog with data
   12. Take all input/output data and check input using proper exceptions
   		and if-else statements
   13. if data is correct write to end of the file
   13.1 Add two methods that will show a window about error or Successful
   			actions by user
   
   Editor
   14. Create layout and add two parameters in constructor
   15. We will take the name or tag String plus the long location in file
   16. Using this information, we will pull the old data into the text boxes
   		and allow user to edit and save to file
   17. Use proper exceptions and if-else statements for checks
   18. See if the results match
  
   KennelDemo
   19. create main class
   20. create KennelManager object
   21. Show results and FIND TEST DUMMIES
   22. Print Screen
   
   Classes needed and Purpose: Final Project
   main class – KennelDemo
*/

//main class
public class KennelDemo 
{
	public static void main(String[] args) 
	{
		//create object
		KennelManager demo = new KennelManager();
		//show results
		demo.setVisible(true);
	}
}