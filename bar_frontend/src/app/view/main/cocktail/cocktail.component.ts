import {ChangeDetectorRef, Component, InjectionToken, OnInit} from '@angular/core';
import {Cocktail} from "../../../model/Cocktail";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NavigationStart, Router} from "@angular/router";
import {filter} from "rxjs";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {Vote} from "../../../model/Vote";
import {VoteServiceImpl} from "../../../service/entity/impl/VoteServiceImpl";

export const IMAGE_URL_TOKEN = new InjectionToken<string>('url');


@Component({
  selector: 'app-cocktail',
  templateUrl: './cocktail.component.html',
  styleUrls: ['./cocktail.component.css']
})
export class CocktailComponent implements OnInit {

  cocktail: Cocktail;
  imageSrc: string;
  vote: Vote;
  ratingForm: FormGroup;
  cocktailId: number;
  cocktailName: string;

  isUserLoggedIn: boolean;

  constructor(private cocktailService: CocktailServiceImpl,
              private imageService: ImageServiceImpl,
              private voteService: VoteServiceImpl,
              private fb: FormBuilder,
              private cdr: ChangeDetectorRef,
              private router: Router,
              private tokenStorage: TokenStorageService) {
    this.ratingForm = this.fb.group({
      rating: ['', Validators.required],
    });

    this.cocktailId = Number(this.router.url.split('/')[this.router.url.split('/').length-1]);
    this.isUserLoggedIn = this.tokenStorage.getUser() == null ? false : true;
    console.log(this.isUserLoggedIn);
    this.cocktailService.findById(this.cocktailId).subscribe(cocktail =>{
     this.cocktail = cocktail;
     console.log(cocktail.recipes[0].cocktail);
     this.vote = new Vote(null, this.tokenStorage.getUser(), cocktail, 0);
     this.cocktailName = cocktail.cocktailName;
     this.imageSrc = this.imageService.getImage(this.cocktail.cocktailImage.imageId);
      voteService.findByCocktailUserVote(this.vote).subscribe(res => {
        this.vote = res;
        console.log(res);
      });    });

  }

  ngOnInit(): void {
  }


  onRate(value: number) {
    this.vote.voteValue = value;
    console.log(this.vote);
    this.voteService.add(this.vote).subscribe(res =>{
      this.vote = res;
      this.cdr.detectChanges();
    });
    console.log(value);

  }
}
