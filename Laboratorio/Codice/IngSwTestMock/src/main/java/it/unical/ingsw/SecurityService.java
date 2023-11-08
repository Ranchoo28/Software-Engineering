package it.unical.ingsw;
public interface SecurityService {
    String hash(String password) throws Exception;
}