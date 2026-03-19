package com.ankit.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping
	public List<JournalEntry> getAll() {
		return null;
	}

	@PostMapping
	public String createEntry(@RequestBody JournalEntry myEntry) {
 
		journalEntryService.saveEntry(myEntry);
		return "Data Storeed";
	}

	@GetMapping("/id/{myId}")
	public JournalEntry getJournalEntryById(@PathVariable Long myId) {
		return null;

	}

	@DeleteMapping("/id/{myId}")
	public JournalEntry deleteJournalEntryById(@PathVariable Long myId) {
		return null;

	}
	
	@PutMapping("/id/{myId}")
	public JournalEntry updateJournalEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry) {
		return myEntry;

	}
}
