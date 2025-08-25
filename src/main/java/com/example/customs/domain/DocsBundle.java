package com.example.customs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
public class DocsBundle extends Auditable {
  @OneToOne(cascade = CascadeType.ALL) private UploadDoc invoice;
  @OneToOne(cascade = CascadeType.ALL) private UploadDoc packing;
  @OneToOne(cascade = CascadeType.ALL) private UploadDoc blOrAwb;
  @OneToMany(cascade = CascadeType.ALL) private List<UploadDoc> others = new ArrayList<>();
  @NotBlank private String currency;
  @PositiveOrZero private Double invoiceTotal;
  @Size(max=500) private String notes;

  public UploadDoc getInvoice(){return invoice;} public void setInvoice(UploadDoc v){invoice=v;}
  public UploadDoc getPacking(){return packing;} public void setPacking(UploadDoc v){packing=v;}
  public UploadDoc getBlOrAwb(){return blOrAwb;} public void setBlOrAwb(UploadDoc v){blOrAwb=v;}
  public List<UploadDoc> getOthers(){return others;}
  public String getCurrency(){return currency;} public void setCurrency(String v){currency=v;}
  public Double getInvoiceTotal(){return invoiceTotal;} public void setInvoiceTotal(Double v){invoiceTotal=v;}
  public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
}
