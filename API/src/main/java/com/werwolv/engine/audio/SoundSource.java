package com.werwolv.engine.audio;

import org.lwjgl.openal.AL10;

public class SoundSource {

    private int sourceId;
    private int bufferId;

    public SoundSource(String fileName, float volume, float pitch, boolean background) {
        sourceId = AL10.alGenSources();
        bufferId = Audio.loadSoundFile(fileName);
        AL10.alSourcef(sourceId, AL10.AL_GAIN, volume);
        AL10.alSourcef(sourceId, AL10.AL_PITCH, pitch);
        AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);

        AL10.alSourcef(sourceId, AL10.AL_ROLLOFF_FACTOR, background ? 0 : 3F);
        AL10.alSourcef(sourceId, AL10.AL_REFERENCE_DISTANCE, 2.5F);

    }

    public SoundSource play() {
        stop();
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, bufferId);
        AL10.alSourcePlay(sourceId);

        return this;
    }

    public SoundSource pause() {
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, bufferId);
        AL10.alSourcePause(sourceId);

        return this;
    }

    public SoundSource stop() {
        AL10.alSourceStop(sourceId);

        return this;
    }

    public void delete() {
        stop();
        AL10.alDeleteSources(sourceId);
    }

    public SoundSource setPosition(float x, float y, float z) {
        AL10.alSource3f(sourceId, AL10.AL_POSITION, x, y, z);

        return this;
    }

    public SoundSource setVelocity(float x, float y, float z) {
        AL10.alSource3f(sourceId, AL10.AL_VELOCITY, x, y, z);

        return this;
    }

    public SoundSource looping() {
        AL10.alSourcei(sourceId, AL10.AL_LOOPING, AL10.AL_TRUE);

        return this;
    }

    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }

}
