package io.github.glorrian;

public class DlibInterface {
    public static native int[] detectFaces(long matNativeAddress, int width, int height);
}
