import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FormsModule,ReactiveFormsModule} from '@angular/forms';
import {Router} from "@angular/router";
import {first} from "rxjs/operators";
import {AuthService} from "../service/auth.service";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  submitted: boolean = false;
  invalidLogin: boolean = false;
  email:String;
  password:String;
  showErrorMessage=false;

  constructor(private formBuilder: FormBuilder, private router: Router, private authService:AuthService) { }

  onSubmit() {
    this.submitted = true;
    console.log(this.email);
    console.log(this.password);
    this.authService.login(this.email,this.password).subscribe(
        (user: any) => {
            if(user.error == true){
              this.showErrorMessage = true;
//              this.router.navigate(['ecomm']);

            }else{
                this.showErrorMessage = false;
              localStorage.setItem("currentUser", JSON.stringify( user));
              console.log( JSON.parse(localStorage.getItem('currentUser')) );

              this.router.navigate(['orderhistory']);


            }

        },
        (error) =>{
          console.log(error);
        //  this.router.navigate(['login']);
        }
    );

  }

  ngOnInit() {

  }
}
