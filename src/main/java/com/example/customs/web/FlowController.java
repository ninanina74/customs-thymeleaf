package com.example.customs.web;

import com.example.customs.domain.*;
import com.example.customs.repo.*;
import com.example.customs.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/flow")
public class FlowController {
  private final ArrivalNoticeRepo arrivalRepo;
  private final DocsBundleRepo docsRepo;
  private final CustomsDeclRepo customsRepo;
  private final ReceivingRepo receivingRepo;
  private final AccountingRepo accountingRepo;
  private final UploadDocRepo uploadRepo;
  private final StorageService storage;

  public FlowController(ArrivalNoticeRepo a, DocsBundleRepo d, CustomsDeclRepo c, ReceivingRepo r,
                        AccountingRepo ac, UploadDocRepo u, StorageService s){
    this.arrivalRepo=a; this.docsRepo=d; this.customsRepo=c; this.receivingRepo=r; this.accountingRepo=ac; this.uploadRepo=u; this.storage=s;
  }

  @GetMapping
  public String flow(Model model){
    model.addAttribute("arrival", new ArrivalNotice());
    model.addAttribute("docs", new DocsBundle());
    model.addAttribute("decl", new CustomsDecl());
    model.addAttribute("recv", new Receiving());
    model.addAttribute("acct", new Accounting());
    return "flow";
  }

  @PostMapping("/arrival")
  @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
  public String saveArrival(@Valid @ModelAttribute("arrival") ArrivalNotice form,
                            BindingResult br,
                            @RequestParam(name="arrivalFiles", required=false) MultipartFile[] files,
                            Model model) throws IOException {
    if (form.getEta()==null) form.setEta(LocalDate.now());
    if (br.hasErrors()) { model.addAttribute("activeTab","t1"); return "flow"; }
    if (files!=null){
      for (MultipartFile f: files){ if(!f.isEmpty()){
        var sf = storage.store(f);
        UploadDoc ud = uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path()));
        form.getAttachments().add(ud);
      }}
    }
    arrivalRepo.save(form);
    return "redirect:/flow";
  }

  @PostMapping("/docs")
  @PreAuthorize("hasAnyRole('ADMIN','BUYER')")
  public String saveDocs(@Valid @ModelAttribute("docs") DocsBundle form,
                         BindingResult br,
                         @RequestParam(name="invoiceFile", required=false) MultipartFile invoice,
                         @RequestParam(name="packingFile", required=false) MultipartFile packing,
                         @RequestParam(name="blFile", required=false) MultipartFile bl,
                         Model model) throws IOException {
    if (br.hasErrors()) { model.addAttribute("activeTab","t2"); return "flow"; }
    if (invoice!=null && !invoice.isEmpty()){
      var sf = storage.store(invoice);
      form.setInvoice(uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path())));
    }
    if (packing!=null && !packing.isEmpty()){
      var sf = storage.store(packing);
      form.setPacking(uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path())));
    }
    if (bl!=null && !bl.isEmpty()){
      var sf = storage.store(bl);
      form.setBlOrAwb(uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path())));
    }
    docsRepo.save(form);
    return "redirect:/flow";
  }

  @PostMapping("/decl")
  @PreAuthorize("hasAnyRole('ADMIN','BROKER')")
  public String saveDecl(@Valid @ModelAttribute("decl") CustomsDecl form,
                         BindingResult br,
                         @RequestParam(name="declFiles", required=false) MultipartFile[] files,
                         Model model) throws IOException {
    if (br.hasErrors()) { model.addAttribute("activeTab","t3"); return "flow"; }
    if (files!=null){
      for (MultipartFile f: files){ if(!f.isEmpty()){
        var sf = storage.store(f);
        form.getDocs().add(uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path())));
      }}
    }
    customsRepo.save(form);
    return "redirect:/flow";
  }

  @PostMapping("/recv")
  @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSE')")
  public String saveRecv(@Valid @ModelAttribute("recv") Receiving form,
                         BindingResult br,
                         @RequestParam(name="recvFiles", required=false) MultipartFile[] files,
                         Model model) throws IOException {
    if (br.hasErrors()) { model.addAttribute("activeTab","t4"); return "flow"; }
    if (files!=null){
      for (MultipartFile f: files){ if(!f.isEmpty()){
        var sf = storage.store(f);
        form.getPhotos().add(uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path())));
      }}
    }
    receivingRepo.save(form);
    return "redirect:/flow";
  }

  @PostMapping("/acct")
  @PreAuthorize("hasAnyRole('ADMIN','ACCOUNTING')")
  public String saveAcct(@Valid @ModelAttribute("acct") Accounting form,
                         BindingResult br,
                         @RequestParam(name="acctFiles", required=false) MultipartFile[] files,
                         Model model) throws IOException {
    if (br.hasErrors()) { model.addAttribute("activeTab","t5"); return "flow"; }
    if (files!=null){
      for (MultipartFile f: files){ if(!f.isEmpty()){
        var sf = storage.store(f);
        form.getPayDocs().add(uploadRepo.save(new UploadDoc(sf.fileName(), sf.contentType(), sf.size(), sf.path())));
      }}
    }
    accountingRepo.save(form);
    return "redirect:/flow";
  }
}
