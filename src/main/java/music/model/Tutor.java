package music.model;

import java.util.List;

public class Tutor {
	
	private String name;

	private List<Instrument> instrument;
	
	public Tutor(){
		
	}
	 
	
	public Tutor (String name, List<Instrument> instrument) {
		this.name = name;
		this.instrument= instrument;
		
	}

	public Tutor (Tutor that) {
		this.name = that.name;
		this.instrument= that.instrument;
		
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public List<Instrument> getInstrument() {
		return instrument;
	}

	public void setInstrument(List<Instrument> instrument) {
		this.instrument = instrument;
	}

	
	
}
