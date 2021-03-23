package jp.pois.oxacillin.extensions

public interface Card {
    public val bindingValues: BindingValues

    public interface BindingValues {
        public val choice1Label: StringValue?
        public val choice2Label: StringValue?
        public val choice3Label: StringValue?
        public val choice4Label: StringValue?

        public val choice1Count: StringValue?
        public val choice2Count: StringValue?
        public val choice3Count: StringValue?
        public val choice4Count: StringValue?

        public val countsAreFinal: BooleanValue?
        public val endDatetimeUtc: StringValue?
        public val lastUpdatedDatetimeUtc: StringValue?
        public val durationMinutes: StringValue?

        public interface StringValue {
            public val type: String
            public val stringValue: String
        }

        public interface BooleanValue {
            public val type: String
            public val booleanValue: Boolean
        }
    }
}