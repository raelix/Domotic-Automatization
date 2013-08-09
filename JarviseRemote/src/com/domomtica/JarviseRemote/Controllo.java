package com.domomtica.JarviseRemote;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.domotica.JarviseRemote.R;
 
public class Controllo extends Fragment  implements OnClickListener {
	Button garageOn;
	Button garageOff;
	Button AllarmeCasaOn;
	Button AllarmeCasaOff;
	Button AllarmeGarageOn;
	Button AllarmeGarageOff;
	Button setgarageOn;
	Button setgarageOff;
	Button setAllarmeCasaOn;
	Button setAllarmeCasaOff;
	Button setAllarmeGarageOn;
	Button setAllarmeGarageOff;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Personalizzato", "Selezionato");
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.controllo, container, false);
        TextView textView = (TextView) view.findViewById(R.id.infoControlli);
//        textView.setText("CONTROLLO  REMOTO  RELE'");
        garageOn = (Button)view.findViewById(R.id.ControlloGarageOn);
		garageOff = (Button)view.findViewById(R.id.ControlloGarageOff);
		AllarmeCasaOn = (Button)view.findViewById(R.id.ControlloAllarmeCasaOff);
		AllarmeCasaOff = (Button)view.findViewById(R.id.ControlloAllarmeCasaOn);
		AllarmeGarageOn = (Button)view.findViewById(R.id.ControlloAllarmeGarageOn);
		AllarmeGarageOff = (Button)view.findViewById(R.id.ControlloAllarmeGarageOff);
		garageOn.setOnClickListener(this);
		garageOff.setOnClickListener(this);
		AllarmeCasaOn.setOnClickListener(this);
		AllarmeCasaOff.setOnClickListener(this);
		AllarmeGarageOn.setOnClickListener(this);
		AllarmeGarageOff.setOnClickListener(this);
		/*setgarageOn = (Button)view.findViewById(R.id.SetGarageOn);
		setgarageOff = (Button)view.findViewById(R.id.SetGarageOff);
		setAllarmeCasaOn = (Button)view.findViewById(R.id.SetAllarmeCasaOn);
		setAllarmeCasaOff = (Button)view.findViewById(R.id.SetAllarmeCasaOff);
		setAllarmeGarageOn = (Button)view.findViewById(R.id.SetAllarmeGarageOn);
		setAllarmeGarageOff = (Button)view.findViewById(R.id.SetAllarmeGarageOff);
		setgarageOn.setOnClickListener(this);
		setgarageOff.setOnClickListener(this);
		setAllarmeCasaOn.setOnClickListener(this);
		setAllarmeCasaOff.setOnClickListener(this);
		setAllarmeGarageOn.setOnClickListener(this);
		setAllarmeGarageOff.setOnClickListener(this);
        */return view;
    }

	@Override
	public void onClick(View button) {
		if(button == garageOn){
			AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity()); 
			builder.setMessage("Sei sicuro di voler aprire il Garage?").setCancelable(false); 
			builder.setPositiveButton("Si", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					new ProgressMessageTask().execute("");
					new ToastMessageTask().execute("Provo ad aprire il garage");
					new MultiThread("127.0.0.1", 9001,new PaccoGpio(4, 1));
					
					 }});
			
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) { 
				dialog.cancel();      } 
			});
			AlertDialog alert = builder.create();  
			alert.show();
		}
		if(button == garageOff){
			AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity()); 
			builder.setMessage("Sei sicuro di voler chiudere il Garage?").setCancelable(false); 
			builder.setPositiveButton("Si", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					new ProgressMessageTask().execute("");
					new ToastMessageTask().execute("Provo a chiudere il garage");
					new MultiThread("127.0.0.1", 9001,new PaccoGpio(5, 0));
					 }});
			
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) { 
				dialog.cancel();      } 
			});
			AlertDialog alert = builder.create();  
			alert.show();
		}
		if(button == AllarmeCasaOn){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(7, 0));//FIXED BUTTON 
		}
		if(button == AllarmeCasaOff){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(7, 1));//FIXED BUTTON 
		}
		if(button == AllarmeGarageOff){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(6, 0));
		}
		if(button == AllarmeGarageOn){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(6, 1));
		}
		if(button == setgarageOn){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(4, 3));
		}
		if(button == setgarageOff){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(5, 4));
		}
		if(button == setAllarmeCasaOn){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(7, 3));
		}
		if(button == setAllarmeCasaOff){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(7, 4));
		}
		if(button == setAllarmeGarageOff){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(6, 4));
		}
		if(button == setAllarmeGarageOn){
			new MultiThread("127.0.0.1", 9001,new PaccoGpio(6, 3));
		}
		
	}
}