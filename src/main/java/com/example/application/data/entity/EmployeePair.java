package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "employees_pair")
public class EmployeePair extends AbstractEntity {

	private Integer employeeId1;
	private Integer employeeId2;
	private Integer projectId;
	private Long workingDaysTogether;

	public Integer getEmployeeId1() {
		return employeeId1;
	}

	public void setEmployeeId1(Integer employeeId1) {
		this.employeeId1 = employeeId1;
	}

	public Integer getEmployeeId2() {
		return employeeId2;
	}

	public void setEmployeeId2(Integer employeeId2) {
		this.employeeId2 = employeeId2;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Long getWorkingDaysTogether() {
		return workingDaysTogether;
	}

	public void setWorkingDaysTogether(long workingDaysTogether) {
		this.workingDaysTogether = workingDaysTogether;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		EmployeePair that = (EmployeePair) o;
		return Objects.equals(employeeId1, that.employeeId1) && Objects.equals(employeeId2,
		  that.employeeId2) && Objects.equals(projectId, that.projectId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), employeeId1, employeeId2, projectId);
	}
}
