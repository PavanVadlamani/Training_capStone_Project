package casestudy.bookingservice.models;

import javax.validation.constraints.NotNull;

public class Passenger {
	String passengerfirstname;
	String passengerlastname;
	int age;
	String gender;
	long id;
	public Passenger() {
		super();
	}
	public Passenger(String passengerfirstname, String passengerlastname, int age, String gender, long id) {
		super();
		this.passengerfirstname = passengerfirstname;
		this.passengerlastname = passengerlastname;
		this.age = age;
		this.gender = gender;
		this.id = id;
	}
	public String getPassengerfirstname() {
		return passengerfirstname;
	}
	public void setPassengerfirstname(String passengerfirstname) {
		this.passengerfirstname = passengerfirstname;
	}
	public String getPassengerlastname() {
		return passengerlastname;
	}
	public void setPassengerlastname(String passengerlastname) {
		this.passengerlastname = passengerlastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
