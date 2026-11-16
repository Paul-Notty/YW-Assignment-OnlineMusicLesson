// A resource, living in the model sub-package.

package music.model;

import java.time.LocalTime;

public class BookingLessonModel {
	
	private Pupil pup;
	private Instrument instruments;
	private Tutor tutors;
	
	private Integer bookingId;
    private String day;
    private LocalTime time; 
    
    
    public BookingLessonModel() {
    	
    }
    
    
    public BookingLessonModel(Pupil pup , Instrument inst , Tutor tut , Integer bookingId) {
    	this.bookingId=bookingId;
    	this.pup = pup;
    	this.instruments = inst;
    	this.tutors=tut;
    }

    public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Pupil getPup() {
		return pup;
	}

	public void setPup(Pupil pup) {
		this.pup = pup;
	}

	public Instrument getInstruments() {
		return instruments;
	}

	public void setInstruments(Instrument instruments) {
		this.instruments = instruments;
	}

	public Tutor getTutors() {
		return tutors;
	}

	public void setTutors(Tutor tutors) {
		this.tutors = tutors;
	}

	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}


}
