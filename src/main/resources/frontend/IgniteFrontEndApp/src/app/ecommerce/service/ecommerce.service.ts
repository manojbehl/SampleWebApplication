import { Injectable } from '@angular/core';
import {ProductOrder} from "../models/product-order";
import {Subject} from "rxjs/internal/Subject";
import {ProductOrders} from "../models/product-orders";
import {User} from "../models/user";

import {HttpClient} from '@angular/common/http';
import { HttpHeaders } from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class EcommerceService {

  private productsUrl = "/api/products";
  private ordersUrl = "/api/orders";

  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();

  private productOrderSubject = new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();

  private total: number;

  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  user:User;

  getAllProducts() {
    // user:User = localStorage.getItem("currentUser");
      this.user =   JSON.parse(localStorage.getItem('currentUser'))
      return this.http.get(this.productsUrl+"?authToken="+this.user.authToken);
  }

  getAllOrders(userId: number) {
    // user:User = localStorage.getItem("currentUser");
      this.user =   JSON.parse(localStorage.getItem('currentUser'))
      return this.http.get(this.ordersUrl+"/"+userId + "?authToken="+this.user.authToken);
  }


  saveOrder(order: ProductOrders) {
      this.user =   JSON.parse(localStorage.getItem('currentUser'))
      order.userId = this.user.id;
      console.log(order);
      return this.http.post(this.ordersUrl +"?authToken="+this.user.authToken, order);
  }

  set SelectedProductOrder(value: ProductOrder) {
      this.productOrder = value;
      this.productOrderSubject.next();
  }

  get SelectedProductOrder() {
      return this.productOrder;
  }

  set ProductOrders(value: ProductOrders) {
      this.orders = value;
      this.ordersSubject.next();
  }

  get ProductOrders() {
      return this.orders;
  }

  get Total() {
      return this.total;
  }

  set Total(value: number) {
      this.total = value;
      this.totalSubject.next();
  }
}
