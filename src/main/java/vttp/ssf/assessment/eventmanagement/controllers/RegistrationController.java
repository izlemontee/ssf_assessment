package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Participant;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;


@Controller
@RequestMapping
public class RegistrationController {
    @Autowired
    DatabaseService dataSvc;
    

    // TODO: Task 6
    @GetMapping(path = "/events/register")
	public String register( Model model, HttpSession session){
        String idString = session.getAttribute("id").toString();
        Integer id = Integer.parseInt(idString);
        List<Event> eventList = dataSvc.getEvents();
        //Integer idNumber = 4 - Integer.parseInt(id);
        Integer idNumber = 4 - id;
        Event event = eventList.get(idNumber);
        model.addAttribute("event", event);
        session.setAttribute("event", event);
        model.addAttribute("participant", new Participant());
        return "eventregister";
	}


    // TODO: Task 7
    @PostMapping(path = "/registration/register")
    public String processRegistration(@Valid @ModelAttribute("participant") Participant participant,
     BindingResult result, Model model, HttpSession session){
        System.out.println("Participant: "+participant);
        if(result.hasErrors()){
            System.out.println("has errors");
            model.addAttribute(session.getAttribute("event"));
            //model.addAttribute("participant", new Participant());
            return "eventregister";
        }
        boolean aboveTwentyOne = dataSvc.compareDates(participant);
        Event event = (Event)session.getAttribute("event");
        boolean eventHasSpace = dataSvc.checkTickets(event, participant);
        if(aboveTwentyOne && eventHasSpace){
            dataSvc.updateEvent(event, participant);
            model.addAttribute("event",event);
            session.invalidate();
            return "SuccessRegistration";
        }
        else{
            model.addAttribute("aboveTwentyOne", aboveTwentyOne);
            model.addAttribute("eventHasSpace", eventHasSpace);
            session.invalidate();
            return "ErrorRegistration";
        }
    }
}
