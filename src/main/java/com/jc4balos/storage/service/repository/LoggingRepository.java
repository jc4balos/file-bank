package com.jc4balos.storage.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jc4balos.storage.service.model.Logs;

public interface LoggingRepository extends JpaRepository<Logs, Long> {

}
