package com.example.jarvisebutton;




import com.example.jarvisebutton.R;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	static TextToSpeech talker;
	Button default0on;
	Button default0off ;
	Button default1on;
	Button default1off;
	Button default2on;
	Button default2off;
	Button default3on ;
	Button default3off ;
	Button garageOn;
	Button garageOff;
	Button allarmeGarageOn;
	Button allarmeGarageOff;
	Button allarmeCasaOn;
	Button allarmeCasaOff;
	Button acquaAcquario;
	Button acquaCasa;
	Button move;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		default0on = (Button) findViewById(R.id.button1);
		default0off = (Button) findViewById(R.id.Button01);
		default1on = (Button) findViewById(R.id.Button02);
		default1off = (Button) findViewById(R.id.Button03);
		default2on = (Button) findViewById(R.id.Button04);
		default2off = (Button) findViewById(R.id.Button05);
		default3on = (Button) findViewById(R.id.Button06);
		default3off = (Button) findViewById(R.id.Button07);
		garageOn = (Button) findViewById(R.id.Button08);
		garageOff = (Button) findViewById(R.id.Button09);
		allarmeGarageOn = (Button) findViewById(R.id.Button10);
		allarmeGarageOff = (Button) findViewById(R.id.Button11);
		allarmeCasaOn = (Button) findViewById(R.id.Button12);
		allarmeCasaOff = (Button) findViewById(R.id.Button13);
		acquaAcquario = (Button) findViewById(R.id.Button14);
		acquaCasa = (Button) findViewById(R.id.Button15);
		move = (Button) findViewById(R.id.Button16);
		default0off.setOnClickListener(this);
		default0on.setOnClickListener(this);
		default1off.setOnClickListener(this);
		default1on.setOnClickListener(this);
		default2off.setOnClickListener(this);
		default2on.setOnClickListener(this);
		default3off.setOnClickListener(this);
		default3on.setOnClickListener(this);
		garageOn.setOnClickListener(this);
		garageOff.setOnClickListener(this);
		allarmeCasaOff.setOnClickListener(this);
		allarmeCasaOn.setOnClickListener(this);
		allarmeGarageOff.setOnClickListener(this);
		allarmeGarageOn.setOnClickListener(this);
		acquaAcquario.setOnClickListener(this);
		acquaCasa.setOnClickListener(this);
		move.setOnClickListener(this);
	}

	public void log(String log){
		Toast.makeText(this, log, Toast.LENGTH_LONG).show();
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == default0off ){System.out.println("mbare");log("ciaooo");}
		if (arg0 == default0on ){}
		if (arg0 == default1off ){}
		if (arg0 == default1on ){}
		if (arg0 == default2off ){}
		if (arg0 == default2on ){}
		if (arg0 == default3off ){}
		if (arg0 == default3on ){}
		if (arg0 == garageOn ){}
		if (arg0 == garageOff ){}
		if (arg0 == allarmeGarageOff ){}
		if (arg0 == allarmeGarageOn ){}
		if (arg0 == allarmeCasaOff ){}
		if (arg0 == allarmeCasaOn ){}
		if (arg0 == acquaAcquario ){}
		if (arg0 == acquaCasa ){}
		if (arg0 == move ){}

	}

}
