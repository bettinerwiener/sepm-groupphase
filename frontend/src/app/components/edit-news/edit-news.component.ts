import { Component, OnInit, Sanitizer } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EventNews } from 'src/app/dtos/event-news';
import { Room } from 'src/app/dtos/room';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { EventNewsService } from 'src/app/services/event-news.service';
import { AuthService } from 'src/app/services/auth.service';
import { RoomService } from 'src/app/services/room.service';
import { EventService } from 'src/app/services/event.service';
import { News } from 'src/app/dtos/news';
import { User } from 'src/app/dtos/user';
import { NewsService } from 'src/app/services/news.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-edit-news',
  templateUrl: './edit-news.component.html',
  styleUrls: ['./edit-news.component.scss']
})
export class EditNewsComponent implements OnInit {

  news: News;
  imageURL: SafeUrl;
  error: boolean = false;
  success: boolean = false;
  isChangeImage: boolean = false;
  rooms: Room[];
  events: GlobalEvent[];
  formData: FormData;
  submitted: boolean = false;
  errorMessage: string = 'A news entry for the chosen event could not be created.';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formbuilder: FormBuilder,
    private newsService: NewsService,
    private authService: AuthService,
    private roomService: RoomService,
    private eventService: EventService,
    private sanitizer: DomSanitizer) {
      const id: number = parseInt(this.route.snapshot.paramMap.get('id'));
      this.news = new News(null, null, null, null, null, null);
      this.getNewsById(id);
      this.formData = new FormData();
  }

  ngOnInit() {
    
  }


  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }

  onSubmit() {
    this.submitted = true;
    let updatedNews = this.news;
    updatedNews.image = null;
    if (!this.formData.has('image')) {
      this.formData.append('image', this.news.image);
    }
    this.updateNews(updatedNews);
  }

  public getNewsById(id: number) {
    this.newsService.getNewsById(id).subscribe(
      (news: News) => {
        this.news = news;
        this.newsService.getImage(id).subscribe(
          (image: any) => {
            this.news.image = image.body;
            this.formData.append('image', new File([image.body], 'old.jpeg'));
            const reader = new FileReader();
            reader.readAsDataURL(this.news.image);
            let result;
            reader.onloadend = (event:Event) => {
              result = reader.result;
              this.imageURL = this.sanitizer.bypassSecurityTrustUrl(result);
            };
            
          },
          error => {
            this.defaultServiceErrorHandling(error);
          }
        );
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  public sendImage(formData: FormData, id: Number) {
    this.newsService.updateImage(formData, id).subscribe(
      success => {
        this.success = true;
        window.location.reload();
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    )
  }

  public getImage(formData: FormData, id: Number) {
    this.newsService.getImage(id).subscribe(
      (image) => {
        this.success = true;
        this.news.image = image.body; 
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    )
  }

  public updateNews(news: News) {
    this.newsService.updateNews(news).subscribe(
      (resNews: News) => {
        this.news = resNews;
        this.success = true;
        if(!this.formData.has('image')) {
          console.log("setting formData");
          this.formData.append('image', this.news.image);
        };
        this.sendImage(this.formData, resNews.id);
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  changeImage() {
    this.isChangeImage = true;
  }

  onFileSelect(event) {
    if (event.target.files.length > 0)
    {
      this.formData.delete('image');
      this.formData.append('image', event.target.files[0]);
    }
    
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
    this.submitted = false;
    this.isChangeImage = false;
  }
}
