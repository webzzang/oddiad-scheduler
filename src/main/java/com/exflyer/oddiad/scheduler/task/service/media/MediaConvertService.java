package com.exflyer.oddiad.scheduler.task.service.media;

import com.exflyer.oddiad.scheduler.task.dto.MediaConvertResult;
import com.exflyer.oddiad.scheduler.task.dto.MediaConvertTarget;
import com.exflyer.oddiad.scheduler.task.model.Files;
import com.exflyer.oddiad.scheduler.task.repository.FilesRepository;
import com.exflyer.oddiad.scheduler.task.repository.MediaConvertMapper;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MediaConvertService {

  @Autowired
  private MediaConverter mediaConverter;

  @Autowired
  private FilesRepository filesRepository;

  @Autowired
  private MediaConvertMapper mediaConvertMapper;

  public MediaConvertResult convertHls(String uniqFileName) {
    String m3u8Path = mediaConverter.convertMedia(uniqFileName);
    return new MediaConvertResult(m3u8Path);
  }

  public void convert() {
    List<MediaConvertTarget> targetList = mediaConvertMapper.findTarget();
    for (MediaConvertTarget mediaConvertTarget : targetList) {
      MediaConvertResult convertResult = convertHls(mediaConvertTarget.getS3FileKey());
      Optional<Files> optionalFiles = filesRepository.findById(mediaConvertTarget.getFileSeq());
      if (optionalFiles.isPresent()) {
        Files files = optionalFiles.get();
        files.setConvertedPath(convertResult.getFilePath());
        files.setConverted(true);
        filesRepository.save(files);
      }
    }
  }
}
