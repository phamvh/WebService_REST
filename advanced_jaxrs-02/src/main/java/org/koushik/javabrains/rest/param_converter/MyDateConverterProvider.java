package org.koushik.javabrains.rest.param_converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

/**
 * In this class, we create a provider that provides a converter to convert a string to/from an instance of MyDate class.
 * We just need to implement method getConverter, which should return an instance of the interface ParamConverter
 *
 */
@Provider
public class MyDateConverterProvider implements ParamConverterProvider{

	
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if(rawType.getName().equals(MyDate.class.getName())){
			return new ParamConverter<T>(){

				@Override
				public T fromString(String value) {
					Calendar requestedDate = Calendar.getInstance();
					if("tomorrow".equalsIgnoreCase(value))
						requestedDate.add(Calendar.DATE, 1);
					else if("yesterday".equalsIgnoreCase(value))
						requestedDate.add(Calendar.DATE, -1);
					
					MyDate myDate = new MyDate();
					myDate.setDay(requestedDate.get(Calendar.DAY_OF_MONTH));
					myDate.setMonth(requestedDate.get(Calendar.MONTH));
					myDate.setYear(requestedDate.get(Calendar.YEAR));
					
					return rawType.cast(myDate);
				}

				@Override
				public String toString(T myBean) {
					return myBean.toString();
				}
				
			};
		}else
			return null;
	}

}
