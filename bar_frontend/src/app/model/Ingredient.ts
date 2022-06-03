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

}
