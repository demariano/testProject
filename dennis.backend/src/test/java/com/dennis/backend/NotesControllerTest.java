package com.dennis.backend;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.dennis.backend.controller.NotesController;
import com.dennis.backend.domain.Notes;
import com.dennis.backend.services.INotesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NotesController.class)
@EnableSpringDataWebSupport
public class NotesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private INotesService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenBuyer_whenGetBuyer_thenReturnJson() throws Exception {
		Notes note = new Notes();
		note.setId(1l);

		given(service.findById(note.getId())).willReturn(note);

		mockMvc.perform(get("/notes/{id}", note.getId()).contentType(MediaType.APPLICATION_JSON).header("Origin","*"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void findAll_thenReturnJson() throws Exception {

		Notes note = new Notes();
		note.setId(1l);

		Notes note2 = new Notes();
		note2.setId(1l);

		List<Notes> notes = new ArrayList<Notes>();
		notes.add(note);
		notes.add(note2);

		given(service.findByAll()).willReturn(notes);

		mockMvc.perform(get("/notes").contentType(MediaType.APPLICATION_JSON).header("Origin","*")).andExpect(status().isOk());

	}

	@Test
	public void givenValidTitle_thenReturnJson() throws Exception {

		Notes note = new Notes();
		note.setId(1l);

		Notes note2 = new Notes();
		note2.setId(1l);

		List<Notes> notes = new ArrayList<Notes>();
		notes.add(note);
		notes.add(note2);

		given(service.findByTitle("title")).willReturn(notes);

		mockMvc.perform(get("/notes/title").param("title", "title").contentType(MediaType.APPLICATION_JSON).header("Origin","*"))
				.andExpect(status().isOk());

	}

	@Test
	public void givenValidRecord_thenOk() throws Exception {

		Notes note = new Notes();
		note.setId(1l);

		given(service.createRecord(note)).willReturn(note);

		String recordString = objectMapper.writeValueAsString(note);

		mockMvc.perform(post("/notes").content(recordString)
				.contentType(MediaType.APPLICATION_JSON).header("Origin","*")).andExpect(status().isOk());

	}

	@Test
	public void givenValidRecordForUpdate_thenOk() throws Exception {

		Notes note = new Notes();
		note.setId(1l);

		given(service.updateRecord(note)).willReturn(note);

		String recordString = objectMapper.writeValueAsString(note);

		mockMvc.perform(put("/notes/1").content(recordString)
				.contentType(MediaType.APPLICATION_JSON).header("Origin","*")).andExpect(status().isOk());

	}
	
	@Test
	public void givenValidRecordForDelete_thenOk() throws Exception {

		Notes note = new Notes();
		note.setId(1l);

		given(service.deleteRecord(1l)).willReturn(note);

	

		mockMvc.perform(delete("/notes/1")
				.contentType(MediaType.APPLICATION_JSON).header("Origin","*")).andExpect(status().isOk());

	}
}
