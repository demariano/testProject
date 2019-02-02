package com.dennis.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dennis.backend.domain.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {

	List<Notes> findByTitleContainingIgnoreCase(String title);
}
