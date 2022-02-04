package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.model.Files;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FilesRepository extends JpaRepository<Files, Long>, JpaSpecificationExecutor<Files> {


  @Query(value = "select f.* from adv ad, adv_file adf, files f "
    + "where ad.seq = adf.adv_seq "
    + "and adf.file_seq = f.seq "
    + "and ad.seq = :advSeq", nativeQuery = true)
  List<Files> findByAdvSeq(Long advSeq);

  @Query(value = "select * from files "
    + "where seq in ( "
    + "    select pdaf.default_adv_file_seq "
    + "    from partner_default_adv pda, "
    + "         partner pt, "
    + "         partner_default_adv_files pdaf "
    + "    where pda.partner_seq = :partnerSeq "
    + "      and pda.channel_type = 'PTT001' /* 오디존 */ "
    + "      and pda.expo = true "
    + "      and pda.partner_seq = pt.seq "
    + "      and pt.operation = true "
    + "      and pt.channel_type = pdaf.channel_type)", nativeQuery = true)
  List<Files> findAllDefaultAdvByPartnerSeq(Long partnerSeq);

  @Query(value = "select * from files "
    + "where seq in (select pdaf.default_adv_file_seq "
    + "              from partner_default_adv_files pdaf "
    + "              where pdaf.channel_type = 'PTT001' "
    + ")", nativeQuery = true)
  List<Files> findAllDefaultAdv();
}
