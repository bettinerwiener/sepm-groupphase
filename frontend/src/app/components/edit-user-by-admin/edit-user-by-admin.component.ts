import { Component, OnInit, Input } from '@angular/core';
import {User} from "../../dtos/user";
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {AdminService} from "../../services/admin.service";
import {FormBuilder, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-edit-user-by-admin',
  templateUrl: './edit-user-by-admin.component.html',
  styleUrls: ['./edit-user-by-admin.component.scss']
})
export class EditUserByAdminComponent implements OnInit {
  user: User = new User(0,'','','','',false,false);
  registerForm: FormGroup;


  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private service: AdminService,
    private formBuilder: FormBuilder,
  ) {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }


  ngOnInit() {
    this.getUser();

    this.registerForm.controls['firstName'].setValue(this.user.firstName)
  }

  getUser(): void {
    this.service.getUserByName(this.route.snapshot.paramMap.get('username'))
      .subscribe(user => {this.user = user;
          this.registerForm.controls['firstName'].setValue(this.user.firstName);
          this.registerForm.controls['lastName'].setValue(this.user.lastName);
          this.registerForm.controls['email'].setValue(this.user.email);
          this.registerForm.controls['password'].setValue(this.user.password);
        }
      );
  }

  updateUser(): void {
    console.log("Update some User");

    this.user.firstName = this.registerForm.controls.firstName.value;
    this.user.lastName = this.registerForm.controls.lastName.value;
    this.user.password = this.registerForm.controls.password.value;
    this.user.email = this.registerForm.controls.email.value;
    this.service.updateUser(this.user).subscribe(user => this.user = user);
  }

  changeAdmin(): void {
    this.user.isEmployee = !this.user.isEmployee;
  }

  changeLocked(): void {
    this.user.locked = !this.user.locked;
  }

}
