package com.org.composter.dao;

import com.org.composter.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsDao extends JpaRepository<Logs, Long> {
}
