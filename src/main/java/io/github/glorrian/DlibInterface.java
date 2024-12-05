package io.github.glorrian;

public class DlibInterface {
    public static native int[] detectFaces(byte[] imageData, int width, int height);
}
