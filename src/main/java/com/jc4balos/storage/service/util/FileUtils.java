package com.jc4balos.storage.service.util;

public class FileUtils {
    public String getExtensionFromFilename(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex >= 0) {
            return filename.substring(dotIndex);
        } else {
            return null; // no extension found
        }
    }
}
