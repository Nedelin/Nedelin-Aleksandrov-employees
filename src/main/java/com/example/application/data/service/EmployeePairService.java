package com.example.application.data.service;

import com.example.application.data.entity.Employee;
import com.example.application.data.entity.EmployeePair;
import com.example.application.data.repository.EmployeePairRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeePairService {

	private final EmployeePairRepository employeePairRepository;

	public EmployeePairService(EmployeePairRepository employeePairRepository) {
		this.employeePairRepository = employeePairRepository;
	}

	public List<EmployeePair> findAll() {
		return employeePairRepository.findAll();
	}

	public void save(EmployeePair employeePair) {
		employeePairRepository.save(employeePair);
	}

	public void deleteAll() {
		employeePairRepository.deleteAll();
	}

	public void populateEmployeePair(List<Employee> employees) {
		for (int i = 0; i < employees.size(); i++) {
			for (int j = i + 1; j < employees.size(); j++) {
				if (employees.get(i).getProject().getProjectId() == employees.get(j).getProject().getProjectId()) {
					Date startDate1 = employees.get(i).getDateFrom();
					Date endDate1 = employees.get(i).getDateTo() == null ? new Date() : employees.get(i).getDateTo();
					Date startDate2 = employees.get(j).getDateFrom();
					Date endDate2 = employees.get(j).getDateTo() == null ? new Date() : employees.get(j).getDateTo();
					Date start = startDate1.compareTo(startDate2) < 0 ? startDate2 : startDate1;
					Date end = endDate1.compareTo(endDate2) < 0 ? endDate1 : endDate2;
					long dayOfWorks = -1;
					if (end.compareTo(start) >= 0) {
						long diffInMillies = Math.abs(end.getTime() - start.getTime());
						dayOfWorks = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
					}
					if (dayOfWorks != -1) {
						EmployeePair employeePair = new EmployeePair();
						employeePair.setEmployeeId1(employees.get(i).getEmployeeId());
						employeePair.setEmployeeId2(employees.get(j).getEmployeeId());
						employeePair.setProjectId(employees.get(i).getProject().getProjectId());
						employeePair.setWorkingDaysTogether(dayOfWorks);
						employeePairRepository.save(employeePair);
					}
				}
			}
		}
	}
}
