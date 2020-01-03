import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EventNewsService } from 'src/app/services/event-news.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventNews } from 'src/app/dtos/event-news';
import { News } from 'src/app/dtos/news';
import { RoomService } from 'src/app/services/room.service';
import { Room } from 'src/app/dtos/room';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { EventService } from 'src/app/services/event.service';
import { stringify } from 'querystring';
import { throwIfEmpty } from 'rxjs/operators';
import { User } from 'src/app/dtos/user';
import { toBase64String } from '@angular/compiler/src/output/source_map';

@Component({
  selector: 'app-create-event-news',
  templateUrl: './create-event-news.component.html',
  styleUrls: ['./create-event-news.component.scss']
})
export class CreateEventNewsComponent implements OnInit {

  createEventNewsForm: FormGroup;
  eventNews: EventNews;
  error: boolean = false;
  success: boolean = false;
  rooms: Room[];
  events: GlobalEvent[];
  submitted: boolean = false;
  errorMessage: string = 'A news entry for the chosen event could not be created.';

  constructor(
    private formbuilder: FormBuilder,
    private eventNewsService: EventNewsService,
    private authService: AuthService,
    private roomService: RoomService,
    private eventService: EventService) {
    this.createEventNewsForm = this.formbuilder.group({
      event: [Validators.required],
      title: [Validators.required],
      shortDescription: [Validators.required],
      entry: [Validators.required],
      image: [Validators.nullValidator],
    });
  }

  ngOnInit() {
    this.getAllEvents();
  }

  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }

  addEventNews() {
    this.submitted = true;
    if (this.createEventNewsForm.valid) {

      const image_str = toBase64String(this.createEventNewsForm.controls.image.value);

      const news = new News(
        null,
        this.createEventNewsForm.controls.title.value,
        this.createEventNewsForm.controls.shortDescription.value,
        this.createEventNewsForm.controls.entry.value,
        image_str,
        null
      )

      const user = new User(
        null,
        "dummy",
        "dummy",
        this.authService.getUserName(),
        "dummy",
        false,
        true
      )

      const eventNews: EventNews = new EventNews(
        this.createEventNewsForm.controls.event.value,
        news,
        user
      );



      this.createEventNews(eventNews);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public createEventNews(eventNews: EventNews) {
    this.eventNewsService.createEventNews(eventNews).subscribe(
      (retEventNews: EventNews) => {
        this.eventNews = retEventNews;
        this.success = true;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private getAllEvents() {
    this.eventService.getEvent().subscribe(
      (events: GlobalEvent[]) => {
        this.events = events;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }


  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }

  vanishError() {
    this.error = false;
  }

  vanishSuccess() {
    this.success = false;
  }

  private clearForm() {
    this.createEventNewsForm.reset();
    this.submitted = false;
  }
}
