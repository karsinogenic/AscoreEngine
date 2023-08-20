package com.example.BankMega.Ascore.Repositories;

import com.example.BankMega.Ascore.Model.TestAscore;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AscoreRepository extends JpaRepository<TestAscore, Long> {

    // @Query(value="select * from test_ascore where nama_param=:param AND value1 <=
    // :valuex AND value2 >= :valuex AND status_data=TRUE",nativeQuery = true)
    // List<TestAscore> findBetweenData(String param, Integer valuex);
    //
    // @Query(value="select * from test_ascore where nama_param=:param AND value1 >=
    // :valuex AND value2 is NULL AND status_data=TRUE",nativeQuery = true)
    // List<TestAscore> findLessThanData(String param, Integer valuex);
    //
    // @Query(value="select * from test_ascore where nama_param=:param AND value2 <=
    // :valuex AND value1 is NULL AND status_data=TRUE",nativeQuery = true)
    // List<TestAscore> findMoreThanData(String param, Integer valuex);
    // @Query(value="select * from test_ascore where nama_param=:param AND value_str
    // = :valuex AND status_data=TRUE",nativeQuery = true)
    // List<TestAscore> findExactData(String param, String valuex);
    //

    //

    // @Query("select DISTINCT namaparam from TestAscore where operator= :param AND
    // statusdata=TRUE")
    // String[] findParamByOperatorCustom(@Param("param") String param);
    //
    @Query("select e from TestAscore e where e.statusdata=TRUE")
    List<TestAscore> findAllCustom(Sort sort);

    // @Query("select e from TestAscore e where e.score :operator :nilai AND
    // e.statusdata=TRUE")
    // List<TestAscore> cobalagi(Integer nilai, String operator);

    //
    @Query("select e from TestAscore e  where e.namaparam=:param AND e.statusdata=TRUE")
    List<TestAscore> findByNamaParamCustom(String param);

    @Query("select e from TestAscore e where e.isslik= :is_slik and e.namaparam='BASE' and e.statusdata=TRUE")
    List<TestAscore> validasiBase(Boolean is_slik);

    @Query(value = "select ta.id,ta.created_at,ta.updated_at,ta.value_str,ta.is_slik,ta.value1 ,ta.value2, ta.nama_param,ta.score,ta.status_data"
            +
            ",ka.operator, ka.length, ka.trim_length " +
            " from test_ascore ta" +
            " inner join kategori_ascore ka on ka.namaparam = ta.nama_param" +
            " where ka.namaparam = :namaparam and ta.is_slik = :slik " +
            " and ka.status_data = true and ta.status_data = true" +
            " and ta.value1 between :value1 and :value2" +
            " and ta.value2 between :value1 and :value2", nativeQuery = true)
    List<TestAscore> validasiBetween(String namaparam, Boolean slik, Long value1, Long value2);

}
