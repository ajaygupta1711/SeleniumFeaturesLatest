import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CdpCommandsTest {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium with Java\\Software Installs_IMP\\chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		Map<String,Object> deviceMetrics = new HashMap<String,Object>();
		deviceMetrics.put("width",	400);
		deviceMetrics.put("height", 700);
		deviceMetrics.put("deviceScaleFactor", 50);
		deviceMetrics.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		
		Thread.sleep(5000);
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(2000);
		driver.findElement(By.className("navbar-toggler-icon")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Library")).click();
		
	}

}