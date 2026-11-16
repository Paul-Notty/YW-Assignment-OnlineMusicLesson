package music.model;

public class Instrument {
	
	private String instrumentName;
	private Integer studentGrade; 
	private Integer minGrade;
	private Integer maxGrade;

	public Instrument() {
		
	}
	
	public Instrument(String instrumentName , Integer studentGrade ) {
		this.instrumentName = instrumentName;
		this.studentGrade = studentGrade;
		
		
	}
	
	public Instrument(String instrumentName , Integer minGrade ,Integer maxGrade) {
		this.instrumentName = instrumentName;
		this.minGrade = minGrade;
		this.maxGrade = maxGrade;
	}
	

	public String getInstrumentName() {
		return instrumentName;
	}


	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public Integer getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(Integer studentGrade) {
		this.studentGrade = studentGrade;
	}

	public Integer getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}

	public Integer getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(Integer maxGrade) {
		this.maxGrade = maxGrade;
	}

	



}
