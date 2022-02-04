package com.exflyer.oddiad.scheduler.task.service.media;


import com.exflyer.oddiad.scheduler.task.config.AwsMediaConfig;
import com.exflyer.oddiad.scheduler.task.config.AwsS3PublicConfig;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.mediaconvert.MediaConvertClient;
import software.amazon.awssdk.services.mediaconvert.model.AacAudioDescriptionBroadcasterMix;
import software.amazon.awssdk.services.mediaconvert.model.AacCodecProfile;
import software.amazon.awssdk.services.mediaconvert.model.AacCodingMode;
import software.amazon.awssdk.services.mediaconvert.model.AacRateControlMode;
import software.amazon.awssdk.services.mediaconvert.model.AacRawFormat;
import software.amazon.awssdk.services.mediaconvert.model.AacSettings;
import software.amazon.awssdk.services.mediaconvert.model.AacSpecification;
import software.amazon.awssdk.services.mediaconvert.model.AfdSignaling;
import software.amazon.awssdk.services.mediaconvert.model.AntiAlias;
import software.amazon.awssdk.services.mediaconvert.model.AudioCodec;
import software.amazon.awssdk.services.mediaconvert.model.AudioCodecSettings;
import software.amazon.awssdk.services.mediaconvert.model.AudioDefaultSelection;
import software.amazon.awssdk.services.mediaconvert.model.AudioDescription;
import software.amazon.awssdk.services.mediaconvert.model.AudioLanguageCodeControl;
import software.amazon.awssdk.services.mediaconvert.model.AudioSelector;
import software.amazon.awssdk.services.mediaconvert.model.AudioTypeControl;
import software.amazon.awssdk.services.mediaconvert.model.ColorMetadata;
import software.amazon.awssdk.services.mediaconvert.model.ColorSpace;
import software.amazon.awssdk.services.mediaconvert.model.ContainerSettings;
import software.amazon.awssdk.services.mediaconvert.model.ContainerType;
import software.amazon.awssdk.services.mediaconvert.model.CreateJobRequest;
import software.amazon.awssdk.services.mediaconvert.model.CreateJobResponse;
import software.amazon.awssdk.services.mediaconvert.model.DropFrameTimecode;
import software.amazon.awssdk.services.mediaconvert.model.H264AdaptiveQuantization;
import software.amazon.awssdk.services.mediaconvert.model.H264CodecLevel;
import software.amazon.awssdk.services.mediaconvert.model.H264CodecProfile;
import software.amazon.awssdk.services.mediaconvert.model.H264DynamicSubGop;
import software.amazon.awssdk.services.mediaconvert.model.H264EntropyEncoding;
import software.amazon.awssdk.services.mediaconvert.model.H264FieldEncoding;
import software.amazon.awssdk.services.mediaconvert.model.H264FlickerAdaptiveQuantization;
import software.amazon.awssdk.services.mediaconvert.model.H264FramerateControl;
import software.amazon.awssdk.services.mediaconvert.model.H264FramerateConversionAlgorithm;
import software.amazon.awssdk.services.mediaconvert.model.H264GopBReference;
import software.amazon.awssdk.services.mediaconvert.model.H264GopSizeUnits;
import software.amazon.awssdk.services.mediaconvert.model.H264InterlaceMode;
import software.amazon.awssdk.services.mediaconvert.model.H264ParControl;
import software.amazon.awssdk.services.mediaconvert.model.H264QualityTuningLevel;
import software.amazon.awssdk.services.mediaconvert.model.H264QvbrSettings;
import software.amazon.awssdk.services.mediaconvert.model.H264RateControlMode;
import software.amazon.awssdk.services.mediaconvert.model.H264RepeatPps;
import software.amazon.awssdk.services.mediaconvert.model.H264SceneChangeDetect;
import software.amazon.awssdk.services.mediaconvert.model.H264Settings;
import software.amazon.awssdk.services.mediaconvert.model.H264SlowPal;
import software.amazon.awssdk.services.mediaconvert.model.H264SpatialAdaptiveQuantization;
import software.amazon.awssdk.services.mediaconvert.model.H264Syntax;
import software.amazon.awssdk.services.mediaconvert.model.H264Telecine;
import software.amazon.awssdk.services.mediaconvert.model.H264TemporalAdaptiveQuantization;
import software.amazon.awssdk.services.mediaconvert.model.H264UnregisteredSeiTimecode;
import software.amazon.awssdk.services.mediaconvert.model.HlsCaptionLanguageSetting;
import software.amazon.awssdk.services.mediaconvert.model.HlsClientCache;
import software.amazon.awssdk.services.mediaconvert.model.HlsCodecSpecification;
import software.amazon.awssdk.services.mediaconvert.model.HlsDirectoryStructure;
import software.amazon.awssdk.services.mediaconvert.model.HlsGroupSettings;
import software.amazon.awssdk.services.mediaconvert.model.HlsIFrameOnlyManifest;
import software.amazon.awssdk.services.mediaconvert.model.HlsManifestCompression;
import software.amazon.awssdk.services.mediaconvert.model.HlsManifestDurationFormat;
import software.amazon.awssdk.services.mediaconvert.model.HlsOutputSelection;
import software.amazon.awssdk.services.mediaconvert.model.HlsProgramDateTime;
import software.amazon.awssdk.services.mediaconvert.model.HlsSegmentControl;
import software.amazon.awssdk.services.mediaconvert.model.HlsSettings;
import software.amazon.awssdk.services.mediaconvert.model.HlsStreamInfResolution;
import software.amazon.awssdk.services.mediaconvert.model.HlsTimedMetadataId3Frame;
import software.amazon.awssdk.services.mediaconvert.model.Input;
import software.amazon.awssdk.services.mediaconvert.model.InputDeblockFilter;
import software.amazon.awssdk.services.mediaconvert.model.InputDenoiseFilter;
import software.amazon.awssdk.services.mediaconvert.model.InputFilterEnable;
import software.amazon.awssdk.services.mediaconvert.model.InputPsiControl;
import software.amazon.awssdk.services.mediaconvert.model.InputRotate;
import software.amazon.awssdk.services.mediaconvert.model.InputTimecodeSource;
import software.amazon.awssdk.services.mediaconvert.model.JobSettings;
import software.amazon.awssdk.services.mediaconvert.model.M3u8NielsenId3;
import software.amazon.awssdk.services.mediaconvert.model.M3u8PcrControl;
import software.amazon.awssdk.services.mediaconvert.model.M3u8Scte35Source;
import software.amazon.awssdk.services.mediaconvert.model.M3u8Settings;
import software.amazon.awssdk.services.mediaconvert.model.MediaConvertException;
import software.amazon.awssdk.services.mediaconvert.model.Output;
import software.amazon.awssdk.services.mediaconvert.model.OutputGroup;
import software.amazon.awssdk.services.mediaconvert.model.OutputGroupSettings;
import software.amazon.awssdk.services.mediaconvert.model.OutputGroupType;
import software.amazon.awssdk.services.mediaconvert.model.OutputSettings;
import software.amazon.awssdk.services.mediaconvert.model.RespondToAfd;
import software.amazon.awssdk.services.mediaconvert.model.ScalingBehavior;
import software.amazon.awssdk.services.mediaconvert.model.TimedMetadata;
import software.amazon.awssdk.services.mediaconvert.model.VideoCodec;
import software.amazon.awssdk.services.mediaconvert.model.VideoCodecSettings;
import software.amazon.awssdk.services.mediaconvert.model.VideoDescription;
import software.amazon.awssdk.services.mediaconvert.model.VideoSelector;
import software.amazon.awssdk.services.mediaconvert.model.VideoTimecodeInsertion;

@Component
@Slf4j
public class MediaConverter {

  @Autowired
  private AwsS3PublicConfig awsS3PublicConfig;

  @Autowired
  private AwsMediaConfig awsMediaConfig;


  private MediaConvertClient emc;


  @PostConstruct
  public void setS3PublicClient() {
    emc = MediaConvertClient.builder()
      .region(Region.of(awsMediaConfig.getRegion()))
      .endpointOverride(URI.create(awsMediaConfig.getEndPoint()))
      .build();


  }

  public String convertMedia(String fileUniqName) {
    return createMediaJob(awsMediaConfig.getRoleArn(),
      "s3://" + awsS3PublicConfig.getBucket() + awsS3PublicConfig.getVideoDir() + "/"
        + fileUniqName, fileUniqName);
  }

  public String createMediaJob(String mcRoleARN, String fileInput,
    String originFileName) {

    // 확장자 제거
    String removedExtentionFile = originFileName.substring(0, originFileName.length() - 4);
    String fileOutput =
      "s3://" + awsS3PublicConfig.getBucket() + awsMediaConfig.getFileOutDir() + "/" + removedExtentionFile + "/";
    try {
      /**
       * Make the DescribeEndpoints call only once, then hardcode or cache your endpoint.
       * This endpoint is specific to your AWS account and won't change.
       *
       * Don't use DescribeEndpoints to create your AWS client each time that you make a request to MediaConvert.
       * Otherwise, you will reach the throttle maximum on the public API endpoint.
       */
//            String endpointURL = "https://mlboolfjb.mediaconvert.us-west-2.amazonaws.com";

      OutputGroup appleHLS = getOutputGroup(fileOutput);

      Map<String, AudioSelector> audioSelectors = getAudioSelector();

      JobSettings jobSettings = getJobSettings(fileInput, appleHLS, audioSelectors);

      CreateJobRequest createJobRequest = CreateJobRequest.builder().role(mcRoleARN)
        .settings(jobSettings).build();

      CreateJobResponse createJobResponse = emc.createJob(createJobRequest);
      log.debug("job Id : {}", createJobResponse.job().id());
      if (StringUtils.isBlank(createJobResponse.job().id())) {
        return null;
      }
      return awsMediaConfig.getHost() + removedExtentionFile + "/" + removedExtentionFile + ".m3u8";

    } catch (MediaConvertException e) {
      log.error("MediaConvertException ", e);
      return "";
    }
  }

  private OutputGroup getOutputGroup(String fileOutput) {
    Output hlsHigh = createOutput("_$dt$", 3500000, 8, 30, 1920,
      1080, 1920);
    OutputGroup appleHLS = OutputGroup.builder().name("Apple HLS").customName("oddiad")
      .outputGroupSettings(OutputGroupSettings.builder().type(OutputGroupType.HLS_GROUP_SETTINGS)
        .hlsGroupSettings(HlsGroupSettings.builder()
          .directoryStructure(HlsDirectoryStructure.SINGLE_DIRECTORY)
          .manifestDurationFormat(HlsManifestDurationFormat.INTEGER)
          .streamInfResolution(HlsStreamInfResolution.INCLUDE)
          .clientCache(HlsClientCache.ENABLED)
          .captionLanguageSetting(HlsCaptionLanguageSetting.OMIT)
          .manifestCompression(HlsManifestCompression.NONE)
          .codecSpecification(HlsCodecSpecification.RFC_4281)
          .outputSelection(HlsOutputSelection.MANIFESTS_AND_SEGMENTS)
          .programDateTime(HlsProgramDateTime.EXCLUDE).programDateTimePeriod(600)
          .timedMetadataId3Frame(HlsTimedMetadataId3Frame.PRIV).timedMetadataId3Period(10)
          .destination(fileOutput).segmentControl(HlsSegmentControl.SEGMENTED_FILES)
          .minFinalSegmentLength((double) 0).segmentLength(4).minSegmentLength(0).build())
        .build())
      .outputs(hlsHigh).build();
    return appleHLS;
  }

  private Map<String, AudioSelector> getAudioSelector() {
    Map<String, AudioSelector> audioSelectors = new HashMap<String, AudioSelector>();
    audioSelectors.put("Audio Selector 1",
      AudioSelector.builder().defaultSelection(AudioDefaultSelection.DEFAULT).offset(0)
        .build());
    return audioSelectors;
  }

  private JobSettings getJobSettings(String fileInput, OutputGroup appleHLS,
    Map<String, AudioSelector> audioSelectors) {
    JobSettings jobSettings = JobSettings.builder()
      .inputs(Input.builder().audioSelectors(audioSelectors)
        .videoSelector(VideoSelector.builder().colorSpace(ColorSpace.FOLLOW)
          .rotate(InputRotate.AUTO).build())
        .filterEnable(InputFilterEnable.AUTO).filterStrength(0)
        .deblockFilter(InputDeblockFilter.DISABLED)
        .denoiseFilter(InputDenoiseFilter.DISABLED).psiControl(InputPsiControl.USE_PSI)
        .timecodeSource(InputTimecodeSource.EMBEDDED).fileInput(fileInput).build())
      .outputGroups(appleHLS).build();
    return jobSettings;
  }

  // snippet-start:[mediaconvert.java.createjob.create_output]
  private Output createOutput(
    String segmentModifier,
    int qvbrMaxBitrate,
    int qvbrQualityLevel,
    int frame,
    int originWidth,
    int originHeight,
    int targetWidth) {
    int targetHeight = Math.round(originHeight * targetWidth / originWidth)
      - (Math.round(originHeight * targetWidth / originWidth) % 4);
    Output output = null;
    try {
      output = Output.builder().nameModifier("_30fps").outputSettings(OutputSettings.builder()
        .hlsSettings(HlsSettings.builder().segmentModifier(segmentModifier).audioGroupId("program_audio")
          .iFrameOnlyManifest(HlsIFrameOnlyManifest.EXCLUDE).build())
        .build())
        .containerSettings(ContainerSettings.builder().container(ContainerType.M3_U8)
          .m3u8Settings(M3u8Settings.builder().audioFramesPerPes(4)
            .pcrControl(M3u8PcrControl.PCR_EVERY_PES_PACKET).pmtPid(480).privateMetadataPid(503)
            .programNumber(1).patInterval(0).pmtInterval(0).scte35Source(M3u8Scte35Source.NONE)
            .scte35Pid(500).nielsenId3(M3u8NielsenId3.NONE).timedMetadata(TimedMetadata.NONE)
            .timedMetadataPid(502).videoPid(481)
            .audioPids(482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492).build())
          .build())
        .videoDescription(
          VideoDescription.builder().width(targetWidth).height(targetHeight)
            .scalingBehavior(ScalingBehavior.DEFAULT).sharpness(50).antiAlias(AntiAlias.ENABLED)
            .timecodeInsertion(VideoTimecodeInsertion.DISABLED)
            .colorMetadata(ColorMetadata.INSERT).respondToAfd(RespondToAfd.NONE)
            .afdSignaling(AfdSignaling.NONE).dropFrameTimecode(DropFrameTimecode.ENABLED)
            .codecSettings(VideoCodecSettings.builder().codec(VideoCodec.H_264)
              .h264Settings(H264Settings.builder()
                /* 수정 구간 S */
                .framerateConversionAlgorithm(H264FramerateConversionAlgorithm.FRAMEFORMER)
                .framerateNumerator(frame).framerateDenominator(1) //<-- 정수30FPS 로 처리 | 기본공식 24000/1001 = 23.976fps
                /* 수정 구간 E */

                .rateControlMode(H264RateControlMode.QVBR)
                .parControl(H264ParControl.INITIALIZE_FROM_SOURCE)
                .qualityTuningLevel(H264QualityTuningLevel.SINGLE_PASS)
                .qvbrSettings(H264QvbrSettings.builder()
                  .qvbrQualityLevel(qvbrQualityLevel).build())
                .codecLevel(H264CodecLevel.AUTO)
                .codecProfile((targetHeight > 720 && targetWidth > 1280)
                  ? H264CodecProfile.HIGH
                  : H264CodecProfile.MAIN)
                .maxBitrate(qvbrMaxBitrate)
                .framerateControl(H264FramerateControl.SPECIFIED)
                .gopSize(2.0).gopSizeUnits(H264GopSizeUnits.SECONDS)
                .numberBFramesBetweenReferenceFrames(2).gopClosedCadence(1)
                .gopBReference(H264GopBReference.DISABLED)
                .slowPal(H264SlowPal.DISABLED).syntax(H264Syntax.DEFAULT)
                .numberReferenceFrames(3).dynamicSubGop(H264DynamicSubGop.STATIC)
                .fieldEncoding(H264FieldEncoding.PAFF)
                .sceneChangeDetect(H264SceneChangeDetect.ENABLED).minIInterval(0)
                .telecine(H264Telecine.NONE)
                .framerateConversionAlgorithm(
                  H264FramerateConversionAlgorithm.DUPLICATE_DROP)
                .entropyEncoding(H264EntropyEncoding.CABAC).slices(1)
                .unregisteredSeiTimecode(H264UnregisteredSeiTimecode.DISABLED)
                .repeatPps(H264RepeatPps.DISABLED)
                .adaptiveQuantization(H264AdaptiveQuantization.HIGH)
                .spatialAdaptiveQuantization(
                  H264SpatialAdaptiveQuantization.ENABLED)
                .temporalAdaptiveQuantization(
                  H264TemporalAdaptiveQuantization.ENABLED)
                .flickerAdaptiveQuantization(
                  H264FlickerAdaptiveQuantization.DISABLED)
                .softness(0).interlaceMode(H264InterlaceMode.PROGRESSIVE).build())
              .build())
            .build())
        .audioDescriptions(AudioDescription.builder().audioTypeControl(AudioTypeControl.FOLLOW_INPUT)
          .languageCodeControl(AudioLanguageCodeControl.FOLLOW_INPUT)
          .codecSettings(AudioCodecSettings.builder().codec(AudioCodec.AAC).aacSettings(AacSettings
            .builder().codecProfile(AacCodecProfile.LC).rateControlMode(AacRateControlMode.CBR)
            .codingMode(AacCodingMode.CODING_MODE_2_0).sampleRate(44100).bitrate(96000)
            .rawFormat(AacRawFormat.NONE).specification(AacSpecification.MPEG4)
            .audioDescriptionBroadcasterMix(AacAudioDescriptionBroadcasterMix.NORMAL).build())
            .build())
          .build())
        .build();
    } catch (MediaConvertException e) {
      System.out.println(e.toString());
      System.exit(0);
    }
    return output;
  }
}

