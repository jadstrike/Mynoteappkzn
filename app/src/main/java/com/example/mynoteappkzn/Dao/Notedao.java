package com.example.mynoteappkzn.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mynoteappkzn.Entities.Note;

import java.util.List;

@Dao
public interface Notedao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void insertNote(Note note);

    @Delete
    void deleteNote(Note note);
}
