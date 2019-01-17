import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MainComponent} from './MainComponent';
import { UserComponent } from './user/user.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UserDetailComponent} from './user/user-detail/user-detail.component';
import { UserEditComponent } from './user/user-edit/user-edit.component';
import { LoginComponent } from './login/login.component';
import {BasicAuthInterceptor} from './auth/basic-auth.interceptor';
import {ErrorInterceptor} from './auth/error.interceptor';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import {
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule,
    MatOptionModule,
    MatRadioModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatTableModule,
    MatToolbarModule, MatListModule
} from '@angular/material';
import { ProfileComponent } from './profile/profile.component';
import { EventComponent } from './event/event.component';
import { EventDetailsComponent } from './event/event-details/event-details.component';
import { TicketsComponent } from './event/tickets/tickets.component';
import { CreateEventComponent } from './event/create-event/create-event.component';
import { OrgEventsComponent } from './org-events/org-events.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminEventsComponent } from './admin-events/admin-events.component';
import { PublicProfileComponent } from './public-profile/public-profile.component';
import { FeedsComponent } from './feeds/feeds.component';
import { TestComponent } from './test/test.component';
import { FollowingComponent } from './following/following.component';
import { RoleRequestComponent } from './role-request/role-request.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { OrgPanelComponent } from './org-panel/org-panel.component';
import { FilterPipe } from './filter.pipe';
import { EventTicketsComponent } from './event-tickets/event-tickets.component';
import { PassForgetComponent } from './pass-forget/pass-forget.component';
import { PassResetComponent } from './pass-reset/pass-reset.component';


@NgModule({
  declarations: [
    MainComponent,
    AppComponent,
    UserComponent,
    RegisterComponent,
    UserDetailComponent,
    UserEditComponent,
    LoginComponent,
    FooterComponent,
    NavbarComponent,
    ProfileComponent,
    EventComponent,
    EventDetailsComponent,
    TicketsComponent,
    CreateEventComponent,
    OrgEventsComponent,
    AdminUsersComponent,
    AdminEventsComponent,
    PublicProfileComponent,
    FeedsComponent,
    TestComponent,
    FollowingComponent,
    RoleRequestComponent,
    AdminPanelComponent,
    OrgPanelComponent,
    FilterPipe,
    EventTicketsComponent,
    PassForgetComponent,
    PassResetComponent
  ],
  imports: [
      BrowserModule,
      MatNativeDateModule,
      BrowserAnimationsModule,
      AppRoutingModule,
      FormsModule,
      ReactiveFormsModule,
      HttpClientModule,
      MatInputModule,

      MatFormFieldModule,
      MatButtonModule,
      MatMenuModule,
      MatToolbarModule,
      MatIconModule,
      MatCardModule,
      MatDatepickerModule,
      MatNativeDateModule,
      MatRadioModule,
      MatSelectModule,
      MatOptionModule,
      MatSlideToggleModule,
      MatTableModule,
      MatListModule
  ],
  providers: [
      { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
      { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [MainComponent]
})
export class AppModule { }
