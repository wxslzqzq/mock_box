package com.zhtty.mock.box.utils;

/**
 * @author A8142
 */
public class AnyThrow {

    public static void throwUnchecked(Throwable e) {
        AnyThrow.throwAny(e);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable> void throwAny(Throwable e) throws E {
        throw (E) e;
    }
}