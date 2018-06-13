package test4testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Assignment08Jun1 {

	/*
	 * @BeforeTest annotated method will be executed before the any @Test
	 * annotated method of those classes which are inside <test> tag in
	 * testng.xml file.
	 */

	@BeforeTest
	public void ExBeTt() {
		System.out.println("This is print command from Before Test");
	}

	@BeforeMethod
	public void ExBeMt() {
		System.out.println("This is print command from Before Method");
	}

	@Test
	public void f() {
		System.out.println("This is print command from 1st Test Method");
	}

	@Test
	public void f1() {
		System.out.println("This is print command from 2nd Test Method");
	}

	@AfterMethod
	public void ExafMt() {
		System.out.println("This is print command from After Method");
	}

	@AfterTest
	public void ExafTt() {
		System.out.println("This is print command from After Test");
	}
}
