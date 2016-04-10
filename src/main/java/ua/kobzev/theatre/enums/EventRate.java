package ua.kobzev.theatre.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "eventRate", namespace = "http://ua.kobzev.theatre/soap")
@XmlEnum
public enum EventRate {
	LOW, MID, HIGH;
}
