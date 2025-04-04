/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.kafka.eos.avro;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class TransactionEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TransactionEvent\",\"namespace\":\"com.kafka.eos.avro\",\"fields\":[{\"name\":\"eventId\",\"type\":\"string\"},{\"name\":\"timestamp\",\"type\":\"string\"},{\"name\":\"userId\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"double\"},{\"name\":\"status\",\"type\":\"string\"}]}");
    private static final long serialVersionUID = -4031922257805084464L;
    private static final SpecificData MODEL$ = new SpecificData();
    private static final BinaryMessageEncoder<TransactionEvent> ENCODER =
            new BinaryMessageEncoder<>(MODEL$, SCHEMA$);
    private static final BinaryMessageDecoder<TransactionEvent> DECODER =
            new BinaryMessageDecoder<>(MODEL$, SCHEMA$);
    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<TransactionEvent>
            WRITER$ = (org.apache.avro.io.DatumWriter<TransactionEvent>) MODEL$.createDatumWriter(SCHEMA$);
    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<TransactionEvent>
            READER$ = (org.apache.avro.io.DatumReader<TransactionEvent>) MODEL$.createDatumReader(SCHEMA$);
    private java.lang.CharSequence eventId;
    private java.lang.CharSequence timestamp;
    private java.lang.CharSequence userId;
    private double amount;
    private java.lang.CharSequence status;
    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public TransactionEvent() {
    }
    /**
     * All-args constructor.
     * @param eventId The new value for eventId
     * @param timestamp The new value for timestamp
     * @param userId The new value for userId
     * @param amount The new value for amount
     * @param status The new value for status
     */
    public TransactionEvent(java.lang.CharSequence eventId, java.lang.CharSequence timestamp, java.lang.CharSequence userId, java.lang.Double amount, java.lang.CharSequence status) {
        this.eventId = eventId;
        this.timestamp = timestamp;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    public static org.apache.avro.Schema getClassSchema() {
        return SCHEMA$;
    }

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<TransactionEvent> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<TransactionEvent> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<TransactionEvent> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Deserializes a TransactionEvent from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a TransactionEvent instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static TransactionEvent fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    /**
     * Creates a new TransactionEvent RecordBuilder.
     * @return A new TransactionEvent RecordBuilder
     */
    public static com.kafka.eos.avro.TransactionEvent.Builder newBuilder() {
        return new com.kafka.eos.avro.TransactionEvent.Builder();
    }

    /**
     * Creates a new TransactionEvent RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new TransactionEvent RecordBuilder
     */
    public static com.kafka.eos.avro.TransactionEvent.Builder newBuilder(com.kafka.eos.avro.TransactionEvent.Builder other) {
        if (other == null) {
            return new com.kafka.eos.avro.TransactionEvent.Builder();
        } else {
            return new com.kafka.eos.avro.TransactionEvent.Builder(other);
        }
    }

    /**
     * Creates a new TransactionEvent RecordBuilder by copying an existing TransactionEvent instance.
     * @param other The existing instance to copy.
     * @return A new TransactionEvent RecordBuilder
     */
    public static com.kafka.eos.avro.TransactionEvent.Builder newBuilder(com.kafka.eos.avro.TransactionEvent other) {
        if (other == null) {
            return new com.kafka.eos.avro.TransactionEvent.Builder();
        } else {
            return new com.kafka.eos.avro.TransactionEvent.Builder(other);
        }
    }

    /**
     * Serializes this TransactionEvent to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    @Override
    public org.apache.avro.specific.SpecificData getSpecificData() {
        return MODEL$;
    }

    @Override
    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    @Override
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0:
                return eventId;
            case 1:
                return timestamp;
            case 2:
                return userId;
            case 3:
                return amount;
            case 4:
                return status;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    // Used by DatumReader.  Applications should not call.
    @Override
    @SuppressWarnings(value = "unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                eventId = (java.lang.CharSequence) value$;
                break;
            case 1:
                timestamp = (java.lang.CharSequence) value$;
                break;
            case 2:
                userId = (java.lang.CharSequence) value$;
                break;
            case 3:
                amount = (java.lang.Double) value$;
                break;
            case 4:
                status = (java.lang.CharSequence) value$;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    /**
     * Gets the value of the 'eventId' field.
     * @return The value of the 'eventId' field.
     */
    public java.lang.CharSequence getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the 'eventId' field.
     * @param value the value to set.
     */
    public void setEventId(java.lang.CharSequence value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the 'timestamp' field.
     * @return The value of the 'timestamp' field.
     */
    public java.lang.CharSequence getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the 'timestamp' field.
     * @param value the value to set.
     */
    public void setTimestamp(java.lang.CharSequence value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the 'userId' field.
     * @return The value of the 'userId' field.
     */
    public java.lang.CharSequence getUserId() {
        return userId;
    }

    /**
     * Sets the value of the 'userId' field.
     * @param value the value to set.
     */
    public void setUserId(java.lang.CharSequence value) {
        this.userId = value;
    }

    /**
     * Gets the value of the 'amount' field.
     * @return The value of the 'amount' field.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the 'amount' field.
     * @param value the value to set.
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the 'status' field.
     * @return The value of the 'status' field.
     */
    public java.lang.CharSequence getStatus() {
        return status;
    }

    /**
     * Sets the value of the 'status' field.
     * @param value the value to set.
     */
    public void setStatus(java.lang.CharSequence value) {
        this.status = value;
    }

    @Override
    public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @Override
    public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    @Override
    protected boolean hasCustomCoders() {
        return true;
    }

    @Override
    public void customEncode(org.apache.avro.io.Encoder out)
            throws java.io.IOException {
        out.writeString(this.eventId);

        out.writeString(this.timestamp);

        out.writeString(this.userId);

        out.writeDouble(this.amount);

        out.writeString(this.status);

    }

    @Override
    public void customDecode(org.apache.avro.io.ResolvingDecoder in)
            throws java.io.IOException {
        org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
        if (fieldOrder == null) {
            this.eventId = in.readString(this.eventId instanceof Utf8 ? (Utf8) this.eventId : null);

            this.timestamp = in.readString(this.timestamp instanceof Utf8 ? (Utf8) this.timestamp : null);

            this.userId = in.readString(this.userId instanceof Utf8 ? (Utf8) this.userId : null);

            this.amount = in.readDouble();

            this.status = in.readString(this.status instanceof Utf8 ? (Utf8) this.status : null);

        } else {
            for (int i = 0; i < 5; i++) {
                switch (fieldOrder[i].pos()) {
                    case 0:
                        this.eventId = in.readString(this.eventId instanceof Utf8 ? (Utf8) this.eventId : null);
                        break;

                    case 1:
                        this.timestamp = in.readString(this.timestamp instanceof Utf8 ? (Utf8) this.timestamp : null);
                        break;

                    case 2:
                        this.userId = in.readString(this.userId instanceof Utf8 ? (Utf8) this.userId : null);
                        break;

                    case 3:
                        this.amount = in.readDouble();
                        break;

                    case 4:
                        this.status = in.readString(this.status instanceof Utf8 ? (Utf8) this.status : null);
                        break;

                    default:
                        throw new java.io.IOException("Corrupt ResolvingDecoder.");
                }
            }
        }
    }

    /**
     * RecordBuilder for TransactionEvent instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TransactionEvent>
            implements org.apache.avro.data.RecordBuilder<TransactionEvent> {

        private java.lang.CharSequence eventId;
        private java.lang.CharSequence timestamp;
        private java.lang.CharSequence userId;
        private double amount;
        private java.lang.CharSequence status;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$, MODEL$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(com.kafka.eos.avro.TransactionEvent.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.eventId)) {
                this.eventId = data().deepCopy(fields()[0].schema(), other.eventId);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.timestamp)) {
                this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.userId)) {
                this.userId = data().deepCopy(fields()[2].schema(), other.userId);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
            if (isValidValue(fields()[3], other.amount)) {
                this.amount = data().deepCopy(fields()[3].schema(), other.amount);
                fieldSetFlags()[3] = other.fieldSetFlags()[3];
            }
            if (isValidValue(fields()[4], other.status)) {
                this.status = data().deepCopy(fields()[4].schema(), other.status);
                fieldSetFlags()[4] = other.fieldSetFlags()[4];
            }
        }

        /**
         * Creates a Builder by copying an existing TransactionEvent instance
         * @param other The existing instance to copy.
         */
        private Builder(com.kafka.eos.avro.TransactionEvent other) {
            super(SCHEMA$, MODEL$);
            if (isValidValue(fields()[0], other.eventId)) {
                this.eventId = data().deepCopy(fields()[0].schema(), other.eventId);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.timestamp)) {
                this.timestamp = data().deepCopy(fields()[1].schema(), other.timestamp);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.userId)) {
                this.userId = data().deepCopy(fields()[2].schema(), other.userId);
                fieldSetFlags()[2] = true;
            }
            if (isValidValue(fields()[3], other.amount)) {
                this.amount = data().deepCopy(fields()[3].schema(), other.amount);
                fieldSetFlags()[3] = true;
            }
            if (isValidValue(fields()[4], other.status)) {
                this.status = data().deepCopy(fields()[4].schema(), other.status);
                fieldSetFlags()[4] = true;
            }
        }

        /**
         * Gets the value of the 'eventId' field.
         * @return The value.
         */
        public java.lang.CharSequence getEventId() {
            return eventId;
        }


        /**
         * Sets the value of the 'eventId' field.
         * @param value The value of 'eventId'.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder setEventId(java.lang.CharSequence value) {
            validate(fields()[0], value);
            this.eventId = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'eventId' field has been set.
         * @return True if the 'eventId' field has been set, false otherwise.
         */
        public boolean hasEventId() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'eventId' field.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder clearEventId() {
            eventId = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'timestamp' field.
         * @return The value.
         */
        public java.lang.CharSequence getTimestamp() {
            return timestamp;
        }


        /**
         * Sets the value of the 'timestamp' field.
         * @param value The value of 'timestamp'.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder setTimestamp(java.lang.CharSequence value) {
            validate(fields()[1], value);
            this.timestamp = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'timestamp' field has been set.
         * @return True if the 'timestamp' field has been set, false otherwise.
         */
        public boolean hasTimestamp() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'timestamp' field.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder clearTimestamp() {
            timestamp = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'userId' field.
         * @return The value.
         */
        public java.lang.CharSequence getUserId() {
            return userId;
        }


        /**
         * Sets the value of the 'userId' field.
         * @param value The value of 'userId'.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder setUserId(java.lang.CharSequence value) {
            validate(fields()[2], value);
            this.userId = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'userId' field has been set.
         * @return True if the 'userId' field has been set, false otherwise.
         */
        public boolean hasUserId() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'userId' field.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder clearUserId() {
            userId = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        /**
         * Gets the value of the 'amount' field.
         * @return The value.
         */
        public double getAmount() {
            return amount;
        }


        /**
         * Sets the value of the 'amount' field.
         * @param value The value of 'amount'.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder setAmount(double value) {
            validate(fields()[3], value);
            this.amount = value;
            fieldSetFlags()[3] = true;
            return this;
        }

        /**
         * Checks whether the 'amount' field has been set.
         * @return True if the 'amount' field has been set, false otherwise.
         */
        public boolean hasAmount() {
            return fieldSetFlags()[3];
        }


        /**
         * Clears the value of the 'amount' field.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder clearAmount() {
            fieldSetFlags()[3] = false;
            return this;
        }

        /**
         * Gets the value of the 'status' field.
         * @return The value.
         */
        public java.lang.CharSequence getStatus() {
            return status;
        }


        /**
         * Sets the value of the 'status' field.
         * @param value The value of 'status'.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder setStatus(java.lang.CharSequence value) {
            validate(fields()[4], value);
            this.status = value;
            fieldSetFlags()[4] = true;
            return this;
        }

        /**
         * Checks whether the 'status' field has been set.
         * @return True if the 'status' field has been set, false otherwise.
         */
        public boolean hasStatus() {
            return fieldSetFlags()[4];
        }


        /**
         * Clears the value of the 'status' field.
         * @return This builder.
         */
        public com.kafka.eos.avro.TransactionEvent.Builder clearStatus() {
            status = null;
            fieldSetFlags()[4] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public TransactionEvent build() {
            try {
                TransactionEvent record = new TransactionEvent();
                record.eventId = fieldSetFlags()[0] ? this.eventId : (java.lang.CharSequence) defaultValue(fields()[0]);
                record.timestamp = fieldSetFlags()[1] ? this.timestamp : (java.lang.CharSequence) defaultValue(fields()[1]);
                record.userId = fieldSetFlags()[2] ? this.userId : (java.lang.CharSequence) defaultValue(fields()[2]);
                record.amount = fieldSetFlags()[3] ? this.amount : (java.lang.Double) defaultValue(fields()[3]);
                record.status = fieldSetFlags()[4] ? this.status : (java.lang.CharSequence) defaultValue(fields()[4]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }
}










