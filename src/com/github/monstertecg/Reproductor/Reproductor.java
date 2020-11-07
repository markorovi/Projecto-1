package com.github.monstertecg.Reproductor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class Reproductor {

    private static Reproductor instancia;

    public static Reproductor ObtenerInstancia() {
        if (instancia == null) {
            instancia = new Reproductor();
        }
        return instancia;
    }



    public void Chayanne(){
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\musica\\";
        Reproducir(path + "chayanne.ogg");
    }

    public void Safaera(){
        String path = Paths.get("").toAbsolutePath().toString();
        path = path + "\\src\\main\\resources\\musica\\";
        Reproducir(path + "safaera.ogg");
    }

    private void Reproducir(String ruta) {
        final File archivo = new File(ruta);

        try (final AudioInputStream in = getAudioInputStream(archivo)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final Info info = new Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine linea = (SourceDataLine) AudioSystem.getLine(info)) {

                if (linea != null) {
                    linea.open(outFormat);
                    linea.start();
                    Flujo(getAudioInputStream(outFormat, in), linea);
                    linea.drain();
                    linea.stop();
                }
            }

        } catch (UnsupportedAudioFileException
                | LineUnavailableException
                | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int canal = inFormat.getChannels();
        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, canal, canal * 2, rate, false);
    }

    private void Flujo(AudioInputStream in, SourceDataLine linea)
            throws IOException {
        final byte[] buffer = new byte[65536];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            linea.write(buffer, 0, n);
        }
    }
}
