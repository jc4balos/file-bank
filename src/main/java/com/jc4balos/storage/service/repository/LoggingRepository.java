package com.jc4balos.storage.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jc4balos.storage.service.model.Logs;

public interface LoggingRepository extends JpaRepository<Logs, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM logs ORDER BY time_stamp DESC")
    Page<Logs> getLogsByPage(PageRequest of);
}
