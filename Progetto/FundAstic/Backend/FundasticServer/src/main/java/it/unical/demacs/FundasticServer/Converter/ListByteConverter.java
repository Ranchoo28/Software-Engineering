package it.unical.demacs.FundasticServer.Converter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Service
public class ListByteConverter {

    public byte[] convert(List<Byte> list) {
        byte[] byteArray;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(list);
        } catch (IOException ignored) {
        }
        byteArray = baos.toByteArray();
        return byteArray;
    }
}

