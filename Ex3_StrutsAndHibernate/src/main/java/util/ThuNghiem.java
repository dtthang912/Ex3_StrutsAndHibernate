package util;

import java.io.Serializable;

public class ThuNghiem extends ViewUtil implements Serializable{
	private String a;
	private ViewUtil b;
public static void main(String[] args) throws ClassNotFoundException{
	Class x = Class.forName("util.ThuNghiem");
	System.out.print(x.getClasses());
}
}
