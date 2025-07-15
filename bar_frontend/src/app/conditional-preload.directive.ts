import { Directive, Renderer2, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

interface PreloadLinkConfig {
  href: string;
  as: string;
  media: string;
}

@Directive({
    selector: '[appConditionalPreload]',
    standalone: false
})
export class ConditionalPreloadDirective implements OnInit {

  private preloadConfigs: PreloadLinkConfig[] = [
    { href: 'assets/1,w_200.webp', as: 'image', media: '(max-width: 200px)' },
    { href: 'assets/1,w_639.webp', as: 'image', media: '(max-width: 639px)' },
    { href: 'assets/1,w_902.webp', as: 'image', media: '(max-width: 902px)' },
    { href: 'assets/1,w_1148.webp', as: 'image', media: '(max-width: 1148px)' },
    { href: 'assets/1,w_1296.webp', as: 'image', media: '(min-width: 1149px)' }
  ];

  constructor(
    private renderer: Renderer2,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.loadPreloadLinks();
    console.log('preload');
  }

  private loadPreloadLinks() {
    const currentRoute = this.route.snapshot.url[0]?.path;

    if (currentRoute === '') {
      this.preloadConfigs.forEach(config => this.addPreloadLink(config));
    }
  }

  private addPreloadLink(config: PreloadLinkConfig) {
    const head = document.head || document.getElementsByTagName('head')[0];
    const link = document.createElement('link');
    link.rel = 'preload';
    link.as = config.as;
    link.href = config.href;
    link.media = config.media;
    head.appendChild(link);
  }
}
