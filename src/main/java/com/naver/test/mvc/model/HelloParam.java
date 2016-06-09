package com.naver.test.mvc.model;

import java.time.DayOfWeek;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.naver.test.formatter.FormatTelephone;

public class HelloParam {
	private String message;

	private Boolean lightYn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFormat;
	@NumberFormat(pattern = "#,##0")
	private int priceFormat;

	private DayOfWeek dayEnumFormat;

	@FormatTelephone("(###)###-####")
	private String phone;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getLightYn() {
		return lightYn;
	}

	public void setLightYn(Boolean lightYn) {
		this.lightYn = lightYn;
	}

	public Date getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(Date dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getPriceFormat() {
		return priceFormat;
	}

	public void setPriceFormat(int priceFormat) {
		this.priceFormat = priceFormat;
	}

	public DayOfWeek getDayEnumFormat() {
		return dayEnumFormat;
	}

	public void setDayEnumFormat(DayOfWeek dayEnumFormat) {
		this.dayEnumFormat = dayEnumFormat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
