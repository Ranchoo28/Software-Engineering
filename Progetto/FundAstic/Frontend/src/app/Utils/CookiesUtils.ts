import { Injectable } from "@angular/core"
import { JwtHelperService } from "@auth0/angular-jwt";
@Injectable({
    providedIn: 'root'
})

export class CookiesUtils {
    getUsernameFromCookie(): any {
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(document.cookie);
        const allKeys = Object.keys(decodedToken)
        return decodedToken[allKeys[0]]
    }

    getRoleFromCookie(): any {
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(document.cookie);
        const allKeys = Object.keys(decodedToken)
        return decodedToken[allKeys[3]]
    }
}

