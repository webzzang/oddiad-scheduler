package com.exflyer.oddiad.scheduler.task.dto;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "wid")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherResponse {


  @XmlElement(name = "header")
  private WeatherHeader weatherHeader;

  @XmlElement(name = "body")
  private WeatherBody weatherBody;


}
