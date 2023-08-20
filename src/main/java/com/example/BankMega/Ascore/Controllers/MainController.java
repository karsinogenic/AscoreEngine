package com.example.BankMega.Ascore.Controllers;

import com.example.BankMega.Ascore.Model.KategoriAscore;
import com.example.BankMega.Ascore.Model.Score;
import com.example.BankMega.Ascore.Model.TestAscore;
import com.example.BankMega.Ascore.Model.Users;
import com.example.BankMega.Ascore.Repositories.AscoreRepository;
import com.example.BankMega.Ascore.Repositories.UserRepository;
import com.example.BankMega.Ascore.Repositories.kategoriAscoreRepository;
import com.example.BankMega.Ascore.config.SecurityConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private kategoriAscoreRepository kategoriAscoreRepository;

    @Autowired
    private AscoreRepository ascoreRepository;

    @GetMapping("")
    public RedirectView index() {
        // ModelAndView view = new ModelAndView("layout/manajemenAscore/test_vue");
        // return view;
        return new RedirectView("ascore_calculator");
    }

    @GetMapping("/login1")
    public ModelAndView login() {

        return new ModelAndView("sign-in");
    }

    @PostMapping("/login_failure_handler")
    public ModelAndView loginFailureHandler() {
        ModelAndView view = new ModelAndView("sign-in");
        view.addObject("error", "Tidak diizinkan");
        return view;
    }

    @GetMapping("ascore_calculator")
    public ModelAndView layout_index() {
        List<TestAscore> list = this.ascoreRepository.findAllCustom(Sort.by(Sort.Direction.ASC, "id"));
        List<KategoriAscore> list_kategori = this.kategoriAscoreRepository
                .findAllCustom(Sort.by(Sort.Direction.ASC, "id"));

        ModelAndView view = new ModelAndView("layout/manajemenAscore/index");
        view.addObject("list_ascore", list);
        view.addObject("list_kategori", list_kategori);

        String bread[] = { "Home ", "Calculator " };
        String bread_url[] = { "/", "/ascore_calculator" };

        List<String> params = new ArrayList<>();

        for (TestAscore lists : list) {
            // params.add(lists.getKategoriAscore().getNamaparam());
        }

        view.addObject("breads", Arrays.asList(bread));
        view.addObject("breads_url", Arrays.asList(bread_url));

        view.addObject("list", params.stream().distinct());
        return view;
    }

    @GetMapping("ascore_management")
    public ModelAndView ascore_management() {
        List<TestAscore> list = this.ascoreRepository.findAllCustom(Sort.by(Sort.Direction.DESC, "id"));
        List<KategoriAscore> list_kategori = this.kategoriAscoreRepository
                .findAllCustom(Sort.by(Sort.Direction.DESC, "id"));

        ModelAndView view = new ModelAndView("layout/manajemenAscore/management");
        view.addObject("list_ascore", list);
        view.addObject("list_kategori", list_kategori);

        String bread[] = { "Home ", "Management " };
        String bread_url[] = { "/", "/ascore_management" };

        view.addObject("breads", Arrays.asList(bread));
        view.addObject("breads_url", Arrays.asList(bread_url));
        // params.stream().distinct();
        // for (String tes:params.stream().distinct()) {
        // System.out.println(tes);
        // }

        return view;
    }

    @GetMapping("ascore_management/kategori")
    public ModelAndView ascore_management_kategori() {
        List<TestAscore> list = this.ascoreRepository.findAllCustom(Sort.by(Sort.Direction.DESC, "id"));
        List<KategoriAscore> list_kategori = this.kategoriAscoreRepository
                .findAllCustom(Sort.by(Sort.Direction.ASC, "id"));
        List<KategoriAscore> list_kategori_nonaktif = this.kategoriAscoreRepository
                .findAllNonAktif(Sort.by(Sort.Direction.ASC, "id"));
        ModelAndView view = new ModelAndView("layout/manajemenAscore/managementKategori");
        view.addObject("list_ascore", list);
        view.addObject("list_kategori", list_kategori);
        view.addObject("list_kategori_nonaktif", list_kategori_nonaktif);

        String bread[] = { "Home ", "Management ", "Kategori " };
        String bread_url[] = { "/", "/ascore_management", "/ascore_management/kategori" };

        view.addObject("breads", Arrays.asList(bread));
        view.addObject("breads_url", Arrays.asList(bread_url));
        // params.stream().distinct();
        // for (String tes:params.stream().distinct()) {
        // System.out.println(tes);
        // }

        return view;
    }

}
