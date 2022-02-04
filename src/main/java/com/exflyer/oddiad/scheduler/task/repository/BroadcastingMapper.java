package com.exflyer.oddiad.scheduler.task.repository;

import com.exflyer.oddiad.scheduler.task.dto.BroadcastingDevice;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastingMapper {

  List<BroadcastingDevice> findBroadcastingDevice(String targetDay, Long partnerSeq, String deviceId);

  List<String> findDeviceIdByPartnerSeq(Long partnerSeq);

  List<BroadcastingDevice> findDefaultAdvDevice();

  List<String> findDevice();

  List<BroadcastingDevice> findBroadcastingNewMappingDeviceForDefaultAd();
}
