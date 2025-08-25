package com.example.customs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class ArrivalNotice extends Auditable {
  @NotBlank private String party;
  @NotBlank private String blOrAwb;
  private String voyage;
  @NotNull private LocalDate eta;
  private String warehouse;
  private String notifyTo;
  @Size(max=500) private String remarks;

  @OneToMany(cascade = CascadeType.ALL)
  private List<UploadDoc> attachments = new ArrayList<>();

  public String getParty(){return party;} public void setParty(String v){party=v;}
  public String getBlOrAwb(){return blOrAwb;} public void setBlOrAwb(String v){blOrAwb=v;}
  public String getVoyage(){return voyage;} public void setVoyage(String v){voyage=v;}
  public LocalDate getEta(){return eta;} public void setEta(LocalDate v){eta=v;}
  public String getWarehouse(){return warehouse;} public void setWarehouse(String v){warehouse=v;}
  public String getNotifyTo(){return notifyTo;} public void setNotifyTo(String v){notifyTo=v;}
  public String getRemarks(){return remarks;} public void setRemarks(String v){remarks=v;}
  public List<UploadDoc> getAttachments(){return attachments;}
}
