package com.example.application.data.service;

import com.example.application.data.entity.Project;
import com.example.application.data.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void save(Project project) {
		projectRepository.save(project);
	}

	public void deleteAll() {
		projectRepository.deleteAll();
	}

}
