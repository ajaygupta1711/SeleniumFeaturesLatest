import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v99.network.Network;
import org.openqa.selenium.devtools.v99.network.model.Response;

public class NetworkLogActivity {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium with Java\\Software Installs_IMP\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		DevTools devtools = driver.getDevTools();
		devtools.createSession();
		devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		devtools.addListener(Network.requestWillBeSent(), request ->
		{
//			System.out.println("Request");
//			Request req = request.getRequest();
//			System.out.println(req.getUrl());
//			System.out.println(req.getHeaders());
		});
		
		//Event will get fire
		devtools.addListener(Network.responseReceived(), response ->				 // handler need lambda to implement
		{
//			System.out.println("Response");
			Response res = response.getResponse();
//			System.out.println(res.getUrl());
//			System.out.println(res.getStatus());
			if(res.getStatus().toString().startsWith("4"))
			{
				System.out.println(res.getUrl() + " is failing with status code" + res.getStatus());
			}
				
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		
	}
}