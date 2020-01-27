import { Component, OnInit } from '@angular/core';
import {User} from "../../dtos/user";
import {Observable, Subject} from 'rxjs';
import {AdminService} from "../../services/admin.service";
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';


@Component({
  selector: 'app-useradmin',
  templateUrl: './search-user.component.html',
  styleUrls: ['./search-user.component.scss']
})
export class SearchUserComponent implements OnInit {
  users: Observable<User[]>;
  searched: boolean = false;
  private searchTerms = new Subject<String>();

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.users = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.adminService.searchUsersByName(term))
    );
  }

  search(name: String): void {
    this.searchTerms.next(name);
    this.searched = true;
  }

}
