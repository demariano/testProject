package com.dennis.backend.services;

import java.security.InvalidParameterException;
import java.util.List;

import com.dennis.backend.domain.Notes;

public interface INotesService {

	Notes findById(Long id) throws InvalidParameterException;

	List<Notes> findByTitle(String title);
	
	List<Notes> findByAll();

	Notes createRecord(Notes note) throws InvalidParameterException;;

	Notes updateRecord(Notes note) throws InvalidParameterException;;

	Notes deleteRecord(Long id) throws InvalidParameterException;;
}
