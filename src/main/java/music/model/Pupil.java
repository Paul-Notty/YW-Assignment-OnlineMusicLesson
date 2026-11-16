package music.model;

import java.util.List;
import java.util.Map;


public class Pupil {
	
	private String name; 
	private Integer studentid ; 
	private Integer age;
	private List<Instrument> instrument; 

	public Pupil() {
		
	}
	
	public Pupil(String name , Integer age , Integer studentid , List<Instrument> instrument  ) {
		this.name = name;
		this.age = age;
		this.studentid = studentid;
		this.instrument = instrument;
		
		
	}
	

	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public int getStudentid() {
		return studentid;
	}



	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}


	public List<Instrument> getInstrument() {
		return instrument;
	}


	public void setInstrument(List<Instrument> instrument) {
		this.instrument = instrument;
	}	
	
	
}
