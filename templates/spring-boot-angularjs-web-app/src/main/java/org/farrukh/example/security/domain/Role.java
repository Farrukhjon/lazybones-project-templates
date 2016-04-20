package org.farrukh.example.security.domain;

public enum Role {

    USER("ROLE_USER"),

    ADMIN("ROLE_ADMIN"),

    DBA("ROLE_DBA");

    private final String value;

    Role(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
