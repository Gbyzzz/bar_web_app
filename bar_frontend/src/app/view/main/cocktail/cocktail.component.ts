import {ChangeDetectorRef, Component, InjectionToken, OnInit} from '@angular/core';
import {Cocktail} from "../../../model/Cocktail";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {ImageServiceImpl} from "../../../service/entity/impl/ImageServiceImpl";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NavigationStart, Router} from "@angular/router";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {Vote} from "../../../model/Vote";
import {VoteServiceImpl} from "../../../service/entity/impl/VoteServiceImpl";
import {Recipe} from "../../../model/Recipe";
import {RecipeServiceImpl} from "../../../service/entity/impl/RecipeServiceImpl";
import {Image} from "../../../model/Image";

export const IMAGE_URL_TOKEN = new InjectionToken<string>('url');


@Component({
  selector: 'app-cocktail',
  templateUrl: './cocktail.component.html',
  styleUrls: ['./cocktail.component.css']
})
export class CocktailComponent implements OnInit {

  cocktail: Cocktail;
  image: Image;
  vote: Vote;
  ratingForm: FormGroup;
  cocktailId: number;
  cocktailName: string;
  recipes: Recipe[];
  voteCount: number;

  isUserLoggedIn: boolean;

  constructor(private cocktailService: CocktailServiceImpl,
              private imageService: ImageServiceImpl,
              private voteService: VoteServiceImpl,
              private recipeService: RecipeServiceImpl,
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
     this.recipeService.findByCocktail(cocktail).subscribe(res => {
       this.recipes = res;
       console.log(res);
     });
      this.voteService.getVoteCountByCocktail(cocktail).subscribe(count =>{
        this.voteCount = count;
      });
     this.vote = new Vote(null, this.tokenStorage.getUser(), cocktail, 0);
     this.cocktailName = cocktail.cocktailName;
     this.image = cocktail.cocktailImage;
     voteService.findByCocktailUserVote(this.vote).subscribe(res => {
        this.vote = res;
        console.log(res);
      });
    });

  }

  ngOnInit(): void {
  }


  onRate(value: number) {
    this.vote.voteValue = value;
    console.log(this.vote);
    this.voteService.add(this.vote).subscribe(res =>{
      this.vote = res;
      this.cdr.detectChanges();
    this.cocktailService.findById(this.cocktailId).subscribe(cocktail =>{
      this.cocktail = cocktail;
      console.log(cocktail);
      this.voteService.getVoteCountByCocktail(cocktail).subscribe(count =>{
        this.voteCount = count;
        console.log(count);
      });
      this.voteService.findByCocktailUserVote(this.vote).subscribe(res => {
        this.vote = res;
        console.log(res);
      });
    });
    });
    console.log(value);
  }
}
