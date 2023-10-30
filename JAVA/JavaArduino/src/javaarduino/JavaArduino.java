/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduino;

import com.google.gson.Gson;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class JavaArduino {

//    private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
//    private static Aplicacion app = new Aplicacion();
//
//    static {
//        app.setVisible(true);
//    }
//
//    private static final SerialPortEventListener listener = new SerialPortEventListener() {
//        @Override
//        public void serialEvent(SerialPortEvent spe) {
//            try {
//                if (ino.isMessageAvailable()) {
//                    String mensaje = ino.printMessage();
//
//                    Gson gson = new Gson();
//                    Data data = gson.fromJson(mensaje, Data.class);
//
//                    System.out.println(data.toString());
//
//                    app.PortEvento(data.toString());
//                }
//            } catch (SerialPortException | ArduinoException ex) {
//                Logger.getLogger(JavaArduino.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    };
//
//    public static void main(String[] args) {
//        try {
//            ino.arduinoRX("COM6", 9600, listener);
//        } catch (ArduinoException | SerialPortException ex) {
//            Logger.getLogger(JavaArduino.class.getName()).log(Level.SEVERE, null, ex);
//            app.alerta(ex.getMessage());
//        }
//    }
//
//    public void toggle() {
//        try {
//            ino.arduinoTX("COM6", 9600);
//        } catch (ArduinoException ex) {
//            Logger.getLogger(JavaArduino.class.getName()).log(Level.SEVERE, null, ex);
//            app.alerta(ex.getMessage());
//        }
//
//    }

}
