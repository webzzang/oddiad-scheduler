package com.exflyer.oddiad.scheduler.task.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "header")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherHeader {

  @XmlElement(name = "tm")
  private String tm;

  @XmlElement(name = "ts")
  private String ts;

  @XmlElement(name = "x")
  private Integer x;

  @XmlElement(name = "y")
  private Integer y;
}
