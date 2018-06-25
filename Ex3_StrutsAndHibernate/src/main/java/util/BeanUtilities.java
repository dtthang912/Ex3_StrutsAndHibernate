package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import model.Student;
import model.StudentParameter;

public final class BeanUtilities {
	private BeanUtilities() {

	}

	public static <T, E> void copyProperties(T dest, E orig) {
		Class<?> classT = dest.getClass();
		Class<?> classE = orig.getClass();
		try {
			for (Method methodT : classT.getMethods()) {
				for (Method methodE : classE.getMethods()) {

					String methodTName = methodT.getName();

					if (!methodTName.equals("getClass") && methodTName.startsWith("get")
							&& methodTName.equals(methodE.getName()) && (methodT.getParameterCount() == 0)
							&& (methodE.getParameterCount() == 0)) {
						Method setMethodT = classT.getMethod(methodTName.replaceFirst("get", "set"),
								methodT.getReturnType());
						Object value = methodE.invoke(orig);
						if (value != null && methodE.getReturnType().equals(methodT.getReturnType())) {
							setMethodT.invoke(dest, value);
						} else if (value != null && methodE.getReturnType().getName().equals("java.lang.String")
								&& (methodT.getReturnType().getName().equals("float"))) {
							setMethodT.invoke(dest, Float.parseFloat((String) value));
						} else if (value != null && methodE.getReturnType().getName().equals("java.lang.String")
								&& (methodT.getReturnType().getName().equals("int"))) {
							setMethodT.invoke(dest, Integer.parseInt((String) value));
						} else if (value != null && (methodE.getReturnType().getName().equals("int") || (methodE.getReturnType().getName().equals("float")))
								&& methodT.getReturnType().getName().equals("java.lang.String")) {
							setMethodT.invoke(dest, value.toString());
						}
					}
				}
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
