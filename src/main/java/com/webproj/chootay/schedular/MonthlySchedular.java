package com.webproj.chootay.schedular;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.webproj.chootay.Model.Reciept;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.RecieptRepository;

@Configuration
@EnableScheduling
public class MonthlySchedular {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RecieptRepository recieptRepository;

	@Scheduled(cron = "0 0 12 1 * ?") // Every month on the 1st, at noon
	public void scheduleTaskUsingCronExpression() throws ResourceNotFoundException {

		List<Reciept> reciept = recieptRepository.getMonthyReport();

	}
}
