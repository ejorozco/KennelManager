
// Dog class object
public class Dog
{
	//Instance Variables for Dog 
	private String name;
	private String age;
	private String sex;
	private String intact;
	private String tagNumber;
	private String breed;
	private String diet;
	private String medications;
	
	//create defau;t constructor
	public Dog()
	{
		this.name = "Dog";
		this.age = "none";
		this.sex = "F";
		this.tagNumber = "0000";
		this.breed = "labrador";
		this.diet = "food";
		this.medications = "none";
	}
	
	//create main constructor to create object
	public Dog(String name, String age, String sex, String tagNumber,
			String breed, String diet, String medications, String intact) 
	{
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.tagNumber = tagNumber;
		this.breed = breed;
		this.diet = diet;
		this.medications = medications;
		this.intact = intact;
	}
	//Create a serious of setters and getters for instance variables
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getAge() 
	{
		return age;
	}
	public void setAge(String age) 
	{
		this.age = age;
	}
	public String getSex() 
	{
		return sex;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	public String getIntact() 
	{
		return intact;
	}
	public void setIntact(String intact) 
	{
		this.intact = intact;
	}
	public String getTagNumber() 
	{
		return tagNumber;
	}
	public void setTagNumber(String tagNumber) 
	{
		this.tagNumber = tagNumber;
	}
	public String getBreed() 
	{
		return breed;
	}
	public void setBreed(String breed) 
	{
		this.breed = breed;
	}
	public String getDiet() 
	{
		return diet;
	}
	public void setDiet(String diet) 
	{
		this.diet = diet;
	}
	public String getMedications()
	{
		return medications;
	}
	public void setMedications(String medications) 
	{
		this.medications = medications;
	}
	//Create toString to show results of objects
	public String toString() 
	{
		return  "Dog Found.\n" + 
				"Name: " + getName() + "\n"+ 
				"Age: "  + getAge()  + "\n"+ 
				"Sex: "  + getSex()  + "\n"+ 
				"TagNumber: " + getTagNumber() +"\n"+ 
				"Breed: " + getBreed() + "\n"+ 
				"Diet: " + getDiet() + "\n"+ 
				"Medications: " + medications;
	}
	//equals method
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return false;
	}	
}