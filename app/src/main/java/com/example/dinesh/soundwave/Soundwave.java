package com.example.dinesh.soundwave;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Handler;

public class Soundwave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundwave);
        Button button = (Button) findViewById(R.id.button);
        final EditText name = (EditText) findViewById(R.id.editText);

        final int duration = 3; // seconds
        final int sampleRate = 44100;
        final int numSamples = 44100; //duration * samplerate
        final double sample[] = new double[numSamples];
        final double sample2[] = new double[numSamples];

        final double freqOfTone = 19000; // hz
        final double freqOfTone2 = 20300; // hz

        final byte generatedSnd[] = new byte[2 * numSamples];
        final byte generatedSnd2[] = new byte[2 * numSamples];

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fill out the array
                int biname = Integer.parseInt(name.getText().toString());
                String biname1 = Integer.toBinaryString(biname);
                int length = biname1.length();
                Log.d("myTag", biname1);
                for (int i = 0; i < numSamples; ++i) {
                    sample[i] = 1000*Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone));
                    sample2[i] = 100*Math.sin(2 * Math.PI * i / (sampleRate / freqOfTone2));
                }

                // convert to 16 bit pcm sound array
                // assumes the sample buffer is normalised.
                int idx = 0;
                for (double dVal : sample) {
                    short val = (short) (dVal * 32767);
                    generatedSnd[idx++] = (byte) (val & 0x00ff);
                    generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);

                }

                int idx2 = 0;
                for (double dVal2 : sample2) {
                    short val2 = (short) (dVal2 * 32767);
                    generatedSnd2[idx2++] = (byte) (val2 & 0x00ff);
                    generatedSnd2[idx2++] = (byte) ((val2 & 0xff00) >>> 8);

                }


                //Log.d("myTag", String.valueOf(length));
//begin
//                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
//                        44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
//                        AudioFormat.ENCODING_PCM_16BIT, numSamples,
//                        AudioTrack.MODE_STATIC);
//                audioTrack.write(generatedSnd, 0, numSamples);
//                audioTrack.play();
//                try {
//                    Thread.sleep(550);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//

                AudioTrack audioTrack2 = new AudioTrack(AudioManager.STREAM_MUSIC,
                        44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, numSamples,
                        AudioTrack.MODE_STATIC);
                audioTrack2.write(generatedSnd2, 0, numSamples);


                for(int i=0; i<length; i++)
                {
                    if (biname1.charAt(i)== '1'){
                       AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                                44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                                AudioTrack.MODE_STATIC);
                        audioTrack.write(generatedSnd, 0, numSamples);
                        audioTrack.play();
                        try {

                                Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        audioTrack.stop();
                        audioTrack.release();

                    }
                    else{
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    audioTrack2 = new AudioTrack(AudioManager.STREAM_MUSIC,
                            44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                            AudioFormat.ENCODING_PCM_16BIT, numSamples,
                            AudioTrack.MODE_STATIC);
                    audioTrack2.write(generatedSnd2, 0, numSamples);
                    audioTrack2.play();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    audioTrack2.stop();
                    audioTrack2.release();
                }
//end
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                        44100, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                        AudioFormat.ENCODING_PCM_16BIT, numSamples,
                       AudioTrack.MODE_STATIC);
               audioTrack.write(generatedSnd2, 0, numSamples);
               audioTrack.play();
               try {
                   Thread.sleep(800);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
                audioTrack.release();
//end

            }
        });

    }
}
