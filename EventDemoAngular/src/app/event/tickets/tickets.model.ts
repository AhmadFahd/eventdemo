import {Events} from '../event.model';
import {User} from '../../user/user.model';

export class Tickets {
    constructor(
        public id: string,
        public event: Events,
        public user: User,
        public chicked: boolean
    ) { }
}
