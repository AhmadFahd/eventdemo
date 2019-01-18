import {Events} from '../event.model';
import {User} from '../../user/user.model';

export class Tickets {
    constructor(
        public id: number,
        public event: Events,
        public user: User,
        public chicked: boolean
    ) { }
}
