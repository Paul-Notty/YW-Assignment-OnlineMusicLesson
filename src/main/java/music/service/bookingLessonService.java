// An interface to the business logic, living in the service sub-package.

package music.service;

import java.time.LocalTime;
import java.util.List;

import music.model.BookingLessonModel;
import music.model.Pupil;
import music.model.Tutor;

public interface bookingLessonService {

    
    void bookLesson(BookingLessonModel lesson);

    boolean hasLesson(int id ,String day);
    
    boolean hasLesson(String tutor ,String day, int hour , int minute);
    
    List<String> availableTimeSlots(String tutor , String day);
    
    List<String> bookedTimeSlot(LocalTime time);

    List<BookingLessonModel> getBookedLessons(String id);
    
    List<BookingLessonModel> getTutorsLessons(String name);
    
    void cancelLesson(BookingLessonModel lesson );
    
    boolean isBlocked(String tutorName, LocalTime time, String day);
    
    void addTutor(Tutor tutor);
    
    void blockLesson(String tutorName, LocalTime time, String day);
    
    List<Tutor>getAllTutors();
    
    
    
        
   

}
