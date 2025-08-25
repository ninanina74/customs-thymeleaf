package com.example.customs.repo;
import com.example.customs.domain.Accounting;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountingRepo extends JpaRepository<Accounting, Long> {}
