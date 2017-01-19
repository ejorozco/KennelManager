import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.io.*;

public class Editor implements ActionListener
{
	//Create instance variables to establish layout
	//Object is created to show error messages
	KennelManager messageShower = new KennelManager();
	
	JFrame editWindow = new JFrame("Editing Dog info");
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
	
	long location;
	
	//parameters take in dog's info and location on file
	//to overwrite the file
	public Editor(String dog, long location)
	{
		//Establish layout
		editWindow.setLayout(new FlowLayout());
		editWindow.setSize(250, 500);
		editWindow.setVisible(true);
		editWindow.add(addMainPanel);
		
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
		
		editWindow.add(bottom);
		
		save.addActionListener(this);
		exit.addActionListener(this);
		
		this.location = location;
		try
		{
		
			RandomAccessFile input = new RandomAccessFile("data.dat", "rw");
			input.seek(location);
			String name  = input.readUTF();
			String age  = input.readUTF();
			String tag  = input.readUTF();
			String breed  = input.readUTF();
			String diet  = input.readUTF();
			String meds  = input.readUTF();
			String gender  = input.readUTF();
			String intact  = input.readUTF();
			
	        //set boxes text with old data
	        nameBox.setText(name);
	    	ageBox.setText(age);
	    	tagNumberBox.setText(tag);
	    	breedBox.setText(breed);
	    	dietBox.setText(diet);
	    	medicationsBox.setText(meds);
	    	
	    	if(gender.equalsIgnoreCase("male"))
	    	{
	    		JRadioButton male = new JRadioButton("Male", true);
	    	}
	    	else
	    	{
	    		JRadioButton female = new JRadioButton("Female", true);
	    	}
	    	if(intact.equalsIgnoreCase("Yes"))
	    	{
	    		JRadioButton intactYes = new JRadioButton("Yes", true);
	    	}
	    	else
	    	{
	    		JRadioButton intactNo = new JRadioButton("No", true);
	    	}
	        input.close();
		}
		catch(FileNotFoundException o)
    	{
    		System.out.println("Problem opening file.");
        }
    	catch(IOException o)
    	{
    		
        }
		
	}
	public void actionPerformed(ActionEvent e) 
	{
		//Saving new data file
		//go through checks
		String s = e.getActionCommand();
		int good = 1;
		if(s.equals("Save"))
		{
			String name = nameBox.getText();
			String age = "";
			
			try
			{
				int test = Integer.parseInt(ageBox.getText());
			}
			catch(NumberFormatException o)
			{
				messageShower.ErrorMessage("Wrong Age Format: No Letters or punctuation");
				good--;
			}
			age = ageBox.getText();
			
			String tag = tagNumberBox.getText();
		    try
			{
				int test = Integer.parseInt(tagNumberBox.getText());
			}
			catch(NumberFormatException o)
			{
				messageShower.ErrorMessage("Wrong Tag Format");
				good--;
			}
			tag = tagNumberBox.getText();
	
			String breed = breedBox.getText();
			String diet = dietBox.getText();
			String meds = medicationsBox.getText();
			
			String gender = "";
			String intact = "";
			
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
			
			if(good == 1)
			{
				try
				{
					//if checks pass, add new data to file
					RandomAccessFile input = new RandomAccessFile("data.dat", "rw");
					input.seek(location);
			        input.writeUTF(name);
			        input.writeUTF(age);
			        input.writeUTF(tag);
			        input.writeUTF(breed);
			        input.writeUTF(diet);
			        input.writeUTF(meds);
			        input.writeUTF(gender);
			        input.writeUTF(intact);
			        messageShower.successMessage();
			        input.close();
				}
				catch(FileNotFoundException o)
	        	{
	        		System.out.println("Problem opening file.");
	            }
	        	catch(IOException o)
	        	{
	        		System.out.println("");
	            }
			}	
		}
		//ends window
		if(s.equals("Exit"))
		{
			editWindow.setVisible(false);
		}
	}
}
