package com.dimelthoz.application;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.dimelthoz.application.Application.application;

public class MediaPlayer {

    private static MediaPlayer app;
    private final JFrame mainFrame;

    public static void main(String[] args) throws InterruptedException {
        new NativeDiscovery().discover();
        System.out.println(LibVlc.libvlc_get_version());

        app = new MediaPlayer();
        app.start();

        application().mediaPlayer().media().play("src/main/resources/media/video_sample.mp4");

        Thread.sleep(3000);

        application().mediaPlayer().media().play("src/main/resources/media/video_sample_2.mp4");

        Thread.sleep(3000);

        application().mediaPlayer().media().play("src/main/resources/media/video_sample.mp4");
    }

    private void start() {
        mainFrame.setVisible(true);
//        application().mediaPlayer().fullScreen().set(true);
    }

    public MediaPlayer() {
        EmbeddedMediaPlayerComponent mediaPlayerComponent = application().mediaPlayerComponent();
        mainFrame = new MainFrame();
        mainFrame.addWindowListener(new WindowAdapter() { //CLEANUP
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        application().mediaPlayerComponent().mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(mainFrame));
    }
}
