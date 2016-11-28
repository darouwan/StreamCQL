package com.huawei.streaming.serde;

import com.google.common.collect.Lists;
import com.huawei.streaming.exception.StreamSerDeException;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by darouwan on 2016/11/28.
 */
public class AvroSerDe extends BaseSerDe {
    private static final Schema TEST_SCHEMA = Schema.parse(
            "{\"type\": \"record\", \"name\": \"tuple\", \"fields\": [{\"data\": " +
                    "\"name\", \"type\": \"string\"}]}");

    public static final GenericDatumWriter<GenericRecord> WRITER = new GenericDatumWriter<GenericRecord>(
            TEST_SCHEMA);

    public static final GenericDatumReader<GenericRecord> READER = new GenericDatumReader<GenericRecord>(
            TEST_SCHEMA);

    @Override
    public void initialize() throws StreamSerDeException {

    }

    @Override
    public List<Object[]> deSerialize(Object data) throws StreamSerDeException {
        byte[] array = data.toString().getBytes();
        Decoder decoder = DecoderFactory.get().binaryDecoder(array, null);
        Object datum = null;
        List<Object[]> splitResults = Lists.newArrayList();
        try {
            datum = READER.read(null, decoder);
            GenericRecord record = (GenericRecord) datum;
            Object value = record.get("data");
            splitResults.add(new Object[]{value});
            return createAllInstance(splitResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object serialize(List<Object[]> events) throws StreamSerDeException {
        GenericRecord content = new GenericData.Record(TEST_SCHEMA);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Encoder encoder = EncoderFactory.get().directBinaryEncoder(out, null);
        for (int i = 0; i < events.size(); i++) {
            Object[] vals = events.get(i);
            if (vals.length == 1) {
                content.put("data", vals[0]);
                try {
                    WRITER.write(content, encoder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return out.toByteArray();
            }
        }

        return null;
    }
}
