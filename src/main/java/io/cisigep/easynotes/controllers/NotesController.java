package io.cisigep.easynotes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cisigep.easynotes.exceptions.ResourceNotFoundException;
import io.cisigep.easynotes.model.Note;
import io.cisigep.easynotes.repositories.NoteRepository;

@RestController
@RequestMapping("/api")
public class NotesController {
  
  @Autowired
  NoteRepository noteRepo;

  @GetMapping("/notes")
  public List<Note> getAllNotes() {
    return noteRepo.findAll();
  }
  
  @PostMapping("/notes")
  public Note createNote(@RequestBody @Valid Note note) {
    return noteRepo.save(note);
  }
  
  @GetMapping("/notes/{id}")
  public Note getNote(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
    return noteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
  }
  
  @PutMapping("/notes/{id}")
  public Note updateNote(@PathVariable(name = "id") Long id, @RequestBody @Valid Note noteUpdates) throws ResourceNotFoundException {
    Note toUpdate = noteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    toUpdate.setTitle(noteUpdates.getTitle());
    toUpdate.setContent(noteUpdates.getContent());
    
    noteRepo.save(toUpdate);
    
    
    return toUpdate;
  }
  
  @DeleteMapping("/notes/{id}")
  public ResponseEntity<?> deleteNote(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
    Note toDelete = noteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    
    noteRepo.delete(toDelete);
    
    return ResponseEntity.ok().build();
  }
  
}
