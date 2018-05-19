import {Component, OnInit} from '@angular/core';
import {CustomerService} from '../customer.service';
import {Customer} from "../customer";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Array<Customer>;
  selectedCust: Customer;

  constructor(private customerService: CustomerService) {
  }

  ngOnInit() {
    this.customerService.getAll().subscribe(data => {
        this.customers = data;
      }
    );
  }

  onSelect(customer: Customer) {
    this.selectedCust = customer;
  }
}
