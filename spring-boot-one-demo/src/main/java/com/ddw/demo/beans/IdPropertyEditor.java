package com.ddw.demo.beans;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class IdPropertyEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        if (StringUtils.hasText(text)) {
            long id = Long.parseLong(text);
            setValue(id);
        } else {
            setValue(Long.MIN_VALUE);
        }


    }
}
