package org.yzh.protocol.basics;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.yzh.framework.orm.annotation.Field;
import org.yzh.framework.orm.annotation.Message;
import org.yzh.framework.orm.model.DataType;
import org.yzh.protocol.commons.ParameterUtils;

import java.nio.charset.Charset;

/**
 * 终端参数项
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message
public class TerminalParameter {

    private Integer id;
    private Integer length;
    private byte[] bytesValue;

    public TerminalParameter() {
    }

    public TerminalParameter(Integer id, byte[] bytes) {
        this.id = id;
        this.length = bytes.length;
        this.bytesValue = bytes;
    }

    public TerminalParameter(Integer id, int value) {
        this.id = id;
        byte[] bytes = ParameterUtils.toBytes(id, value);
        this.length = bytes.length;
        this.bytesValue = bytes;
    }


    public TerminalParameter(Integer id, String value) {
        this.id = id;
        this.bytesValue = value.getBytes(Charset.forName("GBK"));
        this.length = bytesValue.length;
    }

    @Field(index = 0, type = DataType.DWORD, desc = "参数ID", version = {0, 1})
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Field(index = 2, type = DataType.BYTE, desc = "参数长度", version = {0, 1})
    public Integer getLength() {
        if (length == null)
            return bytesValue.length;
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Field(index = 3, type = DataType.BYTES, lengthName = "length", desc = "参数值", version = {0, 1})
    public byte[] getBytesValue() {
        return bytesValue;
    }

    public void setBytesValue(byte[] bytesValue) {
        this.bytesValue = bytesValue;
        if (bytesValue == null)
            this.length = 0;
        else
            this.length = bytesValue.length;
    }

    public Object getValue() {
        return ParameterUtils.toValue(id, bytesValue);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}