package br.org.ufpr.tcc.util;

import static javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DataUtil {
	
	public static boolean isDataPassado(Date date) {
        return isDataPassado(toLocalDateTime(date));
    }
	
	public static boolean isDataFuturo(Date date) {
        return isDataFuturo(toLocalDateTime(date));
    }

    public static boolean isDataPassado(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }
    
    public static boolean isDataFuturo(LocalDateTime date) {
        return date.isAfter(LocalDateTime.now());
    }

    public static boolean isDataPassado(Date date, ZoneId zoneId) {
        return isDataPassado(toLocalDateTime(date, zoneId));
    }
    
    public static boolean isDataFuturo(Date date, ZoneId zoneId) {
        return isDataFuturo(toLocalDateTime(date, zoneId));
    }

    public static boolean isDataPassado(LocalDateTime date, ZoneId zoneId) {
        return date.isBefore(LocalDateTime.now(zoneId));
    }

    public static int compare(Date date, LocalDate localDate) {
        LocalDate toCompare = toLocalDate(date);
        return toCompare.compareTo(localDate);
    }

    public static int compare(Calendar date, LocalDate localDate) {
        LocalDate toCompare = toLocalDate(date.getTime());
        return toCompare.compareTo(localDate);
    }

    public static int compare(Date date, LocalDateTime localDateTime) {
        LocalDateTime toCompare = toLocalDateTime(date);
        return toCompare.compareTo(localDateTime);
    }

    public static int compare(Calendar date, LocalDateTime localDateTime) {
        LocalDateTime toCompare = toLocalDateTime(date.getTime());
        return toCompare.compareTo(localDateTime);
    }

    public static String fromDateToString(Date date) {
        return fromLocalDateTimeToString(toLocalDateTime(date));
    }
    
    public static String fromDateToString(Date date, String format) {
        return fromLocalDateTimeToString(toLocalDateTime(date), format);
    }

    public static String fromLocalDateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public static String fromLocalDateTimeToString(LocalDateTime dateTime, String format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String fromLocalDateToString(LocalDate dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String fromLocalDateTimeToIso(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toString();
    }

    public static String fromLocalTimeToString(LocalTime time) {
        return String.format("%02d:%02d", time.getHour(), time.getMinute());
    }

    public static XMLGregorianCalendar fromLocalDateToXMLGregorianCalendar(LocalDate date) {
        if (isNotNull(date)) {
            try {
                GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
                XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
                xcal.setTimezone(FIELD_UNDEFINED);

                return xcal;

            } catch (DatatypeConfigurationException e) {
                // TODO: handle exception
            }
        }

        return null;
    }

    public static LocalDateTime toLodalDateTime(XMLGregorianCalendar xmlDate) {
        LocalDateTime localDateTime = null;

        if (isNotNull(xmlDate)) {
            localDateTime = toLocalDateTime(xmlDate.toGregorianCalendar().getTime());
        }

        return localDateTime;
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(Date date, String time) {
        LocalDate localDate = toLocalDate(date);
        LocalTime localTime = toLocalTime(time);
        return LocalDateTime.of(localDate, localTime);
    }

    public static LocalTime toLocalTime(String time) {
        String[] split = time.split(":");
        return LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public static LocalDate toLocalDate(XMLGregorianCalendar xmlDate) {
        LocalDate localDate = null;

        if (isNotNull(xmlDate)) {
            localDate = toLocalDate(xmlDate.toGregorianCalendar().getTime());
        }

        return localDate;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(date, ZoneId.systemDefault());
    }

    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneID) {
        LocalDateTime retorno = null;

        if (isNotNull(date)) {
            Instant instant = Instant.ofEpochMilli(date.getTime());
            retorno = LocalDateTime.ofInstant(instant, zoneID);
        }

        return retorno;
    }

    public static Date toDate(LocalDateTime data) {
        return toDate(data, ZoneId.systemDefault());
    }

    public static Date toDate(LocalDate data) {
        return toDate(data, ZoneId.systemDefault());
    }

    public static Date toDate(LocalDateTime data, ZoneId zoneID) {
        Date date = null;

        if (data != null) {
            date = Date.from(data.atZone(zoneID).toInstant());
        }

        return date;
    }

    public static Date toDate(LocalDate data, ZoneId zoneID) {
        Date date = null;

        if (isNotNull(data)) {
            date = Date.from(data.atStartOfDay(zoneID).toInstant());
        }

        return date;
    }

    public static LocalDate toLocalDate(String dataString) {
        LocalDate retorno = null;
        if (dataString != null) {
            retorno = toLocalDate(toDate(dataString));
        }
        return retorno;
    }

    public static LocalDateTime toLocalDateTime(String dataString) {
        LocalDateTime retorno = null;
        if (dataString != null) {
            retorno = toLocalDateTime(toDate(dataString));
        }
        return retorno;
    }

    public static XMLGregorianCalendar toXMLCalendar(LocalDate date) {
        return toXMLCalendar(toDate(date));
    }

    public static XMLGregorianCalendar toXMLCalendar(LocalDateTime date) {
        return toXMLCalendar(toDate(date));
    }

    public static XMLGregorianCalendar toXMLCalendar(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory;
        try {
            datatypeFactory = DatatypeFactory.newInstance();
            gregorianCalendar.setTime(date);
            XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
            return now;
        } catch (DatatypeConfigurationException e) {
            return null;
        }
    }

    public static Date isoDateToDate(String isoDate) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(isoDate);
        return Date.from(Instant.from(accessor));
    }

    public static LocalDateTime isoDateToLocalDateTime(String isoDate) {
        return toLocalDateTime(isoDateToDate(isoDate));
    }

    public static Date toDate(String dataString) {
        return toDate(dataString, "dd/MM/yyyy");
    }

    public static Date toDate(String dataString, String dateFormat) {
        Date retorno = null;

        if (dataString != null) {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            try {
                retorno = df.parse(dataString);
            } catch (final ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return retorno;
    }

    public static LocalDate toLocalDate(Date date) {
        return isNotNull(date) ? toLocalDateTime(date).toLocalDate() : null;
    }
    
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

}
