package com.werwolv.data;

import com.werwolv.api.Log;

import java.io.*;

/**
 * Created by werwo on 26.07.2017.
 */
public abstract class SerializableDataObject implements Serializable {

        public void serialize() {
            try {
                File file = new File(System.getProperty("user.home") + "/everphase/data/" + getClass().getSimpleName() + ".sdo");
                File parent = file.getParentFile();

                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }

                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

                out.writeObject(this);
                out.close();

                Log.i("Serializer", "Data serialized successfully to " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public SerializableDataObject deserialize() {
            SerializableDataObject  sdo;
            try {
                File file = new File(System.getProperty("user.home") + "/everphase/data/" + getClass().getSimpleName() + ".sdo");
                File parent = file.getParentFile();

                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }

                file.createNewFile();
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileInputStream);

                sdo = (SerializableDataObject) in.readObject();
                in.close();
                fileInputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                Log.i("Serializer", "Save file not found. Created a new one.");
                this.serialize();
                sdo = this.deserialize();
            }

            return sdo;
        }

        public boolean doesFileExist() {
            File file = new File(System.getProperty("user.home") + "/everphase/data/" + getClass().getSimpleName() + ".sdo");

            return file.exists();
        }
    }
