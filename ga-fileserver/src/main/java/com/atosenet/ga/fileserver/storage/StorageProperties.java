package com.atosenet.ga.fileserver.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    private String location = "upload-dir";
    private String defaultstorepath = "others";
    private String domain = "http://127.0.0.1:18081/file";
}
