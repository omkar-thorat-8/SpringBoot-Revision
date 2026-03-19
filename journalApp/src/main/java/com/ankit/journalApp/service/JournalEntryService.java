package com.ankit.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ankit.journalApp.entity.JournalEntry;
import com.ankit.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	public void saveEntry(JournalEntry journalEntry) {
		
		 journalEntryRepository.save(journalEntry);
;	}
}
