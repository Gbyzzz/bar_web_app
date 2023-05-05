export class Ingredient {
  private _ingredientId: number;
  private _ingredientName: string;
  private _ingredientAlcohol: number;
  private _unitOfMeasurement: string;


  constructor(ingredientName: string, ingredientAlcohol: number, unitOfMeasurement: string, ingredientId?: number) {
    this._ingredientId = ingredientId;
    this._ingredientName = ingredientName;
    this._ingredientAlcohol = ingredientAlcohol;
    this._unitOfMeasurement = unitOfMeasurement;
  }


  get ingredientId(): number {
    return this._ingredientId;
  }

  set ingredientId(value: number) {
    this._ingredientId = value;
  }

  get ingredientName(): string {
    return this._ingredientName;
  }

  set ingredientName(value: string) {
    this._ingredientName = value;
  }

  get ingredientAlcohol(): number {
    return this._ingredientAlcohol;
  }

  set ingredientAlcohol(value: number) {
    this._ingredientAlcohol = value;
  }

  get unitOfMeasurement(): string {
    return this._unitOfMeasurement;
  }

  set unitOfMeasurement(value: string) {
    this._unitOfMeasurement = value;
  }
}
