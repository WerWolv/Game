package com.werwolv.api.lang;

import com.google.common.collect.Lists;
import com.werwolv.api.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalizationHelper {

    private static String currLang = "en_US";
    private static Map<String, List<LocalizedString>> localizations = new HashMap<>();

    public static void loadLocalizationFiles(String path) {
        File folder = new File(path);
        File[] langFiles = folder.listFiles();

        for(File langFile : langFiles) {
            if(!langFile.isFile()) return;

            List<LocalizedString> localizedStrings = Lists.newArrayList();

            try {
                List<String> lines = Files.readAllLines(langFile.toPath());

                lines.forEach(line -> {
                    String[] tokens = line.split("=");
                    localizedStrings.add(new LocalizedString(tokens[0], tokens[1]));

                    localizations.put(langFile.getName().replace(".lang", ""), localizedStrings);
                    Log.i("LocalizationHelper", "Localization file \"" + langFile.getName() + "\" loaded!");
                });
            } catch (IOException e) {
                Log.wtf("LocalizationHelper", "Cannot load language file " + langFile.toPath());
            }
        }
    }

    public static boolean setLanguage(String language) {
        if (localizations.keySet().contains(language)) {
            LocalizationHelper.currLang = language;
            Log.i("Localization", "Language set to " + language + "!");
            return true;
        }

        return false;
    }

    public static String getLocalizedString(String unlocalizedString) {
        for (LocalizedString loc : localizations.get(currLang)) {
            if (loc.getLocalizationString().equals(unlocalizedString))
                return loc.getLocalizedString();
        }

        return unlocalizedString;

    }

}
