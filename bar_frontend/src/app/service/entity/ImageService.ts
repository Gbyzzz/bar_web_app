import {Observable} from "rxjs";
import {Image} from "../../model/Image";

export interface ImageService {

  uploadImage(formData:FormData): Observable<Image>;

  getImage(id: number): string;

}
