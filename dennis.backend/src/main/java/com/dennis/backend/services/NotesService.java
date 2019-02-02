package com.dennis.backend.services;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dennis.backend.domain.Notes;
import com.dennis.backend.repository.NotesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotesService implements INotesService {

	@Autowired
	NotesRepository repository;

	@Override
	public Notes findById(Long id) {

		Optional<Notes> record = repository.findById(id);
		if (record.isPresent()) {
			return record.get();
		} else {
			log.error("Notes ID: " + id + " does not exist");
			throw new InvalidParameterException("Notes ID: " + id + " does not exist");
		}

	}

	@Override
	public List<Notes> findByTitle(String title) {
		return repository.findByTitleContainingIgnoreCase(title);
	}

	@Override
	public Notes createRecord(Notes note) {
		return repository.save(note);
	}

	@Override
	public Notes updateRecord(Notes note) {

		Optional<Notes> record = repository.findById(note.getId());

		if (record.isPresent()) {
			return repository.save(note);
		} else {
			log.error("Notes ID: " + note.getId() + " does not exist");
			throw new InvalidParameterException("Notes ID: " + note.getId() + " does not exist");
		}

	}

	@Override
	public Notes deleteRecord(Long id) {
		Optional<Notes> record = repository.findById(id);

		if (record.isPresent()) {

			Notes existingRecord = record.get();
			repository.delete(existingRecord);
			return existingRecord;
		} else {
			log.error("Notes ID: " + id + " does not exist");
			throw new InvalidParameterException("Notes ID: " + id + " does not exist");
		}
	}

	@Override
	public List<Notes> findByAll() {
		return repository.findAll();
	}

}
