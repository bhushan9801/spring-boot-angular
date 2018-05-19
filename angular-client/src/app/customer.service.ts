import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Customer} from "./customer";

@Injectable()
export class CustomerService {

  selectedCust: Customer;

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any  > {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa('br92749:password')
      })
    };

    return this.http.get('//localhost:8080/api/customers', httpOptions);
  }


}
