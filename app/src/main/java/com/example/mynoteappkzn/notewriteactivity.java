package com.example.mynoteappkzn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynoteappkzn.Entities.Note;
import com.example.mynoteappkzn.database.NotesDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class notewriteactivity extends AppCompatActivity {
    EditText edtTitle,edtNoteText;
    TextView txtTitle,txtDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notewriteactivity);

        edtTitle = findViewById(R.id.edt_title);
        edtNoteText = findViewById(R.id.edt_note_text);
        txtTitle = findViewById(R.id.text_title);
        txtDateTime = findViewById(R.id.text_date);

        txtDateTime.setText(
                new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );
    }

    public void saveOnClick(View view) {
        saveNote();
    }

    private void saveNote(){
        if(edtTitle.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Empty Title", Toast.LENGTH_SHORT).show();
        }else if (edtNoteText.getText().toString().isEmpty()){
            Toast.makeText(this,"Empty Note text",Toast.LENGTH_SHORT).show();
        }
        final Note note = new Note();
        note.setTitle(edtTitle.getText().toString());
        note.setNoteText(edtNoteText.getText().toString());
        note.setDatetime(txtDateTime.getText().toString());

        class SaveNoteTask extends AsyncTask<Void,Void,Void>{


            @Override
            protected Void doInBackground(Void... voids) {
                NotesDatabase.getDatabase(getApplicationContext()).notedao().insertNote(note);
                return null;
            }
            @Override
            protected void  onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
        new SaveNoteTask().execute();


    }
}