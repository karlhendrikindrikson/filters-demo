export enum CriteriaType {
  AMOUNT = 'AMOUNT',
  TITLE = 'TITLE',
  DATE = 'DATE',
}

export enum CriteriaCondition {
  EQUALS = 'EQUALS',
  NOT_EQUALS = 'NOT_EQUALS',
  GREATER_THAN = 'GREATER_THAN',
  GREATER_THAN_OR_EQUALS = 'GREATER_THAN_OR_EQUALS',
  LESS_THAN = 'LESS_THAN',
  LESS_THAN_EQUALS = 'LESS_THAN_EQUALS',
  STARTS_WITH = 'STARTS_WITH',
  ENDS_WITH = 'ENDS_WITH',
  CONTAINS = 'CONTAINS',
  FROM = 'FROM',
  TO = 'TO',
}

export const CriteriaConditionsByType: { [type: string]: CriteriaCondition[] } =
  {
    [CriteriaType.AMOUNT]: [
      CriteriaCondition.EQUALS,
      CriteriaCondition.NOT_EQUALS,
      CriteriaCondition.GREATER_THAN,
      CriteriaCondition.GREATER_THAN_OR_EQUALS,
      CriteriaCondition.LESS_THAN,
      CriteriaCondition.LESS_THAN_EQUALS,
    ],
    [CriteriaType.TITLE]: [
      CriteriaCondition.EQUALS,
      CriteriaCondition.NOT_EQUALS,
      CriteriaCondition.CONTAINS,
      CriteriaCondition.STARTS_WITH,
      CriteriaCondition.ENDS_WITH,
    ],
    [CriteriaType.DATE]: [
      CriteriaCondition.EQUALS,
      CriteriaCondition.NOT_EQUALS,
      CriteriaCondition.FROM,
      CriteriaCondition.TO,
    ],
  };

export type FilterCriteria = {
  criteriaType: CriteriaType;
  criteriaCondition: CriteriaCondition;
  value: string;
};

export type Filter = {
  id?: number;
  name: string;
  criteria: FilterCriteria[];
};
