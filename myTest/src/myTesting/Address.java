

public class Address {

	private String streetAddress;	// Street number + street name
	private int	   streetNmbr; 		// Street number
	private String streetName;		// Street name
	private String apt;
	private String city;
	private String state;
	private int	   zip;
	
	// Constructor
	public Address () {
		
		this.streetAddress = "StreetNumber and StreetName";
		this.streetNmbr = 0;
		this.streetName = "StreetName";
		this.apt = null;
		this.city = "City";
		this.state = "State";
		this.zip = 0;
		
/*		this.streetNmbr = strNumber;
		this.streetName = strName;
		this.city = City;
		this.state = State;
		this.zip = Zip;
		this.streetAddress = strNumber + " " + strName; */
	}
	
	// Getters
	public void getAddress() {
		
		System.out.println(streetAddress + "\n" 
							+ city + ", " + state + "\n" 
							+ zip);
	}
	public String getStreetAddress() {
		
		return this.streetAddress;
	}
	public int getStreetNumber() {
		
		return this.streetNmbr;
	}
	public String getStreetName() {
		
		return this.streetName;
	}
	public String getApt() {
		
		return this.apt;
	}
	public String getCity() {
		
		return this.city;
	}
	public String getState() {
		
		return this.state;
	}
	public int getZip() {
		
		return this.zip;
	}
	// Setters
	public void setStreetAddress() {
		
		this.streetAddress = streetNmbr + " " + streetName;
		// If an apartment or suite number was provided
		if (apt != null) {
			this.streetAddress = streetAddress + " " + apt;
		}
	}
	public void setStreetNumber(int Nmbr) {
		
		this.streetNmbr = Nmbr;
	}
	public void setStreetName(String Name) {
		
		this.streetName = Name;
	}
	public void setApt(String Apt) {
		
		this.apt = Apt;
	}
	public void setCity(String City) {
		
		this.city = City;
	}
	public void setState(String State) {
		
		this.state = State;
	}
	public void setZip(int Zip) {
		
		this.zip = Zip;
	}
}
