package music;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import music.model.*;
import music.service.*;

@SpringBootApplication
public class MusicLessonBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicLessonBookingApplication.class, args);
		
		
	}
	
	@Bean
	public CommandLineRunner initDB(bookingLessonService ws) {
        return (args) -> {
        	
        	List<Instrument> Kofi = new ArrayList<>();
        	List<Instrument> Franky = new ArrayList<>();
        	List<Instrument> Keith = new ArrayList<>();
        	List<Instrument> Poo = new ArrayList<>();
        	
        	Kofi.add(new Instrument("piano" , 3 ,8));
        	Kofi.add(new Instrument("guitar" , 1 ,3));
        	Kofi.add(new Instrument("oboe" , 6 ,9));
        	
       
        	Franky.add(new Instrument("drums" , 4,9));
        	Franky.add(new Instrument("Harmonica",4,8));
        	Franky.add(new Instrument("trumpet" , 3,9));
        	
        	Keith.add(new Instrument("violin" , 2,5));
        	Keith.add(new Instrument("cello" , 5,9));
        	
    
        	Tutor kofi = new Tutor("Kofi-Paul" ,Kofi);
        	Tutor franky = new Tutor("Frank Barrios", Franky);
        	Tutor keith = new Tutor("Keith Vernon" , Keith);
        	
        	
        	
        	ws.addTutor(kofi);
        	ws.addTutor(franky);
        	ws.addTutor(keith);
        	
        	
        	
        	
        	
        };
        
    }
    
}
