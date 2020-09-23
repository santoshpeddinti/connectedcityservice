package com.peddinti.cityservice.common;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;

	public static ExtentReports getInstance(String fileName) {
		if (extent == null)
			createInstance(fileName);

		return extent;
	}

	public static ExtentReports createInstance(String fileName) {
		if (extent == null) {
			Date date = new Date();
			fileName = fileName + "_" + date.toString().replace(":", "_").replace(" ", "_") + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
			htmlReporter.setAppendExisting(true);
			htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle(fileName);
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName(fileName);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

}
