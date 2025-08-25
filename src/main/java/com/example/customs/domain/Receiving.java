package com.example.customs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class Receiving extends Auditable {
  @NotBlank private String grNo;
  @NotNull private LocalDate receivedDate;
  @NotBlank private String person;
  @NotBlank private String result;
  @Size(max=500) private String issueNote;

  @OneToMany(cascade = CascadeType.ALL)
  private List<UploadDoc> photos = new ArrayList<>();

  public String getGrNo(){return grNo;} public void setGrNo(String v){grNo=v;}
  public LocalDate getReceivedDate(){return receivedDate;} public void setReceivedDate(LocalDate v){receivedDate=v;}
  public String getPerson(){return person;} public void setPerson(String v){person=v;}
  public String getResult(){return result;} public void setResult(String v){result=v;}
  public String getIssueNote(){return issueNote;} public void setIssueNote(String v){issueNote=v;}
  public List<UploadDoc> getPhotos(){return photos;}
}
