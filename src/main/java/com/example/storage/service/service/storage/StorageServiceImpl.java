package com.example.storage.service.service.storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.storage.service.dto.StorageInfoDto;
import com.example.storage.service.exception.SessionNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    public List<StorageInfoDto> getStorageInfo(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long sessionUserId = (Long) session.getAttribute("userId");
        if (sessionUserId != null) {

            File[] drives = File.listRoots();
            List<StorageInfoDto> storageInfoDtos = new ArrayList<>();

            if (drives.length > 0 && drives != null) {
                for (File drive : drives) {
                    StorageInfoDto storageInfoDto = new StorageInfoDto();

                    storageInfoDto.setDriveName(drive.toString());

                    float usedSpace = (drive.getTotalSpace() - drive.getUsableSpace());
                    String formmattedUsedSpace = String.format("%.02f", usedSpace / (1024 * 1024 * 1024));
                    storageInfoDto.setCurrentStorageAllocation(formmattedUsedSpace);

                    float freeSpace = drive.getFreeSpace();
                    String formattedFreeSpace = String.format("%.02f", freeSpace / (1024 * 1024 * 1024));
                    storageInfoDto.setFreeStorageAllocation(formattedFreeSpace);

                    float totalSpace = drive.getTotalSpace();
                    String formattedTotalSpace = String.format("%.02f", totalSpace / (1024 * 1024 * 1024));
                    storageInfoDto.setMaxStorageAllocation(formattedTotalSpace);

                    float storagePercentageAllocation = (usedSpace / totalSpace) * 100;
                    String formmattedStoragePercentageAllocation = String.format("%.02f", storagePercentageAllocation);
                    storageInfoDto.setStoragePercentageAllocation(formmattedStoragePercentageAllocation);

                    storageInfoDtos.add(storageInfoDto);
                }
            }
            return storageInfoDtos;

        } else {
            throw new SessionNotFoundException("Session Expired. Please log in.");
        }

    }
}
