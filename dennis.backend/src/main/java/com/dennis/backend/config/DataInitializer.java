package com.dennis.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dennis.backend.domain.Notes;
import com.dennis.backend.repository.NotesRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	NotesRepository repository;

	@Override
	public void run(String... args) throws Exception {

		Notes record1 = Notes.builder().title("Title 1").content(
				"Lorem ipsum dolor sit amet, harum intellegebat duo an, in per illum eleifend contentiones. Vivendo forensibus mel eu, eruditi copiosae nec ex, gubergren definiebas eam in. Aeque causae vix id, eos vide homero altera et. An facete quaeque aliquam has.")
				.build();
		
		new Notes();
		Notes record2 = Notes.builder().title("Title 2").content(
				"Ne quaeque abhorreant has, cu vix solet labores concludaturque, vim te modus quidam voluptaria. Eos brute impedit ad. Per ferri dicant et, homero latine ad eam. Veniam diceret an eos.")
				.build();
		
		Notes record3 = Notes.builder().title("Title 3").content(
				"Diceret epicuri signiferumque usu ut, id liber detracto quaestio vix. Ei mazim quaestio hendrerit eam, ei est sint veritus. Eum te sint animal, pertinacia definitionem no pri. Vix eius exerci adversarium ea, qui aeque repudiandae te.")
				.build();
		
		repository.save(record1);
		repository.save(record2);
		repository.save(record3);
			

	}

}
