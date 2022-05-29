import {Component, InjectionToken, OnInit} from '@angular/core';
import {Cocktail} from "../../../model/Cocktail";
import {CocktailServiceImpl} from "../../../service/impl/CocktailServiceImpl";
import {ImageServiceImpl} from "../../../service/impl/ImageServiceImpl";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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

  constructor(private cocktailService: CocktailServiceImpl,
              private imageService: ImageServiceImpl,
              private fb: FormBuilder) {
    this.ratingForm = this.fb.group({
      rating: ['', Validators.required],
    });
    this.cocktailService.findById(1).subscribe(cocktail =>{
     this.cocktail = cocktail;
     this.imageSrc = this.imageService.getImage(this.cocktail.cocktailImage.imageId);
     console.log(this.cocktail);
    });
  }

  ngOnInit(): void {
  }

}
