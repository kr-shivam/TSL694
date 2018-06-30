package test4testng;

import static org.testng.Assert.assertEquals;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GetByObjectAndAct_KDF {

	static WebDriver driver;
	OpenBrowser_KDF browserobj;
	ExcelExecutor_KDF excelex = new ExcelExecutor_KDF();

	public GetByObjectAndAct_KDF(WebDriver driver) {
		this.driver = driver;
	}

	public void performAction(String operation, String objectName,
			String objectType, String value, String variable) throws Exception {
		System.out.println("performing action");
		switch (operation.toUpperCase()) {

		case "CLICK":
			// Perform click
			driver.findElement(this.getByObject(objectName, objectType))
					.click();
			break;

		case "SETTEXT":
			// Set text on control
			driver.findElement(this.getByObject(objectName, objectType))
					.sendKeys(value);
			break;

		case "GOTOURL":
			// Get url of application
			if (driver != null)
				driver.get(value);
			else
				System.out.println("driver object is null");
			break;

		case "GETTEXT":
			// Get text of an element
			String str = driver.findElement(
					this.getByObject(objectName, objectType)).getText();
			System.out.println(str);
			break;

		case "TIMEOUT":
			// Get url of application
			float sleeptime = Float.parseFloat(value);
			Thread.sleep((long) (sleeptime) * 1000);
			break;

		case "OPENBROWSER":
			browserobj = new OpenBrowser_KDF(value);
			// Thread.sleep(5000);
			this.driver = browserobj.getDriver();
			break;

		case "CLOSEBROWSER":
			System.out.println("attempting to close browser");
			driver.quit();
			System.out.println("browser closed");
			break;

		case "STORE":
			String variableName = variable;
			variableName = variableName.substring(2, variableName.length() - 1);
			// System.out.println(variableName);
			// variableName = value;
			excelex.getVariableMap().put(variableName, value);
			// variableData.put(variableName, value);
			// System.out.println(variableName);
			break;

		case "ECHO":
			
			/*
			 * 
			 * // Print the hashmap 
			 * for (String name: excelex.getPeopleMap().keySet()){
			 * 
			 * String key =name.toString(); String value3 =
			 * excelex.getPeopleMap().get(name).toString();
			 * System.out.println(key + " " + value3);
			 * 
			 * }
			 */
			System.out.println(excelex.getVariableMap().get(
					variable.substring(2, variable.length() - 1)));

			break;

		case "VERIFYTITLE":

			try {
				assertEquals(driver.getTitle(), value);
			} catch (AssertionError e) {
				System.out.println("Verification Failed");
				e.getMessage();
			}
			break;

		case "VERIFYTEXT":

			try {
				
				assertEquals(driver.findElement(this.getByObject(objectName, objectType)).getText(), value);
			} catch (AssertionError e) {
				System.out.println("Verification Failed");
				e.getMessage();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Find element BY using object type and value * @param objectName
	 * 
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getByObject(String objectName, String objectType)
			throws Exception {
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) {

			return By.xpath(objectName);
		}
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) {

			return By.className(objectName);

		}
		// find by id
		else if (objectType.equalsIgnoreCase("ID")) {

			return By.id(objectName);

		}
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) {

			return By.name(objectName);

		}
		// Find by css
		else if (objectType.equalsIgnoreCase("CSS")) {

			return By.cssSelector(objectName);

		}
		// find by link
		else if (objectType.equalsIgnoreCase("LINK")) {

			return By.linkText(objectName);

		}
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) {

			return By.partialLinkText(objectName);

		} else {
			throw new Exception("Wrong object type");
		}
	}
}
