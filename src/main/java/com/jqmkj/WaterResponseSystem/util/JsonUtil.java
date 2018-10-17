package com.jqmkj.WaterResponseSystem.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;


/**
 * 解析对象为json
 */
public class JsonUtil {

	public static String[]ignores = {"createPerson","createDate","updatePerson","updateDate"};
	/*
	public static StringBuffer convertObj2json(Collection<?> cs) {

		StringBuffer sbf = new StringBuffer();

		if (null == cs || 0 == cs.toArray().length){
			
			return sbf.append("[").append("]");
		}

		Object[] ls = cs.toArray();
		int size = ls.length;

		sbf.append("[");
		if (0 == size)
			return new StringBuffer("[]");
		for (int k = 0; k < size; k++) {
			Object o = ls[k];

			sbf.append(convertObj2json(o));

			if (k < size - 1)
				sbf.append(", ");
		}

		return sbf.append("]");

	}


	public static StringBuffer convertObj2json(Object o) {

		StringBuffer sbf = new StringBuffer();
		if (null == o)
			return sbf;

		sbf.append("{");
		Class<?> classType = o.getClass();
		Field[] fields = classType.getDeclaredFields();

		int length = fields.length;

		for (int i = 0; i < length; i++) {

			String fieldName = fields[i].getName();
			Class<?> clazzType = fields[i].getType();
			Package package1 = clazzType.getPackage();
			Object fo = ReflectionUtil.getFieldValue(o, fieldName);


			if (fo!=null && fo.getClass().isArray()) {
				Object [] arr = ( Object[] ) fo ;
				sbf.append(fieldName).append("\":\"").append("[");
				for ( int k = 0 ; k < arr.length ; k++ ){
					sbf.append(arr[k]);
					if(k < arr.length -1)
						sbf.append(",");
				}
				sbf.append("]");
			}
			else{
				if (!(fo instanceof Collection)
						&& (clazzType.isPrimitive() || null == package1
						|| package1.getName().equals("java.lang") || package1
						.getName().equals("java.util"))) {
//					boolean flag = false;
//					System.out.println(fieldName+","+clazzType+","+clazzType.isPrimitive()+","+ (null == package1) );
//					for (String ig :ignores){
//						if(ig.equals(fieldName)) {
//							flag=true;
//							break;
//						}
//					}
//					if(!flag)
					sbf.append("\"").append(fieldName).append("\":\"").append(fo)
							.append("\"");
				} else if (!(fo instanceof Collection)) {
//					boolean flag = false;
//					System.out.println(fieldName);
//					for (String ig :ignores){
//						if(ig.equals(fieldName)) {
//							flag=true;
//							break;
//						}
//					}
//					if(!flag)
					sbf.append("\"").append(fieldName).append("\":").append(
							convertObj2json(fo));
				}
			}



			if (fo instanceof Collection) {
					sbf.append("\"").append(fieldName).append("\":").append(
						convertObj2json((Collection<?>) fo));

			}

			if (i < length - 1)
				sbf.append(", ");

		}

		return sbf.append("}");
	}
	*/


	private  static ObjectMapper objectMapper = new ObjectMapper();
	static {
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//DeserializationConfig cfg = objectMapper.getDeserializationConfig();
		//cfg.with(formatter);
		//objectMapper = objectMapper.setDateFormat(formatter);
		//objectMapper.
		//objectMapper.getSerializationConfig().with(formatter);


		//解析LocalDateTime
		//DateUtils.
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtils._yyyyMMddHHmmss)));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtils._yyyyMMdd)));
		//javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		objectMapper.registerModule(javaTimeModule);

		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}
	/**
	 * 对象转换成格式化的json
	 * 使用jackosn的类库 紧凑模式 用于传输
	 */
	public static <T>String objectToJson(T obj){
		if(obj == null){
			return null;
		}
		try {
			return obj instanceof String ? (String) obj : objectMapper.writer().writeValueAsString(obj);
		} catch (Exception e) {
			//log.warn("Parse Object to Json error",e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对象转换成格式化的json
	 *  使用jackosn的类库 Pretty模式 用于测试和查看输出的格式
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public static <T>String objectToJsonPretty(T obj){
		if(obj == null){
			return null;
		}
		try {
			return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			//log.warn("Parse Object to Json error",e);
			e.printStackTrace();
			return null;
		}
	}

}