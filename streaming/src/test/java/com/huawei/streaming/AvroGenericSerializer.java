package com.huawei.streaming;

/**
 * Created by darouwan on 2016/11/28.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.util.Utf8;


public class AvroGenericSerializer {
    public static void main(String args[]) {
        AvroGenericSerializer ags = new AvroGenericSerializer();
        try {
            GenericRecord gr = ags.create();
            byte[] bytes = ags.serialize(gr);
            System.out.println(new String(bytes));
            GenericRecord grback = ags.deserialize(bytes);
            System.out.println(grback);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static final Schema MEDIA_SCHEMA = Schema.parse(
            "{\"type\": \"record\", \"name\": \"Media\", \"fields\": [{\"name\": " +
                    "\"data\", \"type\": \"string\"}]}");

    public static final GenericDatumWriter<GenericRecord> WRITER = new GenericDatumWriter<GenericRecord>(
            MEDIA_SCHEMA);

    public static final GenericDatumReader<GenericRecord> READER = new GenericDatumReader<GenericRecord>(
            MEDIA_SCHEMA);

    public String getName() {
        return "avro-generic";
    }

    public GenericRecord create() throws Exception {
        GenericRecord media = new GenericData.Record(MEDIA_SCHEMA);
        media.put("data", new Utf8("http://javaone.com/keynote.mpg"));

        return media;
    }

    public GenericRecord deserialize(byte[] array) throws Exception {
        Decoder decoder = DecoderFactory.get().binaryDecoder(array, null);
        Object datum = READER.read(null, decoder);
        return (GenericRecord) datum;
    }

    public byte[] serialize(GenericRecord content) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
        WRITER.write(content, encoder);
        return out.toByteArray();
    }
}
