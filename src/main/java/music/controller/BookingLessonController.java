package music.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import music.model.*;
import music.service.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
public class BookingLessonController {

    private bookingLessonService bls;
    private PupilTrackerService pts;

    public BookingLessonController(bookingLessonService bls , PupilTrackerService pts){
        this.bls = bls;
        this.pts = pts;
    }

    //Returns all records from the database
    @GetMapping("/viewAllBookings")
    public List<BookingLessonModel> getAllLessons(@RequestParam String id) {
        return bls.getBookedLessons(id);
    }
    
    @GetMapping("/testPupil/{matric}")
    public Pupil testPupil(@PathVariable int matric) {
        return pts.getPupil(matric);
    }
    
    @GetMapping("/getTutors")
    public List<Tutor>getTutors(){
    	return bls.getAllTutors();
    	
    }
    
    @GetMapping("/listAvalibility")
    public List <String> listTimeSlots(@RequestParam LocalTime time) {
    	return bls.bookedTimeSlot(time);
    }
    
    @PostMapping("/book")
    public ResponseEntity<String> addLesson(@RequestBody BookingLessonModel lesson) {
    	
    	int studentId = lesson.getPup().getStudentid();
    	String name = lesson.getPup().getName();
    	Pupil pupilId = pts.getPupil(studentId);
    	int age = pupilId.getAge();
    	int hour = lesson.getTime().getHour();
    	Tutor tutor = null ; 
    	
    	for(Tutor tutors  : bls.getAllTutors()) {
    		if(tutors.getName().equalsIgnoreCase(lesson.getTutors().getName())) {
    			tutor = tutors;
    			break;
    		}
    		
    	}
    	
    	if(tutor==null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No tutor here");	
    	}
    	
    	if(pupilId == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nothing matches our records");	
    	}
    	
    	if(age<16 && hour>=18) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Who let the new born book this late");	
    	}
    	
    	if(!name.equals(pupilId.getName())) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id does not match this student name");
    		
    	}
    	
    	
    	
    	String studentInstrument = lesson.getInstruments().getInstrumentName();
    	int grade = 0;
    	
    	boolean tutorteaches= false;
    	boolean gradeInRange = false;;
    	
    	
    	for(Instrument instruments : pupilId.getInstrument()) {
    		if (instruments.getInstrumentName().equals(studentInstrument)) {
    			grade = instruments.getStudentGrade();
    			break;
    		}
    	}
    	
    	
    	
    	for (Instrument instrument : tutor.getInstrument()) {
		    if (instrument.getInstrumentName().equalsIgnoreCase(studentInstrument)) {
		        tutorteaches = true;
		        
		        if(grade >=instrument.getMinGrade()  && grade <=instrument.getMaxGrade()) {
		        	gradeInRange = true;
		        }
		        
		        
		        break;
		    }
    	}
 
    	
    		
    	if(!tutorteaches) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tutor Doesn't teach this Instrument");	
    	}
    	
    	if (grade==0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student doesn't play this instrument");
    	}
    	
    	if (!gradeInRange) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tutor can't teach this stuents grade");
    		
    	}
    	
        if (bls.hasLesson(studentId,lesson.getDay())) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can only have one lesson per day");
        }
        
        Pupil bookedPupil = new Pupil() ;
        
        bookedPupil.setName(name);
        bookedPupil.setStudentid(studentId);
        bookedPupil.setAge(age);
        
        
        Instrument chosenInstrument = new Instrument();
        
        chosenInstrument.setInstrumentName(studentInstrument);
        chosenInstrument.setStudentGrade(grade);
        
        Tutor chosenTutor = new Tutor();
        
        chosenTutor.setName(tutor.getName());
        
        
        
        lesson.setTutors(chosenTutor);
    	lesson.setPup(bookedPupil);
    	lesson.setInstruments(chosenInstrument);
        bls.bookLesson(lesson);

        
        java.net.URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lesson.getBookingId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    
    @DeleteMapping("/cancelLesson")
    public ResponseEntity<String> cancelLesson(@RequestBody BookingLessonModel lesson ) {
    	
    	int studentId = lesson.getPup().getStudentid();
    	
    	String day = lesson.getDay();
    	
        if (!bls.hasLesson(studentId,day)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Yoooo Family, You not got a lesson that day man, are you alright?");
        }

        bls.cancelLesson(lesson);
     
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Lesson Cancelled");
  
  }
  
}