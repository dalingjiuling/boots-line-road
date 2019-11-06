package com.line.road.common.template;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.springframework.core.io.Resource;

import com.line.road.common.utils.FileUtils;

import freemarker.cache.TemplateLoader;

public class FreeMarkersTemplateLoader implements TemplateLoader {
	private final String templateLoaderPath;

	public FreeMarkersTemplateLoader(String templateLoaderPath) {
		if (!templateLoaderPath.endsWith("/")) {
			templateLoaderPath = templateLoaderPath + "/";
		}
		this.templateLoaderPath = templateLoaderPath;
	}

	public Object findTemplateSource(String name) throws IOException {
		Resource resource = FileUtils.getResourceLoader().getResource(this.templateLoaderPath + name);
		return resource.exists() ? resource : null;
	}

	public Reader getReader(Object templateSource, String encoding) throws IOException {
		Resource resource = (Resource) templateSource;
		try {
			return new InputStreamReader(resource.getInputStream(), encoding);
		} catch (IOException ex) {
			throw ex;
		}

	}

	public long getLastModified(Object templateSource) {
		Resource resource = (Resource) templateSource;
		try {
			return resource.lastModified();
		} catch (IOException ex) {
		}
		return -1L;
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
	}
}
