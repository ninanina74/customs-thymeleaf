package com.example.customs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
public class CustomsDecl extends Auditable {
  @NotBlank private String entryNo;
  @NotBlank private String regime;
  @NotBlank private String status;
  @PositiveOrZero private Double duties;
  @Size(max=1000) private String dutyDetail;

  @OneToMany(cascade = CascadeType.ALL)
  private List<UploadDoc> docs = new ArrayList<>();

  public String getEntryNo(){return entryNo;} public void setEntryNo(String v){entryNo=v;}
  public String getRegime(){return regime;} public void setRegime(String v){regime=v;}
  public String getStatus(){return status;} public void setStatus(String v){status=v;}
  public Double getDuties(){return duties;} public void setDuties(Double v){duties=v;}
  public String getDutyDetail(){return dutyDetail;} public void setDutyDetail(String v){dutyDetail=v;}
  public List<UploadDoc> getDocs(){return docs;}
}
