import {User} from '../user/user.model';
import {Rate} from '../event/rate.model';
import {Comments} from '../event/comment.model';
import {Events} from '../event/event.model';

export class Feed {
    id: number;
    user: User;
    action: string;
    targetEvent?: Events;
    targetComment?: Comments;
    targetUser?: User;
    targetRate?: Rate;
    time: Date;
}
