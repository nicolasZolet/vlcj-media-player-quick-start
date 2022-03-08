package com.dimelthoz.application;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.base.*;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MediaPlayer {

    private final JFrame jFrame;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final EmbeddedMediaPlayer mediaPlayer;

    public static void main(String[] args) throws InterruptedException {
        new NativeDiscovery().discover();
        System.out.println(LibVlc.libvlc_get_version());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MediaPlayer();
            }
        });
//        new com.dimelthoz.application.MediaPlayer();
    }

    public MediaPlayer() {
        jFrame = new JFrame("vlcj-media-player");
        jFrame.setBounds(100, 100, 600, 400);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() { //CLEANUP
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayer = mediaPlayerComponent.mediaPlayer();

        jFrame.setContentPane(mediaPlayerComponent);
        jFrame.setVisible(true);

        mediaPlayer.fullScreen().strategy(new AdaptiveFullScreenStrategy(jFrame));
        mediaPlayer.fullScreen().set(true);

//        mediaPlayerComponent.mediaPlayer().media().play("src/media/video_sample.mp4");
//        mediaPlayerComponent.mediaPlayer().media().start("src/media/video_sample.mp4");
//        mediaPlayerComponent.mediaPlayer().media().prepare("src/media/video_sample.mp4");

        Marquee marquee = Marquee.marquee()
                .text("18298")
                .size(200)
                .colour(Color.WHITE)
                .timeout(3000)
                .position(MarqueePosition.CENTRE)
                .opacity(1.0f)
                .enable();
        mediaPlayerComponent.mediaPlayer().marquee().set(marquee);

        mediaPlayerComponent.mediaPlayer().media().start("src/media/video_sample.mp4");
        System.out.println(mediaPlayerComponent.mediaPlayer().titles());

//        Logo logo = Logo.logo()
//                .file("src/media/image_sample_2.png")
//                .position(LogoPosition.CENTRE)
//                .opacity(1.0f)
//                .enable();
//        mediaPlayerComponent.mediaPlayer().logo().set(logo);

    }
}
