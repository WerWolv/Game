package com.werwolv.api.modloader;

public class ModFile {

    private Class<? extends Mod> modMainClass;

    private String filePath;
    private String modId;
    private String modName;
    private String modVersion;

    public ModFile(Class<? extends Mod> modMainClass, String filePath, String modId, String modName, String modVersion) {
        this.modMainClass = modMainClass;
        this.filePath = filePath;
        this.modId = modId;
        this.modName = modName;
        this.modVersion = modVersion;
    }

    public Class<? extends Mod> getModMainClass() {
        return modMainClass;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getModId() {
        return modId;
    }

    public String getModName() {
        return modName;
    }

    public String getModVersion() {
        return modVersion;
    }
}
