
//import all necessary packages 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class KennelManager extends JFrame implements ActionListener
{
	/*--------------------------------------------------------------
	 *  These are the instance variables to create the main menu panel
	 *  main picture in a button, 3 options to select 
	 ---------------------------------------------------------------*/
	private final int WIDTH = 500;
	private final int HEIGHT = 500;
	private ArrayList<Dog> animals = new ArrayList<Dog>();
	
	private JTextArea headText1 = new JTextArea("Welcome to Big BOSS Kennel Database Program V1.999.9.9");
	private JTextArea headText2 = new JTextArea("Click any option below to begin");
	
	private JPanel mainPanel;
	
	private JButton all = new JButton("Display All Dog Info");
	private JButton data = new JButton("Search Info");
	private JButton add = new JButton("Add new Dog");
	
	//create default constructor to set up main menu panel
	public KennelManager()
	{
		super("BIG BOSS Kennel Database");
		this.setSize(WIDTH, HEIGHT);
		setLayout(new FlowLayout());
		
		ImageIcon start = new ImageIcon(getClass().getResource("dogpaw.jpg"));
		JButton button = new JButton (start);
		add(button);

		add(headText1);
		add(headText2);
		
		mainPanel = new JPanel( new GridLayout( 1, 3 ) );
		mainPanel.add(all);
		mainPanel.add(data);
		mainPanel.add(add);
		add(mainPanel);	
		
		//Create 3 actions per button to initialize actions
		all.addActionListener(this);
		data.addActionListener(this);
		add.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) 
	{
		//Every button will create a new window
		//we must create seperate inner classes
		//to call on
		String s = e.getActionCommand();
		if(s.equalsIgnoreCase("Display All Dog Info"))
		{
			All chart = new All();
			chart.setVisible(true);
		}
		else if (s.equalsIgnoreCase("Search Info"))
		{
			Data info = new Data();
			info.setVisible(true);
		}
		else if (s.equalsIgnoreCase("Add new Dog"))
		{
			Add input = new Add();
			input.setVisible(true);
		}
		else
		{
			System.out.println("ERROR");
		}
	}
	//OPTION 1: Show all dogs in the database
	private class All extends JFrame implements ActionListener
	{
		//instance variables to set up window layout
		JPanel textShower = new JPanel();
		JTextArea info = new JTextArea(20,20);
		JButton exit = new JButton("Exit");
		public All()
		{
			//Create window format 
			super("Display all Info");
			setLayout(new FlowLayout());
			setSize(400,600);
			
			//Text box will show all data
			textShower.add(info);
			add(textShower);
			info.setBorder(BorderFactory.createTitledBorder(""));
			add(exit);
			animals.clear();	//Clear arraylist for avoid double entries
			exit.addActionListener(this);
			try
			{
				//Call random access to get info from dat file
				RandomAccessFile input = new RandomAccessFile("data.dat", "rw");
				input.seek(0);
        		//while loop continues until file reaches an end
				while(true)
				{
					String name  = input.readUTF();
					String age  = input.readUTF();
					String tag  = input.readUTF();
					String breed  = input.readUTF();
					String diet  = input.readUTF();
					String meds  = input.readUTF();
					String gender  = input.readUTF();
					String intact  = input.readUTF();
					
					//Create object and store in arraylist
					animals.add(new Dog(name, age, gender, tag,
							breed, diet, meds, intact));
				}
			}
			catch(EOFException o)
			{
				//ends while loop by canceling try block
				//this block will only actvate when reaching 
				//the end of the file
			}
			catch(FileNotFoundException o)
	    	{
				//If file cannot be found
	    		System.out.println("Problem opening file.");
	        }
	    	catch(IOException o)
	    	{
	    		//activates when there are problems with input/output
	    	}
			//This for-each loop will iterate through Arraylist
			//To display all info, we must store what is textbox
			//and continue adding strings until we reach the end of ArrayList
			for (Dog animal: animals)
            {
					String temp = info.getText() +
        						 "Name:................" + animal.getName()      + "\n" +
        						 "Gender:.............." + animal.getSex()       + "\n" +
        						 "Age:...................." + animal.getAge()       + "\n" +
        						 "Tag Number:...." + animal.getTagNumber() + "\n" +
        						 "Breed:................" + animal.getBreed()     + "\n" +
        						 "Diet:..................." + animal.getDiet()      + "\n" +
        						 "Meds:................" + animal.getMedications() + "\n\n\n"
        						;
					info.setText(temp); 
            }
		}
		//this action will exit out this class window
		public void actionPerformed(ActionEvent e) 
		{
			String s = e.getActionCommand();
			if(s.equals("Exit"))
			{
				setVisible(false);
			}	
		}	

	}// End of All class
	
	//OPTION 2: searching for particular Dog in database
	private class Data extends JFrame implements ActionListener
	{
		//create instance variables to establish window
		JLabel header = new JLabel("Please Enter Name or Tag Number");
		JPanel searchArea = new JPanel(new GridLayout());
		JTextArea search = new JTextArea();
		JButton searchButton = new JButton("Search");
		
		JPanel textShower = new JPanel();
		JTextArea info = new JTextArea(20,20);
		
		JMenuBar edit = new JMenuBar();
		JMenu editMenu = new JMenu("Click to Edit ");
		JMenuItem editer = new JMenuItem("Edit Dog searched");
		
		JButton exit = new JButton("Exit");
		public Data()
		{
			//add instance variables to establish window layout
			super("Searching Dog Information");
			setSize(285 , 500);
			setLayout(new FlowLayout());

			
			edit.add(editMenu);
			editMenu.add(editer);
	
			searchArea.add(search);
			searchArea.add(searchButton);
			search.setBorder(BorderFactory.createTitledBorder(""));
			textShower.add(info);
			
			info.setBorder(BorderFactory.createTitledBorder(""));
			
			add(header);
			add(searchArea);
			add(textShower);
			add(edit);
			add(exit);
			
			//this will activate actions when clicked on ny user
			searchButton.addActionListener(this);
			exit.addActionListener(this);
			editer.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) 
		{ 	
			//Create 3 if statements per each senario
			String s = e.getActionCommand();
			animals.clear();
			
			//When search is clicked
			if(s.equals("Search"))
			{
				//Get information from file and fill arraylist
				try
				{
					RandomAccessFile input = new RandomAccessFile("data.dat", "rw");
					input.seek(0);
	        		//while loop continues until file reaches an end
					while(true)
					{
						String name  = input.readUTF();
						String age  = input.readUTF();
						String tag  = input.readUTF();
						String breed  = input.readUTF();
						String diet  = input.readUTF();
						String meds  = input.readUTF();
						String gender  = input.readUTF();
						String intact  = input.readUTF();
						
						animals.add(new Dog(name, age, gender, tag,
								breed, diet, meds, intact));
					}
				}
				catch(EOFException o)
				{
					//ends try block when reaching the end of a file
				}
				catch(FileNotFoundException o)
		    	{
		    		System.out.println("Problem opening file.");
		        }
		    	catch(IOException o)
		    	{
		    		//ends try block if problems with input/output occur
		        }   
				//use a for-each to search arraylist for prticular Dog
				//by either tag number or Name
				int index = 0;
            	for (Dog animal: animals)
                {
            		String input = search.getText().trim();
            		if(input.equalsIgnoreCase(animal.getName()) || 
		            		   input.equalsIgnoreCase(animal.getTagNumber()))
            		{
            			
            			info.setText("Dog Information Found.......\n" + "\n"+
            						 "Name:................" + animal.getName()      + "\n" +
            						 "Gender:.............." + animal.getSex()       + "\n" +
            						 "Age:...................." + animal.getAge()       + "\n" +
            						 "Tag Number:...." + animal.getTagNumber() + "\n" +
            						 "Breed:................" + animal.getBreed()     + "\n" +
            						 "Diet:..................." + animal.getDiet()      + "\n" +
            						 "Meds:................" + animal.getMedications() + "\n"
            						);
            			break;
            		}
            		index++;
                }
            	//this if will activate if there is no match in file
        		if(index == animals.size())
        		{
        			info.setText("No Match Found");
        		}
			}
			
			//this option will occur if user wnat to edit searched dog
			if(s.equalsIgnoreCase("Edit Dog searched"))
			{
				//check if there is anything in search textbox
				if(search.getText().equalsIgnoreCase("")
					|| info.getText().equalsIgnoreCase("No Match Found"))
				{
					ErrorMessage("There is no data to edit");
				}
				//search and find location in file where data is stored
				String finder = search.getText().trim();
				long location; //stores location in file
				try
				{
					RandomAccessFile input = new RandomAccessFile("data.dat", "rw");
					input.seek(0);
	        		//while loop continues until file reaches an end
					while(true)
					{
						location = input.getFilePointer();
						String name  = input.readUTF();
						String age  = input.readUTF();
						String tag  = input.readUTF();
						String breed  = input.readUTF();
						String diet  = input.readUTF();
						String meds  = input.readUTF();
						String gender  = input.readUTF();
						String intact  = input.readUTF();
						
						//if search box String matches data in file, create Editor Window
						if(finder.equalsIgnoreCase(name) || finder.equalsIgnoreCase(tag))
						{
							Editor window = new Editor(finder, location);
							break;//data is foundso end while loop
						}
					}
				}
				catch(EOFException o)
				{
					//ends try block when reaching an end of file
				}
				catch(FileNotFoundException o)
		    	{
		    		System.out.println("Problem opening file.");
		        }
		    	catch(IOException o)
		    	{
		    		//problems with input/output
		        }   
			}
			//closes window for Search
			if(s.equalsIgnoreCase("exit"))
			{
				setVisible(false);
			}
	
		}
	}
	
	//OPTION 3: Add a dog to program
	private class Add extends JFrame implements ActionListener
	{
		//create instance variables to establish window layout
		JLabel addHeader = new JLabel("Please Enter information Below. Click Save to finish");
		
		JPanel addMainPanel = new JPanel( new GridLayout( 4, 1) );
		JPanel bottom = new JPanel(new GridLayout(1,2));
		JPanel box1;
		JPanel box2;
		JPanel box3;
		JPanel box4;
		
		JLabel nameLabel = new JLabel("Name: ");
		JLabel ageLabel = new JLabel("Age: ");
		JLabel sexLabel = new JLabel("Sex: ");
		JLabel intactLabel = new JLabel("Intact: ");
		JLabel tagNumberLabel = new JLabel("Tag Number: ");
		JLabel breedLabel = new JLabel("Breed: ");
		JLabel dietLabel = new JLabel("Diet: ");
		JLabel medicationsLabel = new JLabel("Medications: ");
		
		JTextField nameBox = new JTextField();
		JTextField ageBox = new JTextField();
		JRadioButton male = new JRadioButton("Male", true);
		JRadioButton female = new JRadioButton("Female");
		JRadioButton intactYes = new JRadioButton("Yes");
		JRadioButton intactNo = new JRadioButton("No", true);
		JTextField tagNumberBox = new JTextField();
		JTextField breedBox = new JTextField();
		JTextField dietBox = new JTextField();
		JTextField medicationsBox = new JTextField();
		
		ButtonGroup sexChoice = new ButtonGroup();
		
		JButton save = new JButton("Save");
		JButton exit = new JButton("Exit");
	
		public Add()
		{
			//Create window layout
			//Allow user to enter dog information
			//in the establish boxes
			//the save button will check user input and
			//save to file
			//a window will appear if any errors occur
			super("Adding new Dog");
			setSize(310, 500);
			setLayout(new FlowLayout());
			
			add(addHeader);
			add(addMainPanel);
			
			box1 = new JPanel( new GridLayout( 4, 2) );
			box1.setBorder(BorderFactory.createTitledBorder(""));
			box1.add(nameLabel);
			box1.add(nameBox);
			box1.add(tagNumberLabel);
			box1.add(tagNumberBox);
			box1.add(ageLabel);
			box1.add(ageBox);
			box1.add(breedLabel);
			box1.add(breedBox);
			
			box2 = new JPanel(new GridLayout(1,3));
			box2.setBorder(BorderFactory.createTitledBorder(""));
			box2.add(sexLabel);
			box2.add(male);
			box2.add(female);
			
			box3 = new JPanel(new GridLayout(1,3));
			box3.setBorder(BorderFactory.createTitledBorder(""));
			box3.add(intactLabel);
			box3.add(intactYes);
			box3.add(intactNo);
			
			box4 = new JPanel(new GridLayout(2,2));
			box4.setBorder(BorderFactory.createTitledBorder(""));
			box4.add(dietLabel);
			box4.add(dietBox);
			box4.add(medicationsLabel);
			box4.add(medicationsBox);

			sexChoice.add(male);
			sexChoice.add(female);
			ButtonGroup intactChoice = new ButtonGroup();
			intactChoice.add(intactYes);
			intactChoice.add(intactNo);
			
			addMainPanel.add(box1);
			addMainPanel.add(box2);
			addMainPanel.add(box3);
			addMainPanel.add(box4);
			
			bottom.add(save);
			bottom.add(exit);
			
			add(bottom);
			
			save.addActionListener(this);
			exit.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) 
		{
			//Saving info
			//first lets error check
			//the good variable will determine if an error message will appear
			int good = 1;
			String s = e.getActionCommand();
			if(s.equals("Save"))
			{
				//the variables that will be saved to file
				String name = nameBox.getText();
				String age = "";
				age = ageBox.getText();
				String tag = tagNumberBox.getText();
				tag = tagNumberBox.getText();
				String breed = breedBox.getText();
				String diet = dietBox.getText();
				String meds = medicationsBox.getText();
				String gender = "";
				String intact = "";
				
				//Checks if there is no information in boxs
				if(nameBox.getText() == null || ageBox.getText() == null
				   || tagNumberBox.getText() == null || breedBox.getText() == null
				   || dietBox.getText() == null || medicationsBox.getText() == null
				   || nameBox.getText().equals("") || ageBox.getText().equals("")
				   || tagNumberBox.getText().equals("") || breedBox.getText().equals("")
				   ||  dietBox.getText().equals("") || medicationsBox.getText().equals(""))
				{
					good--;
				}
				try
				{
					int test1 = Integer.parseInt(ageBox.getText());
					int test2 = Integer.parseInt(tagNumberBox.getText());
				}
				catch(NumberFormatException o)
				{
					ErrorMessage("Wrong Format: No Letters or punctuation\n"
							+ "Numbers only for Age and Tag number\n" +
							"Please enter all boxes correctly");
					good--;
				}
				if(male.isSelected())
				{
					 gender = "male";
				}
				else if(female.isSelected())
				{
					gender = "female";
				}
				
				if(intactYes.isSelected())
				{
					intact = "yes";
				}
				else if(intactNo.isSelected())
				{
					intact = "no";
				}
				//If checks pass, add to file
				if(good == 1)
				{
					try
					{
					
						RandomAccessFile input = new RandomAccessFile("data.dat", "rw");
						input.seek(input.length());
				        input.writeUTF(name);
				        input.writeUTF(age);
				        input.writeUTF(tag);
				        input.writeUTF(breed);
				        input.writeUTF(diet);
				        input.writeUTF(meds);
				        input.writeUTF(gender);
				        input.writeUTF(intact);
				        successMessage();
				        input.close();
					}
					catch(FileNotFoundException o)
		        	{
		        		System.out.println("Problem opening file.");
		            }
		        	catch(IOException o)
		        	{
		        		//ends try block if input/output erros found
		            }
					
					nameBox.setText("");
					ageBox.setText("");
					tagNumberBox.setText("");
					breedBox.setText("");
					dietBox.setText("");
					medicationsBox.setText("");
				}
				
			}
			if(s.equalsIgnoreCase("exit"))
			{
				setVisible(false);
			}
		}
	}
	//This method will create a window message
	//that user obeyed all user input checks and
	//and that user use was sucessful
	public void successMessage()
	{
		JFrame alert = new JFrame("Confirmed");
		alert.setSize(200,200);
		alert.setLayout(new FlowLayout());
		JLabel message = new JLabel("");
		JLabel message2 = new JLabel("");
		JLabel message3 = new JLabel("Data was now saved");
		alert.add(message);
		alert.add(message2);
		alert.add(message3);
		alert.setVisible(true);
	}
	//This method will create a window message
	//that user disobeyed all user input checks and
	//and that user use was unsucessful
	//method will take in a error string to be specific
	//about error
	public void ErrorMessage(String error)
	{
		JFrame alert = new JFrame("Error");
		alert.setSize(300,200);
		alert.setLayout(new FlowLayout());
		JLabel message = new JLabel(error);
		JLabel message2 = new JLabel("");
		JLabel message3 = new JLabel("Please Try Again");
		alert.add(message);
		alert.add(message2);
		alert.add(message3);
		alert.setVisible(true);
	}
}
