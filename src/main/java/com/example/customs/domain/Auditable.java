package com.example.customs.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Auditable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate createdDate = LocalDate.now();
  private String createdBy;

  public Long getId(){return id;}
  public LocalDate getCreatedDate(){return createdDate;}
  public String getCreatedBy(){return createdBy;}
  public void setCreatedBy(String u){this.createdBy = u;}
}
