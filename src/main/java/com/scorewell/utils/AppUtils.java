package com.scorewell.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scorewell.common.NotMergable;
import com.scorewell.dto.User;
import com.scorewell.dto.UserRole;

public class AppUtils {

	private static Logger logger = LoggerFactory.getLogger(AppUtils.class);

	public static <T> void mergeObject(T dest, T source) {
		Class<? extends Object> klass = dest.getClass();
		while (klass != null && klass != Object.class) {
			Field[] declaredFields = klass.getDeclaredFields();
			for (Field f : declaredFields) {
				if (!f.isAnnotationPresent(NotMergable.class)) {
					f.setAccessible(true);
					try {
						if (f.getType().isPrimitive()) {
							f.set(dest, f.get(source));
						} else {
							Object value = f.get(source);
							if (value != null) {
								f.set(dest, value);
							}
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
			klass = klass.getSuperclass();
		}

	}

	public static void merge(User dest, User source) {
		if (!StringUtils.isEmpty(source.getAddress())) {
			dest.setAddress(source.getAddress());
		}
		if (!StringUtils.isEmpty(source.getGlobalkey())) {
			dest.setGlobalkey(source.getGlobalkey());
		}
		if (!StringUtils.isEmpty(source.getUsername())) {
			dest.setUsername(source.getUsername());
		}
		if (!StringUtils.isEmpty(source.getAgency())) {
			dest.setAgency(source.getAgency());
		}
		if (!StringUtils.isEmpty(source.getAgencySite())) {
			dest.setAgencySite(source.getAgencySite());
		}
		if (!StringUtils.isEmpty(source.getEmail())) {
			dest.setEmail(source.getEmail());
		}
		if (!StringUtils.isEmpty(source.getLastname())) {
			dest.setLastname(source.getLastname());
		}
		if (!StringUtils.isEmpty(source.getFirstname())) {
			dest.setFirstname(source.getFirstname());
		}
		if (!StringUtils.isEmpty(source.getTitle())) {
			dest.setTitle(source.getTitle());
		}
		if (!StringUtils.isEmpty(source.getPhone())) {
			dest.setPhone(source.getPhone());
		}
		if (!StringUtils.isEmpty(source.getAddress())) {
			dest.setAddress(source.getAddress());
		}
		if (!StringUtils.isEmpty(source.getCity())) {
			dest.setCity(source.getCity());
		}
		if (!StringUtils.isEmpty(source.getState())) {
			dest.setState(source.getState());
		}
		if (source.getZip() > 0) {
			dest.setZip(source.getZip());
		}
		if (source.getPermissions() != null && source.getPermissions().size() > 0) {
			dest.setPermissions(source.getPermissions());
		}
		if (source.getRole() != null && !StringUtils.isEmpty(source.getRole().getName())) {
			dest.setRole(source.getRole());
		}

	}

	public static void merge(UserRole dest, UserRole source) {
		dest.setAccessPatterns(source.getAccessPatterns());
		dest.setDenyPatterns(source.getDenyPatterns());
		if (!StringUtils.isEmpty(source.getHomePage())) {
			dest.setHomePage(source.getHomePage());
		}
		if (!StringUtils.isEmpty(source.getName())) {
			dest.setName(source.getName());
		}
	}


	private static void generateConvertToRowMethod(Class klass) {
		Method[] declaredMethods = klass.getDeclaredMethods();

		for (Method m : declaredMethods) {
			if (m.getName().startsWith("set")) {
				System.out.println("row." + m.getName() + "();");
			}
		}
	}

	private static void generateMergermethod(Class klass) {
		Field[] fields = klass.getDeclaredFields();
		String klassName = klass.getSimpleName();
		System.out.println("public static void merge(" + klassName + " dest, " + klassName + " source) {");
		for (Field f : fields) {
			String name = f.getName();
			name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
			if (f.getType() == String.class) {
				System.out.println("if(!StringUtils.isEmpty(source.get" + name + "())){");
				System.out.println("dest.set" + name + "(source.get" + name + "());");
				System.out.println("}");
			} else if (f.getType().isPrimitive()) {
				if (f.getType() == boolean.class) {
					String boolSetName = f.getName();
					if (boolSetName.startsWith("is")) {
						boolSetName = boolSetName.substring(2);
					}
					boolSetName = Character.toUpperCase(boolSetName.charAt(0)) + boolSetName.substring(1);
					System.out.println("dest.set" + boolSetName + "(source.is" + boolSetName + "());");
				} else {
					System.out.println("if(source.get" + name + "()!=0){");
					System.out.println("dest.set" + name + "(source.get" + name + "());");
					System.out.println("}");
				}
			} else {
				System.out.println("if(source.get" + name + "()!=null){");
				System.out.println("dest.set" + name + "(source.get" + name + "());");
				System.out.println("}");
			}

		}
		System.out.println("}");
	}

	public static <T> void copyObject(T src, T dest) {
		if (src == null || dest == null) {
			return;
		}
		for (Field f : src.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				f.set(dest, f.get(src));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Class<?> superclass = src.getClass().getSuperclass();
		while (superclass != null) {
			for (Field f : superclass.getDeclaredFields()) {
				f.setAccessible(true);
				try {
					f.set(dest, f.get(src));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			superclass = superclass.getSuperclass();
		}

	}
	

//	public static void main(String[] args) {
//
//		String basePathOfClass = new File(".").getAbsolutePath();
//		System.out.println(basePathOfClass+"/src/main/webapp/WEB-INF/pdf/answer/");
//		String classpathStr = System.getProperty("java.class.path");
//		System.out.print(classpathStr);
////		String str = getClass().toString();
//
//	}


}
