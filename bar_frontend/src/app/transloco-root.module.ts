import { HttpClient } from '@angular/common/http';
import {
  TRANSLOCO_LOADER,
  Translation,
  TranslocoLoader,
  TRANSLOCO_CONFIG,
  translocoConfig,
  TranslocoModule,
  TRANSLOCO_TRANSPILER,
  DefaultTranspiler,
  DefaultMissingHandler, TRANSLOCO_MISSING_HANDLER, TRANSLOCO_INTERCEPTOR,
  TranslocoInterceptor, TRANSLOCO_FALLBACK_STRATEGY, DefaultFallbackStrategy
} from '@jsverse/transloco';
import { Injectable, NgModule } from '@angular/core';
import { environment } from '../environments/environment';

@Injectable({ providedIn: 'root' })
export class TranslocoHttpLoader implements TranslocoLoader {
  constructor(private http: HttpClient) {}

  getTranslation(lang: string) {
    return this.http.get<Translation>(`/assets/i18n/${lang}.json`);
  }
}
@Injectable({ providedIn: 'root' })
export class NoopTranslocoInterceptor implements TranslocoInterceptor {
  preSaveTranslation(translation: Translation, lang: string): Translation {
    return translation;  // Return translation as is
  }
  preSaveTranslationKey(key: string, value: string, lang: string): string {
    return value;  // Return value as is
  }
  intercept(translation: any, key: string, config: any) {
    return translation;
  }
}


@NgModule({
  exports: [ TranslocoModule ],
  providers: [
    {
      provide: TRANSLOCO_CONFIG,
      useValue: translocoConfig({
        availableLangs: ['en', 'ru'],
        defaultLang: 'en',
        reRenderOnLangChange: true,
        prodMode: environment.production,
      })
    },
    { provide: TRANSLOCO_LOADER, useClass: TranslocoHttpLoader },
    { provide: TRANSLOCO_TRANSPILER, useClass: DefaultTranspiler },
    { provide: TRANSLOCO_MISSING_HANDLER, useClass: DefaultMissingHandler },
    { provide: TRANSLOCO_INTERCEPTOR, useClass: NoopTranslocoInterceptor },
    { provide: TRANSLOCO_FALLBACK_STRATEGY, useClass: DefaultFallbackStrategy }

  ]
})
export class TranslocoRootModule {}
