package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@Controller
@RequestMapping
public class EventController {
	@Autowired
	DatabaseService dataSvc;

	@Autowired
	RedisRepository redisRepo;

	//TODO: Task 5
	@GetMapping(path = {"/","/events/listing"})
	public String displayEvents(Model model){
		List<Event> eventList = dataSvc.getEvents();
		System.out.println("Controller event list: "+eventList);
		for(Event e:eventList){
			System.out.println(e.getEventId());
			System.out.println(e.getEventName());
			System.out.println(e.getEventDate());
			System.out.println(e.getEventSize());
			System.out.println(e.getParticipants());
		}
		model.addAttribute("events", eventList);

		return "view0";
	}





}
