package com.line.road.common.mapper;

import java.io.StringReader;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;

public class JaxbMapper {
	private static ConcurrentMap<Class<?>, JAXBContext> jaxbContexts = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) throws JAXBException {
		StringReader reader = new StringReader(xml);
		return (T) createUnmarshaller(clazz).unmarshal(reader);
	}

	public static Unmarshaller createUnmarshaller(Class<?> clazz) throws JAXBException {
		JAXBContext jaxbContext = getJaxbContext(clazz);
		return jaxbContext.createUnmarshaller();
	}

	protected static JAXBContext getJaxbContext(Class<?> clazz) {
		if (clazz == null) {
			throw new RuntimeException("'clazz' must not be null");
		}
		JAXBContext jaxbContext = (JAXBContext) jaxbContexts.get(clazz);
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(new Class[] { clazz, CollectionWrapper.class });
				jaxbContexts.putIfAbsent(clazz, jaxbContext);
			} catch (JAXBException ex) {
				throw new RuntimeException(
						"Could not instantiate JAXBContext for class [" + clazz + "]: " + ex.getMessage(), ex);
			}
		}

		return jaxbContext;
	}

	public static class CollectionWrapper {

		@XmlAnyElement
		protected Collection<?> collection;
	}
}
