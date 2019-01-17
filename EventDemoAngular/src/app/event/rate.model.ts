import {User} from '../user/user.model';
import {Events} from './event.model';

export class Rate {
    id: number;
    rate: number;
    user: User;
    event: Events;
}
