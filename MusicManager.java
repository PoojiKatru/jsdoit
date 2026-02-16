import greenfoot.*;

/**
 * Manages background music in a Greenfoot project.
 * Supports playing, stopping, pausing, resuming, and volume control of a single music track.
 */
public class MusicManager {
    /** The currently loaded background music */
    private static GreenfootSound backgroundMusic;

    /** Whether the music is currently playing */
    private static boolean isMusicPlaying = false;

    /** The filename of the currently playing song */
    private static String currentSong = "";

    /**
     * Starts playing a music file in a loop.
     * If the requested song is already playing, it will not restart.
     * 
     * @param musicFile The filename of the music file to play
     */
    public static void startMusic(String musicFile) {
        if (currentSong.equals(musicFile) && isMusicPlaying) {
            return;
        }

        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }

        backgroundMusic = new GreenfootSound(musicFile);
        backgroundMusic.setVolume(50); // Default volume 50%
        backgroundMusic.playLoop();
        isMusicPlaying = true;
        currentSong = musicFile;
    }

    /**
     * Stops the currently playing music.
     */
    public static void stopMusic() {
        if (backgroundMusic != null && isMusicPlaying) {
            backgroundMusic.stop();
            isMusicPlaying = false;
        }
    }

    /**
     * Pauses the currently playing music.
     */
    public static void pauseMusic() {
        if (backgroundMusic != null && isMusicPlaying) {
            backgroundMusic.pause();
            isMusicPlaying = false;
        }
    }

    /**
     * Resumes the currently paused music.
     */
    public static void resumeMusic() {
        if (backgroundMusic != null && !isMusicPlaying) {
            backgroundMusic.play();
            isMusicPlaying = true;
        }
    }

    /**
     * Sets the volume of the music.
     * 
     * @param volume Volume level (0-100)
     */
    public static void setVolume(int volume) {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(volume);
        }
    }

    /**
     * Checks if music is currently playing.
     * 
     * @return true if music is playing, false otherwise
     */
    public static boolean isPlaying() {
        return isMusicPlaying;
    }

    /**
     * Returns the filename of the currently playing music.
     * 
     * @return Current music filename
     */
    public static String getCurrentSong() {
        return currentSong;
    }
}
