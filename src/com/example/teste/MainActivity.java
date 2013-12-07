package com.example.teste;

import com.example.business.Nota;
import com.example.business.Notas;

import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private Notas notas;

	private final int NEW_NOTE = 1;
	private final int VIEW_NOTE = 2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View button = findViewById(R.id.activity_main_new);
        button.setOnClickListener(click);
        notas = new Notas();
        
        ListView lv = (ListView) findViewById(R.id.activity_main_list);
        lv.setOnItemClickListener(itemPicked);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    OnClickListener click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, NewNoteActivity.class);
			startActivityForResult(i, NEW_NOTE);
		}
	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
		case NEW_NOTE:
			if( resultCode == Activity.RESULT_OK ){
				Nota n = (Nota) data.getExtras().get("nota");
				notas.addNota(n);
				refreshList();
				Log.v("","return");
			}
			break;
		case VIEW_NOTE:
			//aqui editava-se a nota
			break;
		}
		
		
	};
	
	private void refreshList(){
		ArrayAdapter<Nota> lista = new ArrayAdapter<Nota>(this,
				android.R.layout.simple_list_item_1,
				notas.getNotas());
		
		ListView lv = (ListView) findViewById(R.id.activity_main_list);
		lv.setAdapter(lista);
	}
	
	OnItemClickListener itemPicked = new OnItemClickListener() {
		//arg0 é o array adapter
		//arg2 é o item onde clicámos
		// era o mesmo fazer arg0.get(arg2); ..ou algo do genero
		public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			Nota n = notas.getNotas().get(arg2);
			Intent i = new Intent(MainActivity.this, NewNoteActivity.class);
			i.putExtra("nota", n);
			startActivityForResult(i, VIEW_NOTE);
		};
	};
    
}
