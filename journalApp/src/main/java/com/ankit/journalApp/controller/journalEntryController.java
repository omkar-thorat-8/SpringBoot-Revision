package com.ankit.journalApp.controller;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
import org.springframework.web.service.annotation.PutExchange;

import com.ankit.journalApp.entity.JournalEntry;
import com.ankit.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
 
	@Autowired
	private JournalEntryService journalEntryService;
	private Optional<JournalEntry> byId;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		List<JournalEntry> allEntries = journalEntryService.getAll();
		
		if(allEntries != null && !allEntries.isEmpty()) {
			
			return new ResponseEntity<>(allEntries,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PostMapping
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
		
		try {
			
			myEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(myEntry);
			return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
					
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
;
	}

	@GetMapping("/id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

		if(journalEntry.isPresent()) {
			
			return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		
	}

	@DeleteMapping("/id/{myId}")
	public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
		
		journalEntryService.deleteById(myId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 
	}
	
	@PutMapping("/id/{myId}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId  myId, @RequestBody JournalEntry newEntry) {
		
		JournalEntry oldEntry = journalEntryService.findById(myId).orElse(null);

//		if(oldEntry != null) {
//			
//			oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
//			oldEntry.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : oldEntry.getContent());
//		}
		
		if (oldEntry != null) {

		    if (newEntry.getTitle() != null && !newEntry.getTitle().equals("")) {
		        oldEntry.setTitle(newEntry.getTitle());
		    }

		    if (newEntry.getContent() != null && !newEntry.getContent().equals("")) {
		        oldEntry.setContent(newEntry.getContent());
		    }
			journalEntryService.saveEntry(oldEntry);
			return new ResponseEntity<>(oldEntry, HttpStatus.OK);
		    
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	


	}
}
