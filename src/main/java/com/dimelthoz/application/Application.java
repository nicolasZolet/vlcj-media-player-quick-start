package com.dimelthoz.application;

import uk.co.caprica.vlcj.player.base.Marquee;
import uk.co.caprica.vlcj.player.base.MarqueePosition;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import java.awt.*;

public final class Application {

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    private static final class ApplicationHolder {
        private static final Application INSTANCE = new Application();
    }

    public static Application application() {
        return ApplicationHolder.INSTANCE;
    }

    private Application() {
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    }

    public EmbeddedMediaPlayerComponent mediaPlayerComponent() {
        return mediaPlayerComponent;
    }

    public EmbeddedMediaPlayer mediaPlayer() {
        return mediaPlayerComponent.mediaPlayer();
    }

    public void defaultMarquee (String text){
        Marquee marquee = Marquee.marquee()
                .text(text)
                .size(200)
                .colour(Color.WHITE)
                .timeout(3000)
                .position(MarqueePosition.CENTRE)
                .opacity(1.0f)
                .enable();

        mediaPlayerComponent.mediaPlayer().marquee().set(marquee);
    }
}
