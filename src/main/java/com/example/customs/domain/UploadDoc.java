package com.example.customs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class UploadDoc extends Auditable {
  @NotBlank private String fileName;
  @NotBlank private String contentType;
  @PositiveOrZero private long size;
  @NotBlank private String storagePath;

  public UploadDoc(){}

  public UploadDoc(String fn,String ct,long sz,String p){
    this.fileName=fn; this.contentType=ct; this.size=sz; this.storagePath=p;
  }

  public String getFileName(){return fileName;} public void setFileName(String v){fileName=v;}
  public String getContentType(){return contentType;} public void setContentType(String v){contentType=v;}
  public long getSize(){return size;} public void setSize(long v){size=v;}
  public String getStoragePath(){return storagePath;} public void setStoragePath(String v){storagePath=v;}
}
