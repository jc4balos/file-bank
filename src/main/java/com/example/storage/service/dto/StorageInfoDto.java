package com.example.storage.service.dto;

import lombok.Data;

@Data
public class StorageInfoDto {
    public String driveName;
    public String maxStorageAllocation;
    public String currentStorageAllocation;
    public String freeStorageAllocation;
    public String storagePercentageAllocation;
}
