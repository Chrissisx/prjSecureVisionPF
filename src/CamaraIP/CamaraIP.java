package CamaraIP;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CamaraIP {
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private boolean isRecording = false;
    private final String cameraURL;
    private String currentRecordingFile;

    public CamaraIP(String cameraURL) {
        this.cameraURL = cameraURL;
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    }

    public void startStreaming() {
        EmbeddedMediaPlayer player = mediaPlayerComponent.mediaPlayer();
        player.media().play(cameraURL);
    }

    public void startRecording() {
        if (!isRecording) {
            EmbeddedMediaPlayer player = mediaPlayerComponent.mediaPlayer();
            currentRecordingFile = generateOutputFilename();
            player.media().play(player.media().info().mrl(),
                    ":sout=#duplicate{dst=file{dst=" + currentRecordingFile + "},dst=display}",
                    ":sout-keep");
            isRecording = true;
            System.out.println("Grabación iniciada: " + currentRecordingFile);
        }
    }

    public void stopRecording() {
        if (isRecording) {
            EmbeddedMediaPlayer player = mediaPlayerComponent.mediaPlayer();
            player.controls().stop();
            player.media().play(cameraURL);
            isRecording = false;
            System.out.println("Grabación detenida");
        }
    }

    public JComponent getVideoComponent() {
        return mediaPlayerComponent; 
    }

    private String generateOutputFilename() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return System.getProperty("user.home") + File.separator + "IPCamera_" + timestamp + ".mp4";
    }

    public void releaseResources() {
        mediaPlayerComponent.release();
    }
}
