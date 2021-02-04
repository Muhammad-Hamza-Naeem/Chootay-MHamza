package com.webproj.chootay.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webproj.chootay.Model.Reciept;

public interface RecieptRepository extends JpaRepository<Reciept, UUID> {

	@Query(value = " select a.* from reciept a " + 
			" where date >= now() - interval 1 day AND date < now()  " , nativeQuery = true)
	public List<Reciept> getDailyReport();
	
	@Query(value = " select month(date),sum(price)" +  "from reciept" +
			" group by month(date)" +" order by month(date)" , nativeQuery = true)
	public List<Reciept> getMonthyReport();
}
