import { Component, OnInit } from '@angular/core';
import {ProductOrders} from "../models/product-orders";
import {ProductOrder} from "../models/product-order";
import {EcommerceService} from "../service/ecommerce.service";
import {User} from "../models/user";
import {Router} from "@angular/router";


@Component({
  selector: 'app-orderhistroy',
  templateUrl: './orderhistroy.component.html',
  styleUrls: ['./orderhistroy.component.css']
})
export class OrderhistroyComponent implements OnInit {

  constructor(private ecommerceService: EcommerceService, private router: Router) { }

  ngOnInit(): void {
    this.loadOrders();
  }

  orders:ProductOrders[];
  productOrders:ProductOrder[];
  user:User;
  userEmail:string;

  placeOrder(){
    this.router.navigate(['ecomm']);
  }

  loadOrders(){
    this.user =   JSON.parse(localStorage.getItem('currentUser'));
    this.userEmail = this.user.email;
    console.log(this.userEmail);
    this.ecommerceService.getAllOrders(this.user.id)
        .subscribe(
            (productOrders: any[]) => {
                this.orders = productOrders;
                console.log(this.orders);
            },
            (error) => console.log(error)
        );

  }
}
