package com.example.mutants;

import com.example.mutants.controller.MutantController;
import com.example.mutants.controller.StatisticsController;
import com.example.mutants.dao.impl.MutantDaoImpl;
import com.example.mutants.repository.DnaRepository;
import com.example.mutants.service.MutantAnalyzeService;
import com.example.mutants.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MutantsApplicationTests {

	@Autowired
	private MutantsApplication app;

	@Autowired
	private MutantController mutantController;

	@Autowired
	private StatisticsController statisticsController;

	@Autowired
	private MutantDaoImpl mutantDao;

	@Autowired
	private DnaRepository repository;

	@Autowired
	private MutantAnalyzeService analyzeService;

	@Autowired
	private StatisticsService statisticsService;

	@Test
	void contextLoads() {
		assertThat(mutantController).isNotNull();
		assertThat(statisticsController).isNotNull();
		assertThat(mutantDao).isNotNull();
		assertThat(repository).isNotNull();
		assertThat(statisticsService).isNotNull();
		assertThat(analyzeService).isNotNull();
		assertThat(app).isNotNull();
	}

	@Test
	void testMainProcessStart() {
		MutantsApplication.main(new String[] {});
	}

}
