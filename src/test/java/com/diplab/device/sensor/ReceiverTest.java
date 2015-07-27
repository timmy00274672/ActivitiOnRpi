package com.diplab.device.sensor;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.junit.Test;

public class ReceiverTest {

	public class CO2 extends SensorData {

		@Override
		public String toString() {
			return "CO2 [ppm=" + ppm + "]";
		}

		private double ppm;

		public double getPpm() {
			return ppm;
		}

		public void setPpm(double ppm) {
			this.ppm = ppm;
		}

		public CO2(double ppm) {
			this.ppm = ppm;
		}

	}

	@Test
	public void testReceiversStream() {
		Receiver.add(new Receiver() {
			NormalDistribution nomal = new NormalDistribution(500, 100);

			@Override
			public SensorData getData(Map<String, Object> parameter) {
				return new CO2(nomal.sample());
			}

			@Override
			public Map<String, Object> getMetadata() {
				Map<String, Object> map = new HashMap<>();
				map.put("type", "co2");
				return map;
			}

		});

		Map<String, Object> map = new HashMap<>();
		map.put("type", "co2");
		assertEquals(1, Receiver.getReceiversStream(map).count());

		Receiver.getReceiversStream(map).forEach(
				receiver -> System.out.println(receiver.getData(null)));

		map.put("location", "room2");
		assertEquals(0, Receiver.getReceiversStream(map).count());
	}
}
