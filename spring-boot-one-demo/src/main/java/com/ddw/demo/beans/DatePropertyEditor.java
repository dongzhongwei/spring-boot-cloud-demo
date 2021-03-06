package com.ddw.demo.beans;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {

    public void setAsText(String text){
        if(StringUtils.hasText(text)){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
            try {
                Date date = dateFormat.parse(text);
                setValue(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
