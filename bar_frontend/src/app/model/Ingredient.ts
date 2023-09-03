export class Ingredient {
  ingredientId: number;
  ingredientName: string;
  ingredientAlcohol: number;
  unitOfMeasurement: string;


  constructor(ingredientName: string, ingredientAlcohol: number, unitOfMeasurement: string, ingredientId?: number) {
    this.ingredientId = ingredientId;
    this.ingredientName = ingredientName;
    this.ingredientAlcohol = ingredientAlcohol;
    this.unitOfMeasurement = unitOfMeasurement;
  }

  // toJSON(): any {
  //   const { ingredientId, ...rest } = this;
  //   return {
  //     '@class': 'com.gbyzzz.bar_web_app.bar_backend.dto.IngredientDTO',
  //     ...rest
  //   };
  // }
}
