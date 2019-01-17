import {User} from '../user/user.model';
import {Events} from './event.model';

export class Comments {
    event: Events;
    comment: string;
    user: User;
    time: Date;
}
