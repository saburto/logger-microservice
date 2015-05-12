package org.saburto.logger;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.saburto.logger.entity.Log;
import org.saburto.logger.entity.LogLevel;
import org.saburto.logger.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class ApplicationMain implements CommandLineRunner {

	@Autowired
	LogRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class);
	}

	@Override
	public void run(String... strings) throws Exception {
		Calendar calendar = Calendar.getInstance();
		LogLevel[] values = LogLevel.values();
		for (int i = 0; i < 100; i++) {
			Log entity = new Log();
			entity.setDate(calendar.getTime());
			entity.setLevel(values[new Random().nextInt(values.length-1)]);
			entity.setLogName("LogName" + i);
			entity.setMessage("message number " + i);
			
			calendar.add(Calendar.MILLISECOND, Math.abs(new Random().nextInt()));
			
			repository.save(entity);
		}
	}

}
