package com.example.application.views;

import com.example.application.data.entity.Employee;
import com.example.application.data.entity.EmployeePair;
import com.example.application.data.entity.Project;
import com.example.application.data.service.EmployeePairService;
import com.example.application.data.service.EmployeeService;
import com.example.application.data.service.ProjectService;
import com.example.application.data.utils.JavaDateUtils;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Route(value = "")
public class MainView extends VerticalLayout {

	private Grid<EmployeePair> gridBord = new Grid<>(EmployeePair.class);
	private final ProjectService projectService;
	private final EmployeeService employeeService;
	private final EmployeePairService employeePairService;

	public MainView(ProjectService projectService, EmployeeService employeeService,
	  EmployeePairService employeePairService) {
		this.projectService = projectService;
		this.employeeService = employeeService;
		this.employeePairService = employeePairService;
		MemoryBuffer buffer = new MemoryBuffer();
		Upload upload = new Upload(buffer);
		upload.setAcceptedFileTypes(".csv");
		upload.addSucceededListener(e -> {
			employeePairService.deleteAll();
			employeeService.deleteAll();
			projectService.deleteAll();
			displayCsv(buffer.getInputStream());
			updateList();
		});
		configureGrid();
		add(upload, gridBord);
	}

	private void displayCsv(InputStream resourceAsStream) {
		// Change the separator if needed to something else (for example, to ',').
		CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
		CSVReader reader =
		  new CSVReaderBuilder(new InputStreamReader(resourceAsStream)).withCSVParser(parser).build();
		try {
			List<String[]> entries = reader.readAll();
			List<Employee> employees = new LinkedList<>();
			for (int i = 0; i < entries.size(); i++) {
				Employee employee = new Employee();
				Project project = new Project();

				String[] item = entries.get(i)[0].split(",");
				employee.setEmployeeId(Integer.valueOf(item[0]));
				project.setProjectId(Integer.valueOf(item[1]));
				employee.setProject(project);
				employee.setDateFrom(JavaDateUtils.parse(item[2]));
				employee.setDateTo(JavaDateUtils.parse(item[3]));
				employees.add(employee);
				project.setEmployees(employees);
				projectService.save(project);
				employeeService.save(employee);
			}
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
	}

	private void updateList() {
		employeePairService.populateEmployeePair(employeeService.findAll());
		gridBord.setItems(employeePairService.findAll());
	}

	private void configureGrid() {
		gridBord.addClassName("employee-grid");
		gridBord.removeAllColumns();
		List<Employee> employees = employeeService.findAll();
		gridBord.addColumn(id -> {
			return id.getEmployeeId1();
		}).setHeader("Employee ID #1");
		gridBord.addColumn(id -> {
			return id.getEmployeeId2();
		}).setHeader("Employee ID #2");
		gridBord.addColumn(project -> {
			return project.getProjectId();
		}).setHeader("Project ID");
		gridBord.addColumn(workingDaysTogether -> {
			return workingDaysTogether.getWorkingDaysTogether();
		}).setHeader("Days worked Together");
	}
}