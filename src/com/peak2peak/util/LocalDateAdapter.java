package com.peak2peak.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.security.KeyStore;
import java.time.LocalDate;

/**
 * Created by colinhill on 2/9/16.
 *
 *  Adapter (for JAXB) to convert between the LocalDate and the ISO 8601
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception{
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
