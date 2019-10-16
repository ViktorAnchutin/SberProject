package com.vanchutin;

import com.vanchutin.dao.DeviceDao;
import com.vanchutin.event.*;
import com.vanchutin.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	ApplicationService service;

	@Autowired
	EventFactory eventFactory;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Queue<Event> eventQueue = new LinkedList<Event>();
		eventQueue.add(eventFactory.getEvent(EventType.ERROR, 1, 1));
		eventQueue.add(eventFactory.getEvent(EventType.ERROR, 1, 2));
		eventQueue.add(eventFactory.getEvent(EventType.ERROR, 1, 3));
		eventQueue.add(eventFactory.getEvent(EventType.RESTORE, 1, 3));
		eventQueue.add(eventFactory.getEvent(EventType.RESTORE, 1, 2));
		eventQueue.add(eventFactory.getEvent(EventType.RESTORE, 1, 1));

		// process event queue
		//service.processEventQueue(eventQueue);





	}
}
