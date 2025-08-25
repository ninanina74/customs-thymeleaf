package com.example.customs.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

@Entity
public class Accounting extends Auditable {
  @NotBlank private String vendor;
  @NotBlank private String apNo;
  @PositiveOrZero private Double amount;
  @NotBlank private String currency;
  @PositiveOrZero private Double rate;
  @NotBlank private String erpStatus;
  @Size(max=500) private String memo;

  @OneToMany(cascade = CascadeType.ALL)
  private List<UploadDoc> payDocs = new ArrayList<>();

  public String getVendor(){return vendor;} public void setVendor(String v){vendor=v;}
  public String getApNo(){return apNo;} public void setApNo(String v){apNo=v;}
  public Double getAmount(){return amount;} public void setAmount(Double v){amount=v;}
  public String getCurrency(){return currency;} public void setCurrency(String v){currency=v;}
  public Double getRate(){return rate;} public void setRate(Double v){rate=v;}
  public String getErpStatus(){return erpStatus;} public void setErpStatus(String v){erpStatus=v;}
  public String getMemo(){return memo;} public void setMemo(String v){memo=v;}
  public List<UploadDoc> getPayDocs(){return payDocs;}
}
