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
public class TutorLessonController {

    private bookingLessonService bls;
    

    public TutorLessonController(bookingLessonService bls){
        this.bls = bls;
       
    }

    
    @PostMapping("/block")
    public ResponseEntity<String> blockLesson(@RequestBody BookingLessonModel lesson) {
    	
    	String day = lesson.getDay();
    	LocalTime time = lesson.getTime(); 
    	String tutor = lesson.getTutors().getName();
    	
    	bls.blockLesson(tutor, time , day);
    	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body("Blocking successful");
        
    }
   
    
    @GetMapping("/viewBookings")
    public List<BookingLessonModel> getAllLessons(@RequestParam String name) {
        return bls.getTutorsLessons(name);
    }
  
}