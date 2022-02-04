package com.exflyer.oddiad.scheduler.task.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherData {

    @XmlElement(name = "hour")
    private Integer hour;

    @XmlElement(name = "day")
    private Integer day;

    @XmlElement(name = "temp")
    private String temp;

    @XmlElement(name = "tmx")
    private String tmx;

    @XmlElement(name = "tmn")
    private String tmn;

    @XmlElement(name = "sky")
    private String sky;

    @XmlElement(name = "pty")
    private String pty;

    @XmlElement(name = "wfKor")
    private String wfKor;

    @XmlElement(name = "wfEn")
    private String wfEn;

    @XmlElement(name = "pop")
    private String pop;

    @XmlElement(name = "r12")
    private String r12;

    @XmlElement(name = "s12")
    private String s12;

    @XmlElement(name = "ws")
    private String ws;

    @XmlElement(name = "wd")
    private String wd;

    @XmlElement(name = "wdKor")
    private String wdKor;

    @XmlElement(name = "wdEn")
    private String wdEn;

    @XmlElement(name = "reh")
    private String reh;

    @XmlElement(name = "r06")
    private String r06;

    @XmlElement(name = "s06")
    private String s06;
}
