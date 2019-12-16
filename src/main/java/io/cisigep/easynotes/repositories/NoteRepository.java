package io.cisigep.easynotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.cisigep.easynotes.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
