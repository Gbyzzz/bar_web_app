import {Component, OnInit} from '@angular/core';
import {UserServiceImpl} from "./service/entity/impl/UserServiceImpl";
import {TranslateService} from "@ngx-translate/core";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    standalone: false
})
export class AppComponent implements OnInit{

  translate: TranslateService;

  ngOnInit(): void {
  }

  constructor(private userService: UserServiceImpl) {
  }

}
