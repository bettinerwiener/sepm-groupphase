import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { Artist } from '../dtos/artist';

@Injectable({
  providedIn: 'root'
})
export class ArtistService {

  private artistBaseUri: string = this.globals.backendUri + '/artists';

  constructor(
    private httpClient: HttpClient,
    private globals: Globals
  ) { }

  getArtists(): Observable<Artist[]> {
    console.log('Get all artists');
    return this.httpClient.get<Artist[]>(this.artistBaseUri);
  }
}
