package it.unical.demacs.FundasticServer.Converter;

import it.unical.demacs.FundasticServer.Project.PublishProjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageConverter {
    public byte[] convert(List<Byte> list){
        byte[] byteArray;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(list);
        } catch (IOException ignored) {}
        byteArray = baos.toByteArray();
        return byteArray;
    }





}
