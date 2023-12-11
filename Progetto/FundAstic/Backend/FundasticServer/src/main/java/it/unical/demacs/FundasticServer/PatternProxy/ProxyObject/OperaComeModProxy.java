package it.unical.demacs.FundasticServer.PatternProxy.ProxyObject;

import it.unical.demacs.FundasticServer.PatternProxy.ActionToProtect;
import it.unical.demacs.FundasticServer.PatternProxy.NormalObject.PubblicaProgetto;
import it.unical.demacs.FundasticServer.Users.Role;

public class OperaComeModProxy implements ActionToProtect {
    PubblicaProgetto pr = new PubblicaProgetto();
    private final String role;
    private final String requiredRole = String.valueOf(Role.Moderatore);

    public OperaComeModProxy(String role){
        this.role = role;
    }

    @Override
    public void protectAction() {
        verificaPermessi();
        pr.protectAction();
    }

    public void verificaPermessi(){
        if(!this.role.equals(requiredRole)){
            throw new SecurityException("Accesso negato!");
        }
    }
}
