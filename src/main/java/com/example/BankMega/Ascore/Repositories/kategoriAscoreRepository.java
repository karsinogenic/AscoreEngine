package com.example.BankMega.Ascore.Repositories;

import com.example.BankMega.Ascore.Model.KategoriAscore;
import com.example.BankMega.Ascore.Model.TestAscore;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface kategoriAscoreRepository extends JpaRepository<KategoriAscore, Long> {
    @Query("select k from KategoriAscore k where k.namaparam NOT IN (:param) AND k.statusdata=TRUE")
    List<KategoriAscore> findnamaparamNotIn(@Param("param") List<String> param);

    @Query("select k from KategoriAscore k where k.namaparam=:param AND k.statusdata=TRUE")
    Optional<KategoriAscore> findByNamaParam(@Param("param") String param);

    @Query("select k from KategoriAscore k where k.namaparam=:param")
    Optional<KategoriAscore> findByNamaParamAll(@Param("param") String param);

    @Query("select  namaparam from KategoriAscore where operator NOT IN (:operator) AND statusdata=TRUE")
    String[] findParamNotEqualOperator(@Param("operator") String operator);

    @Query("select e from KategoriAscore e where e.statusdata=TRUE")
    List<KategoriAscore> findAllCustom(Sort sort);

    @Query("select e from KategoriAscore e where e.statusdata=FALSE")
    List<KategoriAscore> findAllNonAktif(Sort sort);
}
