package iotest;

public @interface PackageSpec {
	String name = "";
	String version = "0.0";
	String version();
	String name();
}
