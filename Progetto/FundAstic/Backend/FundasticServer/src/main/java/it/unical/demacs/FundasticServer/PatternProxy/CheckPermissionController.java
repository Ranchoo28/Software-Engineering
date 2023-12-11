package it.unical.demacs.FundasticServer.PatternProxy;

import it.unical.demacs.FundasticServer.PatternProxy.ProxyObject.FinanziaProgettoProxy;
import it.unical.demacs.FundasticServer.PatternProxy.ProxyObject.OperaComeModProxy;
import it.unical.demacs.FundasticServer.PatternProxy.ProxyObject.PubblicaProgettoProxy;
import it.unical.demacs.FundasticServer.Users.Registration.RegistrationRequest;
import it.unical.demacs.FundasticServer.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/checkPermission")
public class CheckPermissionController {

    @PostMapping()
    public void check(@RequestBody CheckPermissionRequest request){
        switch(request.getAction()){
            case "Pubblica":{
                PubblicaProgettoProxy pr = new PubblicaProgettoProxy(request.getRole());
                pr.protectAction();
                break;
            }
            case "Finanzia":{
                FinanziaProgettoProxy fp = new FinanziaProgettoProxy(request.getRole());
                fp.protectAction();
                break;
            }

            case "Modera":{
                OperaComeModProxy mp = new OperaComeModProxy(request.getRole());
                mp.protectAction();
                break;
            }
        }

    }
}
