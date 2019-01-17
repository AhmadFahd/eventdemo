export class User {
    constructor(
        public id: number,
        public username: string,
        public firstname: string,
        public midname: string,
        public lastname: string,
        public email: string,
        public icon: string,
        public phone: string,
        public password: string,
        public gender: string,
        public dob: string,
        public roles: Role[]
    ) {
    }
}

export class Role {
    id: number;
    roleName: string;
}
