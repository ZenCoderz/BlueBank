package com.zencoderz.bluebank.auth.user.attributes;

import java.util.Arrays;

public enum Authority {

    ADMIN("admin"),  APPUSER("app_user");
    private final String authority;

    Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return this.authority;
    }

    public static boolean contains(String name) {
        return Arrays.stream(Authority.values()).anyMatch( value -> value.toString().equals(name.toLowerCase()));
    }

}
