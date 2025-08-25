package com.example.customs.repo;
import com.example.customs.domain.ArrivalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ArrivalNoticeRepo extends JpaRepository<ArrivalNotice, Long> {}
