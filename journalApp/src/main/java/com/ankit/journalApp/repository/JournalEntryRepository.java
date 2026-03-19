package com.ankit.journalApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ankit.journalApp.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
