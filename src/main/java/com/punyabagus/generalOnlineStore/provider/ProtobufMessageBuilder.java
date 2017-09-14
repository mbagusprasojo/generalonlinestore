package com.punyabagus.generalOnlineStore.provider;

import com.google.common.base.Charsets;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.googlecode.protobuf.format.XmlFormat;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

@Provider
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ProtobufMessageBuilder<T extends Message> implements MessageBodyWriter<T>, MessageBodyReader<T> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return isReadableOrWriteable(type, mediaType);
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return isReadableOrWriteable(type, mediaType);
    }

    public static boolean isReadableOrWriteable(Class<?> type, MediaType mediaType) {
        final boolean assignableFromGeneratedMessage = GeneratedMessageV3.class.isAssignableFrom(type);
        return assignableFromGeneratedMessage;
    }

    @Override
    public long getSize(T message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return getMessageAsJSON(message).length;
    }

    @Override
    public void writeTo(T message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        entityStream.write(getMessageAsJSON(message));
    }

    private byte[] getMessageAsJSON(Message generatedMessage) {
        return JsonFormat.printToString(generatedMessage).getBytes(Charsets.UTF_8);
    }

    @Override
    public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        String data = Streams.asString(entityStream, Charsets.UTF_8.name());
        Message.Builder builder;
        try {
            Method newBuilder = type.getDeclaredMethod("newBuilder");
            builder = (Message.Builder) newBuilder.invoke(null);
        } catch (Exception e) {
            throw new IllegalAccessError("Failed to read Protobuf message object.");
        }

        JsonFormat.merge(data, builder);

        return (T) builder.build();
    }
}