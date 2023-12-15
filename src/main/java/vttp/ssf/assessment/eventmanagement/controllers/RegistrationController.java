package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Participant;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;


@Controller
@RequestMapping
public class RegistrationController {
    @Autowired
    DatabaseService dataSvc;
    

    // TODO: Task 6
    @GetMapping(path = "/events/register/{id}")
	public String register(@PathVariable("id") String id, Model model){
        List<Event> eventList = dataSvc.getEvents();
        Integer idNumber = 4 - Integer.parseInt(id);
        Event event = eventList.get(idNumber);
        model.addAttribute("event", event);
        model.addAttribute("participant", new Participant());
        return "eventregister";
	}


    // TODO: Task 7
    @PostMapping(path = "/registration/register")
    public String processRegistration(){
        return "ok";
    }
}
