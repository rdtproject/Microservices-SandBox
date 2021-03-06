package com.tpolm.microsandbox.domain;

public enum Region {

    CENTRAL_COAST("Central Coast"),
    SOUTHERN_CALIFORNIA("Southern California"),
    NORTHERN_CALIFORNIA("Northern California"),
    NAPA_SONOMA("Napa/Sonoma Counties"),
    VARIES("Varies");

    private String label;

    Region(String label) {
        this.label = label;
    }

    public static Region fromLabel(String byLabel) {
        for (Region r : Region.values()) {
            if (r.label.equalsIgnoreCase(byLabel))
                return r;
        }
        return null;
    }

    public String getLabel() {
        return this.label;
    }
}
