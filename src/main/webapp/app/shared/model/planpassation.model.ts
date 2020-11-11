import { Moment } from 'moment';

export interface IPlanpassation {
  id?: number;
  dateDebut?: Moment;
  dateFin?: Moment;
  commentaire?: string;
}

export class Planpassation implements IPlanpassation {
  constructor(public id?: number, public dateDebut?: Moment, public dateFin?: Moment, public commentaire?: string) {}
}
