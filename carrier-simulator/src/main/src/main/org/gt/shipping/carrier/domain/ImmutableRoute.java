package org.gt.shipping.carrier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link Route}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ImmutableRoute.Builder()}.
 */
@Generated(from = "Route", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.Generated("org.immutables.processor.ProxyProcessor")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ImmutableRoute implements Route {
  private final String sourceAirport;
  private final String destinationAirport;

  private ImmutableRoute(String sourceAirport, String destinationAirport) {
    this.sourceAirport = sourceAirport;
    this.destinationAirport = destinationAirport;
  }

  /**
   * @return The value of the {@code sourceAirport} attribute
   */
  @JsonProperty("sourceAirport")
  @Override
  public String sourceAirport() {
    return sourceAirport;
  }

  /**
   * @return The value of the {@code destinationAirport} attribute
   */
  @JsonProperty("destinationAirport")
  @Override
  public String destinationAirport() {
    return destinationAirport;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Route#sourceAirport() sourceAirport} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for sourceAirport
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableRoute withSourceAirport(String value) {
    String newValue = Objects.requireNonNull(value, "sourceAirport");
    if (this.sourceAirport.equals(newValue)) return this;
    return new ImmutableRoute(newValue, this.destinationAirport);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Route#destinationAirport() destinationAirport} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for destinationAirport
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableRoute withDestinationAirport(String value) {
    String newValue = Objects.requireNonNull(value, "destinationAirport");
    if (this.destinationAirport.equals(newValue)) return this;
    return new ImmutableRoute(this.sourceAirport, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableRoute} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableRoute
        && equalTo((ImmutableRoute) another);
  }

  private boolean equalTo(ImmutableRoute another) {
    return sourceAirport.equals(another.sourceAirport)
        && destinationAirport.equals(another.destinationAirport);
  }

  /**
   * Computes a hash code from attributes: {@code sourceAirport}, {@code destinationAirport}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + sourceAirport.hashCode();
    h += (h << 5) + destinationAirport.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code Route} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Route{"
        + "sourceAirport=" + sourceAirport
        + ", destinationAirport=" + destinationAirport
        + "}";
  }

  /**
   * Creates an immutable copy of a {@link Route} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Route instance
   */
  public static ImmutableRoute copyOf(Route instance) {
    if (instance instanceof ImmutableRoute) {
      return (ImmutableRoute) instance;
    }
    return new ImmutableRoute.Builder()
        .from(instance)
        .build();
  }

  /**
   * Builds instances of type {@link ImmutableRoute ImmutableRoute}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Route", generator = "Immutables")
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static final class Builder {
    private static final long INIT_BIT_SOURCE_AIRPORT = 0x1L;
    private static final long INIT_BIT_DESTINATION_AIRPORT = 0x2L;
    private long initBits = 0x3L;

    private String sourceAirport;
    private String destinationAirport;

    /**
     * Creates a builder for {@link ImmutableRoute ImmutableRoute} instances.
     * <pre>
     * new ImmutableRoute.Builder()
     *    .sourceAirport(String) // required {@link Route#sourceAirport() sourceAirport}
     *    .destinationAirport(String) // required {@link Route#destinationAirport() destinationAirport}
     *    .build();
     * </pre>
     */
    public Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code Route} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(Route instance) {
      Objects.requireNonNull(instance, "instance");
      sourceAirport(instance.sourceAirport());
      destinationAirport(instance.destinationAirport());
      return this;
    }

    /**
     * Initializes the value for the {@link Route#sourceAirport() sourceAirport} attribute.
     * @param sourceAirport The value for sourceAirport 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("sourceAirport")
    public final Builder sourceAirport(String sourceAirport) {
      this.sourceAirport = Objects.requireNonNull(sourceAirport, "sourceAirport");
      initBits &= ~INIT_BIT_SOURCE_AIRPORT;
      return this;
    }

    /**
     * Initializes the value for the {@link Route#destinationAirport() destinationAirport} attribute.
     * @param destinationAirport The value for destinationAirport 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("destinationAirport")
    public final Builder destinationAirport(String destinationAirport) {
      this.destinationAirport = Objects.requireNonNull(destinationAirport, "destinationAirport");
      initBits &= ~INIT_BIT_DESTINATION_AIRPORT;
      return this;
    }

    /**
     * Builds a new {@link ImmutableRoute ImmutableRoute}.
     * @return An immutable instance of Route
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableRoute build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableRoute(sourceAirport, destinationAirport);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_SOURCE_AIRPORT) != 0) attributes.add("sourceAirport");
      if ((initBits & INIT_BIT_DESTINATION_AIRPORT) != 0) attributes.add("destinationAirport");
      return "Cannot build Route, some of required attributes are not set " + attributes;
    }
  }
}
