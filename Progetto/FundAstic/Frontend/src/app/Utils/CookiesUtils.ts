import { Injectable } from "@angular/core"
import { JwtHelperService } from "@auth0/angular-jwt";
@Injectable({
    providedIn: 'root'
})

export class CookiesUtils {
 
    getUsernameFromCookie(): any {
        
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(document.cookie);
        if(decodedToken == null) return null;
        else{
            const allKeys = Object.keys(decodedToken)
            return decodedToken[allKeys[0]]
        }
    }

    checkLogged(){
        var username = this.getUsernameFromCookie()
        if(username != null) 
          return true;
        else 
          return false;
      }

    getRoleFromCookie(): any {
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(document.cookie);
        if(decodedToken == null) return null;
        else{
            const allKeys = Object.keys(decodedToken)
            return decodedToken[allKeys[3]]
        }
    }

    getExpirationDateFromCookie(): any {
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(document.cookie);
        if(decodedToken == null) return null;
        else{
            const allKeys = Object.keys(decodedToken)
            return decodedToken[allKeys[1]]
        }
    }


}

