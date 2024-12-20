import {ChangeDetectorRef, Component, InjectionToken, NgModule, OnInit} from '@angular/core';
import {Cocktail} from "../../../model/Cocktail";
import {CocktailServiceImpl} from "../../../service/entity/impl/CocktailServiceImpl";
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, NavigationEnd, NavigationStart, Router} from "@angular/router";
import {TokenStorageService} from "../../../service/auth/token-storage.service";
import {Vote} from "../../../model/Vote";
import {VoteServiceImpl} from "../../../service/entity/impl/VoteServiceImpl";
import {Recipe} from "../../../model/Recipe";
import {RecipeServiceImpl} from "../../../service/entity/impl/RecipeServiceImpl";
import {LoginSharedService} from "../../../service/auth/login-shared.service";
import {ClickEvent, StarRatingConfigService, StarRatingModule} from 'angular-star-rating';

export const IMAGE_URL_TOKEN = new InjectionToken<string>('url');


@Component({
  selector: 'app-cocktail',
  templateUrl: './cocktail.component.html',
  styleUrls: ['./cocktail.component.css'],
  providers: [StarRatingConfigService]
})
export class CocktailComponent implements OnInit {

  cocktail: Cocktail;
  private route: ActivatedRoute;

  vote: Vote;
  ratingForm: UntypedFormGroup;
  cocktailId: number;
  cocktailName: string;
  // recipes: Recipe[];
  // voteCount: number;

  isUserLoggedIn: boolean;

  constructor(private cocktailService: CocktailServiceImpl,
              private voteService: VoteServiceImpl,
              private recipeService: RecipeServiceImpl,
              private fb: UntypedFormBuilder,
              private cdr: ChangeDetectorRef,
              private router: Router,
              private tokenStorage: TokenStorageService,
              private sharedService: LoginSharedService,
              private starRatingModule: StarRatingModule) {
    this.ratingForm = this.fb.group({
      rating: ['', Validators.required],
    });

    this.cocktailId = Number(this.router.url.split('/')[this.router.url.split('/').length-1]);
    this.cocktailService.findCocktailById(this.cocktailId).subscribe(cocktail =>{
      console.log(cocktail);
      this.isUserLoggedIn = this.tokenStorage.getUser() == null ? false : true;
      this.cocktail = cocktail;
      console.log(this.cocktail);


      // this.recipeService.findByCocktail(cocktail).subscribe(res => {
     //   this.recipes = res;
     // });
     //  this.voteService.getVoteCountByCocktail(cocktail).subscribe(count =>{
     //    this.voteCount = count;
     //  });
      this.vote = new Vote(null, this.tokenStorage.getUser(), this.cocktail, 0);
     this.cocktailName = this.cocktail.cocktailName;
     voteService.findByCocktailUserVote(this.vote).subscribe(res => {
        this.vote = res;
      });
    });

  }

  // ngOnInit(): void {
  //   this.sharedService.eventLoggedSubject.subscribe((loggedIn: boolean) => {
  //     console.log(this.cocktailRecipeDTO);
  //     this.isUserLoggedIn = loggedIn;
  //     this.vote = new Vote(null, this.tokenStorage.getUser(), this.cocktailRecipeDTO.cocktailDTO, 0);
  //     this.voteService.findByCocktailUserVote(this.vote).subscribe(res => {
  //       this.vote = res;
  //       // this.voteService.getVoteCountByCocktail(this.cocktail).subscribe(count =>{
  //       //   this.voteCount = count;
  //       // });
  //     });
  //   });
  // }

  ngOnInit(): void {
    // this.route.queryParams.subscribe(params => {
    //   // this.cocktailId = parseInt(params.get('cocktailId'));
    //   console.log("load")
    //   console.log(params)
    //
    //
    //   if (this.cocktailId) {
    //     this.loadCocktailData(this.cocktailId);
    //   }
    // });
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        // Only process this when navigation starts
        const cocktailId = parseInt(event.url.split('/')[3], 10);

        if (cocktailId && !isNaN(cocktailId)) {
          this.cocktailId = cocktailId;
          console.log("Cocktail ID: ", this.cocktailId);
          this.loadCocktailData(this.cocktailId);  // Only load when the cocktailId is valid
        }
      }
    });
    this.sharedService.eventLoggedSubject.subscribe((loggedIn: boolean) => {
      this.isUserLoggedIn = loggedIn;

      if (this.cocktail) {
        this.vote = new Vote(null, this.tokenStorage.getUser(), this.cocktail, 0);
        this.voteService.findByCocktailUserVote(this.vote).subscribe(res => {
          this.vote = res;
          // this.voteService.getVoteCountByCocktail(this.cocktail).subscribe(count =>{
          //   this.voteCount = count;
          // });
        });
      }
    });
  }

  loadCocktailData(cocktailId: number): void {
    this.cocktailService.findCocktailById(cocktailId).subscribe(data => {
      this.cocktail = data;
      console.log("load")
      console.log(this.cocktail)
    });
  }


  onRate(value: ClickEvent) {
    this.vote.voteValue = value.rating;
    console.log(this.vote);
    this.voteService.add(this.vote).subscribe(res =>{
      this.vote = res;
      this.cdr.detectChanges();
    this.cocktailService.findCocktailById(this.cocktailId).subscribe(cocktail =>{
      this.cocktail = cocktail;
      // this.voteService.getVoteCountByCocktail(cocktail).subscribe(count =>{
      //   this.voteCount = count;
      // });
      this.voteService.findByCocktailUserVote(this.vote).subscribe(res => {
        this.vote = res;
      });
    });
    });
  }
}
