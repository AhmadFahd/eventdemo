import {User} from '../user/user.model';

export class RequestsModel {
    constructor(
        public id: number,
        public user: User,
        public role: string
    ) { }
}
