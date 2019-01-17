import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';
import {RegisterComponent} from './register/register.component';
import {UserDetailComponent} from './user/user-detail.component';
import {UserEditComponent} from './user/user-edit/user-edit.component';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './auth/auth.guard';
import {RoleEnum} from './shared/roles';
import {ProfileComponent} from './profile/profile.component';
import {EventComponent} from './event/event.component';
import {EventDetailsComponent} from './event/event-details/event-details.component';
import {TicketsComponent} from './event/tickets/tickets.component';
import {CreateEventComponent} from './event/create-event/create-event.component';
import {OrgEventsComponent} from './org-events/org-events.component';
import {AdminUsersComponent} from './admin-users/admin-users.component';
import {PublicProfileComponent} from './public-profile/public-profile.component';
import {FeedsComponent} from './feeds/feeds.component';
import {AdminEventsComponent} from './admin-events/admin-events.component';
import {TestCommand} from '@angular/cli/commands/test-impl';
import {TestComponent} from './test/test.component';
import {FollowingComponent} from './following/following.component';
import {RoleRequestComponent} from './role-request/role-request.component';
import {AdminPanelComponent} from './admin-panel/admin-panel.component';
import {OrgPanelComponent} from './org-panel/org-panel.component';
import {EventTicketsComponent} from './event-tickets/event-tickets.component';
import {PassForgetComponent} from './pass-forget/pass-forget.component';
import {PassResetComponent} from './pass-reset/pass-reset.component';

const user = RoleEnum.USER;
const admin = RoleEnum.ADMIN;
const org = RoleEnum.ORGANIZER;
// const admin =
const routes: Routes = [
    {path: '', component: AppComponent , canActivate: [AuthGuard] } ,
    {path: 'home', component: AppComponent, canActivate: [AuthGuard]},
    {path: 'users', component: UserComponent, canActivate: [AuthGuard], data: { authority : [admin] }},
    {path: 'user/:id', component: UserDetailComponent, canActivate: [AuthGuard] , data: { authority : [admin] }},
    {path: 'edit/:id', component: UserEditComponent, canActivate: [AuthGuard] ,data: { authority : [admin] }},
    {path: 'register', component: RegisterComponent},
    {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
    {path: 'profile/:id', component: PublicProfileComponent, canActivate: [AuthGuard] },
    {path: 'search/:name', component: PublicProfileComponent, canActivate: [AuthGuard] },
    {path: 'login', component: LoginComponent},
    {path: 'events', component: EventComponent, canActivate: [AuthGuard] },
    {path: 'events/:id', component: EventDetailsComponent, canActivate: [AuthGuard] },
    {path: 'tickets', component: TicketsComponent, canActivate: [AuthGuard] },
    {path: 'event/create', component: CreateEventComponent, canActivate: [AuthGuard], data: { authority : [org] } },
    {path: 'myEvents', component: OrgEventsComponent, canActivate: [AuthGuard], data: { authority : [org] }  },
    {path: 'admin', component: AdminPanelComponent, canActivate: [AuthGuard], data: { authority : [admin] }  },
    {path: 'admin/users', component: AdminUsersComponent, canActivate: [AuthGuard], data: { authority : [admin] }  },
    {path: 'admin/events', component: AdminEventsComponent, canActivate: [AuthGuard], data: { authority : [admin] }  },
    {path: 'test', component: TestComponent},
    {path: 'organizer', component: OrgPanelComponent, canActivate: [AuthGuard], data: { authority : [org] } },
    {path: 'following', component: FollowingComponent, canActivate: [AuthGuard] },
    {path: 'roleRequests', component: RoleRequestComponent, canActivate: [AuthGuard], data: { authority : [admin] }  },
    {path: 'forget', component: PassForgetComponent},
    {path: 'event/tickets/:id', component: EventTicketsComponent, canActivate: [AuthGuard], data: { authority : [org] }  },
    {path: 'reset/:id', component: PassResetComponent},
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

