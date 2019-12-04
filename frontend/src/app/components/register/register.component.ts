import { Component, OnInit } from '@angular/core';
import {User} from '../../dtos/user';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {Router} from '@angular/router';

import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  user: User;
  registerForm: FormGroup;
  // After first submission attempt, form validation will start
  submitted: boolean = false;
  // Error flag
  error: boolean = false;
  errorMessage: string = '';

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  /**
   * Form validation will start after the method is called, additionally an AuthRequest will be sent
   */
  registerUser() {
    this.submitted = true;
    if (this.registerForm.valid) {
      const newUser = {
        id: null,
        firstName: this.registerForm.controls.firstName.value,
        lastName: this.registerForm.controls.lastName.value,
        email: this.registerForm.controls.email.value,
        password: this.registerForm.controls.password.value,
        locked: false,
        isEmployee: false};

      this.userService.newUser(newUser)
        .subscribe(user => {
            this.user = user;
            this.router.navigate(['']);
          },
          error => {
            this.defaultServiceErrorHandling(error);
          });
      this.clearForm();
    } else {
      console.log('Invalid input');
    }

  }


  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (typeof error.error === 'object') {
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error;
    }
  }

  private clearForm() {
    this.registerForm.reset();
    this.submitted = false;
  }

  ngOnInit() {
  }
}
