import {Inject, Injectable, InjectionToken} from '@angular/core';
import {ImageService} from "../ImageService";
import {Image} from "../../model/Image";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";


export const IMAGE_URL_TOKEN = new InjectionToken<string>('url');

@Injectable({
  providedIn: 'root'
})
export class ImageServiceImpl implements ImageService{

  private readonly url: string;

  constructor(@Inject(IMAGE_URL_TOKEN) private baseUrl: string, private HttpClient: HttpClient) {
    this.url = baseUrl;
  }

  uploadImage(formData: FormData): Observable<number> {
    return this.HttpClient.post<any>(this.url + '/upload', formData);
  }

  getImage(id: number): string {
    return this.url + "/" + id;
  }


}
