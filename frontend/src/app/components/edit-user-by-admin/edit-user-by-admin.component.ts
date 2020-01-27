import { Component, OnInit, Input } from '@angular/core';
import {User} from '../../dtos/user';
import {ActivatedRoute} from '@angular/router';
import {AdminService} from '../../services/admin.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MustMatch} from '../../services/matcher';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-edit-user-by-admin',
  templateUrl: './edit-user-by-admin.component.html',
  styleUrls: ['./edit-user-by-admin.component.scss']
})
export class EditUserByAdminComponent implements OnInit {
  user: User = new User(0, '', '', '', '', false, false);
  registerForm: FormGroup;

  submitted: boolean = false;
  success: boolean = false;

  error: boolean = false;
  errorMessage: string = '';

  veriyfied: boolean = false;


  constructor(
    private route: ActivatedRoute,
    private service: AdminService,
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
    this.service.getUserByName(this.route.snapshot.paramMap.get('username'))
      .subscribe(user => {this.user = user;
          this.registerForm.controls['firstName'].setValue(this.user.firstName);
          this.registerForm.controls['lastName'].setValue(this.user.lastName);
          this.registerForm.controls['email'].setValue(this.user.email);
          this.registerForm.controls['password'].setValue(this.user.password);
          this.registerForm.controls['passwordConf'].setValue(this.user.password);
        }
      );
  }

  vanishSuccess() {
    this.success = false;
  }

  updateUser(): void {
    this.submitted = true;
    this.success = true;

    this.user.firstName = this.registerForm.controls.firstName.value;
    this.user.lastName = this.registerForm.controls.lastName.value;
    this.user.password = this.registerForm.controls.password.value;
    this.user.email = this.registerForm.controls.email.value;

    if (this.registerForm.controls.passwordConf.value === this.registerForm.controls.password.value) {
      this.service.updateUser(this.user).subscribe(user => this.user = user);
    } else {
      this.error = true;
      this.errorMessage = 'password doesn\'t match!';
    }

  }

  changeAdmin(): void {
    console.log("toggled admin")
    this.user.isEmployee = !this.user.isEmployee;
  }

  changeLocked(): void {
    console.log("toggled Lock")
    this.user.locked = !this.user.locked;
  }

  deleteUser(): void {
    console.log('Delete some User');
    this.submitted = true;
    const user = {
      email: this.auth.getUserName(),
      password: this.registerForm.controls.passwordAdmin.value
    }

    this.service.verifyAdmin(user).subscribe(boolValue => {
      this.veriyfied = boolValue;
      if (this.veriyfied) {
        console.log('right');
        this.service.deleteUser(this.registerForm.controls.email.value).subscribe(
          reValue => {
            this.router.navigate(['/admin']).then(() => {
              window.location.reload();
            });
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
