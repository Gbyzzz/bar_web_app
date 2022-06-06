import {Component, OnInit} from '@angular/core';
import {UserServiceImpl} from "./service/entity/impl/UserServiceImpl";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  translate: TranslateService;

  ngOnInit(): void {
  }

  constructor(private userService: UserServiceImpl) {
    // this.translate.addLangs(['en', 'ru']);
    // this.translate.setDefaultLang('en');
    //
    // const browserLang = this.translate.getBrowserLang();
    // this.translate.use(browserLang.match(/en|ru/) ? browserLang : 'en');
  }

}
