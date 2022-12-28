export const buildingData = {
  type: "FeatureCollection",
  features: [
    {
      type: "Feature",
      geometry: {
        type: "Polygon", // patio top right
        coordinates: [
          [
            [50, 50],
            [40, 50],
            [40, 60],
            [35, 60],
            [35, 50],
            [10, 50],
            [10, 45],
            [45, 45],
            [45, 35],
            [50, 35],
            [50, 45],
            [65, 45],
            [65, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      geometry: {
        type: "Polygon", // patio top left
        coordinates: [
          [
            [-50, 50],
            [-40, 50],
            [-40, 60],
            [-35, 60],
            [-35, 50],
            [-10, 50],
            [-10, 45],
            [-45, 45],
            [-45, 35],
            [-50, 35],
            [-50, 45],
            [-65, 45],
            [-65, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      geometry: {
        type: "Polygon", // patio bot right
        coordinates: [
          [
            [60, -50],
            [60, -45],
            [50, -45],
            [50, -35],
            [45, -35],
            [45, -45],
            [35, -45],
            [35, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      geometry: {
        type: "Polygon", // patio staff visitor
        coordinates: [
          [
            [-15, -45],
            [-15, -50],
            [-3, -50],
            [-3, -60],
            [3, -60],
            [3, -50],
            [15, -50],
            [15, -45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      geometry: {
        type: "Polygon", // entrance staff visitor
        coordinates: [
          [
            [3, -80],
            [-3, -80],
            [-3, -90],
            [-15, -90],
            [-15, -95],
            [15, -95],
            [15, -90],
            [3, -90],
          ],
        ],
      },
    },
    {
      type: "Feature",
      geometry: {
        type: "Polygon", // OUTSIDE patio cell1 job
        coordinates: [
          [
            // outside points
            [10, -130],
            [50, -130],
            [50, -95],
            [95, -95],
            [95, -50],
            [135, -50],
            [135, -3],
            [145, -3],
            [145, 50],
            [135, 50],
            [135, 95],
            [40, 95],
            [40, 105],
            [-40, 105],
            [-40, 95],
            [-135, 95],
            [-135, 50],
            [-145, 50],
            [-145, -3],
            [-135, -3],
            [-135, -50],
            [-95, -50],
            [-95, -95],
            [-50, -95],
            [-50, -130],
            [-10, -130],
            // inside bot left
            [-10, -125],
            [-45, -125],
            [-45, -95],
            [-35, -95],
            [-35, -90],
            [-90, -90],
            [-90, -50],
            [-35, -50],
            [-35, -45],
            [-45, -45],
            [-45, -35],
            [-50, -35],
            [-50, -45],
            [-130, -45],
            [-130, -3],
            [-50, -3],
            [-50, -15],
            [-45, -15],
            // inside top left
            [-45, 15],
            [-50, 15],
            [-50, 3],
            [-140, 3],
            [-140, 45],
            [-85, 45],
            [-85, 50],
            [-130, 50],
            [-130, 90],
            [-40, 90],
            [-40, 80],
            [-35, 80],
            [-35, 100],
            // inside top right
            [35, 100],
            [35, 80],
            [40, 80],
            [40, 90],
            [130, 90],
            [130, 50],
            [85, 50],
            [85, 45],
            [140, 45],
            [140, 3],
            [50, 3],
            [50, 15],
            [45, 15],
            // inside bot right
            [45, -15],
            [50, -15],
            [50, -3],
            [130, -3],
            [130, -45],
            [80, -45],
            [80, -50],
            [90, -50],
            [90, -90],
            [35, -90],
            [35, -95],
            [45, -95],
            [45, -125],
            [10, -125],
          ],
        ],
      },
    },
  ],
};

export const areaData = {
  type: "FeatureCollection",
  features: [
    {
      type: "Feature",
      properties: {
        name: "Patio",
        id: 5,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-45, -45],
            [45, -45],
            [45, 45],
            [-45, 45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Visitor Wing",
        id: 2,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-3, -50],
            [-3, -90],
            [-90, -90],
            [-90, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Staff Wing",
        id: 3,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [3, -50],
            [3, -90],
            [90, -90],
            [90, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Entrance",
        id: 1,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-45, -95],
            [-45, -125],
            [45, -125],
            [45, -95],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Infirmary",
        id: 6,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [50, -45],
            [50, -3],
            [130, -3],
            [130, -45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Job Wing",
        id: 4,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-50, -45],
            [-50, -3],
            [-130, -3],
            [-130, -45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Cell Block 2",
        id: 8,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [50, 45],
            [50, 3],
            [140, 3],
            [140, 45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Cell Block 1",
        id: 7,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-50, 45],
            [-50, 3],
            [-140, 3],
            [-140, 45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Showers",
        id: 9,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-40, 90],
            [-40, 50],
            [-130, 50],
            [-130, 90],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Canteen",
        id: 11,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [40, 90],
            [40, 50],
            [130, 50],
            [130, 90],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Solitary",
        id: 10,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-35, 100],
            [-35, 50],
            [35, 50],
            [35, 100],
          ],
        ],
      },
    },
  ],
};

export const sensorData = {
  type: "FeatureCollection",
  features: [
    // sensors now
    {
      type: "Feature",
      properties: {
        name: "Sensor1",
        id: 1,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-15, -90],
            [-35, -95],
            [-15, -95],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor2",
        id: 2,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-15, -90],
            [-35, -90],
            [-35, -95],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor3",
        id: 3,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [35, -90],
            [35, -95],
            [15, -95],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor4",
        id: 4,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [15, -90],
            [35, -90],
            [15, -95],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor5",
        id: 5,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [3, -60],
            [3, -80],
            [-3, -80],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor6",
        id: 6,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-3, -80],
            [3, -60],
            [-3, -60],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor7",
        id: 7,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-15, -45],
            [-35, -50],
            [-15, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor8",
        id: 8,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-35, -45],
            [-15, -45],
            [-35, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor9",
        id: 9,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [35, -45],
            [15, -50],
            [35, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor10",
        id: 10,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [15, -45],
            [35, -45],
            [15, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor11",
        id: 11,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [80, -45],
            [80, -50],
            [60, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor12",
        id: 12,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [80, -45],
            [60, -45],
            [60, -50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor13",
        id: 13,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-50, -15],
            [-50, -35],
            [-45, -35],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor14",
        id: 14,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-45, -35],
            [-50, -15],
            [-45, -15],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor15",
        id: 15,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [50, -15],
            [50, -35],
            [45, -35],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor16",
        id: 16,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [45, -35],
            [50, -15],
            [45, -15],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor17",
        id: 17,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-45, 15],
            [-50, 35],
            [-45, 35],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor18",
        id: 18,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-50, 35],
            [-50, 15],
            [-45, 15],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor19",
        id: 19,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [45, 35],
            [50, 15],
            [45, 15],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor20",
        id: 20,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [50, 15],
            [50, 35],
            [45, 35],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor21",
        id: 21,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [10, 45],
            [10, 50],
            [-10, 45],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor22",
        id: 22,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-10, 45],
            [-10, 50],
            [10, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor23",
        id: 23,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-85, 45],
            [-65, 45],
            [-65, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor24",
        id: 24,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-85, 45],
            [-85, 50],
            [-65, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor25",
        id: 25,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [65, 45],
            [85, 45],
            [85, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor26",
        id: 26,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [65, 45],
            [65, 50],
            [85, 50],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor27",
        id: 27,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-40, 80],
            [-40, 60],
            [-35, 60],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor28",
        id: 28,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [-40, 80],
            [-35, 80],
            [-35, 60],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor29",
        id: 29,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [40, 60],
            [35, 60],
            [35, 80],
          ],
        ],
      },
    },
    {
      type: "Feature",
      properties: {
        name: "Sensor30",
        id: 30,
      },
      geometry: {
        type: "Polygon",
        coordinates: [
          [
            [40, 60],
            [40, 80],
            [35, 80],
          ],
        ],
      },
    },
  ],
};
