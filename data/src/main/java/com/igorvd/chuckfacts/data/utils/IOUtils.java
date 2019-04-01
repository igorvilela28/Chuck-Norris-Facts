package com.igorvd.chuckfacts.data.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO utils
 *
 * @author Vladislav Bauer
 * @see <a href="https://github.com/Trinea/android-common/blob/master/src/cn/trinea/android/common/util/IOUtils.java">
 *     https://github.com/Trinea/android-common/blob/master/src/cn/trinea/android/common/util/IOUtils.java</a>
 */

public class IOUtils {

    private IOUtils() {
        throw new AssertionError();
    }


    /**
     * Close closable object and wrap {@link IOException} with {@link RuntimeException}
     * @param closeable closeable object
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            }
        }
    }
}
