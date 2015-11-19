package com.naver.test.mvc.model;

import java.time.DayOfWeek;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class HelloParam {
	private String message;

	private Boolean lightYn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFormat;
	@NumberFormat(pattern = "#,##0")
	private int priceFormat;

	private DayOfWeek dayEnumFormat;

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

	/*
	 * @Override public String toString() { return
	 * ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	 * }
	 */
}
