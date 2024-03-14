package com.example.storage.service.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.storage.service.model.AccessLevel;
import com.example.storage.service.model.Folder;
import com.example.storage.service.model.User;
import com.example.storage.service.repository.AccessLevelRepository;
import com.example.storage.service.repository.FolderRepository;
import com.example.storage.service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(FolderRepository folderRepository, UserRepository userRepository,
            AccessLevelRepository accessLevelRepository) {

        return args -> {
            Long adminAccessLevelId = 1L;
            if (!accessLevelRepository.existsById(adminAccessLevelId)) {
                AccessLevel adminAccessLevel = new AccessLevel(adminAccessLevelId, "administrator", true);
                accessLevelRepository.save(adminAccessLevel);
            }

            Long adminUserId = 1L;
            if (!userRepository.existsById(adminUserId)) {
                User adminUser = new User(adminUserId, "admin", "", "user", "admin", "administrator",
                        adminAccessLevelId, true);
                userRepository.save(adminUser);
            }

            Long rootFolderId = 1L;
            if (!folderRepository.existsById(rootFolderId)) {
                Folder rootFolder = new Folder(rootFolderId, "root", "", 1L, LocalDateTime.now(), LocalDateTime.now(),
                        true, userRepository.findById(adminUserId).orElse(null));
                folderRepository.save(rootFolder);
            }
        };
    }
}