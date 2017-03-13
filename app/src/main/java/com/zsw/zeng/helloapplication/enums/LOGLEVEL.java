package com.zsw.zeng.helloapplication.enums;

/**
 * @author ZengSuWa
 * @Description：
 * @Company：众鑫贷
 * @Created time：2017/3/13 11:27
 */

public enum LOGLEVEL {
    DEFAULT(1),
    VERBOSE(2),
    DEBUG(3),
    INFO(4),
    WARN(5),
    ERROR(6),
    ASSERT(7);

    private final int value;

    private LOGLEVEL(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
