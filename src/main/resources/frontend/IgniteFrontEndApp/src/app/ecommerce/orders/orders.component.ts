import { Component, OnInit } from '@angular/core';
import {ProductOrders} from "../models/product-orders";
import {Subscription} from "rxjs/internal/Subscription";
import {EcommerceService} from "../service/ecommerce.service";
import {Router} from "@angular/router";
import {first} from "rxjs/operators";


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: ProductOrders;
total: number;
paid: boolean;
sub: Subscription;

constructor(private ecommerceService: EcommerceService, private router: Router) {
    this.orders = this.ecommerceService.ProductOrders;
}

ngOnInit() {
    this.paid = false;
    this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
        this.orders = this.ecommerceService.ProductOrders;
    });
    this.loadTotal();
}

orderHistory(){
    this.router.navigate(['orderhistory']);
}

pay() {
    this.paid = true;
    this.ecommerceService.saveOrder(this.orders).subscribe();
}

loadTotal() {
    this.sub = this.ecommerceService.TotalChanged.subscribe(() => {
        this.total = this.ecommerceService.Total;
    });
  }
}
