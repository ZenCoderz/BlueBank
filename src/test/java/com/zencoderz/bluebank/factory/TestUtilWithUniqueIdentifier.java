package com.zencoderz.bluebank.factory;

public abstract class TestUtilWithUniqueIdentifier {

    private Long uniqueIdentifier = 0L;

    public Long getUniqueIdentifier() {
        return this.uniqueIdentifier++;
    }

}
