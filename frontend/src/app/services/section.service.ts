import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { Section } from '../dtos/section';

@Injectable({
  providedIn: 'root'
})
export class SectionService {

  private sectionBaseUri: string = this.globals.backendUri + '/sections';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  createSections(sections: Array<Section>): Observable<Array<Section>> {
    console.log('Create sections for room.');
    return this.httpClient.post<Array<Section>>(this.sectionBaseUri, sections);
  }

  getSectionsOfRoom(roomID: number): Observable<Array<Section>> {
    console.log('Get sections of room ' + roomID);
    return this.httpClient.get<Array<Section>>(this.sectionBaseUri + roomID);
  }
}
