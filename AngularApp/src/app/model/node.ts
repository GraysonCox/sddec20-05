export enum NodeType {
	SENSOR,
	RELAY
}

export interface Node {
	id: number;
	type: string;
	name: string;
	ipAddress: string;
	batteryPercentage: number;
}
