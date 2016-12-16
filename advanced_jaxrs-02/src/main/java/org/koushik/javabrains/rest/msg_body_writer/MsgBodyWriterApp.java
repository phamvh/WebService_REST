package org.koushik.javabrains.rest.msg_body_writer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//just need this class to register all resource classes in this package.
//No need to override any method; the default methods from Application work fine.
@ApplicationPath("webapi")
public class MsgBodyWriterApp extends Application{

}
