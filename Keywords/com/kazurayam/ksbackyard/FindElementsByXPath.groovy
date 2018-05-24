package com.kazurayam.ksbackyard
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory


class FindElementsByXPath {

	@Keyword
	List<String> getElementContentsAsList(String xpath4element) {
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(xpath4element))
		def contents = new ArrayList<String>()
		for (WebElement el : elements) {
			if (el.getText() != null) {
				contents.add(el.getText())
			} else {
				contents.add('')
			}
		}
		return contents
	}

	@Keyword
	List<String> getAttributeValuesAsList(String xpath4element, String attributeName) {
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(xpath4element))
		def values = new ArrayList<String>()
		for (WebElement el : elements) {
			def value = el.getAttribute(attributeName)
			if (value != null) {
				values.add(el.getAttribute(attributeName))
			} else {
				values.add('')
			}
		}
		return values
	}
}