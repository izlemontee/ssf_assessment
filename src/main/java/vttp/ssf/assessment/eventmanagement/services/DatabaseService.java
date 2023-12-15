package vttp.ssf.assessment.eventmanagement.services;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Participant;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;



@Service
public class DatabaseService {

    private final String EVENTS_DIR = "static/events.json";

    @Autowired
    RedisRepository redisRepo;
    
    

    // TODO: Task 1
    public List<Event> readFile(String fileName)throws Exception{
       
        Resource resource = new ClassPathResource(fileName);
        try(InputStream is = resource.getInputStream()){
            System.out.println("File read");
            List<Event> eventList = new ArrayList<>();
            JsonReader jsonReader = Json.createReader(is);
            JsonArray jsonArray = jsonReader.readArray();
            for(int i=0; i<jsonArray.size();i++){
                JsonObject jsonObject = jsonArray.getJsonObject(i);
                System.out.println(jsonObject);
                Integer eventId = jsonObject.getInt("eventId");
                String eventName = jsonObject.getString("eventName");
                Integer eventSize = jsonObject.getInt("eventSize");
                //Date eventDate = new Date(jsonObject.getJsonNumber("eventDate").longValue());
                Long eventDate = jsonObject.getJsonNumber("eventDate").longValue();
                Integer participants = jsonObject.getInt("participants");
                Event event = new Event(eventId, eventName, eventSize, eventDate, participants);
                eventList.add(event);
            }
            System.out.println("Success");
            System.out.println("List size: "+eventList.size());
            // jsonArray.stream()
            //     .map(j -> j.asJsonObject());
            
            return eventList;
        }
    }

    public List<Event> getEvents(){
        Long eventSize = redisRepo.getNumberOfEvents();
        List<Event> eventList = new ArrayList<>();
        for(int i = 0; i<eventSize; i++){
            Event event = redisRepo.getEvent(i);
            eventList.add(event);
        }
        return eventList;
    }

    public boolean compareDates(Participant participant){
        Date now = new Date();
        Date dob = participant.getDob();
        Long nowTime = now.getTime();
        Long dobTime = dob.getTime();
        System.out.println("More than 21 years: "+((nowTime-dobTime)>Utils.TWENTYONE_YEARS));
        return ((nowTime-dobTime)>Utils.TWENTYONE_YEARS);
    }

    public boolean checkTickets(Event event, Participant participant){
        Integer eventParticipants = event.getParticipants();
        Integer tickets = participant.getTickets();
        Integer eventSize = event.getEventSize();
        return (eventSize >= eventParticipants+tickets);
    }
    
    public void updateEvent(Event event, Participant participant){
        Integer eventId = event.getEventId();
        Integer indexInRedis = 4-eventId; 
        
        Integer eventParticipants = event.getParticipants();
        Integer tickets = participant.getTickets();
        event.setParticipants(eventParticipants+tickets);
        redisRepo.updateEvent(indexInRedis, event);
    }

}
