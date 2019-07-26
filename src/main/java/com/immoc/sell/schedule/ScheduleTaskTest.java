package com.immoc.sell.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskTest {
	private static final Logger log = LoggerFactory.getLogger(ScheduleTaskTest.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate = 1000)
	public void reportCurrent () {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
}
