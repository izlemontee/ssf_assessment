package vttp.ssf.assessment.eventmanagement.services;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.models.Event;
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
                Date eventDate = new Date(jsonObject.getJsonNumber("eventDate").longValue());
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
    

}
