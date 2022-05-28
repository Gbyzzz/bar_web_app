import {Observable} from "rxjs";

export interface ImageService {

  uploadImage(formData:FormData): Observable<number>;
}
