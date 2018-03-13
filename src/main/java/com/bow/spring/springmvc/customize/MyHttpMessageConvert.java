package com.bow.spring.springmvc.customize;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * 请求时，通过{@link HttpMessageConverter}将流转换为对象<br/>
 * ，响应时通过其将对象转为流。
 *
 * 注意：此转换器支持的格式是application/vson , VSON<br/>
 *
 * 格式 name:vv;age:27;date:2017;
 *
 * @see CustomizedControllerTest#test()
 * @author vv
 * @since 2017/2/3.
 */
public class MyHttpMessageConvert extends AbstractHttpMessageConverter<Object>
        implements GenericHttpMessageConverter<Object> {

    private static final String ENTRY_SEPARATOR = ":";

    private static final String ENTRY_TERMINATOR = ";";

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public MyHttpMessageConvert() {
        // 此转换器支持的格式是application/vson
        super(new MediaType("application", "vson", DEFAULT_CHARSET));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // should not be called, since we override canRead/Write instead
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return canWrite(mediaType);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return readMap(inputMessage);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> map = ((ResponseData) o).getData();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append(ENTRY_SEPARATOR).append(entry.getValue())
                    .append(ENTRY_TERMINATOR);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        outputMessage.getBody().write(stringBuilder.toString().getBytes());
    }

    /**
     * @see GenericHttpMessageConverter
     */
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return readMap(inputMessage);
    }

    /**
     *
     * @param inputMessage
     *            输入的内容格式 name:vv;age:27;date:2017;
     * @return {@link RequestData}
     * @throws IOException
     *             e
     * @throws HttpMessageNotReadableException
     *             e
     */
    private Object readMap(HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // 从inputMessage中读取内容
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = inputMessage.getBody();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) != -1) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(length);
            byteBuffer.put(b, 0, length);
            byteBuffer.flip();
            stringBuilder.append(DEFAULT_CHARSET.decode(byteBuffer).array());
        }

        // 将内容截开
        String[] list = stringBuilder.toString().split(ENTRY_TERMINATOR);
        Map<String, String> map = new HashMap<String, String>(list.length);
        for (String element : list) {
            String[] keyValue = element.split(ENTRY_SEPARATOR);
            map.put(keyValue[0], keyValue[1]);
        }
        RequestData requestData = new RequestData();
        requestData.setData(map);
        return requestData;
    }
}
