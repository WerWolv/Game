package com.werwolv.api.lang;

public class LocalizedString {

    private String localizationString, localizedString;

    public LocalizedString(String localizationString, String localizedString) {
        this.localizationString = localizationString;
        this.localizedString = localizedString;
    }

    public String getLocalizationString() {
        return localizationString;
    }

    public String getLocalizedString() {
        return localizedString;
    }

}
