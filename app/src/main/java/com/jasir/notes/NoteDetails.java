package com.jasir.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jasir.notes.Models.Notes;

public class NoteDetails extends AppCompatActivity {
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        id = i.getLongExtra("ID",0);
        NotesDatabase nDB=new NotesDatabase(this);
        Notes notes=nDB.getNote(id);//get single note
        TextView noteDetails=findViewById(R.id.noteDetails);
        noteDetails.setText(notes.getContent().toString());
        noteDetails.setMovementMethod(new ScrollingMovementMethod());
        getSupportActionBar().setTitle(notes.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit){
            Intent i = new Intent(this, EditNotes.class);
            i.putExtra("ID",id);
            startActivity(i);


        }
        else if(item.getItemId()== R.id.delete)
        {
            NotesDatabase db = new NotesDatabase(getApplicationContext());
            db.deleteNote(id);
            Toast.makeText(getApplicationContext(),"Note Deleted", Toast.LENGTH_SHORT).show();
            goToMain();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
