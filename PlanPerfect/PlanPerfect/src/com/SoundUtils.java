package com;

import javax.sound.midi.*;

public class SoundUtils {
    public static void playBeep() {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel[] channels = synthesizer.getChannels();
            channels[0].noteOn(60, 100); // Play note with key 60 (C4) and velocity 100
            try {
                Thread.sleep(200); // Play the note for 200 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channels[0].noteOff(60); // Stop playing the note
            synthesizer.close();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
