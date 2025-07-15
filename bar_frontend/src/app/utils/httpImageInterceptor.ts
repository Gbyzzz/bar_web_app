// prepend-image-base.interceptor.ts
import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable()
export class HttpImageInterceptor implements HttpInterceptor {
  private readonly base = environment.IMAGE_URL;

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      map(event => {
        if (event instanceof HttpResponse && event.body) {
          const body = this.patchImageUrls(event.body);
          return event.clone({ body });
        }
        return event;
      })
    );
  }

  private patchImageUrls(data: any): any {
    // Handle paginated response: { content: [...], ...paginationMeta }
    if (data?.content && Array.isArray(data.content)) {
      return {
        ...data,
        content: data.content.map(c => this.patchSingle(c))
      };
    }

    // Handle array directly
    if (Array.isArray(data)) {
      return data.map(c => this.patchSingle(c));
    }

    // Handle single object
    if (typeof data === 'object') {
      return this.patchSingle(data);
    }

    return data;
  }

  private patchSingle(cocktail: any): any {
    if (!cocktail || typeof cocktail !== 'object') return cocktail;

    return {
      ...cocktail,
      cocktailImage: cocktail.cocktailImage ? this.base + cocktail.cocktailImage : null,
      cocktailImageThumbnail: cocktail.cocktailImageThumbnail
        ? this.base + cocktail.cocktailImageThumbnail
        : null
    };
  }
}
