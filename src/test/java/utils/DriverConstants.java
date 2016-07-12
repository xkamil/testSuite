package utils;

public final class DriverConstants {	
	public static final String DRIVER_FIREFOX = "firefox";
	public static final String DRIVER_CHROME = "chrome";
	public static final String DRIVER_SAFARI = "safari";
	public static final String DRIVER_IE = "internetexplorer";
	public static final String DRIVER_HTMLUNIT = "htmlunit";
	
	public static final String DRIVERS_PATH = "src/test/resources/drivers/";
	
	private static String os = System.getProperty("os.name").toLowerCase();
	public static boolean isUnix = os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0;
	public static boolean isWindows = os.indexOf("win") >= 0;
	public static boolean isMac = os.indexOf("mac") >= 0;
}
