package com.dennis.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dennis.backend.domain.Notes;
import com.dennis.backend.repository.NotesRepository;
import com.dennis.backend.services.INotesService;
import com.dennis.backend.services.NotesService;

@RunWith(SpringRunner.class)
public class NotesServiceTest {

	@TestConfiguration
	static class NotesServiceTestContextConfiguration {
		@Bean
		public INotesService notesService() {
			return new NotesService();
		}
	}

	@Autowired
	NotesService service;

	@MockBean
	private NotesRepository repository;

	@Before
	public void setup() {
		Notes note = new Notes();
		note.setId(1l);

		Mockito.when(repository.findById(note.getId())).thenReturn(Optional.of(note));

		Notes note1 = new Notes();
		note1.setTitle("title1");
		note1.setId(1l);

		Notes note2 = new Notes();
		note1.setTitle("title2");
		note1.setId(2l);

		List<Notes> notesList = new ArrayList<Notes>();
		notesList.add(note1);
		notesList.add(note2);

		Mockito.when(repository.findAll()).thenReturn(notesList);

		Mockito.when(repository.findByTitleContainingIgnoreCase("title")).thenReturn(notesList);

	}

	@Test
	public void whenValidId_thenRecordShouldBeFound() throws Exception {
		long id = 1;

		Notes found = service.findById(id);

		assertThat(found.getId()).isEqualTo(id);
	}

	@Test
	public void whenFindAllIsCalled_thenRecordsShouldBeFound() throws Exception {

		List<Notes> records = service.findByAll();

		assertThat(records.size()).isEqualTo(2);
	}

	@Test
	public void whenFindByTitleIsCalled_thenRecordsShouldBeFound() throws Exception {
		List<Notes> records = service.findByTitle("title");

		assertThat(records.size()).isEqualTo(2);
	}

	@Test
	public void whenCreateRecord_thenRecordsShouldCreated() throws Exception {
		Notes note1 = new Notes();
		note1.setTitle("title1");
		note1.setId(1l);

		Mockito.when(repository.save(Mockito.any(Notes.class))).thenReturn(new Notes());
		Notes record = service.createRecord(note1);

		assertThat(record).isNotNull();
	}

	@Test
	public void whenUpdateRecord_thenRecordsShouldNotBeNull() throws Exception {
		Notes note1 = new Notes();
		note1.setTitle("title1");
		note1.setId(1l);

		Mockito.when(repository.save(Mockito.any(Notes.class))).thenReturn(note1);
		Notes record = service.updateRecord(note1);

		assertThat(record).isNotNull();
	}

	@Test
	public void whenDeleteRecord_thenRecordsShouldNotBeNull() throws Exception {
		Notes note1 = new Notes();
		note1.setTitle("title1");
		note1.setId(1l);

		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(note1));
		Mockito.doNothing().when(repository).delete(note1);
		Notes record = service.deleteRecord(1l);

		assertThat(record).isNotNull();
	}
}
