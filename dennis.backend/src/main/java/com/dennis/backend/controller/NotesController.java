package com.dennis.backend.controller;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dennis.backend.domain.Notes;
import com.dennis.backend.exception.ApiError;
import com.dennis.backend.services.INotesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notes")
@Api(tags = "Notes", value = "Notes Resource")
@Slf4j
public class NotesController {

	@Autowired
	INotesService service;

	@ApiOperation(value = "Get Notes By Id")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error", response = InvalidParameterException.class) })
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Notes> findById(@PathVariable(name = "id", required = true) Long id) {
		log.info("Get Notes by Id :" + id);

		return new ResponseEntity<Notes>(service.findById(id), HttpStatus.OK);

	}

	@ApiOperation(value = "Get list of Notes by Title")
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "title", dataType = "string", paramType = "query", value = "Title", required = true) })
	@GetMapping(path = "/title", produces = "application/json")
	public ResponseEntity<Object> findByTitle(@RequestParam(name = "title", required = true) String title) {

		log.info("Get List of Notes by Title:" + title);

		return new ResponseEntity<Object>(service.findByTitle(title), HttpStatus.OK);
	}

	@ApiOperation(value = "Get All Notes ")
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<Object> findByTitle() {

		return new ResponseEntity<Object>(service.findByAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Create Note Record")
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@PostMapping(path = "", produces = "application/json")
	public ResponseEntity<Notes> createRecord(@RequestBody Notes notes) {
		return new ResponseEntity<Notes>(service.createRecord(notes), HttpStatus.OK);
	}

	@ApiOperation(value = "Update Note Record")
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@PutMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Notes> updateRecord(@PathVariable(name = "id") long id,
			@RequestBody(required = true) Notes notes) {
		return new ResponseEntity<Notes>(service.updateRecord(notes), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Note Record")
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError.class) })
	@DeleteMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Notes> deleteRecord(@PathVariable(name = "id", required = true) Long id) {
		return new ResponseEntity<Notes>(service.deleteRecord(id), HttpStatus.OK);
	}
}
