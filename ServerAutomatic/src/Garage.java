import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;


public class Garage {
public int state;
public int stateAlarm;
public static int BUSY = 0;
public static int OPENING = 1; // Per evitare la chiusura o l'apertura mentre il garage sta già lavorando 
public static int CLOSING = 2;
public static int ACTIVE = 1;
public static int DEACTIVE = 1;
Pin garageOn;
Pin garageOff;
Pin allarme;
GpioPinDigitalOutput GarageOn;
GpioPinDigitalOutput GarageOff;
GpioPinDigitalOutput Allarme;
GpioController gpio;
	
public Garage(GpioController gpio){
	this.gpio = gpio;
	this.state = BUSY;
	this.stateAlarm = BUSY;
	this.allarme = MainServer.allarme;
	this.garageOn = MainServer.garageOn;
	this.garageOff = MainServer.garageOff;
	GarageOn = gpio.provisionDigitalOutputPin(this.garageOn,"GarageAperto",PinState.LOW);
	GarageOff = gpio.provisionDigitalOutputPin(this.garageOff,"GarageChiuso",PinState.LOW);
	Allarme = gpio.provisionDigitalOutputPin(this.allarme,"allarme",PinState.LOW);
	};
	
public void apriGarage(int sec){
	if (state == BUSY){
		state = OPENING;
		GarageOn.high();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		GarageOn.low();
		MainServer.garage = true;
		state = BUSY;
	}
	return;
}

public void chiudiGarage(int sec){
	if (state == BUSY){
		state = CLOSING;
		GarageOff.high();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		GarageOff.low();
		MainServer.garage = false;
		state = BUSY;
	}
	return;
}


public void attivaAlarm(int sec){
	if (stateAlarm == BUSY){
		stateAlarm = ACTIVE;
		Allarme.high();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		Allarme.low();
		MainServer.allarm = true;
		stateAlarm = BUSY;
	}
	return;
}


public void disattivaAlarm(int sec){
	if (stateAlarm == BUSY){
		stateAlarm = DEACTIVE;
		Allarme.high();
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			System.out.println("sorry can't sleep");
			e.printStackTrace();
		}
		Allarme.low();
		MainServer.allarm = false;
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
