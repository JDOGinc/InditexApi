package com.jdog.apirest.domain.model;

public enum Size {
    S("Small"),
    M("Medium"),
    L("Large");
    private final String name;

    Size(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
