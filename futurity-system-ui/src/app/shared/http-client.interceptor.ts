import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class HttpClientInterceptor implements HttpInterceptor {
  private baseUrl = 'http://localhost:8080';
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    // Clone the request and add a custom header
    const modifiedRequest = request.clone({
      url: `${this.baseUrl}/${request.url}`,
      setHeaders: {
        'X-Custom-Header': 'Custom Value',
      },
    });

    return next.handle(modifiedRequest);
  }
}
