import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v99.network.Network;
import org.openqa.selenium.devtools.v99.network.model.ConnectionType;

public class NetworkSpeed {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium with Java\\Software Installs_IMP\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.send(Network.emulateNetworkConditions(true, 3000, 20000, 10000, Optional.of(ConnectionType.ETHERNET)));
		
		devTools.addListener(Network.loadingFailed(), loadingfailed ->
		{
			System.out.println(loadingfailed.getErrorText());
			System.out.println(loadingfailed.getTimestamp());
		}
				);
		
		long startTime = System.currentTimeMillis();
		
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
		driver.findElements(By.cssSelector(".LC20lb.MBeuO.DKV0Md")).get(0).click();
		String title = driver.findElement(By.cssSelector(".our-story-card-title")).getText();
		System.out.println(title);
		
//		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
//		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		driver.close();
		
		// Offline -> 141966 ms & Online -> 8791 ms = Difference 133175 ms
	}

}