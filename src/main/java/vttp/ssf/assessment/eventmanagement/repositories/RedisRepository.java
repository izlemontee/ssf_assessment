package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.assessment.eventmanagement.Utils;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired @Qualifier(Utils.REDIS)//ask spring to look for something, look for myredis bean
	private RedisTemplate<String,Object> template;

	// deletes the events list if it alr has 4 events to avoid overcrowding
	public void delete(){
		if(getNumberOfEvents()>=4){
		template.delete("events");
		}
	}

	// TODO: Task 2
	public void saveRecord(Event event){

		template.opsForList().rightPush("events", event);
	}


	// TODO: Task 3
	public Long getNumberOfEvents(){
		return template.opsForList().size("events");

	}


	// TODO: Task 4
	public void getEvent(Integer index){
		Event event = (Event)template.opsForList().index("events",index);
		System.out.println("Event: "+event);
	}
}
