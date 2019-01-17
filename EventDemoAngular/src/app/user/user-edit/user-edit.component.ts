import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../user.model';
import {UserService} from '../user.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {map} from 'rxjs/operators';

function passwordMatcher(control: AbstractControl) {
    return control.get('password').value === control.get('confirm').value
        ? null : {'nomatch': true};
}
@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html'
})
export class UserEditComponent implements OnInit {
    myReactiveForm: FormGroup;
    // user: User;
    id: number;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private userService: UserService ) { }

  ngOnInit() {
    this.route.params.subscribe((value: any ) => {
      this.id = value.id;
    });
      this.userService.getUser(this.id).subscribe((value0 => {
          // this.user = value0;
          this.myReactiveForm.patchValue(value0 as any);
          }
      ));
      this.myReactiveForm = this.formBuilder.group({
          email: ['', Validators.compose([Validators.required, Validators.email])],
          username: ['', Validators.compose([Validators.required, Validators.pattern(/^[a-zA-Z]/)])],
          password: ['', Validators.compose([Validators.required, Validators.minLength(6), Validators.pattern(/^[a-zA-Z]/)])],
          confirm: ['', Validators.compose([Validators.required, Validators.minLength(6)])],
          usergender: ``,
          firstname: ``,
          midname: ``,
          lastname : ``,
          userphone: ``,
          userdob: ''
      }, {validator: passwordMatcher // pass in the validator function
      });
  }
    onSubmit() {
      console.log(this.myReactiveForm.value);
      return this.userService.updateUser(this.id, this.myReactiveForm).subscribe();
    }

}
