package it.unical.demacs.FundasticServer.PatternProxy.ProxyObject;

import it.unical.demacs.FundasticServer.PatternProxy.ActionToProtect;
import it.unical.demacs.FundasticServer.PatternProxy.NormalObject.PubblicaProgetto;
import it.unical.demacs.FundasticServer.Users.Role;

public class PubblicaProgettoProxy implements ActionToProtect {
    PubblicaProgetto pr = new PubblicaProgetto();
    private final String role;
    private final String requiredRole = String.valueOf(Role.Publisher);

    public PubblicaProgettoProxy(String ruolo){
        this.role = ruolo;
    }

    @Override
    public void protectAction(String  s) {
        verificaPermessi();
        pr.protectAction(s);
    }

    private void verificaPermessi(){
        if(!this.role.equals(requiredRole) && !this.role.equals(String.valueOf(Role.Moderatore))){
            throw new SecurityException("Accesso negato!");
        }
    }
}
