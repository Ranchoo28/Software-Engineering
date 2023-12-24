package it.unical.demacs.FundasticServer.PatternProxy.ProxyObject;

import it.unical.demacs.FundasticServer.PatternProxy.ActionToProtect;
import it.unical.demacs.FundasticServer.PatternProxy.NormalObject.OperaComeMod;
import it.unical.demacs.FundasticServer.Project.ProjectService;
import it.unical.demacs.FundasticServer.Users.Role;

public class OperaComeModProxy implements ActionToProtect {
    OperaComeMod pr = new OperaComeMod();
    private final String role;
    private final String requiredRole = String.valueOf(Role.Moderatore);

    public OperaComeModProxy(String role){
        this.role = role;
    }

    @Override
    public void protectAction(String s) {
        verificaPermessi();
        pr.protectAction(s);
    }

    public void verificaPermessi(){
        if(!this.role.equals(requiredRole)){
            throw new SecurityException("Accesso negato!");
        }
    }
}
