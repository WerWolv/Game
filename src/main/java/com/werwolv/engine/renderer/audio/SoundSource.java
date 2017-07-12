package com.werwolv.engine.renderer.audio;

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

        AL10.alSourcef(sourceId, AL10.AL_ROLLOFF_FACTOR, background ? 0 : 1);
        AL10.alSourcef(sourceId, AL10.AL_REFERENCE_DISTANCE, 1);
        AL10.alSourcef(sourceId, AL10.AL_MAX_DISTANCE, 1);

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

    public SoundSource setLooping(boolean looping) {
        AL10.alSourcei(sourceId, AL10.AL_LOOPING, looping ? AL10.AL_TRUE : AL10.AL_FALSE);

        return this;
    }

    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }

}
