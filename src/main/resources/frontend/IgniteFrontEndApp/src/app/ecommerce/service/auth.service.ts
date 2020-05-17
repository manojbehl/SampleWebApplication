import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { HttpHeaders } from "@angular/common/http";
import {User} from "../models/user";
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private productsUrl = "/api/login";


  private currentUserSubject: BehaviorSubject<User>;
   public currentUser: Observable<User>;

  constructor(private http: HttpClient) { }

  login(username: String, password: String) {
    // Auth0 authorize request

    let authToken =  btoa(username + ":" +password) ;
    return this.http.get(this.productsUrl+"?authToken="+authToken );
  }





  logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');

    }
}
