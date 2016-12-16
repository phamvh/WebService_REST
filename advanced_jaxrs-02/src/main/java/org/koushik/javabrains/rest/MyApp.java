package org.koushik.javabrains.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This annotation defines the root (prefix) path as webapi
 * So if we want to access the resouce defined in MyResource, we can call:
 *    localhost:8080/webapi/test
 *    
 * The presence if this class (extending APplication) replaces the needs to define a servlet in the web.xml file.
 * Note that here we do not need to do anything in the web.xml file.
 */
@ApplicationPath("webapi")
public class MyApp extends Application{
	
/**
 * Overriding this method is optional.
 * If you want to restrict certain classes to be resources, then you can return them in this method.
 * The default is that all the classes in the classpath (with jax-rs annotation) will be added as resources.
 */
@Override
public Set<Class<?>> getClasses() { 
	/*
	Set<Class<?>> set = new HashSet<>();
	set.add(MyResource.class);
	return set;
	*/
	return super.getClasses();
}
}
