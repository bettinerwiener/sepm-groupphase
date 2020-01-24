import { Component, OnInit } from '@angular/core';
import {User} from '../../dtos/user';
import {ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MustMatch} from '../../services/matcher';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})



export class ProfileComponent implements OnInit {
  user: User = new User(0, '', '', '', '', false, false);
  registerForm: FormGroup;

  submitted: boolean = false;
  success: boolean = false;

  error: boolean = false;
  errorMessage: string = '';

  veriyfied: boolean = false;


  constructor(
    private route: ActivatedRoute,
    private service: UserService,
    private formBuilder: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {
    this.registerForm = this.formBuilder.group({
        firstName: ['', [Validators.required]],
        lastName: ['', [Validators.required]],
        email: ['', [Validators.required]],
        password: ['', [Validators.required, Validators.minLength(8)]],
        passwordConf: ['', [Validators.required]],
        passwordAdmin: ['', [Validators.required]]
      },
      {
        validator: MustMatch('password', 'passwordConf')
      });
  }


  ngOnInit() {
    this.getUser();

    this.registerForm.controls['firstName'].setValue(this.user.firstName);
  }

  getUser(): void {
    this.service.getProfile()
      .subscribe(user => {this.user = user;
          this.registerForm.controls['firstName'].setValue(this.user.firstName);
          this.registerForm.controls['lastName'].setValue(this.user.lastName);
          this.registerForm.controls['email'].setValue(this.user.email);
          this.registerForm.controls['password'].setValue(this.user.password);
          this.registerForm.controls['passwordConf'].setValue(this.user.password);
        }
      );
  }

  updateUser(): void {
    this.submitted = true;
    this.success = true;

    this.user.firstName = this.registerForm.controls.firstName.value;
    this.user.lastName = this.registerForm.controls.lastName.value;
    this.user.password = this.registerForm.controls.password.value;
    this.user.email = this.registerForm.controls.email.value;

    if (this.registerForm.controls.passwordConf.value === this.registerForm.controls.password.value) {
      this.service.updateProfile(this.user).subscribe(user => this.user = user);
    } else {
      this.error = true;
      this.errorMessage = 'password doesn\'t match!';
    }
    this.auth.logoutUser();

  }

  deleteUser(): void {
    console.log('Delete some User');
    this.submitted = true;
    const user = {
      email: this.auth.getUserName(),
      password: this.registerForm.controls.passwordAdmin.value
    }

    this.service.verify(user).subscribe(boolValue => {
      this.veriyfied = boolValue;
      if (this.veriyfied) {
        this.service.delete().subscribe(
          reValue => {
            this.auth.logoutUser();
          }
        );

      } else {
        this.error = true;
        console.log('wrong');
        this.errorMessage = 'wrong password';
      }
    });


  }



}

