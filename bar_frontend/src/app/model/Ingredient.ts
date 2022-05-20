export class Ingredient {
  ingredientId: number;
  ingredientName: string;
  ingredientAlcohol: number;
  unitOfMeasurement: string;


  constructor(ingredientId: number, ingredientName: string, ingredientAlcohol: number, unitOfMeasurement: string) {
    this.ingredientId = ingredientId;
    this.ingredientName = ingredientName;
    this.ingredientAlcohol = ingredientAlcohol;
    this.unitOfMeasurement = unitOfMeasurement;
  }
}
