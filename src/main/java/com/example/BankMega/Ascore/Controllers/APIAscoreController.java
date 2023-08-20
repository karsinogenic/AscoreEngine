package com.example.BankMega.Ascore.Controllers;

import com.example.BankMega.Ascore.Model.KategoriAscore;
import com.example.BankMega.Ascore.Model.LogAscore;
import com.example.BankMega.Ascore.Model.TestAscore;
import com.example.BankMega.Ascore.Repositories.AscoreRepository;
import com.example.BankMega.Ascore.Repositories.LogAscoreRepository;
import com.example.BankMega.Ascore.Repositories.UserRepository;
import com.example.BankMega.Ascore.Repositories.kategoriAscoreRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Parameter;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class APIAscoreController {
    @Autowired
    private AscoreRepository ascoreRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogAscoreRepository logAscoreRepository;

    @Autowired
    private kategoriAscoreRepository kategoriAscoreRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/allKategori")
    public ResponseEntity<List<TestAscore>> findAllAscore() {
        List<TestAscore> list = this.ascoreRepository.findAllCustom(Sort.by(Sort.Direction.DESC, "id"));
        return new ResponseEntity<List<TestAscore>>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/getNotInParam")
    @ResponseBody
    public ResponseEntity getNotInParam(@RequestBody Map<String, Object> input) {
        List<String> value = new ArrayList<String>();
        try {
            value = Arrays.asList(input.get("param").toString().split(","));
            List<KategoriAscore> list = this.kategoriAscoreRepository.findnamaparamNotIn(value);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No Value", HttpStatus.NO_CONTENT);
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/getParam")
    @ResponseBody
    public ResponseEntity<Optional<KategoriAscore>> getParam(@RequestParam String param) {
        Optional<KategoriAscore> list = this.kategoriAscoreRepository.findByNamaParam(param);
        return new ResponseEntity<Optional<KategoriAscore>>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/getById")
    @ResponseBody
    public ResponseEntity<Optional<TestAscore>> getById(@RequestParam Long id) {
        Optional<TestAscore> list = this.ascoreRepository.findById(id);
        return new ResponseEntity<Optional<TestAscore>>(list, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/kategori/getById")
    @ResponseBody
    public ResponseEntity<Optional<KategoriAscore>> getKategoriById(@RequestParam Long id) {
        Optional<KategoriAscore> list = this.kategoriAscoreRepository.findById(id);
        return new ResponseEntity<Optional<KategoriAscore>>(list, HttpStatus.OK);
    }

    @PutMapping(value = "/delete")
    public ResponseEntity deleteById(@RequestParam Long id) {
        Optional<TestAscore> opsi = this.ascoreRepository.findById(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        TestAscore ascore = new TestAscore();
        LocalDateTime now = LocalDateTime.now();
        try {
            ascore.setId(opsi.get().getId());
            ascore.setNamaparam(opsi.get().getNamaparam());
            ascore.setIs_slik(opsi.get().getIs_slik());
            ascore.setValue1(opsi.get().getValue1());
            ascore.setValue2(opsi.get().getValue2());
            ascore.setValue_str(opsi.get().getValue_str());
            ascore.setScore(opsi.get().getScore());
            ascore.setCreatedAt(opsi.get().getCreatedAt());
            ascore.setStatus_data(false);
            ascore.setUpdatedAt(now);
            this.ascoreRepository.save(ascore);
            return new ResponseEntity<>("berhasil softdelete", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(value = "/kategori/delete")
    public ResponseEntity deleteKategoriById(@RequestParam Long id) {
        Optional<KategoriAscore> opsi = this.kategoriAscoreRepository.findById(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        KategoriAscore ascore = new KategoriAscore();
        LocalDateTime now = LocalDateTime.now();
        try {
            ascore.setId(opsi.get().getId());
            ascore.setNamaparam(opsi.get().getNamaparam());
            ascore.setDescription(opsi.get().getDescription());
            ascore.setLength(opsi.get().getLength());
            ascore.setOperator(opsi.get().getOperator());
            ascore.setTrim_length(opsi.get().getTrim_length());
            ascore.setCreatedAt(opsi.get().getCreatedAt());
            ascore.setStatus_data(false);
            ascore.setUpdatedAt(now);
            this.kategoriAscoreRepository.save(ascore);
            return new ResponseEntity<>("Berhasil softdelete", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(value = "/kategori/reactived")
    public ResponseEntity reactivedKategoriById(@RequestParam Long id) {
        Optional<KategoriAscore> opsi = this.kategoriAscoreRepository.findById(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        KategoriAscore ascore = new KategoriAscore();
        LocalDateTime now = LocalDateTime.now();
        try {
            ascore.setId(opsi.get().getId());
            ascore.setNamaparam(opsi.get().getNamaparam());
            ascore.setDescription(opsi.get().getDescription());
            ascore.setLength(opsi.get().getLength());
            ascore.setOperator(opsi.get().getOperator());
            ascore.setTrim_length(opsi.get().getTrim_length());
            ascore.setCreatedAt(opsi.get().getCreatedAt());
            ascore.setStatus_data(true);
            ascore.setUpdatedAt(now);
            this.kategoriAscoreRepository.save(ascore);
            return new ResponseEntity<>("Berhasil reactived", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "/getOperator")
    public ResponseEntity getOperator(@RequestParam String operator) {
        String[] arr_operator = this.kategoriAscoreRepository.findParamNotEqualOperator(operator);
        // System.out.println(arr_operator[0]);
        return new ResponseEntity<String[]>(arr_operator, HttpStatus.OK);
    }

    @PostMapping(value = "/test")
    @ResponseBody
    public String test400(@RequestBody String coba) {
        return coba;
    }

    @PersistenceContext
    public EntityManager em;

    // @GetMapping(value = "/testCustom")
    // @ResponseBody
    // public void findWithName(String name) {
    // TypedQuery<Map<String, Object>> test = em.createNativeQuery("SELECT * FROM
    // test_ascore", Map.class);
    // System.out.println(p.getName());
    // }

    // return test;

    @GetMapping(value = "/healthCheck")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<TestAscore> ascore = this.ascoreRepository.findAll();
            List<KategoriAscore> kategoriAscores = this.kategoriAscoreRepository.findAll();
            List<LogAscore> log = this.logAscoreRepository.findAll();
            map.put("status", "Healthy");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("status", e.getCause().getCause().getLocalizedMessage());
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }

    }

    @PostMapping(value = "/postAscore", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, String>> postAscore(@RequestBody Map<String, Object> input) {
        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        @Valid
        TestAscore ascore = mapper.convertValue(input, TestAscore.class);

        HashMap<String, String> response = new HashMap<>();
        HttpStatus new_status = HttpStatus.OK;
        System.out.println(ascore.getNamaparam());
        Boolean coba = validasiDataAscore(ascore, response, new_status);
        System.out.println(coba);
        if (coba) {
            postDataAscore(ascore, response, new_status);
            postLog(input, "Tambah Ascore", "POST", "/postAscore", true, 1);
        } else {
            postLog(input, "Tambah Ascore", "POST", "/postAscore", false, 0);
        }

        return new ResponseEntity<Map<String, String>>(response, new_status);
        // return new ResponseEntity<Map<String, String>>("test", "test");
    }

    @PostMapping(value = "/editAscore", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, String>> editAscore(@RequestBody Map<String, Object> input) {
        HashMap<String, String> response = new HashMap<>();
        HttpStatus new_status = HttpStatus.OK;

        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        @Valid
        TestAscore ascore = mapper.convertValue(input, TestAscore.class);

        // System.out.println(ascore.getNamaparam());
        Boolean coba = validasiDataAscore(ascore, response, new_status);
        // System.out.println(coba);
        if (coba) {
            postDataAscore(ascore, response, new_status);
            postLog(input, "Edit Ascore", "POST", "/editAscore", true, 1);
        } else {
            postLog(input, "Edit Ascore", "POST", "/editAscore", false, 0);
        }

        return new ResponseEntity<Map<String, String>>(response, new_status);
    }

    public Boolean validasiDataAscore(TestAscore ascore, HashMap<String, String> response, HttpStatus new_status) {
        Boolean hasil = true;
        try {
            // cek jika ada
            if (ascore == null) {
                return false;
            }

            Optional<KategoriAscore> kategori = this.kategoriAscoreRepository.findByNamaParam(ascore.getNamaparam());

            if (kategori.isEmpty()) {
                response.put("status", "parameter " + ascore.getNamaparam() + " tidak berada dalam kategori");
                new_status = HttpStatus.BAD_REQUEST;
                return false;
            }

            if (ascore.getNamaparam().equals("BASE")) {
                List<TestAscore> slik = this.ascoreRepository.validasiBase(ascore.getIs_slik());
                System.out.println("test");
                if (slik.size() > 0) {
                    if (ascore.getIs_slik()) {
                        response.put("status", "BASE dengan SLIK sudah ada di Ascore ");
                    } else {
                        response.put("status", "BASE dengan NON-SLIK sudah ada di Ascore ");
                    }
                    return false;
                }
            }

            // cek tiap operator
            if (kategori.get().getOperator().equals("LENGTH")) {
                System.out.println(kategori.get().getTrim_length());

                if (ascore.getValue1() != null || ascore.getValue2() != null || ascore.getValue_str() == null) {
                    response.put("status", "value1 dan value2 harus kosong,value_str harus ada");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

                if (ascore.getValue_str().length() != kategori.get().getTrim_length()) {
                    response.put("status", "panjang value_str harus " + kategori.get().getTrim_length()
                            + " karakter (lihat trim_length)");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

                List<TestAscore> list = this.ascoreRepository.findByNamaParamCustom(ascore.getNamaparam());
                for (TestAscore isi : list) {
                    if (isi.getValue_str().equals(ascore.getValue_str())) {
                        response.put("status", "parameter " + ascore.getNamaparam() + " sudah memiliki value : "
                                + ascore.getValue_str());
                        new_status = HttpStatus.BAD_REQUEST;
                        return false;
                    }
                }

                // System.out.println(ascore.getValue2());

            }
            if (kategori.get().getOperator().equals("BETWEEN")) {
                System.out.println(kategori.get().getLength());

                if (ascore.getValue1() == null || ascore.getValue2() == null || ascore.getValue_str() != null) {
                    response.put("status", "value_str harus kosong, value1 dan value2 harus ada");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

                if (ascore.getValue1().toString().length() > kategori.get().getLength()
                        || ascore.getValue2().toString().length() > kategori.get().getLength()) {
                    response.put("status", "panjang value1 dan value2 harus kurang dari " + kategori.get().getLength()
                            + " karakter (lihat length)");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

                if (ascore.getValue1() >= ascore.getValue2()) {
                    response.put("status", "value1 tidak boleh lebih besar sama dengan dari value2");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

                List<TestAscore> validasi = this.ascoreRepository.validasiBetween(ascore.getNamaparam(),
                        ascore.getIs_slik(),
                        ascore.getValue1(), ascore.getValue2());
                System.out.println("validasi :" + validasi.size());
                if (validasi.isEmpty() == false) {
                    response.put("status", "value1 dan value2 berada pada range " + validasi.get(0).getValue1()
                            + " dan " + validasi.get(0).getValue2());
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

            }
            if (kategori.get().getOperator().equals("EQUAL")) {
                // System.out.println(kategori.get().getLength());

                if (ascore.getValue1() != null || ascore.getValue2() != null || ascore.getValue_str() == null) {
                    response.put("status", "value1 dan value2 harus kosong,value_str harus ada");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }

                if (ascore.getValue_str().length() > kategori.get().getLength()) {
                    response.put("status", "panjang value_str harus kurang dari" + kategori.get().getLength()
                            + " karakter (lihat length)");
                    new_status = HttpStatus.BAD_REQUEST;
                    return false;
                }
                List<TestAscore> list = this.ascoreRepository.findByNamaParamCustom(ascore.getNamaparam());
                for (TestAscore isi : list) {
                    if (isi.getValue_str().equals(ascore.getValue_str())) {
                        response.put("status", "parameter " + ascore.getNamaparam() + " sudah memiliki value : "
                                + ascore.getValue_str());
                        new_status = HttpStatus.BAD_REQUEST;
                        return false;
                    }
                }

            }

        } catch (

        Exception e) {
            response.put("status", e.toString());
            return false;
        }
        return hasil;
    }

    public Boolean validasiKategori(KategoriAscore kat_ascore, HashMap<String, String> response,
            HttpStatus new_status) {
        Boolean hasil = true;
        Optional<KategoriAscore> kat_list;
        // cek jika nama sudah ada
        try {
            kat_list = this.kategoriAscoreRepository.findByNamaParamAll(kat_ascore.getNamaparam());

            System.out.println(kat_list.get().getNamaparam());
            if (kat_list.isEmpty() == false && kat_ascore.getId() == null) {
                response.put("status",
                        "parameter " + kat_ascore.getNamaparam() + " sudah ada dalam kategori aktif/nonaktif");
                new_status = HttpStatus.BAD_REQUEST;
                return false;
            }

            return true;
        } catch (Exception e) {
            String[] operator = { "BETWEEN", "EQUAL", "LENGTH" };
            if (Arrays.stream(operator).anyMatch(kat_ascore.getOperator()::contains) == false) {
                response.put("status", "operator tidak tersedia");
                new_status = HttpStatus.BAD_REQUEST;
                return false;
            }
            // System.out.println(kat_ascore.getTrim_length());
            if (kat_ascore.getOperator().equals("LENGTH") == false && kat_ascore.getTrim_length() != null) {
                response.put("status", "trim_length hanya dapat digunakan untuk operator LENGTH");
                new_status = HttpStatus.BAD_REQUEST;
                return false;
            }
            return true;
        }

    }

    public void postDataAscore(TestAscore ascore, HashMap<String, String> response, HttpStatus new_status) {
        try {
            ascore.setCreatedAt(Date.from(Instant.now()));
            ascore.setStatus_data(true);
            TestAscore save_ascore = this.ascoreRepository.save(ascore);
            response.put("status", "Berhasil menyimpan data");
            new_status = HttpStatus.OK;
        } catch (Exception e) {
            System.out.println(e);
            response.put("status", e.getMessage());
            new_status = HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping(value = "/postKategoriAscore", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, String>> postKategoriAscore(@RequestBody Map<String, Object> input) {

        ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper

        // @Valid
        KategoriAscore kat_ascore = mapper.convertValue(input, KategoriAscore.class);

        HashMap<String, String> response = new HashMap<>();
        HttpStatus new_status = HttpStatus.OK;
        System.out.println(kat_ascore.getNamaparam());
        Boolean validasi = validasiKategori(kat_ascore, response, new_status);
        System.out.println(validasi);
        try {
            if (validasi) {
                postDataKategoriAscore(kat_ascore, response, new_status);
                postLog(input, "Tambah Kategori", "POST", "/postKategoriAscore", true, 1);
                // response.put("status", "Berhasil menginput data");
            }

        } catch (Exception e) {
            postLog(input, "Tambah Kategori", "POST", "/postKategoriAscore", false, 0);
            response.put("status", "Gagal menginput data");

        }
        return new ResponseEntity<Map<String, String>>(response, new_status);

    }

    public void postDataKategoriAscore(@Valid KategoriAscore kat_ascore, HashMap<String, String> response,
            HttpStatus new_status) {
        try {
            kat_ascore.setCreatedAt(Date.from(Instant.now()));
            kat_ascore.setStatus_data(true);
            KategoriAscore save_ascore = this.kategoriAscoreRepository.save(kat_ascore);
            response.put("status", "berhasil menyimpan data");
            new_status = HttpStatus.OK;

        } catch (ConstraintViolationException ce) {
            List<String> errorMessages = ce.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());
            for (String string : errorMessages) {
                response.put("status", string);
            }

            new_status = HttpStatus.BAD_REQUEST;
        }
    }

    @PostMapping(value = "/score", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> Score(@RequestBody Map<String, Object> userMap) {
        HashMap<String, Integer> response_score = new HashMap<>();
        try {
            int[] temp_score = { 0, 0, 0 };
            for (Map.Entry mp : userMap.entrySet()) {
                String temp_key = mp.getKey().toString();
                String temp_value = mp.getValue().toString();
                List<TestAscore> list = this.ascoreRepository.findByNamaParamCustom(temp_key);
                for (int i = 0; i < list.size(); i++) {
                    switch (list.get(i).getKategoriAscore().getOperator()) {
                        case "BETWEEN":
                            System.out.println("between");
                            if (Integer.parseInt(temp_value) >= list.get(i).getValue1()
                                    && Integer.parseInt(temp_value) <= list.get(i).getValue2()) {
                                if (list.get(i).getIs_slik() == true) {
                                    temp_score[0] += list.get(i).getScore();
                                } else {
                                    temp_score[1] += list.get(i).getScore();
                                }
                            }
                            break;
                        case "LENGTH":
                            String new_value = temp_value.substring(0,
                                    list.get(i).getKategoriAscore().getTrim_length());
                            System.out.println(new_value);
                            if (list.get(i).getValue_str().equals(new_value)) {
                                if (list.get(i).getIs_slik() == true) {
                                    temp_score[0] += list.get(i).getScore();
                                } else {
                                    temp_score[1] += list.get(i).getScore();
                                }
                            }
                            break;
                        default:
                            if (temp_value.equals(list.get(i).getValue_str())) {
                                System.out.println("equal");
                                if (list.get(i).getIs_slik() == true) {
                                    temp_score[0] += list.get(i).getScore();
                                } else {
                                    temp_score[1] += list.get(i).getScore();
                                }
                            }
                            break;
                    }

                }
            }
            List<TestAscore> list = this.ascoreRepository.findByNamaParamCustom("BASE");
            if (list.isEmpty()) {
                response_score.put("BASE NOT INCLUDED, CANNOT FIND BASE ON KATEGORI", 400);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIs_slik() == true) {
                        temp_score[0] += list.get(i).getScore();
                    } else {
                        temp_score[1] += list.get(i).getScore();
                    }
                }

            }
            for (int i = 0; i < temp_score.length; i++) {
                response_score.put("rc", 200);
                response_score.put("slik", temp_score[0]);
                response_score.put("nonslik", temp_score[1]);
                // response_score.put("rc",200);

            }
            postLog(userMap, "scoring", "POST", "/score", true, temp_score[0]);
        } catch (Exception e) {
            response_score.put("gagal :", 400);
            postLog(userMap, "scoring", "POST", "/score", false, 0);

        }

        return new ResponseEntity<Map<String, Integer>>(response_score, HttpStatus.OK);
    }

    public void postLog(Map<String, Object> map, String req_detail, String req_type, String req_url, Boolean status,
            Integer skor) {
        if (map.isEmpty() == false) {
            LogAscore log = new LogAscore();
            ArrayList new_value = new ArrayList();
            ArrayList new_param = new ArrayList();
            for (Map.Entry mp : map.entrySet()) {
                if (mp.getKey() == "reqId") {
                    log.setReqId(mp.getValue().toString());
                } else if (mp.getKey() == "channel") {
                    log.setChannel(mp.getValue().toString());
                } else {
                    new_value.add(mp.getValue());
                    new_param.add(mp.getKey());
                }

            }
            // System.out.println(new_param.toString());
            LocalDateTime now = LocalDateTime.now();

            log.setParam(new_param.toString());
            log.setValue(new_value.toString());
            log.setIs_success(status);
            log.setReq_detail(req_detail);
            log.setReq_type(req_type);
            log.setReq_url(req_url);
            log.setCreated_at(now);
            log.setResult(skor);

            this.logAscoreRepository.save(log);
        }

    }
}
