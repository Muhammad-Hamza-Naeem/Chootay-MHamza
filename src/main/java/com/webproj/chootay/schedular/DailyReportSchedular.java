package com.webproj.chootay.schedular;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.webproj.chootay.Model.Reciept;
import com.webproj.chootay.Model.Repair;
import com.webproj.chootay.exception.ResourceNotFoundException;
import com.webproj.chootay.repository.AdminRepository;
import com.webproj.chootay.repository.RecieptRepository;

@Configuration
@EnableScheduling
public class DailyReportSchedular {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RecieptRepository recieptRepository;

	@Scheduled(cron = "0 0 6 * * ?") // @Scheduled(fixedDelay = 1000)
	public void scheduleTaskUsingCronExpression() throws ResourceNotFoundException {

		List<Reciept> reciept = recieptRepository.getDailyReport();
	}
}
