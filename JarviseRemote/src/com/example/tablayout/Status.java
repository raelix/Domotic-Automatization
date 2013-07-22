package com.example.tablayout;
import java.util.concurrent.Semaphore;

import com.example.tablayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Status extends Fragment implements OnClickListener{
	private static String nameFile = "jarvise.txt";
	TextView def1;
	TextView def2;
	TextView def3;
	TextView def4;
	String default1;
	String default2;
	String default3;
	String default4;
	Button getStatus;
	ToggleButton personalizzato1;
	ToggleButton personalizzato2;
	ToggleButton personalizzato3;
	ToggleButton personalizzato4;
	ToggleButton personalizzato5;
	ToggleButton personalizzato6;
	ToggleButton personalizzato7;
	ToggleButton personalizzato8;
	ToggleButton personalizzato9;
	ToggleButton personalizzato10;
	ToggleButton personalizzato11;
	TextView errore;
	static Activity thisActivity = null;
	Configuration readFile;
	int [] status;
	View view;
	Semaphore sem = new Semaphore(0, true);
	MultiThread thread;
	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Controlli", "Tutti");
		this.thisActivity = this.getActivity();
		this.readFile = new Configuration(nameFile);
		this.default1 = readFile.getDefault0(); 
		this.default2 = readFile.getDefault1();
		this.default3 = readFile.getDefault2();
		this.default4 = readFile.getDefault3();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 view = inflater.inflate(R.layout.status0, container, false);
		TextView textView = (TextView) view.findViewById(R.id.infoStatus);
		textView.setText("Status Gpio");
		getStatus = (Button) view.findViewById(R.id.getStatus);
		getStatus.setOnClickListener(this);
		return view;
	}
	
	public static void log(String log){
		Toast.makeText(thisActivity, log, Toast.LENGTH_LONG).show();

	}
	


public void checkButton(ToggleButton personale){
	if(personale.isChecked())
	{
		personale.setBackgroundResource(R.layout.acceso);
		personale.setText("Acceso");
	}
	else
	{
		personale.setBackgroundResource(R.layout.spento);
		personale.setText("Spento");
	}
}

public void checkButtonAlarm(ToggleButton personale, String log){
	if(personale.isChecked())
	{
		personale.setBackgroundResource(R.layout.spento);
		personale.setText(log);
	}
	else
	{
		personale.setBackgroundResource(R.layout.acceso);
		personale.setText(log);
	}
}

	
	
	@SuppressWarnings("static-access")
	@Override
	public void onClick(View button) {

		if(button == getStatus){
			log("Waiting refresh....");
			
			errore =(TextView) view.findViewById(R.id.erroreConnessione);
			
			try {
			thread = new MultiThread("127.0.0.1", 9001,new PaccoStatus(1), sem);
			sem.acquire();
			}
			catch (InterruptedException e) {
			errore.setText("Non riesco a raggiungere il Server di Casa");
			log("Nessun server trovato");
			System.out.println("Non riesco a raggiungere il Server di Casa");
			e.printStackTrace();
			return;
			}
		if(thread.errore){
			errore.setText("Non riesco a raggiungere il Server di Casa");
			log("Nessun server trovato");
			System.out.println("Non riesco a raggiungere il Server di Casa");
			return;
		} else {
			((ViewGroup) view).removeAllViews();

			view = getView().inflate(getView().getContext(), R.layout.status,((ViewGroup) view) );	

			personalizzato1 = (ToggleButton) view.findViewById(R.id.pesonalizzato1);
			personalizzato2 = (ToggleButton) view.findViewById(R.id.pesonalizzato2);
			personalizzato3 = (ToggleButton) view.findViewById(R.id.pesonalizzato3);
			personalizzato4 = (ToggleButton) view.findViewById(R.id.pesonalizzato4);
			personalizzato5 = (ToggleButton) view.findViewById(R.id.ControlloGarage);
			personalizzato6 = (ToggleButton) view.findViewById(R.id.ControlloAllarmeGarage);
			personalizzato7 = (ToggleButton) view.findViewById(R.id.ControlloAllarmeCasa);
			personalizzato8 = (ToggleButton) view.findViewById(R.id.ControlloPerditaAcquarioButton);
			personalizzato9 = (ToggleButton) view.findViewById(R.id.ControlloPerditaCasaButton);
			personalizzato10 = (ToggleButton) view.findViewById(R.id.ControlloMovimentoCasaButton);
			personalizzato1.setClickable(false);
			personalizzato2.setClickable(false);
			personalizzato3.setClickable(false);
			personalizzato4.setClickable(false);
			personalizzato5.setClickable(false);
			personalizzato6.setClickable(false);
			personalizzato7.setClickable(false);
			personalizzato8.setClickable(false);
			personalizzato9.setClickable(false);
			personalizzato10.setClickable(false);
			
//			int [] prova = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
			status = thread.settings;
//			status = prova;
			if(status[0] == 0){
				personalizzato1.setChecked(false);
				checkButton(personalizzato1);
			}
			else if(status[0] == 1){
				personalizzato1.setChecked(true);
				checkButton(personalizzato1);
			}
			
			if(status[1] == 0){
				personalizzato2.setChecked(false);
				checkButton(personalizzato2);
			}
			else if(status[1]==1){
				personalizzato2.setChecked(true);
				checkButton(personalizzato2);
			}
			
			if(status[2] == 0){
				personalizzato3.setChecked(false);
				checkButton(personalizzato3);
			}
			else if(status[2]==1){
				personalizzato3.setChecked(true);
				checkButton(personalizzato3);
			}
			
			if(status[3] == 0){
				personalizzato4.setChecked(false);
				checkButton(personalizzato4);
			}
			else if(status[3]==1){
				personalizzato4.setChecked(true);
				checkButton(personalizzato4);
			}
			
			if(status[4] == 0){
				personalizzato5.setChecked(false);
				checkButton(personalizzato5);
			}
			else if(status[4]==1){
				personalizzato5.setChecked(true);
				checkButton(personalizzato5);
			}
			
			if(status[5] == 0){
				personalizzato6.setChecked(false);
				checkButton(personalizzato6);
			}
			else if(status[5]==1){
				personalizzato6.setChecked(true);
				checkButton(personalizzato6);
			}
			
			if(status[6] == 0){
				personalizzato7.setChecked(false);
				checkButton(personalizzato7);
			}
			else if(status[6]==1){
				personalizzato7.setChecked(true);
				checkButton(personalizzato7);
			}
			
			if(status[7] == 0){
				personalizzato8.setChecked(false);
				checkButtonAlarm(personalizzato8,"OK");
			}
			else if(status[7]==1){
				personalizzato8.setChecked(true);
				checkButtonAlarm(personalizzato8,"allarme");
			}
			
			if(status[8] == 0){
				personalizzato9.setChecked(false);
				checkButtonAlarm(personalizzato9,"OK");
			}
			else if(status[8]==1){
				personalizzato9.setChecked(true);
				checkButtonAlarm(personalizzato9,"allarme");
			}

			if(status[9] == 0){
				personalizzato10.setChecked(false);
				checkButtonAlarm(personalizzato10,"OK");
			}
			else if(status[9]==1){
				personalizzato10.setChecked(true);
				checkButtonAlarm(personalizzato10,"allarme");
			}
		}

			

		}
		
		
		
		
		
		
		
		
	}
	// TODO Auto-generated method stub

}
