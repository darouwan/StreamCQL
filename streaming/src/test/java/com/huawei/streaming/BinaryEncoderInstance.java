package com.huawei.streaming;

import org.apache.avro.io.BinaryEncoder;

import java.io.IOException;

/**
 * Created by darouwan on 2016/11/28.
 */
public class BinaryEncoderInstance extends BinaryEncoder {
    @Override
    protected void writeZero() throws IOException {

    }

    @Override
    public int bytesBuffered() {
        return 0;
    }

    @Override
    public void writeBoolean(boolean b) throws IOException {

    }

    @Override
    public void writeInt(int i) throws IOException {

    }

    @Override
    public void writeLong(long l) throws IOException {

    }

    @Override
    public void writeFloat(float v) throws IOException {

    }

    @Override
    public void writeDouble(double v) throws IOException {

    }

    @Override
    public void writeFixed(byte[] bytes, int i, int i1) throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }
}
