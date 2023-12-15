package vttp.ssf.assessment.eventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner{

	@Autowired
	DatabaseService svc;

	@Autowired
	RedisRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}
	
	// TODO: Task 1
	@Override
	public void run(String... args) throws Exception{
		List<Event> eventList = svc.readFile("static/events.json");
		// clears redis repo if it already has events
		repo.delete();
		for(Event event:eventList){
			repo.saveRecord(event);
		}
		System.out.println("repo success");
		Long numberOfEvents = repo.getNumberOfEvents();
		System.out.println("Number of events: "+ numberOfEvents);
		repo.getEvent(2);
		
	}

}
