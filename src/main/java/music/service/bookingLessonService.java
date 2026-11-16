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
    
    List<String> availableTimeSlots(LocalTime time);
    
    List<String> bookedTimeSlot(LocalTime time);

    List<BookingLessonModel> getBookedLessons(String id);
    
    void cancelLesson(BookingLessonModel lesson );
    
    void addTutor(Tutor tutor);
    
    List<Tutor>getAllTutors();
    
    
    
        
   

}
