import {User} from '../user/user.model';

export class Events {
    constructor(
        public id: number,
        public name: string,
        public capacity: number,
        public overview: string,
        public date: string,
        public time: string,
        public image: string,
        public minage: number,
        public gender: string,
        public category: string,
        public city: string,
        public location: string,
        public organizer: User

    ) { }
}
