package it.unical.demacs.FundasticServer.PatternProxy.ProxyObject;

import it.unical.demacs.FundasticServer.PatternProxy.NormalObject.FinanziaProgetto;
import it.unical.demacs.FundasticServer.Users.Role;

public class FinanziaProgettoProxy {
    private final String role;
    private final FinanziaProgetto fp = new FinanziaProgetto();
    private final String requiredRole = String.valueOf(Role.Finanziatore);

    public FinanziaProgettoProxy(String role){
        this.role = role;
    }

    public void protectAction(String s){
        verificaPermessi();
        fp.protectAction(s);
    }

    private void verificaPermessi(){
        if(!this.role.equals(requiredRole) && !this.role.equals(String.valueOf(Role.Moderatore))){
            throw new SecurityException("Accesso negato!");
        }
    }
}
