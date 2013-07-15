

import java.util.Calendar;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;




	public class ThreadListenGpio extends Thread {
		private GpioController sensore;
		private GpioPinDigitalInput movimento;
		private Pin pin;
		
		public ThreadListenGpio (GpioController sensore) {
			super();
			this.pin = MainServer.move;
			this.sensore = sensore;
			if(MainServer.movimento != null)
				System.out.println("Thread Gpio "+"ultimo movimento "+MainServer.movimento);
			else System.out.println("Thread Gpio "+"nessun movimento fin'ora");
			this.run();
		};

		@Override
		public void run () {

			movimento = sensore.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
			
			movimento.addListener(new GpioPinListenerDigital() {
				@Override
				public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
					System.out.println("Il sensore ha cambiato stato : " + event.getPin() + " = " + event.getState());
					MainServer.file.write("Il sensore ha cambiato stato : " + event.getPin() + " = " + event.getState());
					MainServer.movimento = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
				}

			});

			/* keep program running until user aborts (CTRL-C)
			for (;;) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Thread sleep pause error");
				}
			}*/

		};

	

}
