package com;

import javax.sound.midi.*;
//sound is played using the MIDI (Musical Instrument Digital Interface) protocol
public class SoundUtils {
   public static void playBeep() {
      //method is responsible for playing a beep sound
       try {
           Synthesizer synthesizer = MidiSystem.getSynthesizer();
          //If successful, it opens the synthesizer
           synthesizer.open();
           MidiChannel[] channels = synthesizer.getChannels();
          //Once the synthesizer is open, the method obtains an array of MidiChannel objects from the synthesizer using the getChannels() method
           channels[0].noteOn(60, 100); // Play note with key 60 (C4) and velocity 100
           try {
              //method then plays a note by calling the noteOn() method on channel 0 of the synthesizer
               Thread.sleep(200); // Play the note for 200 milliseconds
           } catch (InterruptedException e) {
              //parameters to this method specify the key number (60 for the note C4) and the velocity (100) of the note
               e.printStackTrace();
           }
           channels[0].noteOff(60); // Stop playing the note
           synthesizer.close();
          //After playing the note for 200 milliseconds using the Thread.sleep() method
          //method calls noteOff() on channel 0 to stop playing the note
       } catch (MidiUnavailableException e) {
          //method encounters any exceptions during the process, it prints the stack trace
           e.printStackTrace();
       }
   }
}
