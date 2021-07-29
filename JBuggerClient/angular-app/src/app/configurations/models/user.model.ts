import {RestRole} from './role.model'
export interface IUser {
	firstname: string;
	lastname: string;
	email: string;
	password: string;
}

export interface RestUser {
	activated: number;
	email: string;
	firstname: string;
	lastname: string;
	mobile: string;
	password: string;
	userId: number;
	username: string;
	isEditable: boolean;
	roles: RestRole[];
}

export interface ILogIn {
	username: string;
	password: string;
}