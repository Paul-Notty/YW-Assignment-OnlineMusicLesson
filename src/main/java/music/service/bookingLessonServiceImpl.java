
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
    private Map<String , List<String>> blockedSlots;
    private int noOfBookings = 1; 
    
    
   

    public bookingLessonServiceImpl() {
        db = new HashMap<>();
        tutors = new HashMap<>();
        blockedSlots = new HashMap<>();
  
    }

  
    public void bookLesson(BookingLessonModel lesson) {
        if (lesson != null) {
            lesson.setBookingId(noOfBookings);
            db.put(noOfBookings, lesson);
            noOfBookings++;
        }
        
        
    }
    
    public List<String> availableTimeSlots(String tutor, String day) {

        List<String> timeSlots = new ArrayList<>();

        LocalTime start;
        LocalTime end;

        if (day.equalsIgnoreCase("Sat")) {
            start = LocalTime.of(9, 0);
            end = LocalTime.of(20, 0);
        } else {
            start = LocalTime.of(16, 0);
            end = LocalTime.of(20, 0);
        }

        for (LocalTime time = start;
             time.isBefore(end);
             time = time.plusMinutes(30)) {

            if (!hasLesson(tutor, day, time.getHour(), time.getMinute())) {
                timeSlots.add(time.toString());
            }
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
    			db.remove(lesson.getBookingId());	

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
    
    
    public boolean hasLesson(String tutor, String day, int hour , int minute) {

        for (BookingLessonModel lesson : db.values()) {

            if (lesson.getTutors().getName().equals(tutor)
            		&& lesson.getDay().equals(day)
                    && lesson.getTime().getHour() == hour && lesson.getTime().getMinute() == minute) {

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

    
    public void blockLesson(String tutorName, LocalTime time, String day) {

        String key = tutorName + "-" + day;

        List<String> slots = blockedSlots.getOrDefault(key, new ArrayList<>());

        String slot = time == null ? "ALL" : time.toString();

        if (slot.equals("ALL")) {
            slots.clear();
        }

        if (!slots.contains("ALL") && !slots.contains(slot)) {
            slots.add(slot);
        }

        blockedSlots.put(key, slots);
    }
    
    public boolean isBlocked(String tutorName, LocalTime time, String day) {

        String key = tutorName + "-" + day;

        List<String> slots = blockedSlots.get(key);

        if (slots == null) {
            return false;
        }

        return slots.contains("ALL") || slots.contains(time.toString());
    }


	@Override
	public List<BookingLessonModel> getTutorsLessons(String name) {
		
		 List<BookingLessonModel> lessons = new ArrayList<>();

	        for (BookingLessonModel lesson : db.values()) {
	            if (lesson.getTutors().getName().equals(name)) {

	                lessons.add(lesson);
	            }
	        }

	        return lessons;
	    
	}


	






    
}
