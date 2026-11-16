
package music.service;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

//import lesson.model.musicLesson;
import music.model.*;
@Component
public class bookingLessonServiceImpl implements bookingLessonService {

    private Map<Integer, BookingLessonModel> db;
    private Map<String ,Tutor> tutors;
    private int noOfBookings = 1; 
    private int bookingId = 0;
    
    
   

    public bookingLessonServiceImpl() {
        db = new HashMap<>();
        tutors = new HashMap<>();
    }

  
    public void bookLesson(BookingLessonModel lesson) {
    
        if (lesson !=null) {
            bookingId = noOfBookings++ ;
            lesson.setBookingId(bookingId);
            db.put(lesson.getPup().getStudentid(), lesson);
   
        }
    }
    
    
    public List<String> availableTimeSlots(LocalTime time){
    	
    	List<String> bookedSlots = bookedTimeSlot(time);
    	
    	List<String> timeSlots = new ArrayList<>();
    	
    		if (!bookedSlots.contains(time)) {
    			timeSlots.add(time.toString());
    		
    	}
    	
		return timeSlots;
    	
    }
    
    public List<String> bookedTimeSlot(LocalTime time) {

        List<String> bookedSlots = new ArrayList<>();
        
        for (BookingLessonModel lesson : db.values()) {
        		if(lesson.getTime().equals(time)) {
        		bookedSlots.add(lesson.getTime().toString());	
        }
        
    
        }

        return bookedSlots;
    }

   
    public List<BookingLessonModel> getBookedLessons(int bookingId) {

        List<BookingLessonModel> lessons = new ArrayList<>();

        for (BookingLessonModel lesson : db.values()) {

            if (Integer.valueOf(lesson.getPup().getStudentid()).equals(bookingId)) {

                lessons.add(lesson);
            }
        }

        return lessons;
    }

    public void cancelLesson(BookingLessonModel lesson) {	
    			db.remove(lesson.getPup().getStudentid());	

    }

   
    public boolean hasLesson(int studentId, String day) {

        for (BookingLessonModel lesson : db.values()) {

            if (lesson.getPup().getStudentid() == studentId
                    && lesson.getDay().equals(day)) {

                return true;
            }
        }

        return false;
    }

    
    public void addTutor(Tutor tutor) {
    	if (tutor != null && tutor.getName() != null) {
            tutor = new Tutor(tutor);
            tutors.put(tutor.getName(), tutor);
        }
    	
    }
    
    public List<Tutor>getAllTutors(){
    	return new ArrayList<>(tutors.values());
    }



    public List<BookingLessonModel> getBookedLessons(String id) {

        List<BookingLessonModel> lessons = new ArrayList<>();

        for (BookingLessonModel lesson : db.values()) {

            if (String.valueOf(lesson.getPup().getStudentid()).equals(id)) {
                lessons.add(lesson);
            }
        }

        return lessons;
    }





    
}
