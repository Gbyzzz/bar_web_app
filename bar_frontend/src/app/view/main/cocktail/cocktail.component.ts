import {Component, InjectionToken, OnInit} from '@angular/core';
import {Cocktail} from "../../../model/Cocktail";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NavigationStart, Router} from "@angular/router";
import {filter} from "rxjs";

export const IMAGE_URL_TOKEN = new InjectionToken<string>('url');


@Component({
  selector: 'app-cocktail',
  templateUrl: './cocktail.component.html',
  styleUrls: ['./cocktail.component.css']
})
export class CocktailComponent implements OnInit {

  cocktail: Cocktail;
  imageSrc: string;
  ratingForm: FormGroup;
  cocktailId: number

  constructor(private cocktailService: CocktailServiceImpl,
              private imageService: ImageServiceImpl,
              private fb: FormBuilder,
              private router: Router) {
    this.ratingForm = this.fb.group({
      rating: ['', Validators.required],
    });
    this.cocktailId = Number(this.router.url.split('/')[this.router.url.split('/').length-1]);
    this.cocktailService.findById(this.cocktailId).subscribe(cocktail =>{
     this.cocktail = cocktail;
     this.imageSrc = this.imageService.getImage(this.cocktail.cocktailImage.imageId);
     console.log(this.cocktail);
    });
  }

  ngOnInit(): void {
  }

}
