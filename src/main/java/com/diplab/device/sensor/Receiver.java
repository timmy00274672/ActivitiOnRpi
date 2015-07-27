package com.diplab.device.sensor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Receiver {

	protected static Set<Receiver> receivers = new HashSet<>();

	public abstract Map<String, Object> getMetadata();

	public static boolean add(Receiver e) {
		return receivers.add(e);
	}

	public static boolean addAll(Collection<? extends Receiver> c) {
		return receivers.addAll(c);
	}

	public static boolean contains(Object o) {
		return receivers.contains(o);
	}

	public static boolean containsAll(Collection<?> c) {
		return receivers.containsAll(c);
	}

	public static Stream<Receiver> getReceiversStream() {
		return receivers.stream();
	}

	public static Stream<Receiver> getReceiversStream(
			Map<String, Object> metadata) {
		return receivers.stream().filter(
				receiver -> {
					return metadata
							.entrySet()
							.stream()
							.allMatch(
									m -> {
										return receiver
												.getMetadata()
												.getOrDefault(m.getKey(),
														Void.TYPE)
												.equals(m.getValue());
									});
				});

	}

	public static Set<Receiver> getReceivers() {
		return getReceiversStream().collect(Collectors.toSet());
	}

	public static Set<Receiver> getReceivers(Map<String, Object> metadata) {
		return getReceiversStream(metadata).collect(Collectors.toSet());
	}

	public abstract SensorData getData(Map<String, Object> parameter);

	@Override
	public String toString() {
		return String.format("Receiver[%s]", getMetadata());
	}

}
