
export interface RestBug {
    bugId: number;
	title: string;
	description: string;
	version: string;
	fixedInVersion: string;
	targetDate: string;
	severity: string,
	createdBy: number;
	createdByUsername: string;
	status: string;
	assignedTo: number; 
	attachment: string;
	isEditable: boolean;
}