package org.koushik.javabrains.rest.param_converter;

/**
 * This is a custom class.
 * We want to be able to convert a string param to an instance of this class
 * Therefore, we need to provide a ParamConverter and Provider that tells jaxrs that if you see
 * a request to convert to MyDate, then use my class ParamConverter to do that
 * @author van
 *
 */
public class MyDate {
	
	private int day;
	private int month;
	private int year;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString(){
		return day+":"+month+":"+year;
	}
	
	

}
