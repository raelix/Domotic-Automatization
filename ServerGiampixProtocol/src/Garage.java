import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;


public class Garage {
public int state;
public int stateAlarm;
public static int BUSY = 0;
public static int OPENING = 1; // Per evitare la chiusura o l'apertura mentre il garage sta gi� lavorando 
public static int CLOSING = 2;
public static int ACTIVE = 1;
public static int DEACTIVE = 1;
Pin garageOn;
Pin garageOff;
Pin allarmeGarage;
Pin allarmeCasa;
GpioPinDigitalOutput GarageOn;
GpioPinDigitalOutput GarageOff;
GpioPinDigitalOutput AllarmeGarage;
GpioPinDigitalOutput AllarmeCasa;
GpioController gpio;
	
public Garage(GpioController gpio){
	this.gpio = gpio;
	this.state = BUSY;
	this.stateAlarm = BUSY;
	this.allarmeGarage = MainServer.allarmeGarage;
	this.allarmeCasa   = MainServer.allarmeCasa;
	this.garageOn = MainServer.garageOn;
	this.garageOff = MainServer.garageOff;
	GarageOn = gpio.provisionDigitalOutputPin(this.garageOn,"GarageAperto",PinState.HIGH);
	GarageOff = gpio.provisionDigitalOutputPin(this.garageOff,"GarageChiuso",PinState.HIGH);
	AllarmeGarage = gpio.provisionDigitalOutputPin(this.allarmeGarage,"allarmeGarage",PinState.HIGH);
	AllarmeCasa = gpio.provisionDigitalOutputPin(this.allarmeCasa,"allarmeCasa",PinState.HIGH);
	};
	
public void apriGarage(int sec){
	if (state == BUSY){
		state = OPENING;
		GarageOn.low();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		GarageOn.high();
		MainServer.garage = true;
		state = BUSY;
	}
	return;
}

public void chiudiGarage(int sec){
	if (state == BUSY){
		state = CLOSING;
		GarageOff.low();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		GarageOff.high();
		MainServer.garage = false;
		state = BUSY;
	}
	return;
}


public void attivaAlarmGarage(int sec){
	if (stateAlarm == BUSY){
		stateAlarm = ACTIVE;
		AllarmeGarage.low();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		AllarmeGarage.high();
		MainServer.AllarmeGarage = true;
		stateAlarm = BUSY;
	}
	return;
}


public void disattivaAlarmGarage(int sec){
	if (stateAlarm == BUSY){
		stateAlarm = DEACTIVE;
		AllarmeGarage.low();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		AllarmeGarage.high();
		MainServer.AllarmeGarage = false;
		stateAlarm = BUSY;
	}
	return;
}

public void attivaAlarmCasa(int sec){
	if (stateAlarm == BUSY){
		stateAlarm = ACTIVE;
		AllarmeCasa.low();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		AllarmeCasa.high();
		MainServer.AllarmeCasa = true;
		stateAlarm = BUSY;
	}
	return;
}


public void disattivaAlarmCasa(int sec){
	if (stateAlarm == BUSY){
		stateAlarm = DEACTIVE;
		AllarmeCasa.low();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		AllarmeCasa.high();
		MainServer.AllarmeCasa = false;
		stateAlarm = BUSY;
	}
	return;
}

public int statoAllarme(){
	return stateAlarm;
}
public int statoGarage(){
	return state;
}
}
