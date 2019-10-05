package com.vanchutin;

import com.vanchutin.dao.DeviceDao;
import com.vanchutin.event.ErrorEvent;
import com.vanchutin.event.Event;
import com.vanchutin.event.RestoreEvent;
import com.vanchutin.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.Queue;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	ApplicationService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");

		Queue<Event> eventQueue = new LinkedList<Event>();
		eventQueue.add(new ErrorEvent(1, 1));
		eventQueue.add(new ErrorEvent(1, 2));
		eventQueue.add(new ErrorEvent(1, 3));
		eventQueue.add(new RestoreEvent(1, 3));
		eventQueue.add(new RestoreEvent(1, 2));
		eventQueue.add(new RestoreEvent(1, 1));

		// process event queue
		service.processEventQueue(eventQueue);





	}
}
