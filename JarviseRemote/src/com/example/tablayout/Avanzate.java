package com.example.tablayout;
import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class Avanzate extends Fragment implements OnClickListener{
	private static String nameFile = "jarvise.txt";
	EditText host;
	EditText user;
	EditText pass;
	EditText def1;
	EditText def2;
	EditText def3;
	EditText def4;
	Button save;
	Configuration readFile;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Personalizzato", "Selezionato");

    	this.readFile = new Configuration(nameFile);
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.avanzate, container, false);
        TextView textView = (TextView) view.findViewById(R.id.infoAvanzate);
        textView.setText("Opzioni Avanzate");
        save = (Button)	  view.findViewById(R.id.Salva);
        user = (EditText) view.findViewById(R.id.UserValore);
        host = (EditText) view.findViewById(R.id.hostValore);
        pass = (EditText) view.findViewById(R.id.passwordValore);
        def1 = (EditText) view.findViewById(R.id.default1Value);
        def2 = (EditText) view.findViewById(R.id.default2Value);
        def3 = (EditText) view.findViewById(R.id.default3Value);
        def4 = (EditText) view.findViewById(R.id.default4Value);
        save.setOnClickListener(this);
        return view;
    }

    
   
	@Override
	public void onClick(View button) {
		if(button == save){
			try {
				readFile.write2File(user.getText().toString(), pass.getText().toString(), host.getText().toString(), def1.getText().toString(), def2.getText().toString(), def3.getText().toString(), def4.getText().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}