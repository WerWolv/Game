package com.werwolv.engine.renderer.audio;

import com.werwolv.api.API;
import com.werwolv.entities.EntityPlayer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.openal.*;
import org.lwjgl.stb.STBVorbis;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import static org.lwjgl.openal.ALC10.*;
import org.lwjgl.system.libc.LibCStdlib;

public class Audio {

    private static long context, device;

    public static void createContext() {
        String defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
        device = alcOpenDevice(defaultDeviceName);
        context = alcCreateContext(device, new int[]{ 0 });
        alcMakeContextCurrent(context);

        ALCapabilities alCapabilities = AL.createCapabilities(ALC.createCapabilities(device));

        AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);

        if(!alCapabilities.OpenAL10) {
            System.err.println("[OpenAL] OpenAL 1.0 isn't supported!");
        }
    }

    public static int loadSoundFile(String fileName) {
        MemoryStack.stackPush();
        IntBuffer channelsBuffer = MemoryStack.stackMallocInt(1);
        MemoryStack.stackPush();
        IntBuffer sampleRateBuffer = MemoryStack.stackMallocInt(1);

        String[] tokens = fileName.split(":");
        String path = ClassLoader.getSystemClassLoader().getResource(tokens[0] + "/sounds/" + tokens[1] + ".ogg").getFile();

        ShortBuffer rawAudioBuffer = STBVorbis.stb_vorbis_decode_filename(path.substring(1, path.length()), channelsBuffer, sampleRateBuffer);

        int channels = channelsBuffer.get();
        int sampleRate = sampleRateBuffer.get();

        MemoryStack.stackPop();
        MemoryStack.stackPop();

        int format = -1;
        if(channels == 1)
            format = AL10.AL_FORMAT_MONO16;
        else if(channels == 2)
            format = AL10.AL_FORMAT_STEREO16;

        int bufferPointer = AL10.alGenBuffers();

        AL10.alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);

        LibCStdlib.free(rawAudioBuffer);

        return bufferPointer;
    }

    public static void setListenerPosition() {
        EntityPlayer player = API.thePlayer;

        AL10.alListener3f(AL10.AL_POSITION, 0, player.getY(), player.getX());
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);

        Matrix4f absMatrix = new Matrix4f().identity().translate(new Vector3f(0, player.getY(), player.getX()));

        AL10.alListenerfv(AL10.AL_ORIENTATION, new float[] { absMatrix.m00(), absMatrix.m10(), absMatrix.m20(), absMatrix.m01(), absMatrix.m11(), absMatrix.m21() });
    }

    public static void clean() {
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}
