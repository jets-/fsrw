package com.usto.re.fsrw.service;

import java.util.Arrays;

public enum FsEnum {

    XFS("xfs"), EXT4("ext4"), BRFS("brfs"), F2FS("f2fs"), NONE("");

    private final String name;

    FsEnum(final String name) {
        this.name = name;
    }

    public String getValue() {
        return this.name;
    }

    public static FsEnum fetch(String constant) {
        return Arrays.stream(FsEnum.values())
                .filter(e -> e.name().equals(constant.toUpperCase()))
                .findFirst()
                .orElse(NONE);
    }

    public boolean isValid() {
        return !this.name.equals("");
    }

    @Override
    public String toString()  {
        return this.name;
    }

}
