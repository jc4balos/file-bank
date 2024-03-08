package com.example.storage.service.service;

public interface PermissionsService {
    public String allowUserToAddFileOnFolder(Boolean perms, Long folderId, Long userId);

    public String allowUserToViewFile(Boolean perms, Long fileId, Long userId);

    public String allowUserToDeleteFile(Boolean perms, Long fileId, Long userId);

    public String allowUserToRestoreFile(Boolean perms, Long fileId, Long userId);

    public String allowUserToCreateFolder(Boolean perms, Long folderId, Long userId);

    public String allowUserToDeleteFolder(Boolean perms, Long folderId, Long userId);

    public String allowUserToRestoreFolder(Boolean perms, Long folderId, Long userId);

    public String allowUserToViewFolder(Boolean perms, Long folderId, Long userId);

    public String allowUserToModifyFolder(Boolean perms, Long folderId, Long userId);

    public String allowUserToDeleteTrashFiles(Boolean perms, Long userId);

    public String allowUserToRestoreTrashFiles(Boolean perms, Long userId);

}
