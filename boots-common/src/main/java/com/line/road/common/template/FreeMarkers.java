package com.line.road.common.template;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import com.line.road.common.security.Digests;
import com.line.road.common.utils.Exceptions;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkers {

	private static Configuration configuration;

	public static synchronized Configuration getConfiguration() {
		if (configuration == null) {
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			configuration.setTemplateLoader(new FreeMarkersTemplateLoader("templates"));
			configuration.setTagSyntax(2);
			configuration.setEncoding(Locale.getDefault(), "UTF-8");
		}
		return configuration;
	}

	public static String renderString(String tplString, Map<String, ?> dataModel) {
		try {
			StringWriter result = new StringWriter();
			Template template = new Template(Digests.md5(tplString), new StringReader(tplString), getConfiguration());
			template.process(dataModel, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(String tplFilePath, Map<String, ?> dataModel) {
		try {
			return renderTemplate(getConfiguration().getTemplate(tplFilePath), dataModel);
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(Template template, Object dataModel) {
		try {
			StringWriter result = new StringWriter();
			template.process(dataModel, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}
}
